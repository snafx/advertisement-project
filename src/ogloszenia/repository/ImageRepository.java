package ogloszenia.repository;

import ogloszenia.model.Advertisement;
import ogloszenia.model.Image;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ImageRepository {

    public static List<Image> findByAdvertisementId(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "select e from Image where e.advertisement.id = :id";
            org.hibernate.query.Query query = session.createQuery(hql, Image.class);
            query.setParameter("id", id);
            return query.getResultList();

        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    public static void persist(Image image) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.persist(image);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static boolean delete(Integer id) {
        Session session = null;
        try {
            Optional<Image> image = findById(id);
            if (image.isPresent()) {

                session = HibernateUtil.openSession();
                session.getTransaction().begin();
                session.remove(image.get());
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

    public static Optional<Image> findById(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT  e FROM Image e WHERE e.id=:id";
            org.hibernate.query.Query query = session.createQuery(hql);
            query.setParameter("id",id);
            return Optional.ofNullable((Image) query.getSingleResult());
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }
}
