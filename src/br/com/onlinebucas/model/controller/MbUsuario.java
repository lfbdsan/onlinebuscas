package br.com.onlinebucas.model.controller;
 
import br.com.onlinebuscas.conversores.ConverterSHA1;
import br.com.onlinebuscas.model.dao.HibernateDAO;
import br.com.onlinebuscas.model.dao.InterfaceDAO;
import br.com.onlinebuscas.model.entities.Usuario;
import br.com.onlinebuscas.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name="mbUsuario")
@RequestScoped
public class MbUsuario
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String confereSenha;
  private Usuario usuario = new Usuario();
  private List<Usuario> usuarios;
   
  private InterfaceDAO<Usuario> usuarioDAO()
  {
    InterfaceDAO<Usuario> usuarioDAO = new HibernateDAO<Usuario>(Usuario.class, FacesContextUtil.getRequestSession());
    return usuarioDAO;
  }
   
  public String limpUsuario()
  {
    this.usuario = new Usuario();
    return editUsuario();
  }
   
  public String editUsuario()
  {
    return "/restrict/cadastrar_usuario.jsf";
  }
   
  public String addUsuario()
  {
    if ((this.usuario.getIdUsuario() == null) || (this.usuario.getIdUsuario().intValue() == 0)) {
      insertUsuario();
    } else {
      updateUsuario();
    }
    limpUsuario();
    return null;
  }
   
  private void insertUsuario()
  {
    this.usuario.setSenha(ConverterSHA1.cipher(this.usuario.getSenha()));
    if (this.usuario.getSenha() == null ? this.confereSenha == null : this.usuario.getSenha().equals(ConverterSHA1.cipher(this.confereSenha)))
    {
      this.usuario.setPermissao("ROLE_USER");
      this.usuario.setStatus(1);
      this.usuario.setEnable(1);
      usuarioDAO().save(this.usuario);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravaзгo efetuada com sucesso", ""));
    }
    else
    {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas nгo conferem.", ""));
    }
  }
   
  private void updateUsuario()
  {
    usuarioDAO().update(this.usuario);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizaзгo efetuada com sucesso", ""));
  }
   
  public void deleteUsuario()
  {
    usuarioDAO().remove(this.usuario);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusгo efetuada com sucesso", ""));
  }
   
  public List<Usuario> getUsuarios()
  {
    this.usuarios = usuarioDAO().getEntities();
    return this.usuarios;
  }
   
  public void setUsuarios(List<Usuario> usuarios)
  {
    this.usuarios = usuarios;
  }
   
  public Usuario getUsuario()
  {
    return this.usuario;
  }
   
  public void setUsuario(Usuario usuario)
  {
    this.usuario = usuario;
  }
   
  public String getConfereSenha()
  {
    return this.confereSenha;
  }
   
  public void setConfereSenha(String confereSenha)
  {
    this.confereSenha = confereSenha;
  }
}