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

/**
 * servlet, ktory zapisuje nowa wiadomosc do istniejacej juz konwersacji
 */

public class AddNewConversationMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = "";
        Integer conversationId = 0;


        //tymczasowi uzytkownik bedacy autorem wiadomosci
        User messageSender = new User("Kazio", "pass", "kazio@gmail.com", "Poznan");

        //pobranie zmiennych z formularza
        try {
            conversationId = Integer.valueOf(req.getParameter("conversationId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        text = req.getParameter("message");
        //pobieramy konwersacje (proba)
        Optional<Conversation> conversationTmp = ConversationRepository.findById(conversationId);

        //jesli obiekt istnieje (czyli ze z formularza przyszlo poprawne id, oraz takie, ktore istnieje w bazie) to moge dzialac dalej
        if (conversationTmp.isPresent()) {
            Conversation conversation = conversationTmp.get();

            ConversationMessage newMessage = new ConversationMessage(text, conversation, messageSender);

            ConversationMessageRepository.persist(newMessage);

            resp.getWriter().write("Wiadomość wysłana!");
        }
    }
}
