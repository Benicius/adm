package financeiro.util;

import com.livro.capitulo3.conexao.HibernateUtil;

import financeiro.conta.ContaDAO;
import financeiro.conta.ContaInterface;
import financeiro.usuario.UsuarioDAO;
import financeiro.usuario.UsuarioDAOHibernate;

public class DAOFactory 
{
	public static UsuarioDAO criarUsuarioDAO()
	{
		UsuarioDAOHibernate uDAO = new UsuarioDAOHibernate();
		uDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return uDAO;
	}
	public static ContaInterface criarContaDAO()
	{
		ContaDAO cDAO = new ContaDAO();
		cDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return cDAO;
	}
}
