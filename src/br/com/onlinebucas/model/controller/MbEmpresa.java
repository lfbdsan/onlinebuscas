package br.com.onlinebucas.model.controller;
 
import br.com.onlinebuscas.model.dao.HibernateDAO;
import br.com.onlinebuscas.model.dao.InterfaceDAO;
import br.com.onlinebuscas.model.entities.Empresa;
import br.com.onlinebuscas.model.entities.Plano;
import br.com.onlinebuscas.util.FacesContextUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;
 
@ManagedBean(name="mbEmpresa")
@RequestScoped
public class MbEmpresa
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Empresa Empresa = new Empresa();
  private Plano plano = new Plano();
  private StreamedContent arquivoRetorno;
  private int tipoRelatorio;
  private List<Empresa> Empresas;
  private List<Plano> planos;
   
  private InterfaceDAO<Empresa> EmpresaDAO()
  {
    InterfaceDAO<Empresa> EmpresaDAO = new HibernateDAO<Empresa>(Empresa.class, FacesContextUtil.getRequestSession());
    return EmpresaDAO;
  }
   
  public String limpEmpresa()
  {
    this.Empresa = new Empresa();
    return editEmpresa();
  }
   
  public String editEmpresa()
  {
    return "/restrict/cadastrar_empresa.jsf";
  }
   
  public String addEmpresa()
  {
    if ((this.Empresa.getIdEmpresa() == null) || (this.Empresa.getIdEmpresa().intValue() == 0)) {
      insertEmpresa();
    } else {
      updateEmpresa();
    }
    limpEmpresa();
    return null;
  }
   
  private void insertEmpresa()
  {
    EmpresaDAO().save(this.Empresa);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravaзгo efetuada com sucesso", ""));
  }
   
  private void updateEmpresa()
  {
    EmpresaDAO().update(this.Empresa);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualizaзгo efetuada com sucesso", ""));
  }
   
  public void deleteEmpresa()
  {
    EmpresaDAO().remove(this.Empresa);
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclusгo efetuada com sucesso", ""));
  }
   
  public List<Empresa> getEmpresas()
  {
    this.Empresas = EmpresaDAO().getEntities();
    return this.Empresas;
  }
   
  public void setEmpresas(List<Empresa> Empresas)
  {
    this.Empresas = Empresas;
  }
   
  public Empresa getEmpresa()
  {
    return this.Empresa;
  }
   
  public void setEmpresa(Empresa Empresa)
  {
    this.Empresa = Empresa;
  }
   
  public List<Plano> getPlanos()
  {
    return this.planos;
  }
   
  public void setPlanos(List<Plano> planos)
  {
    this.planos = planos;
  }
   
  public Plano getPlano()
  {
    return this.plano;
  }
   
  public void setPlano(Plano plano)
  {
    this.plano = plano;
  }
 
public StreamedContent getArquivoRetorno() {
    return arquivoRetorno;
}
 
public void setArquivoRetorno(StreamedContent arquivoRetorno) {
    this.arquivoRetorno = arquivoRetorno;
}
 
public int getTipoRelatorio() {
    return tipoRelatorio;
}
 
public void setTipoRelatorio(int tipoRelatorio) {
    this.tipoRelatorio = tipoRelatorio;
}
}