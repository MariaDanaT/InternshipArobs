package ex3;

import java.util.Comparator;
import java.util.TreeSet;

public class Bank {
    private TreeSet<BankAccount> bankAccounts;

    public Bank(TreeSet bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void addAccount(String owner, double balance) {
        bankAccounts.add(new BankAccount(owner, balance));
    }

    public void printAccounts() {
        bankAccounts.stream()
                .sorted(Comparator.comparingDouble(BankAccount::getBalance))
                .forEach(System.out::println);
    }
    public void printAccounts(double minBalance, double maxBalance){
        bankAccounts.stream()
                .sorted(Comparator.comparingDouble(BankAccount::getBalance))
                .filter(bankAccount -> bankAccount.getBalance()>=minBalance && bankAccount.getBalance()<=maxBalance)
                .forEach(System.out::println);
    }
    public TreeSet<BankAccount> getBankAccounts(){
        return bankAccounts;
    }
}
