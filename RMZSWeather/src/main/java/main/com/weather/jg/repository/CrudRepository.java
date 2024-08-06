package main.com.weather.jg.repository;

import jakarta.persistence.Query;
import main.com.weather.jg.model.User;
import main.com.weather.jg.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;

import java.util.List;

public abstract class CrudRepository <T,Integer>  {

    protected void save(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    protected void update(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(entity);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void delete(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    abstract List<T> findAll();
    abstract T findById(Integer id);


}
