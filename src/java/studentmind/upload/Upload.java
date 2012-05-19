package studentmind.upload;

/**
 *
 * @author ProjetJava
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/fileUpload")
@MultipartConfig
public class Upload extends HttpServlet {
  private static Logger logger = Logger.getLogger(Upload.class);

  public Upload() {
    super();
  }

    @Override
  protected void doGet(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    for (Part part : request.getParts()) {
      logger.info(part.getName());
      InputStream is = request.getPart(part.getName()).getInputStream();
      int i = is.available();
      byte[] b  = new byte[i];
      is.read(b);
      logger.info("Length : " + b.length);
      String fileName = getFileName(part);
      logger.info("File name : " + fileName);
      FileOutputStream os = new FileOutputStream("c:/temp/logs/" + fileName);
      os.write(b);
      is.close();
      /* si on ajoute ça pour faire le forward alors ça plante */
      //RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
      //dispatcher.forward( request, response );

    }

  }

  private String getFileName(Part part) {
    String partHeader = part.getHeader("content-disposition");
    logger.info("Part Header = " + partHeader);
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        return cd.substring(cd.indexOf('=') + 1).trim()
            .replace("\"", "");
      }
    }
    return null;

  }

    @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}

