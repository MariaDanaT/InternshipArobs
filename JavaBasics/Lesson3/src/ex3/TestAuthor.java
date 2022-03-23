package ex3;

public class TestAuthor {
    public static void main(String[] args) {
        Author author = new Author("Ana", "ana@yahoo.com", 'f');
        assert author != null;
        assert author.getName().equals("Ana");
        assert author.getEmail().equals("ana@yahoo.com");
        assert author.getGender() == 'f';
        author.setEmail("ana@gmail.com");
        assert author.getEmail().equals("ana@gmail.com");
        assert author.toString().equals("Ana (f) at ana@gmail.com");
    }
}

