package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

@ManagedBean(name="user")
@SessionScoped
public class UserBean implements Serializable {
	private static final Logger log = Logger.getLogger(ContactBean.class);
	private static final long serialVersionUID = 1L;
	private String name = "";
	private String password;
	
	/**
	 * @return the name
	 */
	public final String getName() {
    	log.debug("getName = " + name);
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
    	log.debug("setName = " + name);
		this.name = name;
	}
	
	/**
	 * @return the password
	 */
	public final String getPassword() {
    	log.debug("getPassword = " + password);
		return password;
	}
	
	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
    	log.debug("setPassword = " + password);
		this.password = password;
	}
	
}
