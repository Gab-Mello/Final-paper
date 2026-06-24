package com.gabriel.pive.animals.services;

import com.gabriel.pive.animals.dtos.BullDto;
import com.gabriel.pive.animals.dtos.DonorBullCombinationDto;
import com.gabriel.pive.animals.dtos.DonorCattleDto;
import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.repositories.BullRepository;
import com.gabriel.pive.animals.repositories.DonorCattleRepository;
import com.gabriel.pive.fiv.oocytecollection.entities.OocyteCollection;
import com.gabriel.pive.fiv.oocytecollection.repositories.OocyteCollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonorBullCombinationService {

    private final OocyteCollectionRepository oocyteCollectionRepository;
    private final DonorCattleRepository donorCattleRepository;
    private final BullRepository bullRepository;


    public List<DonorBullCombinationDto> getBestDonorBullCombinations() {

            List<DonorCattle> donors = donorCattleRepository.findAll();
            List<Bull> bulls = bullRepository.findAll();

            // Carry the raw average alongside the DTO so the sort uses the actual numeric
            // value, not a re-parsed formatted String (which would be locale-dependent and
            // would lose precision through the format → parse round-trip).
            record ScoredCombination(double rawAverage, DonorBullCombinationDto dto) {}
            List<ScoredCombination> scored = new ArrayList<>();

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
                        scored.add(new ScoredCombination(
                                averageEmbryosPercentage,
                                DonorBullCombinationDto.toDonorBullCombinationDto(
                                        DonorCattleDto.toDonorCattleDto(donor),
                                        BullDto.toBullDto(bull),
                                        averageEmbryosPercentage)));
                    }
                }
            }

            scored.sort(Comparator.comparingDouble(ScoredCombination::rawAverage).reversed());

            return scored.stream().map(ScoredCombination::dto).toList();
        }}
