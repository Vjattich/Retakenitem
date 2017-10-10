package toggle.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApp extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppInit.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppInit.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
