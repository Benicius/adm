package com.livro.capitulo3.crud.annotations;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import financeiro.web.ContatoCrudHibernate;
import financeiro.web.ContatoCrudXML;

@Entity
@Table(name="contato")
public class ContatoAnnotations 
{
	@Id @GeneratedValue
	@Column(name="codigo")
	private Integer codigo;
	
	@Column(name="nome", length=50, nullable = true)
	private String nome;
	
	@Column(name="telefone", length=50, nullable = true)
	private String telefone;
	
	@Column(name="email", length=50, nullable = true)
	private String email;
	
	@Column(name="dt_cad", nullable = true)
	private Date dataCadastro;
	
	@Column(name="obs", nullable = true) 
	private String observacao;
	
	public static void main(String[] args)
	{
		ContatoCrudHibernate contato = new ContatoCrudHibernate();
		
		String[] nomes = {"Solanu", "Lunare", "Venusiana"};
		String[] fones = {"(11) 96483-9696", "(11) 96483-5522", "(52) 4775-5566"};
		String[] emails = {"solanu@teste.com.be", "lunare@teste.com.br", "venusiana@teste.com.br" };
		String[] observacoes = {"Novo Cliente", "Cliente em dia", "ligar para este cliente"};
		
		ContatoAnnotations cont = null;
		
		for(int i = 0; i <nomes.length; i++)
		{
			cont = new ContatoAnnotations();
			cont.setNome(nomes[i]);
			cont.setTelefone(fones[i]);
			cont.setEmail(emails[i]);
			cont.setDataCadastro(new Date(System.currentTimeMillis()));
			cont.setObservacao(observacoes[i]);
			contato.salvar(cont);
		}
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
}
