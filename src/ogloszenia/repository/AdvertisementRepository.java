package ogloszenia.repository;

import ogloszenia.model.Advertisement;
import ogloszenia.model.CATEGORY;
import ogloszenia.model.User;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AdvertisementRepository {

    final static Logger logger = Logger.getLogger(ConversationMessageRepository.class);

    public static Optional<Advertisement> findById(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM Advertisement e WHERE e.id = :id";
            Query<Advertisement> query = session.createQuery(hql, Advertisement.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
            //Optional opakowuje nam obiekt ktory moze byc nullem (informuje nas ze moze byc nullem, programisto sprawdz to)
        } catch (Exception ex) {
            logger.error(ex);
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
            String hql = "SELECT e FROM Advertisement e WHERE e.category = :category";
            Query query = session.createQuery(hql);
            query.setParameter("category", category);
            return query.getResultList();
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    //dodanie ogloszenia
    public static Integer persist(Advertisement advertisement, Integer userId) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            advertisement.setOwner( (User) session.merge(UserRepository.findById(userId).get()));
            //
            //zadanego usera dopisuje jako ownera ogloszenia
            session.persist(advertisement);
            session.getTransaction().commit();
            return advertisement.getId();
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    //update ogloszenia - moja wersja definiowania sesji i transakcji
    public static boolean merge(Advertisement advertisement) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();
            transaction = session.beginTransaction();
            session.merge(advertisement);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            logger.error(ex);
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    //usuwanie ogloszena, czyli przeniesienie do archiwum
    public static boolean delete(Integer id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.openSession();

            Optional<Advertisement> advertisement = findById(id);
            //sprawdzam, czy optional nie jest nullem
            if (advertisement.isPresent()) {
                transaction = session.beginTransaction();
                Advertisement advertisement1 = advertisement.get();
                advertisement1.setIsActive(false); //ustawiam ogloszenie jako nieaktywne
                session.merge(advertisement1);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception ex) {
            logger.error(ex);
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    //wyszukiwanie po frazie
    public static List<Advertisement> findByPhrase(String phrase) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM Advertisement e WHERE upper(e.title) like :param or upper(e.text) like :param order by e.id DESC";
            Query query = session.createQuery(hql, Advertisement.class);
            query.setParameter("param", "%" + phrase.toUpperCase() + "%"); //w zapytaniu tez robie wielkie litery
            return query.getResultList();
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    //wyszukiwanie po frazie i lokalizacji
    public static List<Advertisement> findByPhraseAndLocation(String phrase, String location) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM Advertisement e WHERE " +
                    "(upper(e.title) like :param and upper(e.cityName) like :location ) " +
                    "or " +
                    "(upper(e.text) like :param and upper(e.cityName) like :location) " +
                    "order by e.id DESC";
            Query query = session.createQuery(hql, Advertisement.class);
            query.setParameter("param", "%" + phrase.toUpperCase() + "%"); //w zapytaniu tez robie wielkie litery
            query.setParameter("location", "%" + location.toUpperCase() + "%");
            return query.getResultList();
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
}