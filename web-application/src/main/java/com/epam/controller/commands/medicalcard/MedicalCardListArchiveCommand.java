package com.epam.controller.commands.medicalcard;

import com.epam.controller.Direct;
import com.epam.controller.commands.Command;
import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.implementation.medicalcard.MedicalCardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * MedicalCardListCommand definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MedicalCardListArchiveCommand extends Command {

    /**
     * Medical card list command
     * @return direct
     */
    @Override
    public Direct execute(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            if ("false".equals(req.getParameter("archive"))) {
                MedicalCardService service
                        = getServiceFactory().getMedicalCardService();
                List<MedicalCard> medicalCards = service
                        .findMedicalCardsWithDischargeDate();
                req.setAttribute("medicalCards", medicalCards);
                return null;
            }
            MedicalCardService service
                    = getServiceFactory().getMedicalCardService();
            List<MedicalCard> medicalCards = service
                    .findMedicalCardsWithDischargeDate();
            req.setAttribute("medicalCards", medicalCards);
            return null;
        } catch(Exception e) {
            throw new ServletException(e);
        }
    }
}
