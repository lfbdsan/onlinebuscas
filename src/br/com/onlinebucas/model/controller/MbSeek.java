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
public class MbSeek
    implements Serializable
{

    public MbSeek()
    {
        Empresa = new Empresa();
        plano = new Plano();
    }

    private InterfaceDAO EmpresaDAO()
    {
        InterfaceDAO EmpresaDAO = new HibernateDAO(null, FacesContextUtil.getRequestSession());
        return EmpresaDAO;
    }

    public String limpEmpresa()
    {
        Empresa = new Empresa();
        return editEmpresa();
    }

    public String editEmpresa()
    {
        return "/restrict/cadastrar_empresa.jsf";
    }

    public String addEmpresa()
    {
        if(Empresa.getIdEmpresa() == null || Empresa.getIdEmpresa().intValue() == 0)
            insertEmpresa();
        else
            updateEmpresa();
        limpEmpresa();
        return null;
    }

    private void insertEmpresa()
    {
        EmpresaDAO().save(Empresa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grava\347\343o efetuada com sucesso", ""));
    }

    private void updateEmpresa()
    {
        EmpresaDAO().update(Empresa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualiza\347\343o efetuada com sucesso", ""));
    }

    public void deleteEmpresa()
    {
        EmpresaDAO().remove(Empresa);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclus\343o efetuada com sucesso", ""));
    }

    public List getEmpresas()
    {
        Empresas = EmpresaDAO().getEntities();
        return Empresas;
    }

    public void setEmpresas(List Empresas)
    {
        this.Empresas = Empresas;
    }

    public Empresa getEmpresa()
    {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa)
    {
        this.Empresa = Empresa;
    }

    public List getPlanos()
    {
        return planos;
    }

    public void setPlanos(List planos)
    {
        this.planos = planos;
    }

    public Plano getPlano()
    {
        return plano;
    }

    public void setPlano(Plano plano)
    {
        this.plano = plano;
    }

    public StreamedContent getArquivoRetorno()
    {
        return arquivoRetorno;
    }

    public void setArquivoRetorno(StreamedContent arquivoRetorno)
    {
        this.arquivoRetorno = arquivoRetorno;
    }

    public int getTipoRelatorio()
    {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(int tipoRelatorio)
    {
        this.tipoRelatorio = tipoRelatorio;
    }

    private static final long serialVersionUID = 1L;
    private Empresa Empresa;
    private Plano plano;
    private StreamedContent arquivoRetorno;
    private int tipoRelatorio;
    private List Empresas;
    private List planos;
}
