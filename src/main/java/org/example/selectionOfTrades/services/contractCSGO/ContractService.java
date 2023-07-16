package org.example.selectionOfTrades.services.contractCSGO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.selectionOfTrades.models.contract.ContractManager;
import org.example.selectionOfTrades.models.entities.contractCSGO.Contract;
import org.example.selectionOfTrades.models.updateTimer.CreateContractsThread;
import org.example.selectionOfTrades.repositories.contractCSGO.ContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final ContractManager contractManager;
    private boolean isFinish = true;

    public List<Contract> listContract(Double coefficient){
        if (coefficient != null) contractRepository.findByCoefficient(coefficient);
        return contractRepository.findAllContractsGreaterNumberWithCoefficient(1.15);
    }

    public void saveContract(Contract contract) {
        if (contract != null) {
            log.info("Saving contract");
            contractRepository.save(contract);
        }
    }

    public void saveContracts(List<Contract> contracts) {
        if (contracts != null && contracts.size() > 0) {
            log.info("Saving contract size:{}", contracts.size());
            contractRepository.saveAll(contracts);
        }
    }

    public void deleteContractsAll(){
        contractRepository.deleteAll();
    }

    public boolean getIsFinish(){
        return isFinish;
    }

    private void createContracts(){
        if(!isFinish){
           log.info("Not start create contracts! The previous contract has not been completed !!!!");
           return;
        }
        isFinish = false;
        this.deleteContractsAll();
        this.saveContracts(contractManager.newContracts());
        isFinish = true;
    }

    private void updateContracts(){
        List<Contract> contractsOld = this.listContract(null);
        this.saveContracts( contractManager.updateContracts(contractsOld));
    }

    public void startCreateContracts(boolean isCreateContracts){
        if(isCreateContracts){
            log.info("Start create contracts !!!!");
            new Thread( new CreateContractsThread(this::createContracts)).start();
        }else {
            log.info("Start update contracts !!!!");
            new Thread( new CreateContractsThread(this::updateContracts)).start();
        }
    }
}
