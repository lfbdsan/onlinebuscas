package br.com.onlinebuscas.support;
 
import br.com.onlinebuscas.model.dao.HibernateDAO;
import br.com.onlinebuscas.model.dao.InterfaceDAO;
import br.com.onlinebuscas.model.entities.Empresa;
import br.com.onlinebuscas.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
@ManagedBean(name="bbEmpresa")
@RequestScoped
public class BbEmpresa
  implements Serializable
{
  private static final long serialVersionUID = 1L;
   
  public List<Empresa> getEmpresas()
  {
    InterfaceDAO<Empresa> empresaDAO = new HibernateDAO<Empresa>(Empresa.class, FacesContextUtil.getRequestSession());
    return empresaDAO.getEntities();
  }
}