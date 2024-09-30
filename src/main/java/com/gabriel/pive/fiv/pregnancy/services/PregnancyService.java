package com.gabriel.pive.fiv.pregnancy.services;

import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;
import com.gabriel.pive.fiv.pregnancy.repositories.PregnancyRepository;
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

    @Scheduled(cron = "@daily")
    public void updateGestationalAge(){
        for (Pregnancy pregnancy : pregnancyRepository.findAll()){
            pregnancy.setGestationalAge(calculateGestationalAge(pregnancy.getTransferDay()));
        }
    }

    private Integer calculateGestationalAge(LocalDate transferDay) {
        if (transferDay == null) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(transferDay, LocalDate.now());
    }

    public void confirmPregnancy(ReceiverCattle receiverCattle, boolean is_pregnant){
        Pregnancy pregnancy = receiverCattle.getPregnancy();

        if (is_pregnant) {
            pregnancy.setStatus(PregnancyStatus.PREGNANT);
        } else {
            pregnancy.setStatus(PregnancyStatus.NOT_PREGNANT);
            Embryo embryo = receiverCattle.getEmbryo();
            embryo.setEmbryoReceiverCattle(null);
            embryoRepository.save(embryo);
        }

        pregnancyRepository.save(pregnancy);
    }
    }

