package financeiro.usuario;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class UsuarioDAOHibernate implements UsuarioDAO
{
	private Session session;
	
	public void UsuarioDAOHibernate(Session session)
	{
		this.session = session;
	}

	@Override
	public void salvar(Usuario usuario) 
	{
		session.save(usuario);
	}

	@Override
	public void atualizar(Usuario usuario) 
	{
		if(usuario.getPermissao() == null || usuario.getPermissao().size() ==0)
		{
			Usuario usuarioPermissao = this.carregar(usuario.getCodigo());
			usuario.setPermissao(usuarioPermissao.getPermissao());
			session.evict(usuarioPermissao);
		}
		session.update(usuario);
	}

	@Override
	public void excluir(Usuario usuario) 
	{
		session.delete(usuario);
	}

	@Override
	public Usuario carregar(Integer codigo) 
	
	{
		return (Usuario)session.get(Usuario.class, codigo);
	}

	@Override
	public Usuario buscarProLogin(String login) 
	{
		String hql = "select u from Usuario u where u.login = :login";
		Query consulta = session.createQuery(hql);
		consulta.setString("login", login);
		return (Usuario) consulta.uniqueResult();
	}

	@Override
	public List<Usuario> listar() 
	{
		
		return this.session.createCriteria(Usuario.class).list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
	
}
