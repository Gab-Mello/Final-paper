package com.gabriel.pive.fiv.EmbryoProduction.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferDataDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.EmbryoProduction.dtos.TransferResponseDto;
import com.gabriel.pive.fiv.EmbryoProduction.entities.Embryo;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.EmbryoProduction.entities.EmbryoTransfer;
import com.gabriel.pive.fiv.EmbryoProduction.enums.EmbryoDestiny;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.AllEmbryosAlreadyRegisteredException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.ReceiverCattleAlreadyHasEmbryoException;
import com.gabriel.pive.fiv.EmbryoProduction.exceptions.TransferNotFoundException;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.EmbryoProduction.repositories.TransferRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private FivRepository fivRepository;

    @Autowired
    private ReceiverCattleRepository receiverCattleRepository;

    @Autowired
    private ProductionRepository productionRepository;

    @Autowired
    private EmbryoRepository embryoRepository;

    @Autowired
    private FivService fivService;

    public TransferInitialDto newTransfer(TransferInitialDto dto){
        Fiv fiv = fivRepository.findById(dto.fivId()).
                orElseThrow(FivNotFoundException::new);

        return TransferInitialDto.toTransferDto(transferRepository.save(dto.toTransfer(fiv)));
    }

    public List<TransferInitialDto> getTransfersByFivId(Long fivId){
        return TransferInitialDto.toTrasnferDtoList(transferRepository.findByFivTransferId(fivId));
    }

    public TransferResponseDto saveTransferData(TransferDataDto dto){

        EmbryoProduction production = productionRepository.findById(dto.productionId())
                .orElseThrow(ProductionNotFoundException::new);

        Fiv fiv = production.getOocyteCollection().getFiv();

        if (fiv.getStatus() == FivStatusEnum.COMPLETED){
            throw new AllEmbryosAlreadyRegisteredException();
        }

        EmbryoTransfer transfer = transferRepository.findById(dto.transferId())
                .orElseThrow(TransferNotFoundException::new);

        ReceiverCattle receiverCattle = receiverCattleRepository.findById(dto.receiverId())
                .orElseThrow(ReceiverCattleNotFoundException::new);

        DonorCattle donorCattle = production.getOocyteCollection().getDonorCattle();

        Bull bull = production.getOocyteCollection().getBull();

        if (receiverCattle.getEmbryo() != null){
            throw new ReceiverCattleAlreadyHasEmbryoException();
        }

        fivService.checkToSetFivAsCompleted(fiv);

        Embryo embryo = new Embryo(production, transfer, receiverCattle,
                                    donorCattle, bull, EmbryoDestiny.TRANSFERRED);

        embryoRepository.save(embryo);

        fivService.checkToSetFivAsCompleted(fiv);
        fivService.updateEmbryosRegistered(fiv);

        return TransferResponseDto.toTransferResponseDto(transfer);
    }
}
