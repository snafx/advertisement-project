package ogloszenia;

import ogloszenia.model.User;
import ogloszenia.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email;
        String password;

        email = request.getParameter("email");
        password = request.getParameter("password");

        Optional<User> user = UserRepository.findByEmailAndPassword(email, password);

        if (user.isPresent()) {
            request.getSession().setAttribute("userId", user.get().getId());
            response.sendRedirect("index.jsp");
        } else {
            response.getWriter().write("Zly login lub haslo");
        }
    }
}
