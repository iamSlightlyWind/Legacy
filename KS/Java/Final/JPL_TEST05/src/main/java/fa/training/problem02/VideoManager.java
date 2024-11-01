package fa.training.problem02;

import java.util.List;
import java.util.Scanner;
import fa.training.problem02.dao.impl.PersonDaoImpl;
import fa.training.problem02.dao.impl.VideoDaoImpl;
import fa.training.problem02.entities.Person;
import fa.training.problem02.entities.Video;
import fa.training.problem02.utils.Validator;

public class VideoManager {
    private Scanner scan = new Scanner(System.in);
    private Person current = null;

    public void start() {
        printBigMenu();
    }

    public void printBigMenu() {
        System.out.println("1. Person Management");
        System.out.println("2. Video Management");
        System.out.println("3. Close Program");
        System.out.print("Enter choice: ");

        printSubMenu(scan.nextInt());
    }

    public void printSubMenu(int choice) {
        scan.nextLine();
        switch (choice) {
            case 1:
                System.out.println("1. Add a new Person");
                System.out.println("2. Show 'View' History");
                break;
            case 2:
                System.out.println("1. Upload a new Video");
                System.out.println("2. Update a View");
                System.out.println("3. Show top video");
                System.out.println("4. Show video's detail");
                break;
            case 3:
                return;
        }

        System.out.print("Enter choice: ");
        exec(choice, Integer.parseInt(scan.nextLine()));
    }

    public void exec(int bigChoice, int subChoice) {
        switch (bigChoice) {
            case 1:
                switch (subChoice) {
                    case 1:
                        addPerson();
                        break;
                    case 2:

                        break;
                }
                break;
            case 2:
                switch (subChoice) {
                    case 1:
                        addVideo();
                        break;
                    case 2:

                        break;
                    case 3:
                        showTopVideos();
                        break;
                    case 4:

                        break;
                }
        }
    }

    public void showTopVideos() {
        int limit = Validator.getLimit();

        VideoDaoImpl videoDao = new VideoDaoImpl();
        List<Video> videos = videoDao.getVideosMostView(limit);

        System.out.println("\n");
        for (Video video : videos) {
            System.out.println(video);
            System.out.println("\n");
        }
    }

    public Video newVideo() {
        System.out.println("-- Creating a new Video -- ");

        PersonDaoImpl personDao = new PersonDaoImpl();

        Video video = new Video();
        video.setPersonIdUpload(Validator.getID());

        while (!personDao.personExist(video.getPersonIdUpload())) {
            System.out.println("Person ID does not exist. Please enter again.");
            video.setPersonIdUpload(Validator.getID());
        }

        video.setTitle(Validator.getTitle());
        video.setDuration(Validator.getDuration());
        video.setUrl(Validator.getURL());
        video.setUploadDate(Validator.getUploadDate());
        video.setPublished(Validator.isPublished());

        return video;
    }

    public void addVideo() {
        VideoDaoImpl videoDao = new VideoDaoImpl();
        PersonDaoImpl personDao = new PersonDaoImpl();
        Video newVideo = newVideo();
        videoDao.upload(newVideo, personDao.getPersonById(newVideo.getPersonIdUpload()));
    }

    public Person newPerson() {
        Person person = new Person();
        person.setPersonName(Validator.personName());
        person.setBod(Validator.getDate());

        return person;
    }

    public void addPerson() {
        PersonDaoImpl personDao = new PersonDaoImpl();
        personDao.save(newPerson());
    }

}