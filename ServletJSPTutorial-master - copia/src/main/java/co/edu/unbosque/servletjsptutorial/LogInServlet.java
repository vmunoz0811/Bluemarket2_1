package co.edu.unbosque.servletjsptutorial;

import java.io.*;
import java.util.List;

import co.edu.unbosque.servletjsptutorial.dtos.User;
import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LogInServlet extends HttpServlet {

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        List<User> users = new UserService().getUsers();

        User userFounded = users.stream()
                .filter(user -> username.equals(user.getUsername()) && password.equals(user.getPassword()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {
            request.setAttribute("name", userFounded.getName());


            Cookie cookie = new Cookie("name", userFounded.getName());
            cookie.setMaxAge(20);
            response.addCookie(cookie);



            RequestDispatcher dispatcher = request.getRequestDispatcher("./ArtistProfile.jsp");
            dispatcher.forward(request, response);

        } else {
            response.sendRedirect("./401.html");
        }
    }

    public void destroy() {}
}