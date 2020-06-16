package uk.co.meridenspares.web.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import uk.co.meridenspares.domain.Basket;
import uk.co.meridenspares.domain.BasketItem;
import uk.co.meridenspares.domain.Customer;
import uk.co.meridenspares.domain.SectionItem;
import uk.co.meridenspares.domain.StockItem;
import uk.co.meridenspares.service.api.BasketService;
import uk.co.meridenspares.service.api.exception.MsServiceException;

@ManagedBean(name="basketBean")
@SessionScoped
public class BasketBean implements Serializable {
	private static final Logger log = Logger.getLogger(BasketBean.class);
	
	private static final long serialVersionUID = 1L;
    private static final String SUCCESS = "success";
    private static final String ERROR   = "error";
    
    private Basket basket;
    private Customer customer;
    private static Collection<SelectItem> availableItems;
    
    static {
    	availableItems = new ArrayList<SelectItem>();
    	for (int i=0; i<11; ++i) {
    		availableItems.add(new SelectItem(i));
    	}
    }

    @ManagedProperty(value="#{basketService}")
    private BasketService basketService;

	public String checkout() {
		return "confirm";
	}

	/**
	 * @return the basketService
	 */
	public final BasketService getBasketService() {
		return basketService;
	}

	/**
	 * @param basketService the basketService to set
	 */
	public final void setBasketService(BasketService basketService) {
		this.basketService = basketService;
    	basket = new Basket();
    	
    	try {
			basketService.save(basket);
		}
    	catch (MsServiceException e) {
		}
	}

	public void addItem(StockItem stockItem) {
		log.debug("Adding stockItem to basket: " + stockItem.getDescription());
		
		try {
			basketService.add(basket, stockItem, 1);
		}
		catch (MsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeItem(StockItem stockItem) {
		log.debug("Removing stockItem from basket: " + stockItem.getDescription());
		
		try {
			basketService.remove(basket, stockItem, 1);
		}
		catch (MsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeItem(BasketItem basketItem) {
		log.debug("Removing basketItem from basket: " + basketItem.getStockItem().getDescription());
		
		try {
			basketService.remove(basket, basketItem, 1);
		}
		catch (MsServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getBasketQuantity(SectionItem item) {
		return basketService.getBasketQuantity(basket, item);
	}

	/**
	 * @return the items
	 */
	public final List<BasketItem> getItems() {
		List<BasketItem> items = basketService.getItems(basket);
		return items;
	}

	/**
	 * Effectively redundant - used for SelectOneMenu.
	 * @param item
	 */
	public Collection<SelectItem> availableItems(SectionItem item) {
		return availableItems;
	}

	/**
	 * Effectively redundant - used for SelectOneMenu.
	 * @param num
	 */
	public void setNumberInBasket(Integer num) {
		System.out.println("RXED setNumberInBasket " + num);
	}
	
	/**
	 * Effectively redundant - used for SelectOneMenu.
	 * @param item
	 * @return
	 */
	public Integer numberInBasket(SectionItem item) {
		return getBasketQuantity(item);
	}

	/**
	 * Effectively redundant - used for SelectOneMenu.
	 * @param event
	 */
	public void changeCurrentItem(ValueChangeEvent event) {
		Integer val = (Integer) event.getNewValue();
		System.out.println("RXED " + val);
	}
}
