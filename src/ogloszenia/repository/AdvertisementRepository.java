package ogloszenia.repository;

import ogloszenia.model.Advertisement;
import ogloszenia.model.CATEGORY;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AdvertisementRepository {

    public static Optional<Advertisement> findById(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM Advertisement e WHERE e.id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return Optional.ofNullable((Advertisement) query.getSingleResult());
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    public static List<Advertisement> findByCategory(CATEGORY category) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM Advertisement e WHERE e.category=:category";
            Query query = session.createQuery(hql);
            query.setParameter("category", category);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public static Integer persist(Advertisement advertisement) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.persist(advertisement);
            session.getTransaction().commit();
            return advertisement.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    public static Integer merge(Advertisement advertisement) {
        Session session = null;
        try {

            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.persist(advertisement);
            session.getTransaction().commit();
            return advertisement.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }
    }
}
