package financeiro.web;

import java.sql.Date;

public class Contato 
{
	private Integer codigo;
	private String nome;
	private String telefone;
	private String email;
	private Date dataCadastro;
	private String observacao;
	
	public static void main(String[] args) {
		ContatoCrudJDBC c = new ContatoCrudJDBC();
		
		Contato beltrano = new Contato();
		beltrano.setNome("Beltrano Solar");
		beltrano.setTelefone("(11)964839696");
		beltrano.setEmail("beniciusclaro@hotmail.com");
		beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
		beltrano.setObservacao("novo cliente");
		c.salvar(beltrano);
		System.out.println("contato cadastrado: " +c.listar().size());
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
