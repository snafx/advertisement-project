package ogloszenia.repository;

import ogloszenia.model.User;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Optional;

public class UserRepository {

    public static Optional<User> findByEmail(String email) {
        Session session = null;
        try{
            session = HibernateUtil.openSession();
            String hql = "SELECT e FROM User e WHERE e.email= :email";
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
        try{
            session = HibernateUtil.openSession();
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
}
