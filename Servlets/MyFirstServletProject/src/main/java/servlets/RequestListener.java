package servlets;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class RequestListener implements ServletRequestListener {
    AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        if (request.getServletPath().equals("/myPage")) {
            counter.addAndGet(1);
            System.out.println(counter);
        }
    }
}
