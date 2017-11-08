/**
 * Created by Nandan on 07-11-2017.
 */
public class BankAcc {
    private int currentBalance;
    private int savingsBalance;

    public BankAcc() {
        currentBalance = 10000;
        savingsBalance = 10000;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(int savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
}
