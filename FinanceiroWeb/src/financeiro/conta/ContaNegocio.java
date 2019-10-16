package financeiro.conta;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import financeiro.usuario.Usuario;
import financeiro.util.DAOFactory;

public class ContaNegocio implements Serializable
{
	private ContaInterface cDAO;
	
	public ContaNegocio()
	{
		this.cDAO = DAOFactory.criarContaDAO();
	}
	
	public List<Conta> listar(Usuario usuario)
	{
		return cDAO.listar(usuario);
	}
	
	public Conta carregar(Integer conta)
	{
		return cDAO.carregar(conta);
	}
	
	public void salvar(Conta conta)
	{
		conta.setDataCadastro(new Date());
		cDAO.salvar(conta);
	}
	
	public void excluir(Conta conta)
	{
		cDAO.excluir(conta);
	}
	
	public void tornarFavorita(Conta contaFavorita)
	{
		Conta conta = this.buscarFavorita(contaFavorita.getUsuario());
		
		if(conta != null)
		{
			conta.setFavorita(false);
			cDAO.salvar(conta);
		}
		contaFavorita.setFavorita(true);
		cDAO.salvar(conta);
	}
	
	public Conta buscarFavorita(Usuario usuario)
	{
		return cDAO.buscarFavorita(usuario);
	}
}
