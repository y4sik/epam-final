package com.web.securedApp.controller.command;

import com.web.securedApp.controller.resource.ConfigurationManager;
import com.web.securedApp.controller.servlet.SessionRequestContent;
import com.web.securedApp.model.domain.product.Product;
import com.web.securedApp.service.ProductService;
import com.web.securedApp.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

public class ProductCommand implements ActionCommand {
    private static final Logger LOGGER = Logger.getLogger(ProductCommand.class);
    private static final String PARAM_NAME_ID = "productId";
    private ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(SessionRequestContent request) {
        String page = null;
        int id = Integer.valueOf(request.getRequestParameter(PARAM_NAME_ID));
        Product product = productService.getProductWithPictureFeedbackOrderById(id);
        if (product != null) {
            request.setRequestAttribute("product", product);
            page = ConfigurationManager.getProperty("path.page.product");
            LOGGER.info("Product display success!");
        } else {
            LOGGER.info("Product display failed!");
            page = ConfigurationManager.getProperty("path.page.error");
        }
        return page;
    }
}
