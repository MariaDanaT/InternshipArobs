package ex1;

public class TestClass {
    public static void main(String[] args) {
        BankAccount bankAccountAna = new BankAccount("Ana", 50);
        BankAccount bankAccountMaria = new BankAccount("Maria", 20);
        BankAccount bankAccountMihai = new BankAccount("Mihai", 70);

        BankAccount bankAccountAnotherAna = new BankAccount("Ana", 90);
        System.out.println(bankAccountAna.equals(bankAccountMihai));
        System.out.println(bankAccountAna.equals(bankAccountAna));
        System.out.println(bankAccountAna.equals(bankAccountAnotherAna));
        System.out.println("Hashcode Ana: "+ bankAccountAna.hashCode());
        System.out.println("Hashcode Maria: "+ bankAccountMaria.hashCode());
        System.out.println("Hashcode another Ana: "+ bankAccountAnotherAna.hashCode());
        System.out.println(bankAccountAna.hashCode()==bankAccountAnotherAna.hashCode());

    }
}
