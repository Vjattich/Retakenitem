package toggle.spring.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import toggle.spring.dao.util.DAO;
import toggle.spring.models.Disc;
import toggle.spring.models.User;


import java.util.List;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class UserDao implements DAO<User> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> users = getSessionFactory().createQuery("from User").list();
        return users;
    }

    @Override
    public User get(int id) {
        Query q = getSessionFactory().createQuery("from User where id= :id").setEntity("User", User.class);
        q.setParameter("id", id);
        User user = (User) q.uniqueResult();
        return user;
    }

    @Override
    public void save(User object) {
        getSessionFactory().saveOrUpdate(object);

    }

    @Override
    public void delete(int id) {
        User user = new User();
        user.setId(id);
        getSessionFactory().delete(user);

    }

    @Override
    public User login(String username, String password) {
        Query q = getSessionFactory().createQuery("from User as u where u.username= :un and u.password= :pw");
        q.setParameter("un", username);
        q.setParameter("pw", password);
        User user = (User) q.uniqueResult();
        return user == null ? null : user;
    }

    @Override
    public void discs(User user, Disc disc) {
        int i = 1;
        if (user.getDiscs().contains(disc) || user.getDiscs().contains(disc) && user.getMyDisc().contains(disc)) i = 0;
        if(!user.getMyDisc().contains(disc)) i = 2;
        switch (i) {
            case 0:
                user.getDiscs().remove(disc);
                break;
            case 1:
                user.getDiscs().add(disc);
                break;
            case 2:
                user.getMyDisc().add(disc);
                break;
        }
        save(user);
    }

}
