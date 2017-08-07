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
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer userId = 0;
        userId = (Integer) req.getSession().getAttribute("userId");

        Integer conversationId = 0;
        String text = "";

        //tymczasowi uzytkownik bedacy autorem wiadomosci
//        User messageSender = null;
//        Optional<User> mesageSenderOptional = UserRepository.findByMail("romek@gmail.com");
//        if (mesageSenderOptional.isPresent()) {
//            messageSender = mesageSenderOptional.get();
//        } else {
//            messageSender = new User("Romek", "11111", "romek@gmail.com", "Poznan");
//        }

        //pobranie zmiennych z formularza
        try {  //czy to jest potrzebne? conversationId ma chyba przychodzic z hidden inputa
            conversationId = Integer.valueOf(req.getParameter("conversationId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        text = req.getParameter("message");

        //proba pobrania konwersacji
        Optional<Conversation> conversationTmp = ConversationRepository.findById(conversationId);

        //jesli obiekt istnieje (czyli ze z formularza przyszlo poprawne id, oraz takie, ktore istnieje w bazie) to moge dzialac dalej
        if (conversationTmp.isPresent()) {
            Conversation conversation = conversationTmp.get();

            ConversationMessage newMessage = new ConversationMessage(conversation, text);

            Optional<ConversationMessage> conversationMessageOptional = ConversationMessageRepository.persist(newMessage, userId);

            if (conversationMessageOptional.isPresent()) {
                resp.sendRedirect("czat.jsp?conversationId=" + conversationMessageOptional.get().getConversation().getId());
            }

            //resp.getWriter().write("wiadomosc zostala wyslana");


        }
    }
}

