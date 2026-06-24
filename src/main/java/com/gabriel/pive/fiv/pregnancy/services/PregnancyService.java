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
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PregnancyService {

    private final PregnancyRepository pregnancyRepository;
    private final EmbryoRepository embryoRepository;
    private final ProductionRepository productionRepository;
    private final FivRepository fivRepository;

    @Scheduled(cron = "@daily")
    @Transactional
    public void updateGestationalAge(){
        for (Pregnancy pregnancy : pregnancyRepository.findAll()){
            pregnancy.setGestationalAge(calculateGestationalAge(pregnancy.getTransferDay()));
        }
    }

    @Transactional
    public void updatePregnancyData(EmbryoProduction production, Boolean is_pregnant){
        Fiv fiv = production.getOocyteCollection().getFiv();

        Integer productionTotalPregnancy = production.getTotalPregnancy();
        Integer fivTotalPregnancy = fiv.getFivTotalPregnancy();

        if (is_pregnant){
            productionTotalPregnancy = production.getTotalPregnancy() + 1;
            fivTotalPregnancy = fiv.getFivTotalPregnancy() + 1;
        }

        Float productionPregnancyPercentage = percentage(productionTotalPregnancy, production.getTransferredEmbryosNumber());

        production.setTotalPregnancy(productionTotalPregnancy);
        production.setPregnancyPercentage(productionPregnancyPercentage);
        productionRepository.save(production);


        Float fivPregnancyPercentage = percentage(fivTotalPregnancy, fiv.getFivTransferredEmbryosNumber());

        fiv.setFivTotalPregnancy(fivTotalPregnancy);
        fiv.setFivPregnancyPercentage(fivPregnancyPercentage);
        fivRepository.save(fiv);

    }

    /**
     * Returns {@code (numerator / denominator) * 100} as a {@link Float}, or
     * {@code 0.0f} when {@code denominator} is null or zero. Used by pregnancy-rate
     * calculations where "no transferred embryos" must surface as 0% rather than
     * {@code NaN} / {@code Infinity}.
     */
    private static Float percentage(Integer numerator, Integer denominator){
        if (denominator == null || denominator == 0){
            return 0.0f;
        }
        return (float) numerator / denominator * 100;
    }

    private Integer calculateGestationalAge(LocalDate transferDay) {
        if (transferDay == null) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(transferDay, LocalDate.now());
    }

    @Transactional
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

