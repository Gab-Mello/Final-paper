package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorBullCombinationDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.fiv.oocyteCollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocyteCollection.repositories.OocyteCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DonorBullCombinationService {

    @Autowired
    private OocyteCollectionRepository oocyteCollectionRepository;

    @Autowired
    private DonorCattleRepository donorCattleRepository;

    @Autowired
    private BullRepository bullRepository;


    public List<DonorBullCombinationDto> getBestDonorBullCombinations() {

            List<DonorCattle> donors = donorCattleRepository.findAll();
            List<Bull> bulls = bullRepository.findAll();

            List<DonorBullCombinationDto> combinations = new ArrayList<>();

            for (DonorCattle donor : donors) {
                for (Bull bull : bulls) {

                    List<OocyteCollection> collections = oocyteCollectionRepository.findByDonorAndBull(donor, bull);

                    if (collections.isEmpty()) continue;

                    double totalEmbryosPercentage = 0;
                    int productionCount = 0;

                    for (OocyteCollection collection : collections) {
                        if (collection.getEmbryoProduction() != null) {
                            totalEmbryosPercentage += collection.getEmbryoProduction().getEmbryosPercentage();
                            productionCount++;
                        }
                    }

                    if (productionCount > 0) {
                        double averageEmbryosPercentage = totalEmbryosPercentage / productionCount;
                        combinations.add(DonorBullCombinationDto.toDonorBullCombinationDto(DonorCattleDto.toDonorCattleDto(donor),
                                BullDto.toBullDto(bull), averageEmbryosPercentage));
                    }
                }
            }

        combinations.sort(Comparator.comparing((DonorBullCombinationDto dto) ->
                        Double.parseDouble(dto.averageCombinationEmbryosPercentage().replace("%", "").replace(",", ".")))
                .reversed());

        return combinations;
        }}
