package app.repository.impl;

import app.config.HibernateConfig;
import app.model.Badge;
import app.repository.BadgeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class BadgeRepositoryImpl implements BadgeRepository {

    @Override
    public Badge save(Badge entity) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Badge update(Badge entity) {
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
    public Badge findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Badge> query = session.getNamedQuery("findByBadgeId");
        query.setParameter("id", id);

        Badge entity;
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
    public List<Badge> findAll() {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Badge> query = session.getNamedQuery("findAllBadges");
        List<Badge> entities = query.getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    @Override
    public boolean delete(Badge entity) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = entity.getId();
        session.delete(entity);

        transaction.commit();
        session.close();

        return findById(id) == null;
    }
}

