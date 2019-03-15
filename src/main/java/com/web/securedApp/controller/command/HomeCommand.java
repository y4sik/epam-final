package com.web.securedApp.controller.command;

import com.web.securedApp.controller.resource.ConfigurationManager;
import com.web.securedApp.controller.servlet.SessionRequestContent;
import com.web.securedApp.model.domain.product.Category;
import com.web.securedApp.model.domain.product.Product;
import com.web.securedApp.service.CategoryService;
import com.web.securedApp.service.ProductService;
import com.web.securedApp.service.impl.CategoryServiceImpl;
import com.web.securedApp.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class HomeCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(ActionCommand.class);
    private CategoryService categoryService = new CategoryServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(SessionRequestContent request) {
        List<Category> categories = categoryService.getAll();
        List<Product> products = productService.getAllProductsWithPictureFeedbackOrder();
        request.setRequestAttribute("categories", categories);
        request.setRequestAttribute("products", products);
        LOGGER.info("Go to Home");
        return ConfigurationManager.getProperty("path.page.home");
    }
}
