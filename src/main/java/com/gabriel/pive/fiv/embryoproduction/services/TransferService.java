package com.gabriel.pive.fiv.embryoproduction.services;

import com.gabriel.pive.animals.entities.Bull;
import com.gabriel.pive.animals.entities.DonorCattle;
import com.gabriel.pive.animals.entities.ReceiverCattle;
import com.gabriel.pive.animals.exceptions.ReceiverCattleNotFoundException;
import com.gabriel.pive.animals.repositories.ReceiverCattleRepository;
import com.gabriel.pive.fiv.embryoproduction.dtos.TransferDataDto;
import com.gabriel.pive.fiv.embryoproduction.dtos.TransferInitialDto;
import com.gabriel.pive.fiv.embryoproduction.dtos.TransferResponseDto;
import com.gabriel.pive.fiv.embryoproduction.entities.Embryo;
import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoProduction;
import com.gabriel.pive.fiv.embryoproduction.entities.EmbryoTransfer;
import com.gabriel.pive.fiv.embryoproduction.enums.EmbryoDestiny;
import com.gabriel.pive.fiv.embryoproduction.exceptions.AllEmbryosAlreadyRegisteredException;
import com.gabriel.pive.fiv.embryoproduction.exceptions.ProductionNotFoundException;
import com.gabriel.pive.fiv.embryoproduction.exceptions.ReceiverCattleAlreadyHasEmbryoException;
import com.gabriel.pive.fiv.embryoproduction.exceptions.TransferNotFoundException;
import com.gabriel.pive.fiv.embryoproduction.repositories.EmbryoRepository;
import com.gabriel.pive.fiv.embryoproduction.repositories.ProductionRepository;
import com.gabriel.pive.fiv.embryoproduction.repositories.TransferRepository;
import com.gabriel.pive.fiv.entities.Fiv;
import com.gabriel.pive.fiv.enums.FivStatusEnum;
import com.gabriel.pive.fiv.exceptions.FivNotFoundException;
import com.gabriel.pive.fiv.pregnancy.entities.Pregnancy;
import com.gabriel.pive.fiv.pregnancy.enums.PregnancyStatus;
import com.gabriel.pive.fiv.pregnancy.repositories.PregnancyRepository;
import com.gabriel.pive.fiv.repositories.FivRepository;
import com.gabriel.pive.fiv.services.FivService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransferService {

    private final TransferRepository transferRepository;
    private final FivRepository fivRepository;
    private final ReceiverCattleRepository receiverCattleRepository;
    private final ProductionRepository productionRepository;
    private final EmbryoRepository embryoRepository;
    private final FivService fivService;
    private final PregnancyRepository pregnancyRepository;
    private final ProductionService productionService;

    @Transactional
    public TransferResponseDto newTransfer(TransferInitialDto dto){
        Fiv fiv = fivRepository.findById(dto.fivId()).
                orElseThrow(FivNotFoundException::new);

        return TransferResponseDto.toTransferResponseDto(transferRepository.save(dto.toTransfer(fiv)));
    }

    public List<TransferResponseDto> getTransfersByFivId(Long fivId){
        return TransferResponseDto.toTrasnferResponseDtoList(transferRepository.findByFivTransferId(fivId));
    }

    @Transactional
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

        Embryo embryo = new Embryo(production, transfer, receiverCattle,
                                    donorCattle, bull, EmbryoDestiny.TRANSFERRED);

        embryoRepository.save(embryo);

        Pregnancy pregnancy = new Pregnancy(transfer.getDate(), receiverCattle, PregnancyStatus.IN_PROGRESS);
        pregnancyRepository.save(pregnancy);

        productionService.updateTransferredEmbryosNumber(production);

        fivService.updateEmbryosRegistered(fiv, production);
        fivService.checkToSetFivAsCompleted(fiv);

        return TransferResponseDto.toTransferResponseDto(transfer);
    }
}
