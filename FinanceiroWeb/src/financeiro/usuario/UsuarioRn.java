package financeiro.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import financeiro.util.DAOFactory;

public class UsuarioRn 
{
	private UsuarioDAO usuario;
	
	private List<SelectItem> idiomas;
	
	public UsuarioRn()
	{
		this.usuario = DAOFactory.criarUsuarioDAO();
	}
	
	public Usuario carregar (Integer codigo)
	{
		return this.usuario.carregar(codigo);
	}
	
	public Usuario buscarProLogin(String login)
	{
		return this.usuario.buscarProLogin(login);
	}
	
	public void salvar(Usuario usuario)
	{
		Integer codigo = usuario.getCodigo();
		if(codigo == null || codigo == 0)
		{
			usuario.getPermissao().add("ROLE_USUARIO");
			this.usuario.salvar(usuario);
		}
		else
		{
			this.usuario.atualizar(usuario);
		}
	}
	
	public void excluir(Usuario usuario)
	{
		this.usuario.excluir(usuario);
	}
	
	public List<Usuario> listar()
	{
		return this.usuario.listar();
	}
	
	public List<SelectItem> getIdiomas()
	{
		if(idiomas == null)
		{
			idiomas = new ArrayList<SelectItem>();
			idiomas.add(new SelectItem("pr_BR", "Portugês"));
			idiomas.add(new SelectItem("en_US", "Inglês"));
			idiomas.add(new SelectItem("es_ES", "Espanhol"));
		}
		return idiomas;
	}

	public UsuarioDAO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDAO usuario) {
		this.usuario = usuario;
	}
	
}
