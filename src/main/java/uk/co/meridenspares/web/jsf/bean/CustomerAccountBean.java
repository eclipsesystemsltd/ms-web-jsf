package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import uk.co.meridenspares.domain.Customer;
import uk.co.meridenspares.service.api.CustomerService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

@ManagedBean(name="customerAccountBean")
@SessionScoped
public class CustomerAccountBean implements Serializable {
	private static final Logger log = Logger.getLogger(CustomerAccountBean.class);
	
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    
    private Long id;
    
	private Integer customerId;
	
	private String title;
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private String mobile;
	
	private String addressFirstLine;
	
	private String addressSecondLine;
	
	private String town;
	
	private String county;
	
	private String postcode;
	
	private String email;
	
	private String password;
	
	private int status;
	
	private Date createdDate;
	
	private String createdBy;
	
	private String lastModifiedBy;
	
	private Date lastModifiedDate;
  
    @ManagedProperty(value="#{customerService}")
    private CustomerService customerService;

    public String createAccount() {
    	log.debug("Creating account for " + title + " " + firstName + " " + lastName);
    	
        try {
        	Customer customer = new Customer();
        	customer.setCustomerId(1);	//TEMP
        	customer.setTitle(title);
        	customer.setFirstName(firstName);
        	customer.setLastName(lastName);
        	customer.setAddressFirstLine(addressFirstLine);
        	customer.setAddressSecondLine(addressSecondLine);
        	customer.setTown(town);
        	customer.setCounty(county);
        	customer.setPostcode(postcode);
        	customer.setPhone(phone);
        	customer.setMobile(mobile);
        	customer.setEmail(email);
        	customer.setPassword(password);
        	createdDate = new Date();
        	// Persist customer
        	getCustomerService().save(customer);
            return SUCCESS;
        } catch (MsServiceException e) {
            e.printStackTrace();
        }  
 
        return ERROR;
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
	 * @return the customerService
	 */
	public final CustomerService getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public final void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * @return the customerId
	 */
	public final Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public final void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public final String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the phone
	 */
	public final String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public final void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobile
	 */
	public final String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public final void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the addressFirstLine
	 */
	public final String getAddressFirstLine() {
		return addressFirstLine;
	}

	/**
	 * @param addressFirstLine the addressFirstLine to set
	 */
	public final void setAddressFirstLine(String addressFirstLine) {
		this.addressFirstLine = addressFirstLine;
	}

	/**
	 * @return the addressSecondLine
	 */
	public final String getAddressSecondLine() {
		return addressSecondLine;
	}

	/**
	 * @param addressSecondLine the addressSecondLine to set
	 */
	public final void setAddressSecondLine(String addressSecondLine) {
		this.addressSecondLine = addressSecondLine;
	}

	/**
	 * @return the town
	 */
	public final String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public final void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the county
	 */
	public final String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public final void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the postcode
	 */
	public final String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public final void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the status
	 */
	public final int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the createdDate
	 */
	public final Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdBy
	 */
	public final String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public final void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public final String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public final void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public final Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public final void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
