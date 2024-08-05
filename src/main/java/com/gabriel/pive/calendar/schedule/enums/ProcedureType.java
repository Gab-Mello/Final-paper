package com.gabriel.pive.calendar.schedule.enums;

public enum ProcedureType {
    OOCYTE_COLLECTION("Coleta de Oócitos"),
    IN_VITRO_MATURATION("Maturação In Vitro"),
    IN_VITRO_FERTILIZATION("Fertilização In Vitro"),
    EMBRYO_TRANSFER("Transferência de Embriões");


    private final String displayName;

    ProcedureType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
