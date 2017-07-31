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
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (id != null) {
            Optional<Advertisement> advertisement = AdvertisementRepository.findById(id); //bo ktos moze wpisac ID ktorego nie ma, dlatego Optional

            advertisement.ifPresent(a -> disableAd(a));
        }
        PrintWriter pw = resp.getWriter();
        pw.write("Ad archived!");

    }

    private void disableAd(Advertisement a) {
        a.setIsActive(false);
        AdvertisementRepository.merge(a);
    }
}
