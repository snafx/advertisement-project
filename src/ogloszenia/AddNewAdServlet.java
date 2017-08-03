package ogloszenia;

import ogloszenia.model.Advertisement;
import ogloszenia.model.User;
import ogloszenia.repository.AdvertisementRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class AddNewAdServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title;
        BigDecimal price = BigDecimal.ZERO;
        String description;
        String location;

        title = req.getParameter("title");
        try {
            price = new BigDecimal(req.getParameter("price"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        description = req.getParameter("description");
        location = req.getParameter("location");

        if (isNotValid(title, price, description, location)) {
            PrintWriter pw = resp.getWriter();
            pw.write("blad");
        }

        User owner = new User();
        owner.setCityName("Poznan");
        owner.setEmail("test@gmail.com");
        owner.setNick("test");
        owner.setPassword("admin");

        Advertisement ad = new Advertisement(title, price, description, location, owner);
        AdvertisementRepository.persist(ad);

        resp.sendRedirect("products.html");
//        PrintWriter pw = resp.getWriter();
//        pw.write("ok");

    }

    private boolean isNotValid(String title, BigDecimal price, String description, String location) {
        return title.isEmpty() || description.isEmpty() || location.isEmpty() || price.compareTo(BigDecimal.ZERO) == -1;
    }
}
