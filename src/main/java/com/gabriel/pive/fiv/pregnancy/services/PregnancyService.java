package com.gabriel.pive.fiv.pregnancy.services;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.EmbryoProduction.services.ProductionService;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;
import com.gabriel.pive.fiv.pregnancy.exceptions.ReceiverCattleDoesNotHaveAnEmbryoException;
import com.gabriel.pive.fiv.pregnancy.repositories.PregnancyRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PregnancyService {

    @Autowired
    private PregnancyRepository pregnancyRepository;

    @Autowired
    private EmbryoRepository embryoRepository;

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private FivRepository fivRepository;

    @Scheduled(cron = "@daily")
    public void updateGestationalAge(){
        for (Pregnancy pregnancy : pregnancyRepository.findAll()){
            pregnancy.setGestationalAge(calculateGestationalAge(pregnancy.getTransferDay()));
        }
    }

    public void updatePregnancyData(EmbryoProduction production, Boolean is_pregnant){
        Fiv fiv = production.getOocyteCollection().getFiv();

        Integer productionTotalPregnancy = production.getTotalPregnancy();
        Integer fivTotalPregnancy = fiv.getFivTotalPregnancy();

        if (is_pregnant){
            productionTotalPregnancy = production.getTotalPregnancy() + 1;
            fivTotalPregnancy = fiv.getFivTotalPregnancy() + 1;
        }

        Float productionPregnancyPercentage = (float) productionTotalPregnancy / production.getTransferredEmbryosNumber() * 100;

        production.setTotalPregnancy(productionTotalPregnancy);
        production.setPregnancyPercentage(productionPregnancyPercentage);
        productionRepository.save(production);


        Float fivPregnancyPercentage = (float) fivTotalPregnancy / fiv.getFivTransferredEmbryosNumber() * 100;

        fiv.setFivTotalPregnancy(fivTotalPregnancy);
        fiv.setFivPregnancyPercentage(fivPregnancyPercentage);
        fivRepository.save(fiv);

    }

    private Integer calculateGestationalAge(LocalDate transferDay) {
        if (transferDay == null) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(transferDay, LocalDate.now());
    }

    public void confirmPregnancy(ReceiverCattle receiverCattle, boolean is_pregnant){

        if (receiverCattle.getEmbryo() == null){
            throw new ReceiverCattleDoesNotHaveAnEmbryoException();
        }

        Pregnancy pregnancy = receiverCattle.getPregnancy();
        EmbryoProduction production = receiverCattle.getEmbryo().getEmbryoEmbryoProduction();

        if (is_pregnant) {
            pregnancy.setStatus(PregnancyStatus.PREGNANT);

        }

        else {
            pregnancy.setStatus(PregnancyStatus.NOT_PREGNANT);
            Embryo embryo = receiverCattle.getEmbryo();
            embryo.setEmbryoReceiverCattle(null);
            embryoRepository.save(embryo);
        }

        updatePregnancyData(production, is_pregnant);
        pregnancyRepository.save(pregnancy);
    }
    }

