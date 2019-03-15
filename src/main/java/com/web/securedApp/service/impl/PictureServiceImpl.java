package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.impl.PictureDao;
import com.web.securedApp.model.domain.product.Picture;
import com.web.securedApp.service.PictureService;
import org.apache.log4j.Logger;

import java.util.List;

public class PictureServiceImpl implements PictureService {
    private static final Logger LOGGER = Logger.getLogger(PictureServiceImpl.class);
    private GenericDao<Picture> pictureDao = new PictureDao();

    @Override
    public List<Picture> getAll() {
        List<Picture> pictures = pictureDao.getAll();
        if (pictures.isEmpty()) {
            LOGGER.info("List pictures is null!");
            return null;
        }
        return pictures;
    }

    @Override
    public Picture getById(int id) {
        if (id < 0) {
            return null;
        }
        Picture picture = pictureDao.getById(id);
        if (picture == null) {
            LOGGER.info("Picture is null!");
        }
        return picture;
    }

    @Override
    public int insert(Picture picture) {
        if (picture == null) {
            return -1;
        }
        int id = pictureDao.insert(picture);
        if (id == -1) {
            LOGGER.error("Failed to insert picture!");
        }
        return id;
    }

    @Override
    public boolean delete(Picture picture) {
        if (picture == null) {
            return false;
        }
        boolean checkDelete = pictureDao.delete(picture);
        if (!checkDelete) {
            LOGGER.info("Failed to delete picture!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = pictureDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete picture by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Picture picture) {
        if (picture == null) {
            return false;
        }
        boolean checkUpdate = pictureDao.update(picture);
        if (!checkUpdate) {
            LOGGER.info("Failed to update picture");
        }
        return checkUpdate;
    }
}
