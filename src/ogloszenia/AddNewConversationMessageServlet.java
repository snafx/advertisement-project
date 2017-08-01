package ogloszenia;

import ogloszenia.model.Conversation;
import ogloszenia.model.ConversationMessage;
import ogloszenia.model.User;
import ogloszenia.repository.ConversationMessageRepository;
import ogloszenia.repository.ConversationRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AddNewConversationMessageServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = "";
        Integer conversationId = 0;
        User messageSender = new User("Kazio", "pass", "kazio@gmail.com", "Poznan");

        try {
            conversationId = Integer.valueOf(req.getParameter("conversationId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        text = req.getParameter("message");
        //pobieramy konwersacje
        Optional<Conversation> conversationTmp = ConversationRepository.findById(conversationId);
        if (conversationTmp.isPresent()) {
            Conversation conversation = conversationTmp.get();

            ConversationMessage newMessage = new ConversationMessage(text, conversation, messageSender);
            ConversationMessageRepository.persist(newMessage);
            resp.getWriter().write("Wiadomość wysłana!");
        }
    }
}
