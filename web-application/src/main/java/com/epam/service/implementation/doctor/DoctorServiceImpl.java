package com.epam.service.implementation.doctor;

import com.epam.dao.exception.DaoException;
import com.epam.dao.implementation.doctor.DoctorDao;
import com.epam.model.actors.Doctor;
import com.epam.service.exception.ServiceException;
import com.epam.service.implementation.BaseService;
import com.epam.service.implementation.doctor.exception.DoctorCreationByIdException;
import com.epam.service.implementation.doctor.exception.DoctorDoesNotExistsException;

import java.util.List;

public class DoctorServiceImpl extends BaseService
        implements DoctorService {

    private DoctorDao doctorDao;

    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Doctor findById(Long id) throws ServiceException {
        try {
            return doctorDao.readEntityById(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Doctor> findAll() throws ServiceException {
        try {
            return doctorDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void saveFromUserCreation(Doctor doctor)
            throws ServiceException {
        try {
            getTransaction().start();
            if(doctor.getId() != null) {
                Doctor storedSurgery = doctorDao
                        .readEntityById(doctor.getId());
                if(storedSurgery != null) {
                    doctorDao.update(doctor);
                } else {
                    Long id = doctorDao.create(doctor);
                    doctor.setId(id);
                }
            } else {
                throw new DoctorCreationByIdException();
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
    public boolean canBeDeleted(Long id) throws ServiceException {
        try {
            return !(doctorDao.hasDoctorDiagnoses(id)
                    || doctorDao.hasDoctorSurgeries(id));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Doctor doctor) throws ServiceException {
        try {
            getTransaction().start();
            if(doctor.getId() != null) {
                Doctor storedSurgery = doctorDao
                        .readEntityById(doctor.getId());
                if(storedSurgery != null) {
                    doctorDao.update(doctor);
                } else {
                    throw new DoctorDoesNotExistsException(
                            doctor.getId());
                }
            } else {
                Long id = doctorDao.create(doctor);
                doctor.setId(id);
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
            doctorDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
