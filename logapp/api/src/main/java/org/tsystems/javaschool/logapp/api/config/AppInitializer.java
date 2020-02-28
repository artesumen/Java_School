package org.tsystems.javaschool.logapp.api.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {HibernateConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebMvcConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}