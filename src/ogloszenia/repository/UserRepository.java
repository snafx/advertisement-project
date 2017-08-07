package ogloszenia.repository;

import ogloszenia.model.User;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

import static ogloszenia.repository.ConversationMessageRepository.logger;

public class UserRepository {
    final static Logger logger = Logger.getLogger(ConversationMessageRepository.class);

    public static Optional<User> findByMail(String email) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM User e WHERE e.email = :email";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            return Optional.ofNullable((User) query.getSingleResult());
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    public static void persist(User user) {

        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static Optional<User> findById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT  e FROM User e WHERE e.id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            return  Optional.ofNullable( (User)query.getSingleResult());
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    public static Optional<User> findByMailAndPassword(String email, String password) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT  e FROM User e WHERE e.email=:email and e.password = :password";
            Query query = session.createQuery(hql);
            query.setParameter("email",email);
            query.setParameter("password",password);
            return  Optional.ofNullable( (User)query.getSingleResult());
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
