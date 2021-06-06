package com.epam.service.factory;

import com.epam.dao.implementation.diagnosis.DiagnosisDao;
import com.epam.dao.implementation.doctor.DoctorDao;
import com.epam.dao.implementation.medicalcard.MedicalCardDao;
import com.epam.dao.implementation.medications.MedicationsDao;
import com.epam.dao.implementation.patient.PatientDao;
import com.epam.dao.implementation.procedure.ProcedureDao;
import com.epam.dao.implementation.surgery.SurgeryDao;
import com.epam.dao.implementation.user.UserDao;
import com.epam.service.Transaction;
import com.epam.service.exception.ServiceFactoryException;
import com.epam.service.implementation.diagnosis.DiagnosisService;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.medicalcard.MedicalCardService;
import com.epam.service.implementation.medications.MedicationsService;
import com.epam.service.implementation.patient.PatientService;
import com.epam.service.implementation.procedure.ProcedureService;
import com.epam.service.implementation.surgery.SurgeryService;
import com.epam.service.implementation.user.UserService;

import java.sql.Connection;

/**
 * ServiceFactory definition class.
 *
 * @version    1.0.0 23 May 2021
 * @author     Belyack Maxim
 */
public interface ServiceFactory extends AutoCloseable {

    /**
     * User service getter
     * @return user service
     */
    UserService getUserService() throws ServiceFactoryException;

    /**
     * User dao getter
     * @return user dao
     */
    UserDao getUserDao() throws ServiceFactoryException;

    /**
     * Patient service getter
     * @return patient service
     */
    PatientService getPatientService()
            throws ServiceFactoryException;

    /**
     * Patient dao getter
     * @return patient dao
     */
    PatientDao getPatientDao() throws ServiceFactoryException;

    /**
     * Doctor service getter
     * @return doctor service
     */
    DoctorService getDoctorService() throws ServiceFactoryException;

    /**
     * Doctor dao getter
     * @return doctor dao
     */
    DoctorDao getDoctorDao() throws ServiceFactoryException;

    /**
     * Diagnosis service getter
     * @return diagnosis service
     */
    DiagnosisService getDiagnosisService()
            throws ServiceFactoryException;

    /**
     * Diagnosis dao getter
     * @return diagnosis dao
     */
    DiagnosisDao getDiagnosisDao() throws ServiceFactoryException;

    /**
     * Medical card service getter
     * @return medical card service
     */
    MedicalCardService getMedicalCardService()
            throws ServiceFactoryException;

    /**
     * Medical card dao getter
     * @return medical card dao
     */
    MedicalCardDao getMedicalCardDao()
            throws ServiceFactoryException;

    /**
     * Medications service getter
     * @return medications service
     */
    MedicationsService getMedicationsService()
            throws ServiceFactoryException;

    /**
     * Medications dao getter
     * @return medications dao
     */
    MedicationsDao getMedicationsDao()
            throws ServiceFactoryException;

    /**
     * Procedure service getter
     * @return procedure service
     */
    ProcedureService getProcedureService()
            throws ServiceFactoryException;

    /**
     * Procedure dao getter
     * @return procedure dao
     */
    ProcedureDao getProcedureDao() throws ServiceFactoryException;

    /**
     * Surgery service getter
     * @return surgery service
     */
    SurgeryService getSurgeryService()
            throws ServiceFactoryException;

    /**
     * Surgery dao getter
     * @return surgery dao
     */
    SurgeryDao getSurgeryDao() throws ServiceFactoryException;

    /**
     * Transaction getter
     * @return transaction
     */
    Transaction getTransaction() throws ServiceFactoryException;

    /**
     * Connection getter
     * @return connection
     */
    Connection getConnection() throws ServiceFactoryException;
}
