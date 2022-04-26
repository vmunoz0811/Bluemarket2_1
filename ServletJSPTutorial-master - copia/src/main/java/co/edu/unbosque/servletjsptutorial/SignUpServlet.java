package co.edu.unbosque.servletjsptutorial;

import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet(name = "signup", value = "/signup")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class SignUpServlet extends HttpServlet {
    private String UPLOAD_DIRECTORY = "uploads";
    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String extension="";
        String fileName="";
        String username = request.getParameter("username");
        String tittle= username;
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String mail = request.getParameter("mail");
        String fcoins = request.getParameter("fcoins");

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        try {
            // Getting each part from the request
            for (Part part : request.getParts()) {
                // Storing the file using the same name_
                fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            new UserService().createUser(username, name, lastname, mail, password, fcoins,fileName,getServletContext().getRealPath("") + File.separator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("./index.html");
    }

    public void destroy() {}
}
