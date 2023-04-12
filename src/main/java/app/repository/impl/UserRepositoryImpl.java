package app.repository.impl;

import app.config.HibernateConfig;
import app.model.User;
import app.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public User save(User entity) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public User update(User entity) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.saveOrUpdate(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public User findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<User> query = session.getNamedQuery("findByUserId");
        query.setParameter("id", id);

        User entity;
        try {
            entity = query.getSingleResult();
        } catch (NoResultException e) {
            entity = null;
        }

        transaction.commit();
        session.close();

        return entity;
    }

    @Override
    public List<User> findAll() {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<User> query = session.getNamedQuery("findAllUsers");
        List<User> entities = query.getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    @Override
    public boolean delete(User entity) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }

    @Override
    public User findByUsername(String username) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<User> query = session.getNamedQuery("findByUsername");
        query.setParameter("username", username);

        User entity;
        try {
            entity = query.getSingleResult();
        } catch (NoResultException e) {
            entity = null;
        }

        transaction.commit();
        session.close();

        return entity;
    }
}
