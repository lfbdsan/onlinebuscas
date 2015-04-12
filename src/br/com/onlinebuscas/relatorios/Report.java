package br.com.onlinebuscas.relatorios;

import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="report")
public class Report
{
  private StreamedContent arquivoRetorno;
  
  public StreamedContent getArquivoRetorno()
  {
    FacesContext context = FacesContext.getCurrentInstance();
    ReportUtil reportUtil = new ReportUtil();
    HashMap parametrosRelatorio = new HashMap();
    try
    {
      this.arquivoRetorno = reportUtil.geraRelatorio(parametrosRelatorio);
    }
    catch (Exception e)
    {
      context.addMessage(null, new FacesMessage(e.getMessage()));
      return null;
    }
    return this.arquivoRetorno;
  }
  
  public void setArquivoRetorno(StreamedContent arquivoRetorno)
  {
    this.arquivoRetorno = arquivoRetorno;
  }
}
