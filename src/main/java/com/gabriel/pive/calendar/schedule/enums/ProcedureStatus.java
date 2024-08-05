package com.gabriel.pive.calendar.schedule.enums;

public enum ProcedureStatus {

        PENDING("pending"),
        SUCCESS("success"),
        ERROR("error"),
        CANCELED("canceled");

        private final String description;

        ProcedureStatus(String description){
            this.description = description;
        }

        public String getDescription(){
            return description;
        }

}

