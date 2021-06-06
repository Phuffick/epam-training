package com.epam.controller.commands.doctor;

import com.epam.controller.DirectionType;
import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Doctor;
import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.doctor.exception.DoctorDoesNotExistsException;
import com.epam.service.implementation.medicalcard.MedicalCardService;
import com.epam.service.implementation.medicalcard.exception.DischargeDateSettingException;
import com.epam.service.implementation.medications.exception.MedicationDoesNotExistsException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DoctorSaveCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class DoctorSaveCommand extends Command {

    /**
     * Doctor save command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Doctor patient = new Doctor();
        try {
            patient.setId(Long.parseLong(req.getParameter("id")));
        } catch(NumberFormatException ignored) {}
        patient.setName(req.getParameter("name"));
        if(patient.getName() != null) {
            try {
                DoctorService service
                        = getServiceFactory().getDoctorService();
                service.save(patient);
            } catch(DoctorDoesNotExistsException e) {
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
            } catch (DischargeDateSettingException e) {
                return new Direct("/error.html?message=400",
                        DirectionType.REDIRECT);
            } catch (MedicationDoesNotExistsException e) {
                return new Direct("/error.html?message=404",
                        DirectionType.REDIRECT);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        return new Direct("/doctor/medical-cards-list.html");
    }
}
