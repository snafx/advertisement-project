package ogloszenia.repository;

import ogloszenia.model.ConversationMessage;
import ogloszeniar.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class ConversationMessageRepository {
    public static Integer persist(ConversationMessage conversationMessage) {
        Session session = null;
        try {
            session = HibernateUtil.openSession().getSession();
            session.getTransaction().begin();
            session.persist(conversationMessage);
            session.getTransaction().commit();
            return conversationMessage.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
            return 0;
        } finally {
            session.close();
        }
    }
}
