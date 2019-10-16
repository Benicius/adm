package financeiro.conta;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import financeiro.usuario.Usuario;

@Entity
@Table(name="conta_bancaria")
public class Conta implements Serializable
{
	@Id @GeneratedValue
	@Column(name="cod_conta")
	private Integer conta;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="cod_usuario", nullable = false)
	private Usuario usuario;
	
	@Column(name ="des_conta")
	private String descricao;
	
	@Column(name="dat_cadastro", nullable = false, updatable = false)
	private Date dataCadastro;
	
	@Column(name="saldo_inical")
	private float saldoInicaial;
	
	@Column(name="favorita")
	private boolean favorita;

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public float getSaldoInicaial() {
		return saldoInicaial;
	}

	public void setSaldoInicaial(float saldoInicaial) {
		this.saldoInicaial = saldoInicaial;
	}

	public boolean isFavorita() {
		return favorita;
	}

	public void setFavorita(boolean favorita) {
		this.favorita = favorita;
	}
	
}
