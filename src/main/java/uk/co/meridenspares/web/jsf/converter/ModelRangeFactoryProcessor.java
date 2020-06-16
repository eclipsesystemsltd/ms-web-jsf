package uk.co.meridenspares.web.jsf.converter;

import org.springframework.beans.factory.InitializingBean;

import uk.co.meridenspares.service.api.ModelService;

public class ModelRangeFactoryProcessor implements InitializingBean {

	private ModelService modelService;
	
	/**
	 * @param modelService the modelService to set
	 */
	public final void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		ModelRangeFactory.setModelService(modelService);
	}

}
