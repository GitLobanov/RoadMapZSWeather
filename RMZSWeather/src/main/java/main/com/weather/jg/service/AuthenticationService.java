package main.com.weather.jg.service;


import main.com.weather.jg.model.User;
import main.com.weather.jg.repository.UserRepository;
import main.com.weather.jg.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.awt.print.Book;
import java.util.Iterator;
import java.util.List;

public class AuthenticationService {

    UserRepository userRepository;

    public AuthenticationService() {
        userRepository = new UserRepository();
    }

    public User login(String login, String password) {
        return userRepository.findByLoginAndPassword(login,password);
    }


    public void logout() {

    }


    public void register(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            User user = new User();
            user.setLogin(username);
            user.setPassword(password);
            session.persist(user);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



}
