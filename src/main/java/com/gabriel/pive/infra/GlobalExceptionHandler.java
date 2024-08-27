package com.gabriel.pive.infra;

import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.exceptions.BullNotFoundException;
import com.gabriel.pive.animals.exceptions.DonorCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.exceptions.RegistrationNumberAlreadyExistsException;
import com.gabriel.pive.fiv.cultivation.exceptions.*;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.FivAlreadyHasOocyteCollectionException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.OocyteCollectionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistrationNumberAlreadyExistsException.class)
    private ResponseEntity<String> registrationNumberAlreadyExists(RegistrationNumberAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ReceiverCattleNotFoundException.class)
    private ResponseEntity<String> receiverCattleNotFound(ReceiverCattleNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(BullNotFoundException.class)
    private ResponseEntity<String> bullNotFound(BullNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DonorCattleNotFoundException.class)
    private ResponseEntity<String> donorNotFound(DonorCattleNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(FivNotFoundException.class)
    private ResponseEntity<String> fivNotFound(FivNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(AllEmbryosAlreadyRegisteredException.class)
    private ResponseEntity<String> allEmbryosAlreadyRegistered(AllEmbryosAlreadyRegisteredException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(CultivationNotFoundException.class)
    private ResponseEntity<String> cultivationNotFound(CultivationNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmbryoNotFoundException.class)
    private ResponseEntity<String> embryoNotFound(EmbryoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(FivAlreadyHasCultivation.class)
    private ResponseEntity<String> fivAlreadyHasCultivation(FivAlreadyHasCultivation exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(FivDoesNotHaveOocyteCollectionException.class)
    private ResponseEntity<String> fivDoesNotHaveOocyteCollection(FivDoesNotHaveOocyteCollectionException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ReceiverCattleAlreadyHasEmbryoException.class)
    private ResponseEntity<String> receiverCattleAlreadyHasEmbryo(ReceiverCattleAlreadyHasEmbryoException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(FivAlreadyHasOocyteCollectionException.class)
    private ResponseEntity<String> embryoNotFound(FivAlreadyHasOocyteCollectionException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(OocyteCollectionNotFoundException.class)
    private ResponseEntity<String> oocyteCollectionNotFound(OocyteCollectionNotFoundException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
