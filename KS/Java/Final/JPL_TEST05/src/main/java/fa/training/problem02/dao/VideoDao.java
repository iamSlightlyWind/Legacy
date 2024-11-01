package fa.training.problem02.dao;

import java.util.List;

import fa.training.problem02.entities.Person;
import fa.training.problem02.entities.Video;

public interface VideoDao {
    public void upload(Video video, Person currentUser);

    public List<Video> getVideosMostView (int limit);
}
