package uk.co.meridenspares.web.jsf.listeners;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import uk.co.meridenspares.web.jsf.bean.CustomerBean;

public class ForceLoginPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	public ForceLoginPhaseListener() {
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		String viewId = context.getViewRoot().getViewId();
		
		// If about to render confirm page
		if (viewId.equals("/confirm.xhtml")) {
			Application app = context.getApplication();
			CustomerBean customerBean = app.evaluateExpressionGet(context, "#{customerBean}", CustomerBean.class);
			
			// If customer is null then not currently logged in
			if (customerBean.getCustomer() == null) {
				customerBean.setOriginalViewId(viewId);
				ViewHandler viewHandler = app.getViewHandler();
				UIViewRoot viewRoot = viewHandler.createView(context, "/login.xhtml");
				context.setViewRoot(viewRoot);
			}
		}
	}

	@Override
	public void afterPhase(PhaseEvent event) {
	}
}
