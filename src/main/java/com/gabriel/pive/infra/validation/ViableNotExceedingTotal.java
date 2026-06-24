package com.gabriel.pive.infra.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class-level constraint that enforces {@code viableOocytes ≤ totalOocytes}
 * on any DTO that exposes those two numeric accessors. Implemented by
 * {@link ViableNotExceedingTotalValidator}, which uses reflection to read the
 * two fields by name.
 *
 * <p>Applied to {@code OocyteCollectionRequestDto} to move what was previously
 * a service-level check ({@code ViableOocytesBiggerThanTotalException}) up to
 * the Bean Validation layer where it belongs.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ViableNotExceedingTotalValidator.class)
public @interface ViableNotExceedingTotal {

    String message() default "Número de oócitos viáveis maior que total.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** Name of the "total" field on the annotated type. */
    String totalField() default "totalOocytes";

    /** Name of the "viable" field on the annotated type. */
    String viableField() default "viableOocytes";
}
