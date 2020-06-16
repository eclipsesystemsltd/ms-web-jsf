package uk.co.meridenspares.web.jsf.converter;

import uk.co.meridenspares.domain.ModelRange;
import uk.co.meridenspares.service.api.ModelService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

public class ModelRangeFactory {
	
	static private ModelService modelService;

	static public ModelRange findModelRangeByName(String modelRangeName) {
		ModelRange modelRange = null;
		
		try {
			modelRange = modelService.findModelRangeByName(modelRangeName);
		}
		catch (MsServiceException e) {
		}
		
		return modelRange;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public static final void setModelService(ModelService modelService) {
		ModelRangeFactory.modelService = modelService;
	}
}
