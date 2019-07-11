package financeiro.web;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livro.capitulo3.conexao.HibernateUtil;
import com.livro.capitulo3.crudxml.Contato;

public class ContatoCrudXML 
{
	public void salvar(Contato contato)
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
	
	public void atualizar(Contato contato)
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
	
	public void excluir(Contato contato)
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
	
	public List<Contato> listar()
	{
		Session session = null;
		Transaction transacao = null;
		Query consulta = null;
		List<Contato> resultado = null;
		
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
	
	public Contato buscaContato(int valor)
	{
		Contato contato = null;
		Session session = null;
		Transaction transacao = null;
		Query consulta = null;
		
		try
		{
			session = HibernateUtil.getSessionFactory().openSession();
			transacao = session.beginTransaction();
			consulta = session.createQuery("from Contato where codigo = :1");
			consulta.setInteger("1", valor);
			contato = (Contato) consulta.uniqueResult();
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
	
	public static void main(String[] args)
	{
		ContatoCrudXML contatoCrudXML = new ContatoCrudXML();
		
		String[] nomes = {"Fulano", "Beltrano", "Ciclano"};
		String[] fones = {"(47)1111-2222", "(11)2222-1111", "(31)3333-4444"};
		String[] emails = {"fulano@teste.com.br", "beltrano@teste.com.br", "ciclano@teste.com.br"};
		String[] observacoes = {"novo cliente", "Cliente em dia", "Ligar na quinta"};
		
		Contato contato = null;
		
		for(int i = 0; i < nomes.length;i++)
		{
			contato = new Contato();
			contato.setNome(nomes[i]);
			contato.setTelefone(fones[i]);
			contato.setEmail(emails[i]);
			contato.setDataCadastro(new Date(System.currentTimeMillis()));
			contato.setObservacao(observacoes[i]);
			
			contatoCrudXML.salvar(contato);
		}
		System.out.println("Total de registros cadastrados: " + contatoCrudXML.listar().size());
	}
}
