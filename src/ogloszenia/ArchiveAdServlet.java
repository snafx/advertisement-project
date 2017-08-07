package ogloszenia;

import ogloszenia.model.Advertisement;
import ogloszenia.repository.AdvertisementRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class ArchiveAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = null;
        //try/catch jest gdyz czysto teoretycznie mogly ktos wyslac Stringa
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) { //jesli zamiast integera przyjdzie np string
            e.printStackTrace();
        }


        if (id != null) {
            //optional, bo np mogl przyjsc takie id, ktorego nie ma w bazie
            //wtedy optional ladnie to opakuje w nulla bez wywalania bledu
            Optional<Advertisement> advertisement = AdvertisementRepository.findById(id);

            advertisement.ifPresent(a -> disableAd(a));

            PrintWriter writer = resp.getWriter();
            writer.write("ok!");
        }

    }

    private void disableAd(Advertisement a) {
        a.setIsActive(false);
        AdvertisementRepository.merge(a);
    }
}
