package com.gabriel.pive.fiv.EmbryoProduction.exceptions;

public class OocyteCollectionAlreadyHasProduction extends RuntimeException{

    public OocyteCollectionAlreadyHasProduction(){
        super(
                "Esta coleta já possui uma produção de embriões registrada."
        );
    }
}
