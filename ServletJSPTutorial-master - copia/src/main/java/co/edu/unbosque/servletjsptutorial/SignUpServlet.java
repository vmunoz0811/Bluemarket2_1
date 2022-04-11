package co.edu.unbosque.servletjsptutorial;

import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignUpServlet extends HttpServlet {

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String fcoins = request.getParameter("fcoins");

        try {
            new UserService().createUser(username, name, lastname, mail, password, fcoins,getServletContext().getRealPath("") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("./index2.html");
    }

    public void destroy() {}
}
