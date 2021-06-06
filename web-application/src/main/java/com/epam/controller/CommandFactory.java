package com.epam.controller;

import com.epam.controller.commands.Command;
import com.epam.controller.commands.LoginCommand;
import com.epam.controller.commands.LogoutCommand;
import com.epam.controller.commands.diagnosis.DiagnosisEditCommand;
import com.epam.controller.commands.diagnosis.DiagnosisListCommand;
import com.epam.controller.commands.diagnosis.DiagnosisSaveCommand;
import com.epam.controller.commands.doctor.DoctorEditCommand;
import com.epam.controller.commands.doctor.DoctorListCommand;
import com.epam.controller.commands.doctor.DoctorSaveCommand;
import com.epam.controller.commands.language.EnLanguageCommand;
import com.epam.controller.commands.language.RuLanguageCommand;
import com.epam.controller.commands.medicalcard.MedicalCardEditCommand;
import com.epam.controller.commands.medicalcard.MedicalCardListArchiveCommand;
import com.epam.controller.commands.medicalcard.MedicalCardListCommand;
import com.epam.controller.commands.medicalcard.MedicalCardSaveCommand;
import com.epam.controller.commands.medication.MedicationEditCommand;
import com.epam.controller.commands.medication.MedicationListCommand;
import com.epam.controller.commands.medication.MedicationSaveCommand;
import com.epam.controller.commands.nurse.NurseListCommand;
import com.epam.controller.commands.patient.PatientEditCommand;
import com.epam.controller.commands.patient.PatientListCommand;
import com.epam.controller.commands.patient.PatientSaveCommand;
import com.epam.controller.commands.procedure.ProcedureEditCommand;
import com.epam.controller.commands.procedure.ProcedureListCommand;
import com.epam.controller.commands.procedure.ProcedureSaveCommand;
import com.epam.controller.commands.surgery.SurgeryEditCommand;
import com.epam.controller.commands.surgery.SurgeryListCommand;
import com.epam.controller.commands.surgery.SurgerySaveCommand;
import com.epam.controller.commands.user.*;
import com.epam.controller.commands.user.password.UserChangePasswordCommand;
import com.epam.controller.commands.user.password.UserSavePasswordCommand;

import javax.servlet.ServletException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * CommandFactory definition class.
 *
 * @version    1.0.0 09 May 2021
 * @author     Belyack Maxim
 */
public class CommandFactory {

    /** Map of page to command */
    private static final Map<String, Class<? extends Command>> commands
            = new HashMap<>();

    static {
        commands.put("/EN", EnLanguageCommand.class);
        commands.put("/RU", RuLanguageCommand.class);
        commands.put("/login", LoginCommand.class);
        commands.put("/logout", LogoutCommand.class);
        commands.put("/user/password/change-password",
                UserChangePasswordCommand.class);
        commands.put("/user/password/save-password",
                UserSavePasswordCommand.class);
        commands.put("/user/user-profile", UserProfileCommand.class);
        commands.put("/user/users-list", UserListCommand.class);
        commands.put("/user/users-edit", UserEditCommand.class);
        commands.put("/user/save", UserSaveCommand.class);
        commands.put("/user/delete", UserDeleteCommand.class);

        commands.put("/diagnosis/diagnosis-list",
                DiagnosisListCommand.class);
        commands.put("/diagnosis/diagnosis-list-archive",
                DiagnosisListCommand.class);
        commands.put("/diagnosis/diagnosis-edit",
                DiagnosisEditCommand.class);
        commands.put("/diagnosis/save", DiagnosisSaveCommand.class);

        commands.put("/medicalcard/medical-cards-list",
                MedicalCardListCommand.class);
        commands.put("/medicalcard/medical-cards-list-archive",
                MedicalCardListArchiveCommand.class);
        commands.put("/medicalcard/medical-cards-edit",
                MedicalCardEditCommand.class);
        commands.put("/medicalcard/save",
                MedicalCardSaveCommand.class);

        commands.put("/medication/medications-list",
                MedicationListCommand.class);
        commands.put("/medication/medications-list-archive",
                MedicationListCommand.class);
        commands.put("/medication/medications-edit",
                MedicationEditCommand.class);
        commands.put("/medication/save",
                MedicationSaveCommand.class);

        commands.put("/procedure/procedures-list",
                ProcedureListCommand.class);
        commands.put("/procedure/procedures-list-archive",
                ProcedureListCommand.class);
        commands.put("/procedure/procedures-edit",
                ProcedureEditCommand.class);
        commands.put("/procedure/save", ProcedureSaveCommand.class);

        commands.put("/surgery/surgeries-list",
                SurgeryListCommand.class);
        commands.put("/surgery/surgeries-list-archive",
                SurgeryListCommand.class);
        commands.put("/surgery/surgeries-edit",
                SurgeryEditCommand.class);
        commands.put("/surgery/save", SurgerySaveCommand.class);

        commands.put("/doctor/doctors-list",
                DoctorListCommand.class);
        commands.put("/doctor/doctors-edit", DoctorEditCommand.class);
        commands.put("/doctor/save", DoctorSaveCommand.class);

        commands.put("/nurse/nurses-list", NurseListCommand.class);

        commands.put("/patient/patients-list",
                PatientListCommand.class);
        commands.put("/patient/patients-list-archive",
                PatientListCommand.class);
        commands.put("/patient/patients-edit",
                PatientEditCommand.class);
        commands.put("/patient/save", PatientSaveCommand.class);
    }

    /**
     * Command getter
     * @param url url to map
     * @return command
     */
    public static Command getCommand(String url) throws ServletException {
        Class<? extends Command> action = commands.get(url);
        if(action != null) {
            try {
                return action.getConstructor().newInstance();
            } catch(InstantiationException | IllegalAccessException
                    | NullPointerException | NoSuchMethodException
                    | InvocationTargetException e) {
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
