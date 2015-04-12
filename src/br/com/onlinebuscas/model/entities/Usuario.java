package br.com.onlinebuscas.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  @Column(name="IdUsuario", nullable=false)
  private Integer idUsuario;
  @Column(name="Name", nullable=false, length=80)
  private String nome;
  @Column(name="Login", unique=true, length=25)
  private String login;
  @Column(name="Senha", length=40)
  private String senha;
  @Column(name="Permissao", length=36)
  private String permissao;
  @Column(name="empresa", nullable=false)
  private Integer empresa;
  @Column(name="Status")
  private Integer status;
  @Column(name="Enable")
  private Integer enable;
  
  @OneToMany(mappedBy="empresa")
  private List<Usuario> usuarios;
  @OneToMany(mappedBy="empresa")
  private List<Plano> planos;
  
  public List<Usuario> getUsuarios()
  {
    return this.usuarios;
  }

public Integer getIdUsuario() {
	return idUsuario;
}

public void setIdUsuario(Integer idUsuario) {
	this.idUsuario = idUsuario;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public String getPermissao() {
	return permissao;
}

public void setPermissao(String permissao) {
	this.permissao = permissao;
}

public Integer getEmpresa() {
	return empresa;
}

public void setEmpresa(Integer empresa) {
	this.empresa = empresa;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

public Integer getEnable() {
	return enable;
}

public void setEnable(Integer enable) {
	this.enable = enable;
}

public List<Plano> getPlanos() {
	return planos;
}

public void setPlanos(List<Plano> planos) {
	this.planos = planos;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

public void setUsuarios(List<Usuario> usuarios) {
	this.usuarios = usuarios;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
	result = prime * result + ((enable == null) ? 0 : enable.hashCode());
	result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
	result = prime * result + ((login == null) ? 0 : login.hashCode());
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
	result = prime * result + ((planos == null) ? 0 : planos.hashCode());
	result = prime * result + ((senha == null) ? 0 : senha.hashCode());
	result = prime * result + ((status == null) ? 0 : status.hashCode());
	result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Usuario other = (Usuario) obj;
	if (empresa == null) {
		if (other.empresa != null)
			return false;
	} else if (!empresa.equals(other.empresa))
		return false;
	if (enable == null) {
		if (other.enable != null)
			return false;
	} else if (!enable.equals(other.enable))
		return false;
	if (idUsuario == null) {
		if (other.idUsuario != null)
			return false;
	} else if (!idUsuario.equals(other.idUsuario))
		return false;
	if (login == null) {
		if (other.login != null)
			return false;
	} else if (!login.equals(other.login))
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (permissao == null) {
		if (other.permissao != null)
			return false;
	} else if (!permissao.equals(other.permissao))
		return false;
	if (planos == null) {
		if (other.planos != null)
			return false;
	} else if (!planos.equals(other.planos))
		return false;
	if (senha == null) {
		if (other.senha != null)
			return false;
	} else if (!senha.equals(other.senha))
		return false;
	if (status == null) {
		if (other.status != null)
			return false;
	} else if (!status.equals(other.status))
		return false;
	if (usuarios == null) {
		if (other.usuarios != null)
			return false;
	} else if (!usuarios.equals(other.usuarios))
		return false;
	return true;
}
  
 
}
