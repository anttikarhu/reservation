package org.anttikarhu.reservation.appointment;

import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Appointment;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class AppointmentResourceProvider implements IResourceProvider {

    private static final Logger LOG = LoggerFactory.getLogger(AppointmentResourceProvider.class);

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return Appointment.class;
    }

    @Search
    @SuppressWarnings("unused")
    public List<Appointment> search(@RequiredParam(name = Appointment.SP_PATIENT) StringParam patientId) {
        LOG.info("Search appointments by patient id {}", patientId.getValue());

        Appointment appointment1 = new Appointment();
        appointment1.setId("1");
        appointment1.setStatus(Appointment.AppointmentStatus.BOOKED);
        appointment1.setStart(toDate(LocalDateTime.of(2020, 12, 6, 12, 0)));
        appointment1.setEnd(toDate(LocalDateTime.of(2020, 12, 6, 13, 0)));
        appointment1.setDescription("Ajanvaraus 1");

        Appointment appointment2 = new Appointment();
        appointment2.setId("2");
        appointment2.setStatus(Appointment.AppointmentStatus.BOOKED);
        appointment2.setStart(toDate(LocalDateTime.of(2020, 12, 6, 13, 0)));
        appointment2.setEnd(toDate(LocalDateTime.of(2020, 12, 6, 14, 0)));
        appointment2.setDescription("Ajanvaraus 2");

        return Arrays.asList(appointment1, appointment2);
    }

    private static Date toDate(@NotNull LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
