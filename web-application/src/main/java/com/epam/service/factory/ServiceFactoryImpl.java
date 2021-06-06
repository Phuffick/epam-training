package com.epam.service.factory;

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
import com.epam.service.Transaction;
import com.epam.service.exception.ServiceFactoryException;
import com.epam.service.implementation.TransactionImpl;
import com.epam.service.implementation.diagnosis.DiagnosisService;
import com.epam.service.implementation.diagnosis.DiagnosisServiceImpl;
import com.epam.service.implementation.doctor.DoctorService;
import com.epam.service.implementation.doctor.DoctorServiceImpl;
import com.epam.service.implementation.medicalcard.MedicalCardService;
import com.epam.service.implementation.medicalcard.MedicalCardServiceImpl;
import com.epam.service.implementation.medications.MedicationsService;
import com.epam.service.implementation.medications.MedicationsServiceImpl;
import com.epam.service.implementation.patient.PatientService;
import com.epam.service.implementation.patient.PatientServiceImpl;
import com.epam.service.implementation.procedure.ProcedureService;
import com.epam.service.implementation.procedure.ProcedureServiceImpl;
import com.epam.service.implementation.surgery.SurgeryService;
import com.epam.service.implementation.surgery.SurgeryServiceImpl;
import com.epam.service.implementation.user.UserServiceImpl;
import com.epam.service.implementation.user.UserService;
import com.epam.util.connectionpool.ConnectionPool;
import com.epam.util.connectionpool.exception.ConnectionPoolException;

import java.sql.Connection;

public class ServiceFactoryImpl implements ServiceFactory {

    private UserService userService = null;

    private UserDao userDao = null;

    private PatientService patientService = null;

    private PatientDao patientDao = null;

    private DoctorService doctorService = null;

    private DoctorDao doctorDao = null;

    private DiagnosisService diagnosisService = null;

    private DiagnosisDao diagnosisDao = null;

    private MedicalCardService medicalCardService = null;

    private MedicalCardDao medicalCardDao = null;

    private MedicationsService medicationsService = null;

    private MedicationsDao medicationsDao = null;

    private ProcedureService procedureService = null;

    private ProcedureDao procedureDao = null;

    private SurgeryService surgeryService = null;

    private SurgeryDao surgeryDao = null;

    private Connection connection = null;

