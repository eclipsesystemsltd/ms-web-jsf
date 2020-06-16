package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="confirmationBean")
@SessionScoped
public class ConfirmationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message = "X";
	private int increment;
	
	TimerTask tt;
	Timer timer;
	
    class IncrementTask extends TimerTask {
        public void run() {
        	++increment;
        }
    }

	public ConfirmationBean() {
	}

	public void confirm(ActionEvent event) {
		message = "Your order is confirmed";
        timer = new Timer();
        timer.schedule(new IncrementTask(), 0, 1000);
	}
	
	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message + increment;
	}

	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}
}
