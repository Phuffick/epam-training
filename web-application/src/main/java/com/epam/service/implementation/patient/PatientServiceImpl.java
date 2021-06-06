package com.epam.service.implementation.patient;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.patient.PatientDao;
import com.epam.model.actors.Patient;
import com.epam.service.exception.ServiceException;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.patient.exception.PatientDoesNotExistsException;

import java.util.List;

public class PatientServiceImpl extends BaseService
        implements PatientService {

    private PatientDao patientDao;

    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public Patient findById(Long id) throws ServiceException {
        try {
            return patientDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Patient> findAll() throws ServiceException {
        try {
            return patientDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Patient patient) throws ServiceException {
        try {
            getTransaction().start();
            if(patient.getId() != null) {
                Patient storedSurgery = patientDao
                        .readEntityById(patient.getId());
                if(storedSurgery != null) {
                    patientDao.update(patient);
                } else {
                    throw new PatientDoesNotExistsException(
                            patient.getId());
                }
            } else {
                Long id = patientDao.create(patient);
                patient.setId(id);
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
            patientDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);

        }
    }
}
