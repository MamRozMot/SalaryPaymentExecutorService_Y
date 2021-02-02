package ir.dotin.business;

import ir.dotin.files.PaymentVO;
import ir.dotin.files.TransactionFileHandler;

import java.io.IOException;
import java.util.List;


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
            try {
                TransactionFileHandler.addTransactionToFile(TransactionProcessor.processPayment(debtorDepositNumber, paymentVO));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
