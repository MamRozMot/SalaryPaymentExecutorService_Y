package ir.dotin.files;

import ir.dotin.PaymentTransactionApp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BalanceFileHandler {


    public static void createInitialBalanceFile(int creditorCount) throws IOException {
        System.out.println("Creating initial balance file...");
        //To Test Transaction Processor
        List<BalanceVO> balanceVOs = new ArrayList<>();
        balanceVOs.add(new BalanceVO(PaymentTransactionApp.DEBTOR_DEPOSIT_NUMBER, PaymentTransactionApp.DEBTOR_DEPOSIT_AMOUNT));
        //To Test InadequateInitialBalanceException
        for (int i = 1; i <= creditorCount; i++) {
            balanceVOs.add(new BalanceVO(PaymentTransactionApp.CREDITOR_DEPOSIT_NUMBER_PREFIX + i, PaymentTransactionApp.generateRandomAmount()));
        }
        writeBalanceVOToFile(balanceVOs);
//        printBalanceVOsToConsole(balanceVOs);
        Files.copy(Paths.get(PaymentTransactionApp.BALANCE_FILE_PATH), Paths.get(PaymentTransactionApp.BALANCE_UPDATE_FILE_PATH));
    }

    public static void writeBalanceVOToFile(List<BalanceVO> balanceVOs) throws IOException {
        PrintWriter printWriter = new PrintWriter(PaymentTransactionApp.BALANCE_FILE_PATH);
        for (BalanceVO balanceVO : balanceVOs) {
            printWriter.println(balanceVO.toString());
        }
        printWriter.close();
    }


    public static void printBalanceVOsToConsole(List<BalanceVO> balanceVOS) {
        System.out.println("*********************** BALANCE *************************");
        for (BalanceVO balanceVO : balanceVOS)
            System.out.println(balanceVO.toString());
        System.out.println("***********************************************************");
    }


    public static void createFinalBalanceFile() throws IOException {
        Path pathBalanceUpdate = Paths.get(PaymentTransactionApp.BALANCE_UPDATE_FILE_PATH);
        if (!Files.exists(pathBalanceUpdate))
            Files.createFile(pathBalanceUpdate);
    }

    public static synchronized void writeFinalBalanceVOToFile(List<BalanceVO> balanceVOs) throws IOException {
        FileWriter fileWriterBalance = new FileWriter(PaymentTransactionApp.BALANCE_UPDATE_FILE_PATH, true);
        PrintWriter printWriter = new PrintWriter(fileWriterBalance);
        for (BalanceVO balanceVO : balanceVOs) {
            printWriter.println(balanceVO.toString());
        }
        printWriter.close();
    }


    public static void printFinalBalanceVOsToConsole(List<BalanceVO> balanceVOS) {
        System.out.println("*********************** FinalBALANCE **********************");
        for (BalanceVO balanceVO : balanceVOS)
            System.out.println(balanceVO.toString());
        System.out.println("***********************************************************");
    }


}





