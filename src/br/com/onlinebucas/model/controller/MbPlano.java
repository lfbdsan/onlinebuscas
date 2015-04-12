package br.com.onlinebucas.model.controller;

import br.com.onlinebuscas.model.dao.HibernateDAO;
import br.com.onlinebuscas.model.dao.InterfaceDAO;
import br.com.onlinebuscas.model.entities.Plano;
import br.com.onlinebuscas.util.FacesContextUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="mbPlano")
@RequestScoped
public class MbPlano
    implements Serializable
{

    public MbPlano()
    {
        plano = new Plano();
    }

    private InterfaceDAO planoDAO()
    {
        InterfaceDAO planoDAO = new HibernateDAO(null, FacesContextUtil.getRequestSession());
        return planoDAO;
    }

    public String limpPlano()
    {
        plano = new Plano();
        return editPlano();
    }

    public String editPlano()
    {
        return "/restrict/cadastrar_plano.jsf";
    }

    public String addPlano()
    {
        if(plano.getIdPlano() == null || plano.getIdPlano().intValue() == 0)
            insertPlano();
        else
            updatePlano();
        limpPlano();
        return null;
    }

    private void insertPlano()
    {
        planoDAO().save(plano);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grava\347\343o efetuada com sucesso", ""));
    }

    private void updatePlano()
    {
        planoDAO().update(plano);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualiza\347\343o efetuada com sucesso", ""));
    }

    public void deletePlano()
    {
        planoDAO().remove(plano);
    }

    public List getPlanos()
    {
        planos = planoDAO().getEntities();
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

    private static final long serialVersionUID = 1L;
    private Plano plano;
    private List planos;
}
