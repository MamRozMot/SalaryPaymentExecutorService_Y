package ir.dotin.business;

import ir.dotin.files.BalanceFileHandler;
import ir.dotin.files.PaymentVO;

import java.io.IOException;
import java.util.List;

import static ir.dotin.PaymentTransactionApp.balanceVOs;
import static ir.dotin.PaymentTransactionApp.transactionVOS;


public class MyThreadPool implements Runnable {
    private String debtorDepositNumber;
    private List<PaymentVO> list;

    public MyThreadPool(String debtorDepositNumber, List<PaymentVO> list) {
        this.debtorDepositNumber = debtorDepositNumber;
        this.list = list;
    }

    @Override
    public void run() {
        for (PaymentVO paymentVO : list) {
            transactionVOS.add(TransactionProcessor.processPayment(balanceVOs, debtorDepositNumber, paymentVO));
        }
        try {
            BalanceFileHandler.writeFinalBalanceVOToFile(balanceVOs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
