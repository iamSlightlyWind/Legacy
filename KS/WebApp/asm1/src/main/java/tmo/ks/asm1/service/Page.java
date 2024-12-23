package tmo.ks.asm1.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public class Page<T> {
    public static <T> List<T> getPage(JpaRepository<T, Integer> repository, int pageSize, int page) {
        int size = (int) repository.count();

        if (pageSize < 1) {
            pageSize = 1;
        } else if (pageSize > size) {
            pageSize = size;
        }

        int maxPage = size / pageSize + (size % pageSize == 0 ? 0 : 1);

        if (page < 1) {
            page = 1;
        } else if (page > maxPage) {
            page = maxPage;
        }

        List<T> result = new java.util.ArrayList<T>();

        for (int i = pageSize * (page - 1) + 1; i <= pageSize * page; i++) {
            if (i > size) {
                break;
            }
            result.add(repository.findById(i).get());
        }

        return result;
    }

    @SuppressWarnings("rawtypes")
    public static int maxPage(JpaRepository repository, int pageSize) {
        int size = (int) repository.count();
        return size / pageSize + (size % pageSize == 0 ? 0 : 1);
    }
}