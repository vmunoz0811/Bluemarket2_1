package co.edu.unbosque.servletjsptutorial;

import co.edu.unbosque.servletjsptutorial.dtos.User;
import co.edu.unbosque.servletjsptutorial.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "uploadNftServlet", value = "/uploadNftServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

public class uploadNftServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        RequestDispatcher dispatcher =null;
        UserService UService = new UserService();

        String extension="";
        String fileName="";
        String tittle = request.getParameter("titulo");
        String price = request.getParameter("precio");
        //BUSCAR
        String emailAuthor = request.getParameter("author");

        UService = new UserService();
        //UService.setRuta(getServletContext().getRealPath("").replace("ServletJSPTutorial-1.0-SNAPSHOT","")+ "classes"+File.separator+"Users.csv");

        List<User> users = new UserService().getUsers();
        User userFounded = users.stream().filter(user -> emailAuthor.equals(user.getUsername()))
                .findFirst().orElse(null);
        String author = userFounded.getName() +" "+ userFounded.getLastname();
        String uploadPath = getServletContext().getRealPath("")+ File.separator+"NFTS";

        request.setAttribute("name",userFounded.getName());
        //request.setAttribute("role",userFounded.getRole());
        request.setAttribute("username",userFounded.getUsername());
        //String randomString = UService.generateRandomString();

        File uploadDir = new File(uploadPath);
        // If path doesn`t exist, create it
        if (!uploadDir.exists()) uploadDir.mkdir();
        try {
            // Getting each part from the request
            int i = 0;
            for (Part part : request.getParts() ) {
                // Storing the file using the same name
                if(part.getSubmittedFileName() != null)
                {
                    extension = part.getSubmittedFileName().toString().split("\\.")[1];
                }
                fileName = tittle+"."+extension;
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        UService.createNFT(fileName,extension,tittle,author,price,emailAuthor,getServletContext().getRealPath("") + File.separator);
        // Redirecting

        dispatcher = request.getRequestDispatcher("./index.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
