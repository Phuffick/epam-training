package com.epam.service.implementation.surgery;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.surgery.SurgeryDao;
import com.epam.model.medicalparts.Surgery;
import com.epam.service.exception.*;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.surgery.exception.SurgeryDateException;
import com.epam.service.implementation.surgery.exception.SurgeryDoesNotExistsException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SurgeryServiceImpl extends BaseService
        implements SurgeryService {

    private SurgeryDao surgeryDao;

    public void setSurgeryDao(SurgeryDao surgeryDao) {
        this.surgeryDao = surgeryDao;
    }

    @Override
    public Surgery findById(Long id) throws ServiceException {
        try {
            return surgeryDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Surgery> findByMedicalCardId(Long medicalCardId)
            throws ServiceException {
        try {
            return surgeryDao
                    .readSurgeryByMedicalCardId(medicalCardId);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Surgery> findAll() throws ServiceException {
        try {
            return surgeryDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Surgery surgery) throws ServiceException {
        try {
            getTransaction().start();
            if(surgery.getId() != null) {
                Surgery storedSurgery
                        = surgeryDao.readEntityById(surgery.getId());
                if (surgery.getPlaningDate() == null) {
                    surgery.setPlaningDate(
                            storedSurgery.getPlaningDate());
                }
                if (surgeryDao.checkIfDateAfterAdmissionDate(
                        surgery)) {
                    if(storedSurgery != null) {
                        surgeryDao.update(surgery);
                    } else {
                        throw new SurgeryDoesNotExistsException(
                                surgery.getId());
                    }
                } else {
                    throw new SurgeryDateException(
                            surgery.getPlaningDate().getTime());
                }
            } else {
                if (surgery.getPlaningDate() == null) {
                    surgery.setPlaningDate(
                            (GregorianCalendar) Calendar.getInstance());
                }
                if (surgeryDao.checkIfDateAfterAdmissionDate(
                        surgery)) {
                    Long id = surgeryDao.create(surgery);
                    surgery.setId(id);
                } else {
                    throw new SurgeryDateException(
                            surgery.getPlaningDate().getTime());
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
            surgeryDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);

        }
    }
}
