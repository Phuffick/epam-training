package com.epam.controller.commands.medicalcard;

import com.epam.controller.commands.Command;
import com.epam.controller.Direct;
import com.epam.model.actors.Patient;
import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.implementation.diagnosis.DiagnosisService;
import com.epam.service.implementation.medicalcard.MedicalCardService;
import com.epam.service.implementation.patient.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * MedicalCardEditCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicalCardEditCommand extends Command {

    /**
     * Medical card edit command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("medicalCardId"));
        } catch (NumberFormatException ignored) {
        }
        if (id != null) {
            try {
                MedicalCardService medicalCardService
                        = getServiceFactory()
                        .getMedicalCardService();
                MedicalCard medicalCard
                        = medicalCardService.findById(id);
                req.setAttribute("medicalCard", medicalCard);
                DiagnosisService diagnosisService
                        = getServiceFactory().getDiagnosisService();
                if (diagnosisService
                        .checkIfExistsAtLeastOneByMedicalCard(
                                medicalCard.getId())) {
                    req.setAttribute("canSetDischargeDate", true);
                } else {
                    req.setAttribute("canSetDischargeDate", false);
                }

            } catch (Exception e) {
                throw new ServletException(e);
            }
        } else {
            req.setAttribute("canSetDischargeDate", false);
        }
        try {
            PatientService patientService
                    = getServiceFactory().getPatientService();
            List<Patient> patients = patientService.findAll();
            req.setAttribute("patients", patients);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        return null;
    }
}
