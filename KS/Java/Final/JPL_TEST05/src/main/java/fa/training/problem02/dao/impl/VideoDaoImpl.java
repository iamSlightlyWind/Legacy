package fa.training.problem02.dao.impl;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fa.training.problem02.dao.VideoDao;
import fa.training.problem02.entities.Person;
import fa.training.problem02.entities.Video;
import fa.training.problem02.utils.Database;

public class VideoDaoImpl implements VideoDao {

    @Override
    public void upload(Video video, Person currentUser) {
        try {
            String query = "INSERT INTO video (person_id_upload, title, duration, url, upload_date, is_published) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, video.getPersonIdUpload());
            preparedStatement.setString(2, video.getTitle());
            preparedStatement.setInt(3, video.getDuration());
            preparedStatement.setString(4, video.getUrl());
            preparedStatement.setDate(5, video.getUploadDate());
            preparedStatement.setBoolean(6, video.isPublished());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Video> getVideosMostView(int limit) { // use callable statement and resultset
        List<Video> videos = new ArrayList<Video>();

        try {
            String query = "{call getVideosMostView(?)}";
            CallableStatement statement = Database.getConnection().prepareCall(query);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Video video = new Video();
                video.setVideoId(resultSet.getInt("video_id"));
                video.setPersonIdUpload(resultSet.getInt("person_id_upload"));
                video.setTitle(resultSet.getString("title"));
                video.setDuration(resultSet.getInt("duration"));
                video.setUrl(resultSet.getString("url"));
                video.setUploadDate(resultSet.getDate("upload_date"));
                video.setPublished(resultSet.getBoolean("is_published"));
                videos.add(video);
            }
            return videos;
        } catch (Exception e) {
        }
        return null;
    }
}
