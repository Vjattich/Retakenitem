package toggle.spring.dao.util;

import toggle.spring.models.Disc;
import toggle.spring.models.User;

import java.util.List;

public interface DAO<T> {

    List<T> list();

    T get(int id);

    void save(T object);

    void delete(int id);

    User login(String username, String password);

    void discs(User user, Disc disc);

}
