package com.epam.dao.factory;

import com.epam.dao.implementation.diagnosis.DiagnosisDao;
import com.epam.dao.implementation.diagnosis.MySqlDiagnosisDao;
import com.epam.dao.implementation.doctor.DoctorDao;
import com.epam.dao.implementation.doctor.MySqlDoctorDao;
import com.epam.dao.implementation.medicalcard.MedicalCardDao;
import com.epam.dao.implementation.medicalcard.MySqlMedicalCardDao;
import com.epam.dao.implementation.medications.MedicationsDao;
import com.epam.dao.implementation.medications.MySqlMedicationsDao;
import com.epam.dao.implementation.patient.MySqlPatientDao;
import com.epam.dao.implementation.patient.PatientDao;
import com.epam.dao.implementation.procedure.MySqlProcedureDao;
import com.epam.dao.implementation.procedure.ProcedureDao;
import com.epam.dao.implementation.surgery.MySqlSurgeryDao;
import com.epam.dao.implementation.surgery.SurgeryDao;
import com.epam.dao.implementation.user.MySqlUserDao;
import com.epam.dao.implementation.user.UserDao;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * MySqlDaoFactory definition class.
 * Implements DaoFactory class for MySqlConnector
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public class MySqlDaoFactory extends DaoFactory {

    /**
     * Default constructor
     */
    MySqlDaoFactory() {}

    /**
     * User DAO getter
     * @return user DAO
     */
    @Override
    public UserDao getUserDao() {
        return new MySqlUserDao();
    }

    /**
     * Doctor DAO getter
     * @return doctor DAO
     */
    @Override
    public DoctorDao getDoctorDao() {
        return new MySqlDoctorDao();
    }

    /**
     * Patient DAO getter
     * @return patient DAO
     */
    @Override
    public PatientDao getPatientDao() {
        return new MySqlPatientDao();
    }

    /**
     * Diagnosis DAO getter
     * @return diagnosis DAO
     */
    @Override
    public DiagnosisDao getDiagnosisDao() {
        return new MySqlDiagnosisDao();
    }

    /**
     * Medications DAO getter
     * @return medications DAO
     */
    @Override
    public MedicationsDao getMedicationsDao() {
        return new MySqlMedicationsDao();
    }

    /**
     * Procedure DAO getter
     * @return procedure DAO
     */
    @Override
    public ProcedureDao getProcedureDao() {
        return new MySqlProcedureDao();
    }

    /**
     * Surgery DAO getter
     * @return surgery DAO
     */
    @Override
    public SurgeryDao getSurgeryDao() {
        return new MySqlSurgeryDao();
    }

    /**
     * Medical card DAO getter
     * @return medical card DAO
     */
    @Override
    public MedicalCardDao getMedicalCardDao() {
        return new MySqlMedicalCardDao();
    }
}
