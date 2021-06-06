package com.epam.service.implementation.medicalcard;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.medicalcard.MedicalCardDao;
import com.epam.model.medicalparts.MedicalCard;
import com.epam.service.exception.ServiceException;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.medicalcard.exception.DischargeDateSettingException;
import com.epam.service.implementation.medications.exception.MedicationDoesNotExistsException;

import java.util.Calendar;
import java.util.List;

public class MedicalCardServiceImpl extends BaseService
        implements MedicalCardService {

    private MedicalCardDao medicalCardDao;

    public void setMedicalCardDao(MedicalCardDao medicalCardDao) {
        this.medicalCardDao = medicalCardDao;
    }

    @Override
    public List<MedicalCard> findMedicalCardsWithDischargeDate()
            throws ServiceException {
        try {
            return medicalCardDao
                    .readMedicalCardsWithDischargeDate();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<MedicalCard> findMedicalCardsWithoutDischargeDate()
            throws ServiceException {
        try {
            return medicalCardDao
                    .readMedicalCardsWithoutDischargeDate();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public MedicalCard findById(Long id) throws ServiceException {
        try {
            return medicalCardDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<MedicalCard> findAll() throws ServiceException {
        try {
            return medicalCardDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(MedicalCard medicalCard)
            throws ServiceException {
        try {
            getTransaction().start();
            if(medicalCard.getId() != null) {
                MedicalCard storedMedicalCard = medicalCardDao
                        .readEntityById(medicalCard.getId());
                if (medicalCard.getAdmissionDate() == null) {
                    if (storedMedicalCard != null) {
                        medicalCard.setAdmissionDate(
                                storedMedicalCard.getAdmissionDate());
                    } else {
                        medicalCard.setAdmissionDate(
                                Calendar.getInstance().getTime());
                    }
                }
                if(storedMedicalCard != null) {
                    if (medicalCard.getDischargeDate() != null
                            && storedMedicalCard.getAdmissionDate().after(
                            medicalCard.getDischargeDate())) {
                        throw new DischargeDateSettingException(
                                medicalCard.getDischargeDate());
                    }
                    if (medicalCard.getDischargeDate() == null) {
                        medicalCardDao.updateWithoutDischargeDate(
                                medicalCard);
                    } else {
                        medicalCardDao.update(medicalCard);
                    }
                } else {
                    throw new MedicationDoesNotExistsException(
                            medicalCard.getId());
                }
            } else {
                Long id;
                if (medicalCard.getDischargeDate() == null) {
                    id = medicalCardDao.createWithoutDischargeDate(
                            medicalCard);
                } else {
                    id = medicalCardDao.create(medicalCard);
                }
                medicalCard.setId(id);
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
            medicalCardDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
