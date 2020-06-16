package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="indexBean")
@RequestScoped
public class IndexPageBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public String actionRegistrationYearPopup() {
		return "registrationYearPopup";
	}
}
