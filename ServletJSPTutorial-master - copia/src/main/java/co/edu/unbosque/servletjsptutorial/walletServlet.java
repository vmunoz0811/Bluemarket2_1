package co.edu.unbosque.servletjsptutorial;

import co.edu.unbosque.servletjsptutorial.dtos.User;
import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "walletServlet", value = "/walletServlet")
public class walletServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Object name =request.getParameter("name");

        List<User> users = new UserService().getUsers();

        User userFounded = users.stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst()
                .orElse(null);

        if (userFounded != null) {

            Cookie cookie = new Cookie("Fconis", userFounded.getFcoins());
            cookie.setMaxAge(20);
            response.addCookie(cookie);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./Wallet.html");
            dispatcher.forward(request, response);

        } else {
            response.sendRedirect("./401.html");
        }
    }

    public void destroy() {}
    }

