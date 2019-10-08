package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import financeiro.usuario.Usuario;
import financeiro.usuario.UsuarioRn;

@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable
{
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	
	
	public String novo()
	{
		this.destinoSalvar = "usuarioSucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/usuario";
	}
	
	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/usuario";
	}
	
	public String salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		String senha = this.usuario.getSenha();
		if(!senha.equals(this.confirmarSenha))
		{
			FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}
		
		UsuarioRn usuarioRn = new UsuarioRn();
		usuarioRn.salvar(this.usuario);
		
		return "usuarioSucesso";
	}
	
	public String excluir() 
	{
		UsuarioRn usuarioRN = new UsuarioRn();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}
	
	public String ativar() 
	{
		if(this.usuario.isAtivo())
		{
			this.usuario.setAtivo(false);
		}
		else
		{
			this.usuario.setAtivo(true);
		}
		
		UsuarioRn usuarioRN = new UsuarioRn();
		usuarioRN.salvar(this.usuario);
		
		return null;
	}
	
	public List<Usuario> getLista()
	{
		if(this.lista == null) 
		{
			UsuarioRn usuarioRN = new UsuarioRn();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}
	
	public String atribuiPermissao(Usuario usuario, String permissao)
	{
		this.usuario = usuario;
		java.util.Set<String> permissoes = this.usuario.getPermissao();
		
		if(permissao.contains(permissao))
		{
			permissoes.remove(permissao);
			
		}
		else {
			permissoes.add(permissao);
		}
		
		return null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}
	
}
