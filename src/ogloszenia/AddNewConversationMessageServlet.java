package ogloszenia;

import ogloszenia.model.Conversation;
import ogloszenia.model.ConversationMessage;
import ogloszenia.model.User;
import ogloszenia.repository.ConversationMessageRepository;
import ogloszenia.repository.ConversationRepository;
import ogloszenia.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * servlet, ktory zapisuje nowa wiadomosc do istniejacej juz konwersacji
 */

public class AddNewConversationMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer conversationId = 0;
        String text = "";
        User messageSender = null;
        Optional<User> messageSendeOptional = UserRepository.findByEmail("romek@gmail.com");
        if (messageSendeOptional.isPresent()) {
            messageSender = messageSendeOptional.get();
        } else {
            messageSender = new User("Romek", "11111", "romek@gmail.com", "Poznan");
        }

        try {
            conversationId = Integer.valueOf(req.getParameter("conversationId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        text = req.getParameter("message");

        // pobieramy konwrsacje
        Optional<Conversation> conversationTmp = ConversationRepository.findById(conversationId);
        if (conversationTmp.isPresent()) {
            Conversation conversation = conversationTmp.get();

            ConversationMessage newMessage = new ConversationMessage(text, conversation, messageSender);

            ConversationMessageRepository.persist(newMessage);

            resp.getWriter().write("wiadomosc zostala wyslana");

        }

    }
}
