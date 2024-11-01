package fa.training.problem01;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import fa.training.problem01.entities.*;
import fa.training.problem01.utils.Validator;

public class PostManagement {
    private List<Author> authors = new ArrayList<Author>();
    private List<Post> posts = new ArrayList<Post>();

    public void init() {
        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            authors.add(new Author(0, "John", "Parker", dateFormat.parse("1990-01-01"), "Male", "Author #1"));
            authors.add(new Author(1, "Jane", "Smith", dateFormat.parse("1991-01-01"), "Female", "Author #2"));
            authors.add(new Author(2, "Michael", "Jones", dateFormat.parse("1992-01-01"), "Male", "Author #3"));
            authors.add(new Author(3, "Emily", "Brown", dateFormat.parse("1993-01-01"), "Female", "Author #4"));
            authors.add(new Author(4, "William", "Davis", dateFormat.parse("1994-01-01"), "Male", "Author #5"));
            authors.add(new Author(5, "Olivia", "Miller", dateFormat.parse("1995-01-01"), "Female", "Author #6"));
            authors.add(new Author(6, "Ethan", "Wilson", dateFormat.parse("1996-01-01"), "Male", "Author #7"));
            authors.add(new Author(7, "Isabella", "Moore", dateFormat.parse("1997-01-01"), "Female", "Author #8"));
            authors.add(new Author(8, "James", "Taylor", dateFormat.parse("1998-01-01"), "Male", "Author #9"));
            authors.add(new Author(9, "Sophia", "Anderson", dateFormat.parse("1999-01-01"), "Female", "Author #10"));
        } catch (Exception e) {

        }
    }

    public boolean authorExist(int authorID) {
        for (Author a : authors) {
            if (a.getId() == authorID) {
                return true;
            }
        }

        System.out.println("Author ID " + authorID + " not found");
        return false;
    }

    public void inputData() {
        Post currentPost = new Post();

        System.out.println("-- Adding a new Post --");

        do {
            currentPost.setAuthor(Validator.authorID());
        } while (!authorExist(currentPost.getAuthor()));

        currentPost.setTitle(Validator.getTitle());
        currentPost.setDate(Validator.getDate());
        currentPost.setContent(Validator.getContent());

        posts.add(currentPost);
        System.out.println("Post added successfully!");
    }

    public void DisplayData() {
        for (Post p : posts) {
            System.out.println(p.toString());
            System.out.println("\n");
        }
    }

    public List<Post> findByTitle(String title) {
        List<Post> result = new ArrayList<Post>();

        for (Post p : posts) {
            if (p.getTitle().equals(title)) {
                result.add(p);
            }
        }

        result.sort((p1, p2) -> p1.getDate().compareTo(p2.getDate()));
        return result;
    }

    public void classifyAuthor() {
        int postCount[] = new int[authors.size()];
        for (Post p : posts) {
            postCount[p.getAuthor()]++;
        }

        // id = ?, name = ?, totalPost = ?
        for (int i = 0; i < authors.size(); i++) {
            System.out.println("id = " + authors.get(i).getId() + ", name = " + authors.get(i).getFirstName() + " "
                    + authors.get(i).getLastName() + ", totalPost = " + postCount[i]);
        }
    }

    public void test() {
        this.init();

        DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            posts.add(new Post(1, "Title 1", dateFormat.parse("1990-01-01"), "Content 1", 1));
            posts.add(new Post(2, "Title 2", dateFormat.parse("1991-01-01"), "Content 2", 2));
            posts.add(new Post(3, "Title 3", dateFormat.parse("1992-01-01"), "Content 3", 3));
            posts.add(new Post(4, "Title 4", dateFormat.parse("1993-01-01"), "Content 4", 1));
            posts.add(new Post(5, "Title 5", dateFormat.parse("1994-01-01"), "Content 5", 2));
            posts.add(new Post(6, "Title 6", dateFormat.parse("1995-01-01"), "Content 6", 3));
            posts.add(new Post(7, "Title 7", dateFormat.parse("1996-01-01"), "Content 7", 1));
            posts.add(new Post(8, "Title 8", dateFormat.parse("1997-01-01"), "Content 8", 2));
            posts.add(new Post(9, "Title 9", dateFormat.parse("1998-01-01"), "Content 9", 3));
            posts.add(new Post(10, "Title 10", dateFormat.parse("1999-01-01"), "Content 10", 1));
        } catch (Exception e) {
        }

        this.inputData();
        this.DisplayData();
        this.classifyAuthor();

        System.out.println(findByTitle("Title 3"));
    }

    public static void main(String[] args) {
        PostManagement pm = new PostManagement();
        pm.test();
    }
}