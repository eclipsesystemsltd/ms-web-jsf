package uk.co.meridenspares.web.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import uk.co.meridenspares.domain.Model;
import uk.co.meridenspares.domain.ModelRange;
import uk.co.meridenspares.domain.Section;
import uk.co.meridenspares.domain.SectionItem;
import uk.co.meridenspares.service.api.ModelService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

@ManagedBean(name="modelSelectorBean")
@SessionScoped
public class ModelSelectorBean {
	
    private List<SelectItem> modelRangeItems = new ArrayList<SelectItem>();
    private List<SelectItem> modelYearItems = new ArrayList<SelectItem>();
    private List<SelectItem> modelItems = new ArrayList<SelectItem>();
    private List<SelectItem> sectionItems = new ArrayList<SelectItem>();
    private HtmlSelectOneMenu modelRangeMenu;
    private HtmlSelectOneMenu modelYearMenu;
    private HtmlSelectOneMenu modelMenu;
    private HtmlSelectOneMenu sectionMenu;
    
    boolean modelRangeItemsFilled = false;
    
    ModelRange modelRange;
    Integer year;
    Model model;
    Section section;
    
    @ManagedProperty(value="#{modelService}")
    private ModelService modelService;

    // Actions ------------------------------------------------------------------------------------

    public void submit() {
        // Show selection and input results as informal message.
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("You have chosen: ......"));
    }

    // Changers -----------------------------------------------------------------------------------

    public void changeModelRangeMenu(ValueChangeEvent event) {
        try {
	        // Get selected modelRange.
	    	modelRange = (ModelRange) event.getNewValue();
	
	        if (modelRange != null) {
	            // Fill model year menu.
				fillModelYearItems(modelService.getModelYears(modelRange));
	        }
	        
	        clearModelItems();
	        clearSectionItems();
		}
        catch (MsServiceException e) {
		}

        // Reset child menu's. This is only possible when using component binding.
        modelMenu.setValue(null);
        sectionMenu.setValue(null);
        year = null;
        model = null;
        section = null;

        // Skip validation of non-immediate components and invocation of the submit() method.
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void changeModelYearMenu(ValueChangeEvent event) {
        try {
	        // Get selected year.
	    	year = (Integer) event.getNewValue();
	
	        if (year != null) {
	            // Fill model menu.
				fillModelItems(modelService.getModelsForYear(year));
	        }
	        
	        clearSectionItems();
		}
        catch (MsServiceException e) {
		}

        // Reset child menu. This is only possible when using component binding.
        sectionMenu.setValue(null);
        model = null;
        section = null;

        // Skip validation of non-immediate components and invocation of the submit() method.
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void changeModelMenu(ValueChangeEvent event) {
        try {
	        // Get selected model name.
	    	String modelName = (String) event.getNewValue();
	
	    	model = modelService.findModelByName(modelName, year);
	    	
	        if (model != null) {
	            // Fill section menu.
	        	// fillSectionItems(modelService.getSections(model));
				fillSectionItems(model.getSections());
	        }
		}
        catch (MsServiceException e) {
		}
        
        section = null;

        // Skip validation of non-immediate components and invocation of the submit() method.
        FacesContext.getCurrentInstance().renderResponse();
    }

    public void changeSectionMenu(ValueChangeEvent event) {
        // Get selected section name.
    	String sectionName = (String) event.getNewValue();
    	
    	//TODO consider using map
    	if (model != null) {
	    	for (Section sec : model.getSections()) {
	    		if (sectionName.equals(sec.getTitle())) {
	    			section = sec;
	    			break;
	    		}
	    	}
    	}
    }
	
    // Render Indicators --------------------------------------------------------------------------
    
    public boolean isYearSelected() {
    	return year != null;
    }
    
    public boolean isModelSelected() {
    	return model != null;
    }
    
    public boolean isSectionSelected() {
    	return section != null;
    }

    // Fillers ------------------------------------------------------------------------------------


    // Getters ------------------------------------------------------------------------------------

    public List<SelectItem> getModelRangeItems() {
       	if (!modelRangeItemsFilled) {
			try {
		       	List<ModelRange> modelRanges = null;
				modelRanges = modelService.getModelRanges();
				
		    	for (ModelRange modelRange : modelRanges) {
		    		modelRangeItems.add(new SelectItem(modelRange));
		    	}
		    	
		    	modelRangeItemsFilled = true;
			}
			catch (MsServiceException e) {
				//Ignore for now
			}
       	}
		
        return modelRangeItems;
    }

    public List<SelectItem> getModelYearItems() {
        return modelYearItems;
    }

    public List<SelectItem> getModelItems() {
        return modelItems;
    }

    public List<SelectItem> getSectionItems() {
        return sectionItems;
    }

    public HtmlSelectOneMenu getModelRangeMenu() {
        return modelRangeMenu;
    }

    public HtmlSelectOneMenu getModelYearMenu() {
        return modelYearMenu;
    }

    public HtmlSelectOneMenu getModelMenu() {
        return modelMenu;
    }

    public HtmlSelectOneMenu getSectionMenu() {
        return sectionMenu;
    }
    
    public List<SectionItem> getParts() {
		List<SectionItem> parts = new ArrayList<SectionItem>();
		List<SectionItem> elements = section.getSectionItems();
		
		for (SectionItem sectionItem : elements) {
			if (sectionItem.getPartNumber() != null && sectionItem.getPartNumber().length() > 0) {
				parts.add(sectionItem);
			}
		}
		
		return parts;
    }

    // Setters ------------------------------------------------------------------------------------

    public void setModelRangeMenu(HtmlSelectOneMenu modelRangeMenu) {
        this.modelRangeMenu = modelRangeMenu;
    }

    public void setModelYearMenu(HtmlSelectOneMenu modelYearMenu) {
        this.modelYearMenu = modelYearMenu;
    }

    public void setModelMenu(HtmlSelectOneMenu modelMenu) {
        this.modelMenu = modelMenu;
    }

    public void setSectionMenu(HtmlSelectOneMenu sectionMenu) {
        this.sectionMenu = sectionMenu;
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

    // Private ------------------------------------------------------------------------------------

	private void fillModelYearItems(List<Integer> modelRangeYears) {
		modelYearItems = new ArrayList<SelectItem>();
		
    	for (Integer year : modelRangeYears) {
    		modelYearItems.add(new SelectItem(year));
    	}
    }

    private void fillModelItems(List<Model> models) {
    	modelItems = new ArrayList<SelectItem>();
		
    	for (Model model : models) {
    		modelItems.add(new SelectItem(model));
    	}
    }

    private void fillSectionItems(List<Section> sections) {
    	sectionItems = new ArrayList<SelectItem>();
		
    	for (Section section : sections) {
    		sectionItems.add(new SelectItem(section));
    	}
    }

    private void clearModelItems() {
    	modelItems = new ArrayList<SelectItem>();
    }

    private void clearSectionItems() {
    	sectionItems = new ArrayList<SelectItem>();
    }

}
