package fa.training.problem02.entities;

import java.sql.Date;

import fa.training.problem02.dao.impl.PersonDaoImpl;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Video {
    private int videoId;
    private int personIdUpload;
    private String title;
    private int duration;
    private String url;
    private Date uploadDate;
    private boolean isPublished;

    public Video(int videoId, int personIdUpload, String title, int duration, String url, Date uploadDate,
            boolean isPublished) {
        this.videoId = videoId;
        this.personIdUpload = personIdUpload;
        this.title = title;
        this.duration = duration;
        this.url = url;
        this.uploadDate = uploadDate;
        this.isPublished = isPublished;
    }

    public String toString(){
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        Person person = personDaoImpl.getPersonById(personIdUpload);

        return "Video ID: " + videoId + "\n" + "Person ID: " + personIdUpload + "\n" + "Person Name: " + person.getPersonName() + "\n" + "Title: " + title + "\n" + "Duration: " + duration + "\n" + "URL: " + url + "\n" + "Upload Date: " + uploadDate + "\n" + "Is Published: " + isPublished;
    }
}
