package com.epam.controller.commands.patient;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Patient;
import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.implementation.medicalcard.MedicalCardService;
import com.epam.service.implementation.medicalcard.exception.DischargeDateSettingException;
import com.epam.service.implementation.medications.exception.MedicationDoesNotExistsException;
import com.epam.service.implementation.patient.PatientService;
import com.epam.service.implementation.patient.exception.PatientDoesNotExistsException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PatientSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class PatientSaveCommand extends Command {

    /**
     * Patient save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Patient patient = new Patient();
        try {
            patient.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        String patientName = req.getParameter("name");
        if(patientName != null) {
            patient.setName(patientName);
            try {
                PatientService service
                        = getServiceFactory().getPatientService();
                service.save(patient);
            } catch(PatientDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch(Exception e) {
                throw new ServletException(e);
            }
        } else {
            try {
                MedicalCardService service
                        = getServiceFactory().getMedicalCardService();
                MedicalCard medicalCard = service.findById(
                        Long.parseLong(
                                req.getParameter("medicalCardId")));
                medicalCard.setPatientId(Long.parseLong(
                        req.getParameter("patientSelect")));
                service.save(medicalCard);
            } catch(DischargeDateSettingException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch(MedicationDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/medicalcard/medical-cards-list.html");
    }
}
