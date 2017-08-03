package ogloszenia.repository;

import ogloszenia.model.Conversation;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConversationRepository {

    public static Optional<Conversation> findById(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            String hql = "SELECT e FROM Conversation e WHERE e.id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return Optional.ofNullable((Conversation) query.getSingleResult());
            //Optional opakowuje nam obiekt ktory moze byc nullem (informuje nas ze moze byc nullem, programisto sprawdz to)
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    //znajdujemy po id sendera lub receivera, zakladamy ze user mogl byc nadawca lub odbiorca konwersacji
    public static List<Conversation> findByUserId(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT e FROM Conversation e WHERE e.conversationSender.id=:id OR e.conversationReceiver.id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }

    //dodanie konwersacji
    public static Integer persist(Conversation conversation) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.persist(conversation);
            session.getTransaction().commit();
            return conversation.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }
    }
}
