package toggle.spring.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import toggle.spring.dao.DiscDao;
import toggle.spring.dao.UserDao;
import toggle.spring.dao.util.DAO;
import toggle.spring.models.Disc;
import toggle.spring.models.User;

import javax.sql.DataSource;
import java.util.Properties;

//todo. Mk sklet ad bld it al.
//MySQL table, ad so mny thig.
//todo. DOE BOOTSHIT! AND THINK WITH GROOVY.

@Configuration
@ComponentScan("toggle.spring")
@EnableTransactionManagement
public class AppInit {

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/system");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.enable_lazy_load_no_trans","true");
        return properties;
    }


    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.addAnnotatedClasses(User.class);
        sessionBuilder.addAnnotatedClasses(Disc.class);
        return sessionBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Autowired
    @Bean(name = "userDAO")
    public DAO<User> getUserDao(SessionFactory sessionFactory) {
        return new UserDao(sessionFactory);
    }

    @Autowired
    @Bean(name = "discDAO")
    public DAO<Disc> getDiscDao(SessionFactory sessionFactory) {
        return new DiscDao(sessionFactory);
    }

}
