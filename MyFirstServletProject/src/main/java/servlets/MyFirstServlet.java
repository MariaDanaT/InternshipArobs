package servlets;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext =getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/jsp/myPage.jsp");
        requestDispatcher.forward(req,resp);
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
    }
}
