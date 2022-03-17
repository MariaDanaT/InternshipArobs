package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "InputParameterFilter", urlPatterns = "/myPage")
public class InputParameterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String inputFirstNumber = servletRequest.getParameter("first");
        String inputSecondNumber = servletRequest.getParameter("second");
        try {
            if (inputFirstNumber != null)
                Integer.parseInt(inputFirstNumber);
        } catch (Exception e) {
            servletResponse.setContentType("text/html");
            servletResponse.getWriter().println("""
                    <span style="color:red">
                        First input is not a number
                    </span>
                    <br>
                    """);
        }
        try {
            if (inputSecondNumber != null)
                Integer.parseInt(inputSecondNumber);
        } catch (Exception e) {
            servletResponse.setContentType("text/html");
            servletResponse.getWriter().println("""
                    <span style="color:red">
                        Second input is not a number
                    </span>
                    <br>
                    """);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
