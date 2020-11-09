package binhtt.utils.context;

import binhtt.blos.EventBLO;
import binhtt.entities.TblEvent;
import binhtt.utils.function.ChangeStatusEvent;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    private ScheduledExecutorService scheduledExecutorService;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        List<TblEvent> events = null;
        try {
            events = new EventBLO().getEventsForContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
        servletContextEvent.getServletContext().setAttribute("events", events);
//        request.setCharacterEncoding("UTF-8");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new ChangeStatusEvent(), 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        scheduledExecutorService.shutdownNow();
    }
}
