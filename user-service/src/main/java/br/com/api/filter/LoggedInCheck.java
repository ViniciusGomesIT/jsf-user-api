package br.com.api.filter;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.api.entity.UserEntity;

public class LoggedInCheck implements PhaseListener {

	private static final long serialVersionUID = 8174314772879213074L;

	public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    public void beforePhase(PhaseEvent event) {
    	
    }

    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        
        boolean loginPage = fc.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true : false;
        
        if ( !loginPage && !loggedIn() ) {
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "logout");
        }
    }

    private boolean loggedIn() {
        return isLoggedIn();
    }
    
    public static boolean isLoggedIn() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        
        UserEntity logedUser = (UserEntity) session.getAttribute("userLogged");
        
        return (logedUser != null);
    }
}
