package au.moodflip.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import au.moodflip.service.ProductManager;

public class InventoryController implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());

	private ProductManager productManager;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		
		String now = (new Date()).toString();
        logger.info("Returning hello view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productManager.getProducts());
        
//        return new ModelAndView("hello", "now", now);
        return new ModelAndView("hello", "model", myModel);
	}
	
	public void setProductManager(ProductManager productManager){
		this.productManager = productManager;
	}

}
