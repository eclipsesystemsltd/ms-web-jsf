package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import uk.co.meridenspares.domain.Model;
import uk.co.meridenspares.service.api.ModelService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

@ManagedBean(name="modelBean")
@SessionScoped
public class ModelBean implements Serializable {
	private static final Logger log = Logger.getLogger(ModelBean.class);
	
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    
    private Model[] models;
    private Model model;
    private Integer year = 1980;
    private String name = "X";
    private Long id;

    @ManagedProperty(value="#{modelService}")
    private ModelService modelService;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Long id) {
		this.id = id;
		
		for (Model model : models) {
			if (id == model.getId()) {
				this.model = model;
				this.name = model.getName();
				break;
			}
		}
	}

	/**
	 * @return the modelService
	 */
	public final ModelService getModelService() {
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public final void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

    public Integer[] getModelYears() {
    	try {
    		List<Integer> years = modelService.getModelYears();
			return years.toArray(new Integer[years.size()]);
		}
    	catch (MsServiceException e) {
			log.warn("Unable to get model years", e);
    		return new Integer[]{};
		}
    }
   
    public String yearSubmitted() {
    	log.info("ModelBean yearSubmitted=" + year);
    	
    	try {
    		List<Model> modelsList = modelService.getModelsForYear(year);
			models = modelsList.toArray(new Model[modelsList.size()]);
			return "success";
		}
    	catch (MsServiceException e) {
			log.warn("Exception thrown on year submission", e);
    		models = new Model[]{};
    		return "failure";
		}
    }

	/**
	 * @return the year
	 */
	public String getYear() {
		return year.toString();
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		try {
			this.year = Integer.parseInt(year.trim());
		}
		catch (NumberFormatException e) {
			log.warn("NumberFormatException thrown after setYear() for [" + year + "]", e);
			this.year = 0;
		}
	}

	/**
	 * @return the models
	 */
	public final Model[] getModels() {
		return models;
	}

	/**
	 * @param models the models to set
	 */
	public final void setModels(Model[] models) {
		this.models = models;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the model
	 */
	public final Model getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public final void setModel(Model model) {
		this.model = model;
	}
}
