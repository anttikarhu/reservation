package org.anttikarhu.reservation.fhir;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {
    private final Server server;

    @Autowired
    public ServerConfiguration(@NotNull Server server) {
        this.server = server;
    }

    @Bean
    public ServletRegistrationBean fhirServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean<>(server, "/fhir/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}
