package ex4;

import ex2.Author;

public class TestBook {
    public static void main(String[] args) {
        Author[] authors = new Author[2];
        authors[0] = new Author("a1", "a1@yahoo.com", 'm');
        authors[1] = new Author("a2", "a2@gmail.com", 'f');

        Book book = new Book("Book1", authors, 35, 3);
        System.out.println(book.getName());
        System.out.println("the old price: "+book.getPrice());
        book.setPrice(30);
        System.out.println("the new price: "+book.getPrice());
        System.out.println("number of authors: " + book.getAuthors().length);

    }
}
