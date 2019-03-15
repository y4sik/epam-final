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

public class HomeByCategoryCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(HomeByCategoryCommand.class);
    private static final String PARAM_NAME_ID = "categoryId";
    private CategoryService categoryService = new CategoryServiceImpl();
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(SessionRequestContent request) {
        int id = Integer.valueOf(request.getRequestParameter(PARAM_NAME_ID));
        List<Category> categories = categoryService.getAll();
        List<Product> products = productService.getAllProductWithPictureFeedbackOrderByCategory(id);
        request.setRequestAttribute("categories", categories);
        request.setRequestAttribute("products", products);
        LOGGER.info("Go home by category");
        return ConfigurationManager.getProperty("path.page.home");
    }
}
