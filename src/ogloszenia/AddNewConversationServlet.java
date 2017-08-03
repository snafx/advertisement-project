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

/**
 * servlet, ktory tworzy nową konwersacje i jednoczesnie dodaje do niej pierwsza wiadomosc
 */

public class AddNewConversationServlet extends HttpServlet {
    public static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text;
        Integer idAdvertisement = 0; //ogloszenie, do ktorego przypisana bedzie wiadomosc

        //tymczasowi uzytkownik rozpoczynajacy nowa konwersacje
        User messageSender = new User("Kazio", "pass", "kazio@gmail.com", "Poznan");

        //pobranie zmiennych z formularza
        text = req.getParameter("message");
        try { //ktos moze tu przyslac stringa, albo liczbe mniejsza od zera itp...
            idAdvertisement = Integer.valueOf(req.getParameter("idAdvertisement"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        //sprawdzamy czy ogloszenie istnieje
        //na podstawie id ogloszenia pobieram caly obiekt i jego wrzucam jako pole do nowego obiektu conversation
        Optional<Advertisement> ad = AdvertisementRepository.findById(idAdvertisement);
        if (text.isEmpty() || !ad.isPresent()) {
            PrintWriter pw = resp.getWriter();
            pw.write("Error!");
        } else {
            //"tworzymy" nasza konwersacje
            Conversation conversation = new Conversation();
            conversation.setMessageDate(LocalDate.now());
            conversation.setAdvertisementId(ad.get());
            conversation.setConversationSender(messageSender); //wysylajacym jest zawsze zalogowanu user, jego dane wezniemy z sesji
            conversation.setConversationReceiver(ad.get().getOwner()); //odiorca bedzie wlasciciel ogloszenia, na rzecz ktorego rozpoczynamy konwersacje

            //na koncu towrze nowa, pierwsza wiadomosc w tej konwesacji
            //UWAGA - nie musze zapisywac do bazy powyzej utworzonego obiektu conveersation
            //bo w relacji miedzy tymi encjami jest wpisana kaskada, wiec przy zapisuwaniu nowej conversationMessage
            //najpierw zostanie wpisany do bazy obiekt conversation
            ConversationMessage conversationMessage = new ConversationMessage(text, conversation, messageSender);

            ConversationMessageRepository.persist(conversationMessage);
            resp.getWriter().write("wiadomosc zostala wyslana");
        }
    }
}