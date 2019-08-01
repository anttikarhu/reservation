package org.anttikarhu.reservation.api.fhir;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {
    private final Server server;

    // Make FHIR API to be in the same root as other RESTful APIs in this web app, /<apiBasePath>/fhir/*
    @Value("${spring.data.rest.base-path}")
    private String apiBasePath;

    @Autowired
    public ServerConfiguration(@NotNull Server server) {
        this.server = server;
    }

    @Bean
    public ServletRegistrationBean fhirServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(server, apiBasePath + "/fhir/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}
