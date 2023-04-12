package app.repository.impl;

import app.config.HibernateConfig;
import app.model.Challenge;
import app.repository.ChallengeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ChallengeRepositoryImpl implements ChallengeRepository {

    @Override
    public Challenge save(Challenge entity) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id = (Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public Challenge update(Challenge entity) {
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
    public Challenge findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Challenge> query = session.getNamedQuery("findByChallengeId");
        query.setParameter("id", id);

        Challenge entity;
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
    public List<Challenge> findAll() {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Challenge> query = session.getNamedQuery("findAllChallenges");
        List<Challenge> entities = query.getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    @Override
    public boolean delete(Challenge entity) {
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
    public Challenge getByName(String name) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<Challenge> query = session.getNamedQuery("findByChallengeName");
        query.setParameter("name", name);

        Challenge entity;
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
