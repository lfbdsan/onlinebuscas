package br.com.onlinebuscas.support;

import br.com.onlinebuscas.model.dao.HibernateDAO;
import br.com.onlinebuscas.model.dao.InterfaceDAO;
import br.com.onlinebuscas.model.entities.Plano;
import br.com.onlinebuscas.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbPlano")
@RequestScoped
public class BbPlano
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  public List<Plano> getPlanos()
  {
    InterfaceDAO<Plano> planoDAO = new HibernateDAO<Plano>(Plano.class, FacesContextUtil.getRequestSession());
    return planoDAO.getEntities();
  }
}
