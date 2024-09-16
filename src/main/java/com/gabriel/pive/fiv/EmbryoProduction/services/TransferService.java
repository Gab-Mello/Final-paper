package com.gabriel.pive.fiv.EmbryoProduction.services;

import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.TransferRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private FivRepository fivRepository;

    public TransferInitialDto newTransfer(TransferInitialDto dto){
        Fiv fiv = fivRepository.findById(dto.fivId()).
                orElseThrow(FivNotFoundException::new);

        return TransferInitialDto.toTransferDto(transferRepository.save(dto.toTransfer(fiv)));
    }

    public List<TransferInitialDto> getTransfersByFivId(Long fivId){
        return TransferInitialDto.toTrasnferDtoList(transferRepository.findByFivTransferId(fivId));
    }
}
