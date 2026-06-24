package com.gabriel.pive.infra;

import com.gabriel.pive.animals.exceptions.*;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.*;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.DonorAlreadyCollectedException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.FivAlreadyHasOocyteCollectionException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.OocyteCollectionNotFoundException;
import com.gabriel.pive.fiv.oocyteCollection.exceptions.ViableOocytesBiggerThanTotalException;
import com.gabriel.pive.fiv.pregnancy.exceptions.ReceiverCattleDoesNotHaveAnEmbryoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistrationNumberAlreadyExistsException.class)
    public ResponseEntity<String> registrationNumberAlreadyExists(RegistrationNumberAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ReceiverCattleNotFoundException.class)
    public ResponseEntity<String> receiverCattleNotFound(ReceiverCattleNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(BullNotFoundException.class)
    public ResponseEntity<String> bullNotFound(BullNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(DonorCattleNotFoundException.class)
    public ResponseEntity<String> donorNotFound(DonorCattleNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(FivNotFoundException.class)
    public ResponseEntity<String> fivNotFound(FivNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(AllEmbryosAlreadyRegisteredException.class)
    public ResponseEntity<String> allEmbryosAlreadyRegistered(AllEmbryosAlreadyRegisteredException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ProductionNotFoundException.class)
    public ResponseEntity<String> cultivationNotFound(ProductionNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmbryoNotFoundException.class)
    public ResponseEntity<String> embryoNotFound(EmbryoNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(OocyteCollectionAlreadyHasProduction.class)
    public ResponseEntity<String> fivAlreadyHasCultivation(OocyteCollectionAlreadyHasProduction exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(FivDoesNotHaveOocyteCollectionException.class)
    public ResponseEntity<String> fivDoesNotHaveOocyteCollection(FivDoesNotHaveOocyteCollectionException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ReceiverCattleAlreadyHasEmbryoException.class)
    public ResponseEntity<String> receiverCattleAlreadyHasEmbryo(ReceiverCattleAlreadyHasEmbryoException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(FivAlreadyHasOocyteCollectionException.class)
    public ResponseEntity<String> fivAlreadyHasOocyteCollection(FivAlreadyHasOocyteCollectionException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(OocyteCollectionNotFoundException.class)
    public ResponseEntity<String> oocyteCollectionNotFound(OocyteCollectionNotFoundException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException exception) {

        FieldError fieldError = (FieldError) exception.getBindingResult().getAllErrors().get(0);
        String errorMessage = fieldError.getDefaultMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(ViableOocytesBiggerThanTotalException.class)
    public ResponseEntity<String> viableOocytesBiggerThanTotal(ViableOocytesBiggerThanTotalException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(DonorAlreadyCollectedException.class)
    public ResponseEntity<String> donorAlreadyCollected(DonorAlreadyCollectedException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<String> invalidDate(InvalidDateException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<String> transferNotFound(TransferNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidNumberOfEmbryosException.class)
    public ResponseEntity<String> invalidNumberOfEmbryos(InvalidNumberOfEmbryosException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(ReceiverCattleDoesNotHaveAnEmbryoException.class)
    public ResponseEntity<String> receiverDoesNotHaveAnEmbryo(ReceiverCattleDoesNotHaveAnEmbryoException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
