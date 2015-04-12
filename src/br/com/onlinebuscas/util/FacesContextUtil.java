package br.com.onlinebuscas.util;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.hibernate.Session;

public class FacesContextUtil
{
  private static final String HIBERNATE_SESSION = "hibernate_session";
  
  public static void setRequestSession(Session session)
  {
    FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("hibernate_session", session);
  }
  
  public static Session getRequestSession()
  {
    return (Session)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("hibernate_session");
  }
}
