package ogloszenia;

import ogloszenia.model.Advertisement;
import ogloszenia.model.Conversation;
import ogloszenia.model.ConversationMessage;
import ogloszenia.repository.AdvertisementRepository;
import ogloszenia.repository.ConversationMessageRepository;
import ogloszenia.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Servlet implementation class AddNewMessageServlet
 */
public class AddNewConversationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Integer userId = 0;
        userId = (Integer) req.getSession().getAttribute("userId");

        //tymczasowi uzytkownik rozpoczynajacy nowa konwersacje
        //User messageSender = new User("Romek", "1111", "romek@gmail.com", "Poznan");

        String text;
        Integer idAdvertisement = 0;  //ogloszenie, do ktorego przypisana bedzie wiadomosc

        //pobranie zmiennych z formularza
        text = req.getParameter("message");
        try {  //ktos moze tu przyslac stringa, albo liczbe mniejsza od zera itp...
            idAdvertisement = Integer.valueOf(req.getParameter("idAdvertisement"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        //na podstawie id ogloszenia pobieram caly obiekt i jego wrzucam jako pole do nowego obiektu conversation
        Optional<Advertisement> ad = AdvertisementRepository.findById(idAdvertisement);

        if (text.isEmpty() || !ad.isPresent()) {
            PrintWriter writer = res.getWriter();
            writer.write("Error!!");
        } else {
            //tworzenowa konwersacje
            Conversation conversation = new Conversation();

            //wiem, ze obiekt Ogloszenie istnieje (patrz isPresent w if-ie), wiec moge pobrac obiekt w nim zawarty
            Advertisement newAdvertisement = ad.get();

            //ustawiam pola nowego obiektu konwersacji
            conversation.setMessageDate(LocalDate.now());
            conversation.setAdvertisementId(newAdvertisement);
            conversation.setConversationSender(UserRepository.findById(userId).get());  //wysylajacym jest zawsze zalogowanu user, jego dane wezniemy z sesji
            conversation.setConversationReceiver(ad.get().getOwner()); //odiorca bedzie wlasciciel ogloszenia, na rzecz ktorego rozpoczynamy konwersacje

            //na koncu towrze nowa, pierwsza wiadomosc w tej konwesacji
            //UWAGA - nie musze zapisywac do bazy powyzej utworzonego obiektu conveersation
            //bo w relacji miedzy tymi encjami jest wpisana kaskada, wiec przy zapisuwaniu nowej conversationMessage
            //najpierw zostanie wpisany do bazy obiekt conversation
            ConversationMessage conversationMessage = new ConversationMessage(text, conversation);

            Optional<ConversationMessage> conversationMessageOptional =
                    ConversationMessageRepository.persist(conversationMessage, userId);

            if (conversationMessageOptional.isPresent())
                res.sendRedirect("czat.jsp?conversationId=" + conversationMessageOptional.get().getConversation().getId());

        }
    }
}
