package ogloszenia.repository;

import ogloszenia.model.Conversation;
import ogloszenia.model.ConversationMessage;
import ogloszenia.model.User;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import org.apache.log4j.Logger;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

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

    public static Integer persist(ConversationMessage conversationMessage) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            if (!session.contains(conversationMessage.getConversation())) {
                conversationMessage.setConversation((Conversation) session.merge(conversationMessage.getConversation()));
            }
            if (!session.contains(conversationMessage.getOwner())) {
                conversationMessage.setOwner((User) session.merge(conversationMessage.getOwner()));
            }
            session.persist(conversationMessage);
            session.getTransaction().commit();
            logger.info("ddddddddd");
            return conversationMessage.getId();
        } catch (Exception ex) {
            logger.error(ex);
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }

    }

    public static List<ConversationMessage> findByConversationId(Integer id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT  e FROM ConversationMessage e WHERE e.conversation.id=:id";
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
