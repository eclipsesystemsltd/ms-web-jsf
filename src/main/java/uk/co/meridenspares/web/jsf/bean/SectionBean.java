package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

import uk.co.meridenspares.domain.Section;
import uk.co.meridenspares.domain.SectionItem;
import uk.co.meridenspares.service.api.SectionService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

@ManagedBean(name="sectionBean")
@RequestScoped
public class SectionBean implements Serializable {
	private static final Logger log = Logger.getLogger(SectionBean.class);
	
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    
    private Section section;
    private Long id;

    @ManagedProperty(value="#{sectionService}")
    transient private SectionService sectionService;

    /**
	 * @return the sectionService
	 */
	public final SectionService getSectionService() {
		return sectionService;
	}

	/**
	 * @param sectionService the sectionService to set
	 */
	public final void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}
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
		
		try {
			section = sectionService.find(id);
		}
		catch (MsServiceException e) {
			log.warn("Unable to find Section for ID [" + id + "]", e);
		}
	}

	/**
	 * Returns an array of SectionItems representing only parts. Note that SectionItems may also include
	 * directives and other information that are not required here.
	 * @return
	 */
	public SectionItem[] getParts() {
		List<SectionItem> parts = new ArrayList<SectionItem>();
		List<SectionItem> elements = section.getSectionItems();
		
		for (SectionItem sectionItem : elements) {
			if (sectionItem.getPartNumber() != null && sectionItem.getPartNumber().length() > 0) {
				parts.add(sectionItem);
			}
		}
		
		return parts.toArray(new SectionItem[parts.size()]);
    }

	/**
	 * @return the section
	 */
	public final Section getSection() {
		return section;
	}

	/**
	 * @param section the section to set
	 */
	public final void setSection(Section section) {
		this.section = section;
	}

}
