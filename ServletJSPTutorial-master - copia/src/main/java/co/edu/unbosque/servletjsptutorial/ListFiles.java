package co.edu.unbosque.servletjsptutorial;



import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listFiles", value = "/list-files")
public class ListFiles extends HttpServlet {
    private String UPLOAD_DIRECTORY = "uploads";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Specifying the content type for the response
        response.setContentType("application/json");

        // Getting an instance of the upload path
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        // Listing file names in path
        List<String> files = new ArrayList<String>();
        for (File file : uploadDir.listFiles()) {
            files.add(UPLOAD_DIRECTORY + File.separator + file.getName());
        }

        // Adding the data to response, parsing it to json using Gson library
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(files));
    }
}
