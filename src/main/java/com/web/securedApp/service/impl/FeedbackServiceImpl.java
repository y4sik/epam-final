package com.web.securedApp.service.impl;

import com.web.securedApp.model.dao.GenericDao;
import com.web.securedApp.model.dao.impl.CustomerDao;
import com.web.securedApp.model.dao.impl.FeedbackDao;
import com.web.securedApp.model.domain.Feedback;
import com.web.securedApp.model.domain.customer.Customer;
import com.web.securedApp.service.FeedbackService;
import org.apache.log4j.Logger;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger LOGGER = Logger.getLogger(FeedbackServiceImpl.class);
    private GenericDao<Feedback> feedbackDao = new FeedbackDao();
    @Override
    public List<Feedback> getAll() {
        List<Feedback> feedbacks = feedbackDao.getAll();
        if (feedbacks.isEmpty()) {
            LOGGER.info("List feedbacks is null!");
            return null;
        }
        return feedbacks;
    }

    @Override
    public Feedback getById(int id) {
        if (id < 0) {
            return null;
        }
        Feedback feedback = feedbackDao.getById(id);
        if (feedback == null) {
            LOGGER.info("Feedback is null!");
        }
        return feedback;
    }

    @Override
    public int insert(Feedback feedback) {
        if (feedback == null) {
            return -1;
        }
        int id = feedbackDao.insert(feedback);
        if (id == -1) {
            LOGGER.error("Failed to insert feedback!");
        }
        return id;
    }

    @Override
    public boolean delete(Feedback feedback) {
        if (feedback == null) {
            return false;
        }
        boolean checkDelete = feedbackDao.delete(feedback);
        if (!checkDelete) {
            LOGGER.info("Failed to delete feedback!");
        }
        return checkDelete;
    }

    @Override
    public boolean delete(int id) {
        if (id < 0) {
            return false;
        }
        boolean checkDelete = feedbackDao.delete(id);
        if (!checkDelete) {
            LOGGER.info("Failed to delete feedback by id!");
        }
        return checkDelete;
    }

    @Override
    public boolean update(Feedback feedback) {
        if (feedback == null) {
            return false;
        }
        boolean checkUpdate = feedbackDao.update(feedback);
        if (!checkUpdate) {
            LOGGER.info("Failed to update feedback");
        }
        return checkUpdate;
    }
}
