package co.edu.unbosque.servletjsptutorial;

import co.edu.unbosque.servletjsptutorial.dtos.User;
import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
       // request.setAttribute("username", username);
        String password = request.getParameter("password");
       /* String name = request.getParameter("name");
        request.setAttribute("name", name);
        String lastname = request.getParameter("lastname");
        request.setAttribute("lastname", lastname);
        String mail = request.getParameter("mail");
        request.setAttribute("mail", mail);
        String fcoins = request.getParameter("fcoins");
        request.setAttribute("fcoins", fcoins);
*/
        List<User> users = new UserService().getUsers();

        User userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {

            Cookie cookie = new Cookie("name", userFounded.getName());
            cookie.setMaxAge(20);
            response.addCookie(cookie);

            Cookie cookie1 = new Cookie("Lastname", userFounded.getLastname());
            cookie1.setMaxAge(20);
            response.addCookie(cookie1);


            RequestDispatcher dispatcher = request.getRequestDispatcher("./Profile.jsp");
            dispatcher.forward(request, response);

        } else {
            response.sendRedirect("./401.html");
        }
    }
}
