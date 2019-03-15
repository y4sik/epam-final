package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.impl.AddressDao;
import com.web.securedApp.model.domain.customer.Address;
import com.web.securedApp.service.AddressService;
import org.apache.log4j.Logger;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private static final Logger LOGGER = Logger.getLogger(AddressService.class);
    private GenericDao<Address> addressDao = new AddressDao();

    @Override
    public List<Address> getAll() {
        List<Address> addresses = addressDao.getAll();
        if (addresses.isEmpty()) {
            LOGGER.info("List addresses is null!");
            return null;
        }
        return addresses;
    }

    @Override
    public Address getById(int id) {
        if (id < 0) {
            return null;
        }
        Address address = addressDao.getById(id);
        if (address == null) {
            LOGGER.info("Address is null");
        }
        return address;
    }

    @Override
    public int insert(Address address) {
        if (address == null) {
            return -1;
        }
        int id = addressDao.insert(address);
        if (id == -1) {
            LOGGER.error("Failed to insert address!");
        }
        return id;
    }

    @Override
    public boolean delete(Address address) {
        if (address == null) {
            return false;
        }
        boolean checkDelete = addressDao.delete(address);
        if (!checkDelete) {
            LOGGER.info("Failed to delete address!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = addressDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete address by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Address address) {
        if (address == null) {
            return false;
        }
        boolean checkUpdate = addressDao.update(address);
        if (!checkUpdate) {
            LOGGER.info("Failed to update address");
        }
        return checkUpdate;
    }
}
