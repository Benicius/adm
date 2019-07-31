package com.livro.capitulo3.conexao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() 
	{
		try 
		{
			AnnotationConfiguration conf = new AnnotationConfiguration();
			conf.configure("hibernate.cfg.xml");
			
			return conf.buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.out.println("Criação inicial do objeto SessionFactory falou. Erro: " +ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
}
