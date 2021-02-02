package ir.dotin.files;

import ir.dotin.PaymentTransactionApp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class TransactionFileHandler {

    public static synchronized void addTransactionToFile(TransactionVO transactionVO) throws IOException {
        Files.write(Paths.get(PaymentTransactionApp.TRANSACTION_FILE_PATH), transactionVO.toString().getBytes(), StandardOpenOption.APPEND);
    }

}

