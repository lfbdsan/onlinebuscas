package br.com.onlinebuscas.support;

import br.com.onlinebuscas.model.dao.HibernateDAO;
import br.com.onlinebuscas.model.dao.InterfaceDAO;
import br.com.onlinebuscas.model.entities.Usuario;
import br.com.onlinebuscas.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="bbUsuario")
@RequestScoped
public class BbUsuario
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  public List<Usuario> getUsuarios()
  {
    InterfaceDAO<Usuario> usuarioDAO = new HibernateDAO<Usuario>(Usuario.class, FacesContextUtil.getRequestSession());
    return usuarioDAO.getEntities();
  }
}
