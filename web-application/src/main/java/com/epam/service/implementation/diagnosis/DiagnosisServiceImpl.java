package com.epam.service.implementation.diagnosis;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.diagnosis.DiagnosisDao;
import com.epam.model.medicalparts.Diagnosis;
import com.epam.service.exception.ServiceException;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.diagnosis.exception.DiagnosisDateException;
import com.epam.service.implementation.medications.exception.MedicationsRequiredCountException;
import com.epam.service.implementation.surgery.exception.SurgeryDateException;
import com.epam.service.implementation.surgery.exception.SurgeryDoesNotExistsException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DiagnosisServiceImpl extends BaseService
        implements DiagnosisService {

    private DiagnosisDao diagnosisDao;

    public void setDiagnosisDao(DiagnosisDao diagnosisDao) {
        this.diagnosisDao = diagnosisDao;
    }

    @Override
    public boolean checkIfExistsAtLeastOneByMedicalCard(
            Long medicalCardId) throws ServiceException {
        try {
            return diagnosisDao.existsAtLeastOneByMedicalCard(
                    medicalCardId);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Diagnosis findById(Long id) throws ServiceException {
        try {
            return diagnosisDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Diagnosis> findByMedicalCardId(Long medicalCardId)
            throws ServiceException {
        try {
            return diagnosisDao
                    .readDiagnosisByMedicalCardId(medicalCardId);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Diagnosis> findAll() throws ServiceException {
        try {
            return diagnosisDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Diagnosis diagnosis) throws ServiceException {
        try {
            getTransaction().start();
            if(diagnosis.getId() != null) {
                Diagnosis storedDiagnosis = diagnosisDao
                        .readEntityById(diagnosis.getId());
                if (diagnosis.getDate() == null) {
                    if (storedDiagnosis != null) {
                        diagnosis.setDate(storedDiagnosis.getDate());
                    } else {
                        diagnosis.setDate(
                                Calendar.getInstance().getTime());
                    }
                }
                if (diagnosisDao.checkIfDateAfterAdmissionDate(
                        diagnosis)) {
                    if(storedDiagnosis != null) {
                        diagnosisDao.update(diagnosis);
                    } else {
                        throw new SurgeryDoesNotExistsException(
                                diagnosis.getId());
                    }
                } else {
                    throw new SurgeryDateException(
                            diagnosis.getDate().getTime());
                }
            } else {
                if (diagnosis.getDate() == null) {
                    diagnosis.setDate(
                            (GregorianCalendar) Calendar.getInstance());
                }
                if (diagnosisDao.checkIfDateAfterAdmissionDate(
                        diagnosis)) {
                    Long id = diagnosisDao.create(diagnosis);
                    diagnosis.setId(id);
                } else {
                    throw new DiagnosisDateException(
                            diagnosis.getDate().getTime());
                }
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException ignored) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try {
                getTransaction().rollback();
            } catch(ServiceException ignored) {}
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            diagnosisDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
