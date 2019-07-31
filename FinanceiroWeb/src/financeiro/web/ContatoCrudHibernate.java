package financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livro.capitulo3.conexao.HibernateUtil;
import com.livro.capitulo3.crud.annotations.ContatoAnnotations;

@ManagedBean
@ViewScoped
public class ContatoCrudHibernate implements Serializable
{
	public void salvar(ContatoAnnotations contato)
	{
		Session session = null;
		Transaction transacao = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transacao = session.beginTransaction();
			session.save(contato);
			
		}
		catch(HibernateException e)
		{
			System.out.println("Não foi possível inserir o contato. Erro: " +e.getMessage());
			
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar operação  de inserção. Mensagem: " +e.getMessage());
			}
		}
	}
	
	public void atualizar(ContatoAnnotations contato)
	{
		Session session = null;
		Transaction transacao = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transacao = session.beginTransaction();
			session.update(contato);
			transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("Não foi possível alterar o contato. Mensagem: " +e.getMessage());
		}
		finally
		{
			try
			{
				session.close();
				
			}
			catch(Throwable e )
			{
				System.out.println("Erro ao fechar a operação de atualização. Mensagem: " +e.getMessage());
			}
		}
	}
	
	public void excluir(ContatoAnnotations contato)
	{
		Session session = null;
		Transaction transacao = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transacao = session.beginTransaction();
			session.delete(contato);
			
		}
		catch(HibernateException e)
		{
			System.out.println("Não foi possível excluir o contato. Erro: " +e.getMessage());
			
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação de exclusão. Mensagem: " +e.getMessage());
			}
		}
	}
	
	public List<ContatoAnnotations> listar()
	{
		Session session = null;
		Transaction transacao = null;
		Query consulta = null;
		List<ContatoAnnotations> resultado = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transacao = session.beginTransaction();
			consulta = session.createQuery("from Contato");
			resultado = consulta.list();
			
			return resultado;
		}
		catch (HibernateException e) 
		{
			System.out.println("Não foi possível selecionar contatos. Erro: " +e.getMessage());
			throw new HibernateException(e);
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação de consulta. Mensagem: " +e.getMessage());
			}
		}
	}
	
	public ContatoAnnotations buscaContato(int valor)
	{
		ContatoAnnotations contato = null;
		Session session = null;
		Transaction transacao = null;
		Query consulta = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transacao = session.beginTransaction();
			consulta = session.createQuery("from Contato where codigo = :1");
			consulta.setInteger("1", valor);
			contato = (ContatoAnnotations) consulta.uniqueResult();
			transacao.commit();
			return contato;
		}
		catch(HibernateException e)
		{
			System.out.println("Não foi possível buscar contato. Erro: " +e.getMessage());
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação de buscar. Mensagem: " +e.getMessage());
			}
		}
		return contato;
	}
	
}
