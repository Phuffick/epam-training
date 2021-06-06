package com.epam.service.implementation.procedure;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.procedure.ProcedureDao;
import com.epam.model.medicalparts.Procedure;
import com.epam.service.exception.ServiceException;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.procedure.exception.ProcedureDateException;
import com.epam.service.implementation.procedure.exception.ProcedureRequiredCountConsumedException;
import com.epam.service.implementation.procedure.exception.ProcedureRequiredCountException;
import com.epam.service.implementation.surgery.exception.SurgeryDateException;
import com.epam.service.implementation.surgery.exception.SurgeryDoesNotExistsException;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ProcedureServiceImpl extends BaseService
        implements ProcedureService {

    private ProcedureDao procedureDao;

    public void setProcedureDao(ProcedureDao procedureDao) {
        this.procedureDao = procedureDao;
    }

    @Override
    public Procedure findById(Long id) throws ServiceException {
        try {
            return procedureDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Procedure> findByMedicalCardId(Long medicalCardId)
            throws ServiceException {
        try {
            return procedureDao
                    .readProcedureByMedicalCardId(medicalCardId);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Procedure> findAll() throws ServiceException {
        try {
            return procedureDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Procedure procedure) throws ServiceException {
        try {
            getTransaction().start();
            if(procedure.getId() != null) {
                Procedure storedProcedure = procedureDao
                        .readEntityById(procedure.getId());
                if (procedure.getDate() == null) {
                    procedure.setDate(storedProcedure.getDate());
                }
                if (!procedureDao
                        .checkIfDateAfterAdmissionDate(procedure)) {
                    throw new ProcedureDateException(
                            procedure.getDate().getTime());
                }
                if (!procedureDao
                        .checkIfRequiredCountIsGraterThanDoneCount(
                                procedure)) {
                    throw new ProcedureRequiredCountException(
                            procedure.getRequiredCountConsumed());
                }
                if (!procedureDao
                        .checkIfDoneCountIsGraterThanRequiredCount(
                                procedure)) {
                    throw new ProcedureRequiredCountConsumedException(
                            procedure.getRequiredCountConsumed());
                }
                if(storedProcedure != null) {
                    procedureDao.update(procedure);
                } else {
                    throw new SurgeryDoesNotExistsException(
                            procedure.getId());
                }
            } else {
                if (procedure.getDate() == null) {
                    procedure.setDate(
                            (GregorianCalendar) Calendar.getInstance());
                }
                if (procedure.getRequiredCountConsumed()
                        .compareTo(procedure.getRequiredCount()) > 0) {
                    throw new ProcedureRequiredCountConsumedException(
                            procedure.getRequiredCountConsumed());
                }
                Long id = procedureDao.create(procedure);
                procedure.setId(id);
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
            procedureDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);

        }
    }
}
