package fa.training.problem01.entities;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private Date date;
    private String content;
    private int author;

    public Post() {
    }

    public Post(int id, String title, Date date, String content, int author) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.content = content;
        this.author = author;
    }

    public String toString() {
        return "ID: " + id + "\nTitle: " + title + "\nDate: " + date + "\nAuthor: " + author + "\nContent: " + content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(int author) {
        this.author = author;
    }
}
