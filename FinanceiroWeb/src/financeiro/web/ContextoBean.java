package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import financeiro.conta.Conta;
import financeiro.conta.ContaNegocio;
import financeiro.usuario.Usuario;
import financeiro.usuario.UsuarioRn;

@ManagedBean
@SessionScoped
public class ContextoBean implements Serializable
{
	private Usuario usuarioLogado = null;
	private Conta contaAtiva = null;
	
	public Usuario getUsuarioLogado()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if(usuarioLogado == null || !login.equals(usuarioLogado.getLogin())) 
		{
			if(login != null)
			{
				UsuarioRn usuarioRn = new UsuarioRn();
				usuarioLogado = usuarioRn.buscarProLogin(login);
				contaAtiva = null;
			}
		}
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuario)
	{
		this.usuarioLogado = usuario;
	}
	
	public Conta getContaAtiva()
	{
		if(contaAtiva == null)
		{
			Usuario usuario = this.getUsuarioLogado();
			ContaNegocio negocio = new ContaNegocio();
			contaAtiva = negocio.buscarFavorita(usuario);
			if(contaAtiva == null)
			{
				List<Conta> contas = negocio.listar(usuario);
				if(contas == null)
				{
					for(Conta conta : contas)
					{
						contaAtiva = conta;
						break;
					}
				}
			}
		}
		return contaAtiva;
	}
	
	public void setContaAtiva(ValueChangeEvent event)
	{
		Integer conta = (Integer) event.getNewValue();
		ContaNegocio negocio = new ContaNegocio();
		contaAtiva = negocio.carregar(conta);
	}
}
