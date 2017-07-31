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
            //Optional opakowuje nam obiekt ktory moze byc nullem (informuje nas ze moze byc nullem, programisto sprawdz to)
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

    public static boolean merge(Advertisement advertisement) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.merge(advertisement);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean delete(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            Optional<Advertisement> advertisement = findById(id);
            if (advertisement.isPresent()) {
                session.getTransaction().begin();
                advertisement.get().setIsActive(false);
                session.merge(advertisement.get());
                session.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
