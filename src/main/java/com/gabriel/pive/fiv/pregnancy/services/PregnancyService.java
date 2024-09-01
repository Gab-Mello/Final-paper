package com.gabriel.pive.fiv.pregnancy.services;

import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
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

    @Scheduled(cron = "0 0 0 * * ?")
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
}
