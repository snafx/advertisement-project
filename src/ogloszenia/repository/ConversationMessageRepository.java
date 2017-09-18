package ogloszenia.repository;

import ogloszenia.model.ConversationMessage;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConversationMessageRepository {
    final static Logger logger = Logger.getLogger(ConversationMessageRepository.class);

    public static Integer addNewConversationMessage(ConversationMessage conversationMessage, int userId) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            conversationMessage.setOwner(UserRepository.findById(userId).get());
            session.persist(conversationMessage);
            session.getTransaction().commit();

            return conversationMessage.getId();
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    //dodanie pojedynczej wiadomosci do bazy
    public static Optional<ConversationMessage> persist(ConversationMessage conversationMessage, int userId) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            conversationMessage.setOwner(UserRepository.findById(userId).get());
            session.saveOrUpdate(conversationMessage);
            session.getTransaction().commit();

            return Optional.ofNullable(conversationMessage);
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return Optional.empty();
        } finally {
            session.close();
        }
    }

    public static List<ConversationMessage> findByConversationId(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT e FROM ConversationMessage e WHERE e.conversation.id=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
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
