package toggle.spring.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toggle.spring.dao.util.DAO;
import toggle.spring.models.Disc;
import toggle.spring.models.User;
import toggle.spring.service.util.DiscService;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class DiscServiceImpl implements DiscService {

    @Autowired
    @Qualifier("discDAO")
    private DAO<Disc> discDAO;

    public List<Disc> list() {
        return this.discDAO.list();
    }

    public Disc get(int id) {
        return this.discDAO.get(id);
    }

    public void save(Disc object) {
        this.discDAO.save(object);
    }

    public void delete(int id) {
        this.discDAO.delete(id);
    }

    public User login(String username, String password) {
        return null;
    }

    public void discs(User user, Disc disc) {
    }
}
