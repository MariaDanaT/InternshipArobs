package ex3;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TestClass {
    public static void main(String[] args) {
        BankAccount bankAccountAna = new BankAccount("Ana", 100);
        BankAccount bankAccountMaria = new BankAccount("Maria", 80);
        BankAccount bankAccountMihai = new BankAccount("Mihai", 300);

        TreeSet<BankAccount> bankAccounts = new TreeSet<>(Comparator.comparing(BankAccount::getOwner));

        bankAccounts.add(bankAccountAna);
        bankAccounts.add(bankAccountMaria);
        bankAccounts.add(bankAccountMihai);

        Bank bank = new Bank(bankAccounts);

        System.out.println("Bank accounts: "+bankAccounts);
        System.out.println("Bank accounts sorted by balance:");
        bank.printAccounts();
        System.out.println("\nBank accounts with the balance between 2 values:");
        bank.printAccounts(20,90);

        System.out.println("Bank accounts sorted by name:");
        bank.getBankAccounts()
                .stream()
                .sorted(Comparator.comparing(BankAccount::getOwner))
                .forEach(System.out::println);
        System.out.println("Bank accounts sorted by balance:");
        bank.getBankAccounts()
                .stream()
                .sorted(Comparator.comparing(BankAccount::getBalance))
                .forEach(System.out::println);


    }
}
