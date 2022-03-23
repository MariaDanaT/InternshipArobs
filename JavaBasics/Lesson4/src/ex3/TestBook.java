package ex3;

import ex2.Author;

public class TestBook {
    public static void main(String[] args) {
        Book book = new Book("Ion", new Author("Liviu Rebreanu", "liviu@gmail.com", 'm'), 12.5, 10);
        System.out.println(book);
        System.out.println(book.getAuthor());
        System.out.println(book.getQtyInStock());
        book.setQtyInStock(8);
        System.out.println(book.getQtyInStock());
        book.setPrice(15);
        System.out.println(book.getPrice());

    }
}
