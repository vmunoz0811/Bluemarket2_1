package co.edu.unbosque.servletjsptutorial;

import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
public class SignUpServlet extends HttpServlet {

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            new UserService().createUser(username, password, getServletContext().getRealPath("../resources/users.csv") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("./index2.html");
    }

    public void destroy() {}
}
