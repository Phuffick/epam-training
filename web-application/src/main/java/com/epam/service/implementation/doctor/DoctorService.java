package com.epam.service.implementation.doctor;

import com.epam.model.actors.Doctor;
import com.epam.service.exception.ServiceException;

import java.util.List;

public interface DoctorService {

    /**
     * Finds doctor by id
     * @param id of doctor to find
     * @return doctor
     * @throws ServiceException
     */
    Doctor findById(Long id) throws ServiceException;

    /**
     * Finds all doctors
     * @return list of doctors
     * @throws ServiceException
     */
    List<Doctor> findAll() throws ServiceException;

    /**
     * Saves doctor's data
     * @param doctor to save
     */
    void save(Doctor doctor) throws ServiceException;

    /**
     * Saves doctor's data from create user page
     * @param doctor to save
     */
    void saveFromUserCreation(Doctor doctor)
            throws ServiceException;

    /**
     * Can doctor be deleted method
     */
    boolean canBeDeleted(Long id) throws ServiceException;

    /**
     * Deletes doctor
     * @param id to delete
     */
    void delete(Long id) throws ServiceException;
}
