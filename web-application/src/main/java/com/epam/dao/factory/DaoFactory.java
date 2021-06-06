package com.epam.dao.factory;

import com.epam.dao.implementation.diagnosis.DiagnosisDao;
import com.epam.dao.implementation.doctor.DoctorDao;
import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.medicalcard.MedicalCardDao;
import com.epam.dao.implementation.medications.MedicationsDao;
import com.epam.dao.implementation.patient.PatientDao;
import com.epam.dao.implementation.procedure.ProcedureDao;
import com.epam.dao.implementation.surgery.SurgeryDao;
import com.epam.dao.implementation.user.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DaoFactory abstract definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public abstract class DaoFactory {

    /** Logger */
    private static final Logger log
            = LogManager.getLogger(DaoFactory.class);

    /** Maintained by dao factory database */
    public static final int MY_SQL = 1;

    /**
     * User DAO getter
     * @return user DAO
     */
    public abstract UserDao getUserDao();

    /**
     * Doctor DAO getter
     * @return doctor DAO
     */
    public abstract DoctorDao getDoctorDao();

    /**
     * Patient DAO getter
     * @return patient DAO
     */
    public abstract PatientDao getPatientDao();

    /**
     * Diagnosis DAO getter
     * @return diagnosis DAO
     */
    public abstract DiagnosisDao getDiagnosisDao();

    /**
     * Medications DAO getter
     * @return medications DAO
     */
    public abstract MedicationsDao getMedicationsDao();

    /**
     * Procedure DAO getter
     * @return procedure DAO
     */
    public abstract ProcedureDao getProcedureDao();

    /**
     * Surgery DAO getter
     * @return surgery DAO
     */
    public abstract SurgeryDao getSurgeryDao();

    /**
     * Medical card DAO getter
     * @return medical card DAO
     */
    public abstract MedicalCardDao getMedicalCardDao();

    /**
     *
     * @param dataBaseToUse database name
     * @return concrete dao factory
     * @throws DaoException if database is not maintained yet
     */
    public static DaoFactory getDaoFactory(int dataBaseToUse)
            throws DaoException {
        switch (dataBaseToUse) {
            case MY_SQL:
                return new MySqlDaoFactory();
            default:
                log.error("Database " + dataBaseToUse
                        + " is not maintained.");
                throw new DaoException();
        }
    }
}
