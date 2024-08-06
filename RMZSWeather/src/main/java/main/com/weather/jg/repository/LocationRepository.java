package main.com.weather.jg.repository;

import main.com.weather.jg.model.Location;
import main.com.weather.jg.model.User;
import main.com.weather.jg.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LocationRepository extends CrudRepository<Location, Integer> {

    public void save(Location location) {
        super.save(location);
    }

    public void update(Location location) {
        super.update(location);
    }

    public void delete(Location location) {
        super.delete(location);
    }

    @Override
    public List<Location> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Location> locations = session.createQuery("from Location").list();
            transaction.commit();
            session.close();
            return locations;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Location findById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Location users = session.get(Location.class, id);
            transaction.commit();
            session.close();
            return users;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public Location findByNameAndUser(String name, User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Location location = (Location) session.createQuery("from Location l where l.name = :name and l.userFK = :user")
                    .setParameter("name", name)
                    .setParameter("user",user).uniqueResult();
            return location;
        }catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

}
