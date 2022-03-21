package servlets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyFirstServlet", urlPatterns = "/myPage")
public class MyFirstServlet extends HttpServlet {

    private static final Logger logger
            = LoggerFactory.getLogger(MyFirstServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext =getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/myPage.jsp");
        requestDispatcher.forward(req,resp);
        logger.info("Call method doGet!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int first;
        int second;
        first = Integer.parseInt(req.getParameter("first"));
        second = Integer.parseInt(req.getParameter("second"));
        req.setAttribute("sum", first+second);

        ServletContext servletContext =getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/myPage.jsp");
        requestDispatcher.forward(req,resp);

        logger.info("Call method doPost!");
    }
}
