package com.livro.capitulo3.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConectarMySQL 
{
	public static void main(String[] args) 
	{
		Connection conexao = null;
		
		try
		{
			//registrando a classe JDBC no sistema em tempo de execução
			String url = "jdbc:mysql://localhost/agenda";
			String usuario = "root";
			String senha = "root";
			
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectou!");
		}
		catch(SQLException ex)
		{
			System.out.println("Ocorreu um Erro no SQL. Erro:" +ex.getMessage());
		}
		finally
		{
			try
			{
				conexao.close();
			}
			catch(SQLException e)
			{
				System.out.println("Erro ao fechar a Conexao, Erro:" +e.getMessage());
			}
			
		}
	}
}
