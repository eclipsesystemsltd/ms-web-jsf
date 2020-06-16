package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import uk.co.meridenspares.domain.Contact;
import uk.co.meridenspares.service.api.ContactService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

@ManagedBean(name="contactBean")
@RequestScoped
public class ContactBean implements Serializable {
	private static final Logger log = Logger.getLogger(ContactBean.class);
	
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    @ManagedProperty(value="#{contactService}")
    ContactService contactService;

    List<Contact> contactList;

    private Long id;
    private String name;
    private String description;
    private DateTime createdDate;
    private DateTime lastContactedDate;

    /**
     * Add contact.
     *
     * @return String - Response Message
     */
    public String addContact() {
    	
    	log.debug("Adding contact, name = " + name + ", description = " + description);
    	
        try {
        	Contact contact = new Contact();
//        	contact.setId(id);
        	contact.setName(name);
        	contact.setDescription(description);
        	createdDate = new DateTime();
        	contact.setCreatedDate(createdDate);
        	lastContactedDate = createdDate;
        	contact.setLastContactedDate(lastContactedDate);
        	// Persist contact
            getContactService().save(contact);
            return SUCCESS;
        } catch (MsServiceException e) {
            e.printStackTrace();
        }  
 
        return ERROR;
    }
 
    /**
     * Reset Fields
     *
     */
    public void reset() {
        this.setId(0L);
        this.setName("");
        this.setDescription("");
    }

	/**
	 * @return the contactService
	 */
	public final ContactService getContactService() {
		return contactService;
	}

	/**
	 * @param contactService the contactService to set
	 */
	public final void setContactService(ContactService contactService) {
		log.debug("ContactService has been set.");
		this.contactService = contactService;
	}

    /**
     * Get Contact List
     *
     * @return List - Contact List
     */
    public List<Contact> getContactList() {
    	contactList = new ArrayList<Contact>();
    	
    	try {
			contactList.addAll(getContactService().findAll());
		}
    	catch (MsServiceException e) {
			e.printStackTrace();
		}
    	
        return contactList;
    }

	/**
	 * @param contactList the contactList to set
	 */
	public final void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
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
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the lastContactedDate
	 */
	public final DateTime getLastContactedDate() {
		return lastContactedDate;
	}

	/**
	 * @param lastContactedDate the lastContactedDate to set
	 */
	public final void setLastContactedDate(DateTime lastContactedDate) {
		this.lastContactedDate = lastContactedDate;
	}
}
