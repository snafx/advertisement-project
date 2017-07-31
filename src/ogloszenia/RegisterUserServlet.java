package ogloszenia;

import ogloszenia.model.User;
import ogloszenia.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nick;
        String password;
        String email;
        String location;

        nick = req.getParameter("nick");
        password = req.getParameter("password");
        email = req.getParameter("email");
        location = req.getParameter("location");

        if (isNotValid(nick, password, email, location)) {
            PrintWriter pw = resp.getWriter();
            pw.write("missing data");
        } else {
            User user = new User(nick, password, email, location);
            UserRepository.persist(user);
            PrintWriter pw = resp.getWriter();
            pw.write("user created");
        }
    }

    private boolean isNotValid(String nick, String password, String email, String location) {
        return nick.isEmpty() || password.isEmpty() || email.isEmpty() || location.isEmpty();
    }
}
