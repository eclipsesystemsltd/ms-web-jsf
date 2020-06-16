package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import uk.co.meridenspares.domain.Customer;
import uk.co.meridenspares.service.api.AuthenticationService;
import uk.co.meridenspares.service.api.CustomerService;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private static final Logger log = Logger.getLogger(LoginBean.class);
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    private static final String LOGIN   = "login";
	
	private String username;
	private String password;
	private boolean loggedIn;
	private CustomerBean customerBean;
	
    @ManagedProperty(value="#{authenticationService}")
    transient private AuthenticationService authenticationService;
	
    @ManagedProperty(value="#{customerService}")
    transient private CustomerService customerService;

	public LoginBean() {
	}

    public String login() {
    	log.debug("Logging in " + username + " " + password);
        loggedIn = authenticationService.login(username, password);
        password = "";
    	log.debug("Login " + (loggedIn ? "successful" : "failed"));
        
        if (loggedIn) {
        	List<Customer> customers = customerService.getCustomerByEmail(username);
        	customerBean.setCustomer(customers.get(0));
        	// Get previous view
        	String viewId = customerBean.getOriginalViewId();
        	
        	if (viewId != null) {
        		FacesContext context = FacesContext.getCurrentInstance();
        		Application app = context.getApplication();
        		ViewHandler viewHandler = app.getViewHandler();
        		UIViewRoot root = viewHandler.createView(context, viewId);
        		context.setViewRoot(root);
        		customerBean.setOriginalViewId(null);
        		return null;
        	}
        }
        
        return loggedIn ? SUCCESS : ERROR;
    }

    public String toggle() {
    	log.debug("Login toggle called");
    	
    	String result = ERROR;
    			
    	if (!loggedIn) {
        	result = LOGIN;
    	}
    	else {
        	log.debug("Logging out " + username);
            password = "";
            username = "";
            loggedIn = false;
            customerBean.removeCustomer();
        	result = SUCCESS;
    	}
        
        return result;
    }
    
    public String getDisplayTag() {
    	return loggedIn ? "Log out" : "Log in";
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
	 * @return the username
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the loggedIn
	 */
	public final boolean isLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn the loggedIn to set
	 */
	public final void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the authenticationService
	 */
	public final AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	/**
	 * @param authenticationService the authenticationService to set
	 */
	public final void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
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
	 * @return the customerBean
	 */
	public final CustomerBean getCustomerBean() {
		return customerBean;
	}

	/**
	 * @param customerBean the customerBean to set
	 */
	public final void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}
}
