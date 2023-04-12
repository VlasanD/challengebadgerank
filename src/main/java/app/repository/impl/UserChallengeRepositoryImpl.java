package app.repository.impl;

import app.config.HibernateConfig;
import app.model.UserChallengeRecord;
import app.repository.UserChallengeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserChallengeRepositoryImpl implements UserChallengeRepository {
    @Override
    public List<UserChallengeRecord> getCompleteRecord(Integer id) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<UserChallengeRecord> query = session.getNamedQuery("findByRecordUserId");
        query.setParameter("id",id);
        List<UserChallengeRecord> entities = query.getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    @Override
    public UserChallengeRecord getRecordByChallengeAndUser(Integer userId, Integer challengeId) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<UserChallengeRecord> query = session.getNamedQuery("findByRecordUserIdAndChallengeId");
        query.setParameter("userId", userId);
        query.setParameter("challengeId",challengeId);

        UserChallengeRecord entity;
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
    public UserChallengeRecord save(UserChallengeRecord entity) {
        SessionFactory sessionFactory= HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Integer id=(Integer) session.save(entity);

        transaction.commit();
        session.close();

        return findById(id);
    }

    @Override
    public UserChallengeRecord update(UserChallengeRecord entity) {
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
    public UserChallengeRecord findById(Integer id) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<UserChallengeRecord> query = session.getNamedQuery("findRecordById");
        query.setParameter("id", id);

        UserChallengeRecord entity;
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
    public List<UserChallengeRecord> findAll() {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        TypedQuery<UserChallengeRecord> query = session.getNamedQuery("findAllRecords");
        List<UserChallengeRecord> entities = query.getResultList();

        transaction.commit();
        session.close();

        return entities;
    }

    @Override
    public boolean delete(UserChallengeRecord entity) {
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
