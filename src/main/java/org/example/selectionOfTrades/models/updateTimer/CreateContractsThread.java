package org.example.selectionOfTrades.models.updateTimer;

public class CreateContractsThread implements Runnable{

    @Override
    public void run() {
        contractsMethod.startContract();
    }

    public CreateContractsThread(StartContracts contractsMethod) {
        this.contractsMethod = contractsMethod;
    }

    public StartContracts contractsMethod;
}
