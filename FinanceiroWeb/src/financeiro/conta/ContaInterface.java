package financeiro.conta;

import java.util.List;

import financeiro.usuario.Usuario;

public interface ContaInterface 
{
	public void salvar(Conta conta);
	public void excluir(Conta conta);
	public Conta carregar(Integer conta);
	public List<Conta> listar(Usuario usuario);
	public Conta buscarFavorita(Usuario usuario);
}
