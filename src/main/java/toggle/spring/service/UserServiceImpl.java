package toggle.spring.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toggle.spring.dao.util.DAO;
import toggle.spring.models.Disc;
import toggle.spring.models.User;
import toggle.spring.service.util.UserService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDAO")
    private DAO<User> userDAO;

    public User login(String username, String password) {
        return this.userDAO.login(username, password);
    }

    public void discs(User user, Disc disc) {
        this.userDAO.discs(user, disc);
    }

    public List<User> list() {
        return this.userDAO.list();
    }

    public User get(int id) {
        return this.userDAO.get(id);
    }

    public void save(User object) {
        this.userDAO.save(object);
    }

    public void delete(int id) {
        this.userDAO.delete(id);
    }
}
