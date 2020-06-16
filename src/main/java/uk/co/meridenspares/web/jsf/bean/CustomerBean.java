package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import uk.co.meridenspares.domain.Customer;

@ManagedBean(name="customerBean")
@SessionScoped
public class CustomerBean implements Serializable {
	private static final Logger log = Logger.getLogger(CustomerBean.class);
	
	private static final long serialVersionUID = 1L;
    
    private Customer customer;
    
    private String originalViewId;

	/**
	 * @return the customer
	 */
	public final Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public final void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public final void removeCustomer() {
		log.debug("removeCustomer");
		customer = null;
	}

	/**
	 * @return the originalViewId
	 */
	public final String getOriginalViewId() {
		return originalViewId;
	}

	/**
	 * @param originalViewId the originalViewId to set
	 */
	public final void setOriginalViewId(String originalViewId) {
		this.originalViewId = originalViewId;
	}
 }
