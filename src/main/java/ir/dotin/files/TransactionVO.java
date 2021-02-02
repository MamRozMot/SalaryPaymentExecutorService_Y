
package ir.dotin.files;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionVO implements Serializable {
    private String debtorDepositNumber;
    private String creditorDepositNumber;
    private BigDecimal amount;

    public String getDebtorDepositNumber() {
        return debtorDepositNumber;
    }

    public void setDebtorDepositNumber(String debtorDepositNumber) {
        this.debtorDepositNumber = debtorDepositNumber;
    }

    public String getCreditorDepositNumber() {
        return creditorDepositNumber;
    }

    public void setCreditorDepositNumber(String creditorDepositNumber) {
        this.creditorDepositNumber = creditorDepositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionVO setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }


    @Override
    public String toString() {
        return debtorDepositNumber +
                " " + creditorDepositNumber +
                " " + amount + "\n";
    }
}


