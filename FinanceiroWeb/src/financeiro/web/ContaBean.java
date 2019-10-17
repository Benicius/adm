package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import financeiro.conta.Conta;
import financeiro.conta.ContaNegocio;
import financeiro.util.ContextoUtil;

@ManagedBean
@RequestScoped
public class ContaBean implements Serializable
{
	private Conta selecionada = new Conta();
	private List<Conta> lista = null;
	
	
	public void salvar() 
	{
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		selecionada.setUsuario(contextoBean.getUsuarioLogado());
		ContaNegocio negocio = new ContaNegocio();
		negocio.salvar(selecionada);
		selecionada = new Conta();
		lista = null;
	}
	public void editar() {
		
	}
	public void excluir() {
		
	}
	public void tornarFavorita() {
		
	}
	public Conta getSelecionada() {
		return selecionada;
	}
	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}
	public List<Conta> getLista() {
		return lista;
	}
	public void setLista(List<Conta> lista) {
		this.lista = lista;
	}
}
