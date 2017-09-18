package ogloszenia;

import ogloszenia.model.Advertisement;
import ogloszenia.model.CATEGORY;
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

        byte[] img = null;

        Integer userId = 0;
        userId = (Integer) req.getSession().getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect("login.html");

        } else {
            String title = "";
            BigDecimal price = BigDecimal.ZERO;
            String description;
            String location;
            CATEGORY category = null;

            try {
                title = req.getParameter("title");
                price = new BigDecimal(req.getParameter("price"));
                category = CATEGORY.valueOf(req.getParameter("category"));
                img = req.getParameter("img").getBytes();
            } catch (Exception e) {
                e.printStackTrace();
            }

            description = req.getParameter("description");
            location = req.getParameter("location");

            if (isNotValid(title, price, description, location)) {
                PrintWriter pw = resp.getWriter();
                pw.write("blad");  //TODO mozna by zrobic ładniejsze przekierowanie zamiast komunikatu
            }

            Advertisement ad = new Advertisement(title, price, description, location, category, img);
            AdvertisementRepository.persist(ad, userId);
            resp.sendRedirect("products.jsp?category=" + ad.getCategory());
        }
    }

    private boolean isNotValid(String title, BigDecimal price, String description, String location) {
        return title.isEmpty() || description.isEmpty() || location.isEmpty() || price.compareTo(BigDecimal.ZERO) == -1;
    }
}
