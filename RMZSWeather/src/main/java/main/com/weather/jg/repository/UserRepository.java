package main.com.weather.jg.repository;

import main.com.weather.jg.model.User;
import main.com.weather.jg.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepository extends CrudRepository<User, Integer> {

    public void save(User user) {
        super.save(user);
    }

    public void update(User entity) {
        super.update(entity);
    }

    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public List<User> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> users = session.createQuery("from User ").list();
            transaction.commit();
            session.close();
            return users;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public User findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User users = session.get(User.class, id);
            transaction.commit();
            session.close();
            return users;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByLoginAndPassword(String login, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = (User) session.createQuery("from User u where u.login = :login and u.password = :password")
                    .setParameter("login", login)
                    .setParameter("password",password).uniqueResult();
            return user;
        }catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
