package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import services.interfaces.IdentificationServiceLocal;
import domain.BusMan;
import domain.Driver;
import domain.Passenger;
import domain.User;

@ManagedBean
@SessionScoped
public class LoginBean {

	private User user = new User();
	@EJB
	private IdentificationServiceLocal identificationServiceLocal;

	@ManagedProperty("#{identity}")
	private IdentityBean identityBean;

	private String login;
	private String pwd;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String doLogin() {
		String navigateTo = null;
		Object found = identificationServiceLocal.login(login, pwd);
		if (found != null) {
			identityBean.setObject(found);
			if (found instanceof BusMan) {
				navigateTo = "/admin/BusMan/home?faces-redirect=true";
			} else if (found instanceof Passenger) {
				navigateTo = "/admin/pssenger/home?faces-redirect=true";
			} else if (found instanceof Driver) {
				navigateTo = "/?faces-redirect=true";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"NON AUTORISE", null

					));
		}
		return navigateTo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
