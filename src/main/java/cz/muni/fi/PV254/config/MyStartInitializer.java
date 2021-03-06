package cz.muni.fi.PV254.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.jsp.jstl.core.Config;
/**
 * User: PC
 * Date: 26. 11. 2014
 * Time: 11:54
 */
public class MyStartInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //create Spring beans context configured in MySpringMvcConfig.class
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MySpringMvcConfig.class);

        //register Spring MVC main Dispatcher servlet
        ServletRegistration.Dynamic disp = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        disp.setLoadOnStartup(1);
        disp.addMapping("/");

        //register filter setting utf-8 encoding on all requests
        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding", CharacterEncodingFilter.class);
        encoding.setInitParameter("encoding", "utf-8");
        encoding.addMappingForUrlPatterns(null, false, "/*");

        //register bundle also for JSTL fmt: tags which are not behind DispatcherServlet
        servletContext.setInitParameter(Config.FMT_LOCALIZATION_CONTEXT,"Texts");
    }
}

