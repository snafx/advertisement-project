package ogloszenia;

import ogloszenia.model.Advertisement;
import ogloszenia.model.Conversation;
import ogloszenia.model.ConversationMessage;
import ogloszenia.model.User;
import ogloszenia.repository.AdvertisementRepository;
import ogloszenia.repository.ConversationMessageRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

public class AddNewConversationServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text;
        Integer idAdvertisement = 0;
        User messageSender = new User("Kazio", "pass", "kazio@gmail.com", "Poznan");

        text = req.getParameter("message");
        try {
            idAdvertisement = Integer.valueOf(req.getParameter("idAdvertisement"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        //sprawdzamy czy ogloszenie istnieje
        Optional<Advertisement> ad = AdvertisementRepository.findById(idAdvertisement);
        if (text.isEmpty() || !ad.isPresent()) {
            PrintWriter pw = resp.getWriter();
            pw.write("Error!");
        } else {
            //"tworzymy" nasza konwersacje
            Conversation conversation = new Conversation();
            conversation.setMessageDate(LocalDate.now());
            conversation.setAdvertisementId(ad.get());
            conversation.setConversationSender(messageSender);
            conversation.setConversationReceiver(ad.get().getOwner());
            ConversationMessage conversationMessage = new ConversationMessage(text, conversation, messageSender);

            ConversationMessageRepository.persist(conversationMessage);
            resp.getWriter().write("wiadomosc zostala wyslana");
        }
    }
}