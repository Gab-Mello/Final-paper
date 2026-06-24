package com.gabriel.pive.fiv.pregnancy.dtos;

import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;

import java.time.LocalDate;

/**
 * Read-only API view of a {@link Pregnancy}.
 *
 * <p>Intentionally omits the back-reference to {@code pregReceiverCattle}: this
 * DTO is designed to be nested inside {@code ReceiverCattleDto}, so including
 * the receiver back-reference would create a circular structure.
 */
public record PregnancyResponseDto(Long id,
                                   LocalDate transferDay,
                                   PregnancyStatus status,
                                   Integer gestationalAge) {

    public static PregnancyResponseDto toPregnancyResponseDto(Pregnancy pregnancy){
        if (pregnancy == null){
            return null;
        }
        return new PregnancyResponseDto(
                pregnancy.getId(),
                pregnancy.getTransferDay(),
                pregnancy.getStatus(),
                pregnancy.getGestationalAge()
        );
    }
}
