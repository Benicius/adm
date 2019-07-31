package financeiro.web.filtro;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import com.livro.capitulo3.conexao.HibernateUtil;

public class ConexaoHibernateFiltro implements Filter
{
	private SessionFactory sf;
	public void init(FilterConfig config) throws ServletException
	{
		this.sf = HibernateUtil.getSessionFactory();
	}
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws ServletException
	{
		try
		{
			sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			sf.getCurrentSession().getTransaction().commit();
			sf.getCurrentSession().close();
		} 
		catch(Throwable ex)
		{
			try
			{
				if(sf.getCurrentSession().getTransaction().isActive())
				{ 
					sf.getCurrentSession().getTransaction().rollback();
				}
			}
			catch(Throwable t)
			{
				t.printStackTrace();
			}
			throw new ServletException(ex);
			
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
