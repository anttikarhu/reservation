package org.anttikarhu.reservation.api.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import org.anttikarhu.reservation.api.fhir.appointment.AppointmentResourceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Server extends RestfulServer {
    private static final long serialVersionUID = 1L;

    private final List<IResourceProvider> resourceProviders;

    public Server(@NotNull AppointmentResourceProvider appointmentResourceProvider) {
        super(FhirContext.forR4());
        this.setDefaultResponseEncoding(EncodingEnum.JSON);

        this.resourceProviders = Collections.singletonList(appointmentResourceProvider);
    }

    @Override
    protected void initialize() {
        setResourceProviders(resourceProviders);
        registerInterceptor(new ResponseHighlighterInterceptor());
        setDefaultPrettyPrint(true);
    }
}
