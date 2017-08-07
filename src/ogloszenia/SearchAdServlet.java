package ogloszenia;

import ogloszenia.model.Advertisement;
import ogloszenia.repository.AdvertisementRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchAdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String location;
        String phrase;

        location = req.getParameter("location");
        phrase = req.getParameter("phrase");

        //user musi podac nazwie, nie musi podac lokaliacji
        if (phrase.isEmpty()) {
            resp.getWriter().write("Prosze wpisac zapytanie.");
        } else if (location.isEmpty()) {
            //szukamy po samej nazwie
            List<Advertisement> searchedAds = AdvertisementRepository.findByPhrase(phrase);
        } else {
            //szukamy po kolalizacji i po nazwie
            List<Advertisement> searchedAds = AdvertisementRepository.findByPhraseAndLocation(phrase, location);
        }
    }
}