    /**
     * User service getter
     * @return user service
     */
    @Override
    public UserService getUserService()
            throws ServiceFactoryException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userServiceImpl.setTransaction(getTransaction());
            userService = userServiceImpl;
        }
        return userService;
    }

    /**
     * User dao getter
     * @return user dao
     */
    @Override
    public UserDao getUserDao() throws ServiceFactoryException {
        if (userDao == null) {
            MySqlUserDao mySqlUserDao  = new MySqlUserDao();
            mySqlUserDao.setConnection(getConnection());
            userDao = mySqlUserDao;
        }
        return userDao;
    }

    /**
     * Patient service getter
     * @return patient service
     */
    @Override
    public PatientService getPatientService()
            throws ServiceFactoryException {
        if (patientService == null) {
            PatientServiceImpl patientServiceImp
                    = new PatientServiceImpl();
            patientServiceImp.setPatientDao(getPatientDao());
            patientServiceImp.setTransaction(getTransaction());
            patientService = patientServiceImp;
        }
        return patientService;
    }

    /**
     * Patient dao getter
     * @return patient dao
     */
    @Override
    public PatientDao getPatientDao()
            throws ServiceFactoryException {
        if (patientDao == null) {
            MySqlPatientDao mySqlPatientDao = new MySqlPatientDao();
            mySqlPatientDao.setConnection(getConnection());
            patientDao = mySqlPatientDao;
        }
        return patientDao;
    }

    /**
     * Doctor service getter
     * @return doctor service
     */
    @Override
    public DoctorService getDoctorService()
            throws ServiceFactoryException {
        if (doctorService == null) {
            DoctorServiceImpl doctorServiceImp
                    = new DoctorServiceImpl();
            doctorServiceImp.setDoctorDao(getDoctorDao());
            doctorServiceImp.setTransaction(getTransaction());
            doctorService = doctorServiceImp;
        }
        return doctorService;
    }

    /**
     * Doctor dao getter
     * @return doctor dao
     */
    @Override
    public DoctorDao getDoctorDao() throws ServiceFactoryException {
        if (doctorDao == null) {
            MySqlDoctorDao mySqlDoctorDao = new MySqlDoctorDao();
            mySqlDoctorDao.setConnection(getConnection());
            doctorDao = mySqlDoctorDao;
        }
        return doctorDao;
    }

    /**
     * Diagnosis service getter
     * @return diagnosis service
     */
    @Override
    public DiagnosisService getDiagnosisService()
            throws ServiceFactoryException {
        if (diagnosisService == null) {
            DiagnosisServiceImpl diagnosisServiceImpl
                    = new DiagnosisServiceImpl();
            diagnosisServiceImpl.setDiagnosisDao(getDiagnosisDao());
            diagnosisServiceImpl.setTransaction(getTransaction());
            diagnosisService = diagnosisServiceImpl;
        }
        return diagnosisService;
    }

    /**
     * Diagnosis dao getter
     * @return diagnosis dao
     */
    @Override
    public DiagnosisDao getDiagnosisDao()
            throws ServiceFactoryException {
        if (diagnosisDao == null) {
            MySqlDiagnosisDao mySqlDiagnosisDao
                    = new MySqlDiagnosisDao();
            mySqlDiagnosisDao.setConnection(getConnection());
            diagnosisDao = mySqlDiagnosisDao;
        }
        return diagnosisDao;
    }

    /**
     * Medical card service getter
     * @return medical card service
     */
    @Override
    public MedicalCardService getMedicalCardService()
            throws ServiceFactoryException {
        if (medicalCardService == null) {
            MedicalCardServiceImpl medicalCardServiceImpl
                    = new MedicalCardServiceImpl();
            medicalCardServiceImpl.setMedicalCardDao(
                    getMedicalCardDao());
            medicalCardServiceImpl.setTransaction(getTransaction());
            medicalCardService = medicalCardServiceImpl;
        }
        return medicalCardService;
    }

    /**
     * Medical card dao getter
     * @return medical card dao
     */
    @Override
    public MedicalCardDao getMedicalCardDao()
            throws ServiceFactoryException {
        if (medicalCardDao == null) {
            MySqlMedicalCardDao mySqlMedicalCardDao
                    = new MySqlMedicalCardDao();
            mySqlMedicalCardDao.setConnection(getConnection());
            medicalCardDao = mySqlMedicalCardDao;
        }
        return medicalCardDao;
    }

    /**
     * Medications service getter
     * @return medications service
     */
    @Override
    public MedicationsService getMedicationsService()
            throws ServiceFactoryException {
        if (medicationsService == null) {
            MedicationsServiceImpl medicationsServiceImpl
                    = new MedicationsServiceImpl();
            medicationsServiceImpl.setMedicationsDao(
                    getMedicationsDao());
            medicationsServiceImpl.setTransaction(getTransaction());
            this.medicationsService = medicationsServiceImpl;
        }
        return medicationsService;
    }

    /**
     * Medications dao getter
     * @return medications dao
     */
    @Override
    public MedicationsDao getMedicationsDao()
            throws ServiceFactoryException {
        if (medicationsDao == null) {
            MySqlMedicationsDao mySqlMedicationsDao
                    = new MySqlMedicationsDao();
            mySqlMedicationsDao.setConnection(getConnection());
            medicationsDao = mySqlMedicationsDao;
        }
        return medicationsDao;
    }

    /**
     * Procedure service getter
     * @return procedure service
     */
    @Override
    public ProcedureService getProcedureService()
            throws ServiceFactoryException {
        if (procedureService == null) {
            ProcedureServiceImpl procedureServiceImpl
                    = new ProcedureServiceImpl();
            procedureServiceImpl.setProcedureDao(getProcedureDao());
            procedureServiceImpl.setTransaction(getTransaction());
            this.procedureService = procedureServiceImpl;
        }
        return procedureService;
    }

    /**
     * Procedure dao getter
     * @return procedure dao
     */
    @Override
    public ProcedureDao getProcedureDao()
            throws ServiceFactoryException {
        if (procedureDao == null) {
            MySqlProcedureDao mySqlProcedureDao
                    = new MySqlProcedureDao();
            mySqlProcedureDao.setConnection(getConnection());
            procedureDao = mySqlProcedureDao;
        }
        return procedureDao;
    }

    /**
     * Surgery service getter
     * @return surgery service
     */
    @Override
    public SurgeryService getSurgeryService()
            throws ServiceFactoryException {
        if (surgeryService == null) {
            SurgeryServiceImpl surgeryServiceImpl
                    = new SurgeryServiceImpl();
            surgeryServiceImpl.setSurgeryDao(getSurgeryDao());
            surgeryServiceImpl.setTransaction(getTransaction());
            this.surgeryService = surgeryServiceImpl;
        }
        return surgeryService;
    }

    /**
     * Surgery dao getter
     * @return surgery dao
     */
    @Override
    public SurgeryDao getSurgeryDao()
            throws ServiceFactoryException {
        if (surgeryDao == null) {
            MySqlSurgeryDao mySqlSurgeryDao = new MySqlSurgeryDao();
            mySqlSurgeryDao.setConnection(getConnection());
            surgeryDao = mySqlSurgeryDao;
        }
        return surgeryDao;
    }

    /**
     * Transaction getter
     * @return transaction
     */
    @Override
    public Transaction getTransaction()
            throws ServiceFactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    /**
     * Connection getter
     * @return connection
     */
    @Override
    public Connection getConnection()
            throws ServiceFactoryException {
        if (connection == null) {
            try {
                connection= ConnectionPool.getInstance()
                        .getConnection();
            } catch (ConnectionPoolException e) {
                throw new ServiceFactoryException(e);
            }
        }
        return connection;
    }

    /**
     * Close connection method
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        try { connection.close(); } catch (Exception e) {}
    }
}
