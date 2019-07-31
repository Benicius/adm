package financeiro.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ContatoCrudJDBC 
{
	public void salvar(Contato contato) 
	{
		Connection conexao = this.gerarConexao();
		
		PreparedStatement insereSt = null;
		String sql = "insert into contato(nome, telefone, email, dt_cad, obs) values (?,?,?,?,?)";
		
		try 
		{
			insereSt = conexao.prepareStatement(sql);
			
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			
			insereSt.executeUpdate();
		}
		catch(SQLException ex)
		{
			System.out.println("Erro ao incluir contato, Mensagem: " +ex.getMessage());
		}
		finally
		{
			try
			{
				insereSt.close();
				conexao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao ficar operações de inserção. Mensagem: " +e.getMessage());
			}
		}
	}
	public void atualizar(Contato contato) 
	{
		Connection conexao = this.gerarConexao();
		
		PreparedStatement insereSt = null;
		String sql = "update contato set nome =? telefone = ? email = ? dt_cad = ? obs = ? where codigo = ?";
		
		try 
		{
			insereSt = conexao.prepareStatement(sql);
			
			insereSt.setString(1, contato.getNome());
			insereSt.setString(2, contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			
			insereSt.executeUpdate();
		}
		catch(SQLException ex)
		{
			System.out.println("Erro ao incluir contato, Mensagem: " +ex.getMessage());
		}
		finally
		{
			try
			{
				insereSt.close();
				conexao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao ficar operações de inserção. Mensagem: " +e.getMessage());
			}
		}
	}
	public void excluir(Contato contato) {
		Connection conexao = this.gerarConexao();
		
		PreparedStatement insereSt = null;
		String sql = "delete from contato where codigo = ?";
		
		try 
		{
			insereSt = conexao.prepareStatement(sql);
			
			insereSt.executeUpdate();
		}
		catch(SQLException ex)
		{
			System.out.println("Erro ao incluir contato, Mensagem: " +ex.getMessage());
		}
		finally
		{
			try
			{
				insereSt.close();
				conexao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao ficar operações de inserção. Mensagem: " +e.getMessage());
			}
		}
	}
	public List<Contato> listar(){
		Connection conexao = this.gerarConexao();
		
		List<Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet rs = null;
		Contato contato = null;
		
		String sql = "select * from contato";
		
		try
		{
			consulta = conexao.createStatement();
			rs = consulta.executeQuery(sql);
			
			while (rs.next())
			{
				contato = new Contato();
				
				contato.setCodigo(new Integer(rs.getInt("codigo")));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				contato.setDataCadastro(rs.getDate("dt_cad"));
				contato.setObservacao(rs.getString("obs"));
				
				contatos.add(contato);
				
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Erro ao buscar código do contato. Mensagem: " +ex.getMessage());
		}
		finally
		{
			try 
			{
				consulta.close();
				rs.close();
				conexao.close();
				
			}
			catch(Throwable ex)
			{
				System.out.println("Erro ao fechar operações, Mensagem: " +ex.getMessage());
			}
		}
		
		return contatos;
	}
	public Contato buscarContato(int valor) 
	{
		Connection conexao = this.gerarConexao();
		
		try
		{
			String sql = "select codigo, nome, telefone, email, dt_cad, obs from contato where codigo = ?";
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setInt(1, valor);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				Contato c = new Contato();
				
				c.setCodigo(rs.getInt("codigo"));
				c.setNome(rs.getString("nome"));
				c.setTelefone(rs.getString("telefone"));
				c.setEmail(rs.getString("email"));
				c.setDataCadastro(rs.getDate("dt_cad"));
				c.setObservacao(rs.getString("obs"));
				
				return c;
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			try 
			{
				conexao.close();
			}
			catch(SQLException ex)
			{
				System.out.println("Erro ao fechar operações, Mensagem: " +ex.getMessage());
			}
			
		}
		
		return null;
	}
	public Connection gerarConexao() 
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
		
		
		return conexao;
	}
	public static void main(String[] args) {}
}
