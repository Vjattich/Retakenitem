package toggle.spring.dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import toggle.spring.dao.util.DAO;
import toggle.spring.models.Disc;
import toggle.spring.models.User;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class DiscDao implements DAO<Disc> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSessionFactory() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Disc> list() {
        @SuppressWarnings("unchecked")
        List<Disc> discs = getSessionFactory().createQuery("from Disc").list();
        return discs;
    }

    @Override
    public Disc get(int id) {
        @SuppressWarnings("unchecked")
        Query q = getSessionFactory().createQuery("from Disc where id= :id");
        q.setParameter("id", id);
        Disc disc = (Disc) q.uniqueResult();
        return disc;
    }

    @Override
    public void save(Disc object) {
        getSessionFactory().saveOrUpdate(object);
    }

    @Override
    //todo can make with object
    public void delete(int id) {
        Disc disc = new Disc();
        disc.setId(id);
        getSessionFactory().delete(disc);
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public void discs(User user, Disc disc) {

    }

}
