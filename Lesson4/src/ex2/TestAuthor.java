package ex2;

public class TestAuthor {
    public static void main(String[] args) {
        Author author = new Author("Maria", "maria@yahoo.com", 'f');
        System.out.println(author.getName());
        System.out.println( author.getGender());
        author.setEmail("maria.m@gmail.com");
        System.out.println(author.getEmail());
        System.out.println( author.getEmail().equals("maria.m@gmail.com"));
        System.out.println(author.toString().equals("Maria (f) at maria.m@gmail.com"));

    }
}
