package fa.training.problem02;

import java.util.List;

import fa.training.problem02.dao.impl.PersonDaoImpl;
import fa.training.problem02.dao.impl.VideoDaoImpl;
import fa.training.problem02.entities.Person;
import fa.training.problem02.entities.Video;

public class Main {
    public static void main(String[] args) {
        VideoManager vm = new VideoManager();

        vm.start();
    }
}