package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.Future;

import org.apache.log4j.Logger;

import uk.co.meridenspares.web.jsf.domain.CreditCard;

@ManagedBean(name="paymentBean")
@SessionScoped
public class PaymentBean implements Serializable {
	private static final Logger log = Logger.getLogger(PaymentBean.class);
	
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";

    private double amount;
    
//    @LuhnCheckValidator
    private CreditCard card;
    
    @Future
    private Date date = new Date();
    
	/**
	 * @return the amount
	 */
	public final double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public final void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the card
	 */
	public final CreditCard getCard() {
		return card;
	}
	/**
	 * @param card the card to set
	 */
	public final void setCard(CreditCard card) {
		this.card = card;
	}
	/**
	 * @return the date
	 */
	public final Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public final void setDate(Date date) {
		this.date = date;
	}

}
