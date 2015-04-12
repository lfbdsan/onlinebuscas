package br.com.onlinebuscas.model.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plano")
public class Plano implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "IdPlano", nullable = false)
	private Integer idPlano;
	@Column(name = "NomePlano", length = 80, nullable = false)
	private String nomePlano;
	@Column(name = "DiaVencimento", nullable = false)
	private Integer diaVencimento;
	@Column(name = "FaturaAuto", nullable = false)
	private Integer faturaAuto;
	@Column(name = "GeraBoleto", nullable = false)
	private Integer geraBoleto;
	@Column(name = "NotaFiscal", nullable = false)
	private Integer notaFiscal;
	@ManyToOne
	@JoinColumn(name = "idEmpresa")
	private Empresa empresa;

	public Plano() {

	}

	public Integer getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}

	public String getNomePlano() {
		return nomePlano;
	}

	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public Integer getFaturaAuto() {
		return faturaAuto;
	}

	public void setFaturaAuto(Integer faturaAuto) {
		this.faturaAuto = faturaAuto;
	}

	public Integer getGeraBoleto() {
		return geraBoleto;
	}

	public void setGeraBoleto(Integer geraBoleto) {
		this.geraBoleto = geraBoleto;
	}

	public Integer getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(Integer notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((diaVencimento == null) ? 0 : diaVencimento.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result
				+ ((faturaAuto == null) ? 0 : faturaAuto.hashCode());
		result = prime * result
				+ ((geraBoleto == null) ? 0 : geraBoleto.hashCode());
		result = prime * result + ((idPlano == null) ? 0 : idPlano.hashCode());
		result = prime * result
				+ ((nomePlano == null) ? 0 : nomePlano.hashCode());
		result = prime * result
				+ ((notaFiscal == null) ? 0 : notaFiscal.hashCode());
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
		Plano other = (Plano) obj;
		if (diaVencimento == null) {
			if (other.diaVencimento != null)
				return false;
		} else if (!diaVencimento.equals(other.diaVencimento))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (faturaAuto == null) {
			if (other.faturaAuto != null)
				return false;
		} else if (!faturaAuto.equals(other.faturaAuto))
			return false;
		if (geraBoleto == null) {
			if (other.geraBoleto != null)
				return false;
		} else if (!geraBoleto.equals(other.geraBoleto))
			return false;
		if (idPlano == null) {
			if (other.idPlano != null)
				return false;
		} else if (!idPlano.equals(other.idPlano))
			return false;
		if (nomePlano == null) {
			if (other.nomePlano != null)
				return false;
		} else if (!nomePlano.equals(other.nomePlano))
			return false;
		if (notaFiscal == null) {
			if (other.notaFiscal != null)
				return false;
		} else if (!notaFiscal.equals(other.notaFiscal))
			return false;
		return true;
	}

}
