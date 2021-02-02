package ir.dotin;

import ir.dotin.business.TransactionProcessor;
import ir.dotin.files.BalanceFileHandler;
import ir.dotin.files.PaymentFileHandler;
import ir.dotin.files.PaymentVO;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


public class PaymentTransactionApp {
    public static final String FILE_PATH_PREFIX = "E://";
    public static final String BALANCE_FILE_PATH = FILE_PATH_PREFIX + "Balance.txt";
    public static final String PAYMENT_FILE_PATH = FILE_PATH_PREFIX + "Payment.txt";
    public static final String TRANSACTION_FILE_PATH = FILE_PATH_PREFIX + "Transactions.txt";
    public static final String BALANCE_UPDATE_FILE_PATH = FILE_PATH_PREFIX + "BalanceUpdate.txt";
    public static final String DEBTOR_DEPOSIT_NUMBER = "1.10.100.1";
    public static final BigDecimal DEBTOR_DEPOSIT_AMOUNT = new BigDecimal(10000000);
    public static final String CREDITOR_DEPOSIT_NUMBER_PREFIX = "1.20.100.";
    public static final int CREDITOR_COUNT = 1000;
    public static final int THREAD_COUNT = 5;

    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 10000;
    private static final Random random = new Random();


    public static BigDecimal generateRandomAmount() {
        return BigDecimal.valueOf(random.nextInt((MAX_AMOUNT - MIN_AMOUNT) + 1) + MIN_AMOUNT);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Starting app...");
            deleteAllExistingFiles();
            List<PaymentVO> paymentVOs = PaymentFileHandler.createPaymentFile(DEBTOR_DEPOSIT_NUMBER, CREDITOR_DEPOSIT_NUMBER_PREFIX, CREDITOR_COUNT);
            BalanceFileHandler.createInitialBalanceFile(CREDITOR_COUNT);
            TransactionProcessor.processThreadPool(paymentVOs);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void deleteAllExistingFiles() throws IOException {
        System.out.println("Deleting all existing files...");
        Files.deleteIfExists(Paths.get(PaymentTransactionApp.BALANCE_FILE_PATH));
        Files.deleteIfExists(Paths.get(PaymentTransactionApp.BALANCE_UPDATE_FILE_PATH));
        Files.deleteIfExists(Paths.get(PaymentTransactionApp.PAYMENT_FILE_PATH));
        Files.deleteIfExists(Paths.get(PaymentTransactionApp.TRANSACTION_FILE_PATH));
    }
}