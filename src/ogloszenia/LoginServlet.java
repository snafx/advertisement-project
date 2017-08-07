package ogloszenia;

import ogloszenia.model.User;
import ogloszenia.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email;
        String password;

        email = req.getParameter("email");
        password = req.getParameter("password");

        Optional<User> user = UserRepository.findByMailAndPassword(email, password);

        if (user.isPresent()) {
            req.getSession().setAttribute("userId", user.get().getId());
            resp.sendRedirect("index.jsp");
        } else {
            resp.getWriter().write("Zly login lub haslo.");
        }

    }
}
