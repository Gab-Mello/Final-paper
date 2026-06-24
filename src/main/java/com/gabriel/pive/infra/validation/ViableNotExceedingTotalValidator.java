package com.gabriel.pive.infra.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

/**
 * Reflectively reads the two fields named in
 * {@link ViableNotExceedingTotal#totalField()} and
 * {@link ViableNotExceedingTotal#viableField()} and validates that
 * {@code viable ≤ total}.
 *
 * <p>If either field is {@code null}, validation is considered to pass — the
 * field-level {@code @NotNull} on the DTO is responsible for that case, so we
 * don't double-report. If the fields can't be read (wrong type, missing), the
 * check returns {@code false} to fail loudly.
 */
public class ViableNotExceedingTotalValidator
        implements ConstraintValidator<ViableNotExceedingTotal, Object> {

    private String totalField;
    private String viableField;

    @Override
    public void initialize(ViableNotExceedingTotal constraint) {
        this.totalField = constraint.totalField();
        this.viableField = constraint.viableField();
    }

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }
        Integer total = readInteger(dto, totalField);
        Integer viable = readInteger(dto, viableField);
        if (total == null || viable == null) {
            // Let @NotNull on the field own the "missing value" report.
            return true;
        }
        return viable <= total;
    }

    private static Integer readInteger(Object dto, String fieldName) {
        try {
            Field f = dto.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            Object value = f.get(dto);
            return value instanceof Integer integer ? integer : null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
