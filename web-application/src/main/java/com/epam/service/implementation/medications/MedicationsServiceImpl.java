package com.epam.service.implementation.medications;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.medications.MedicationsDao;
import com.epam.model.medicalparts.Medications;
import com.epam.service.exception.ServiceException;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.medications.exception.MedicationDoesNotExistsException;
import com.epam.service.implementation.medications.exception.MedicationsAmountPerDayCountException;
import com.epam.service.implementation.medications.exception.MedicationsDateException;
import com.epam.service.implementation.medications.exception.MedicationsRequiredCountException;
import com.epam.service.implementation.procedure.exception.ProcedureDateException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MedicationsServiceImpl extends BaseService
        implements MedicationsService {

    private MedicationsDao medicationsDao;

    public void setMedicationsDao(MedicationsDao medicationsDao) {
        this.medicationsDao = medicationsDao;
    }

    @Override
    public Medications findById(Long id) throws ServiceException {
        try {
            return medicationsDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Medications> findByMedicalCardId(Long medicalCard)
            throws ServiceException {
        try {
            return medicationsDao
                    .readMedicationsByMedicalCardId(medicalCard);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Medications> findAll() throws ServiceException {
        try {
            return medicationsDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Medications medications)
            throws ServiceException {
        try {
            getTransaction().start();
            if(medications.getId() != null) {
                Medications storedMedications = medicationsDao
                        .readEntityById(medications.getId());
                if (medications.getDate() == null) {
                    medications.setDate(storedMedications.getDate());
                }
                if (!medicationsDao
                        .checkIfDateAfterAdmissionDate(medications)) {
                    throw new MedicationsDateException(
                            medications.getDate().getTime());
                }
                if (!medicationsDao
                        .checkIfRequiredCountIsGraterThanAmountPerDayCount(
                                medications)) {
                    throw new MedicationsRequiredCountException(
                            medications.getRequiredCount());
                }
                if (!medicationsDao
                        .checkIfAmountPerDayCountIsLessThanRequiredCount(
                                medications)) {
                    throw new MedicationsAmountPerDayCountException(
                            medications.getRequiredCount());
                }
                if(storedMedications != null) {
                    medicationsDao.update(medications);
                } else {
                    throw new MedicationDoesNotExistsException(
                            medications.getId());
                }
            } else {
                if (medications.getDate() == null) {
                    medications.setDate(
                            (GregorianCalendar) Calendar.getInstance());
                }
                if (medications.getRequiredCount()
                        .compareTo(medications.getAmountPerDay()) < 0) {
                    throw new MedicationsRequiredCountException(
                            medications.getRequiredCount());
                }
                Long id = medicationsDao.create(medications);
                medications.setId(id);
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
            medicationsDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
