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

    public MbUsuario()
    {
        usuario = new Usuario();
    }

    private InterfaceDAO usuarioDAO()
    {
        InterfaceDAO usuarioDAO = new HibernateDAO(null, FacesContextUtil.getRequestSession());
        return usuarioDAO;
    }

    public String limpUsuario()
    {
        usuario = new Usuario();
        return editUsuario();
    }

    public String editUsuario()
    {
        return "/restrict/cadastrar_usuario.jsf";
    }

    public String addUsuario()
    {
        if(usuario.getIdUsuario() == null || usuario.getIdUsuario().intValue() == 0)
            insertUsuario();
        else
            updateUsuario();
        limpUsuario();
        return null;
    }

    private void insertUsuario()
    {
        usuario.setSenha(ConverterSHA1.cipher(usuario.getSenha()));
        if(usuario.getSenha() != null ? usuario.getSenha().equals(ConverterSHA1.cipher(confereSenha)) : confereSenha == null)
        {
            usuario.setPermissao("ROLE_USER");
            usuario.setStatus(Integer.valueOf(1));
            usuario.setEnable(Integer.valueOf(1));
            usuarioDAO().save(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Grava\347\343o efetuada com sucesso", ""));
        } else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas n\343o conferem.", ""));
        }
    }

    private void updateUsuario()
    {
        usuarioDAO().update(usuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualiza\347\343o efetuada com sucesso", ""));
    }

    public void deleteUsuario()
    {
        usuarioDAO().remove(usuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exclus\343o efetuada com sucesso", ""));
    }

    public List getUsuarios()
    {
        usuarios = usuarioDAO().getEntities();
        return usuarios;
    }

    public void setUsuarios(List usuarios)
    {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public String getConfereSenha()
    {
        return confereSenha;
    }

    public void setConfereSenha(String confereSenha)
    {
        this.confereSenha = confereSenha;
    }

    private static final long serialVersionUID = 1L;
    private String confereSenha;
    private Usuario usuario;
    private List usuarios;
}
