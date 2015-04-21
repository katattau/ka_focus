package br.com.kaintegrados.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.kaintegrados.dto.Pessoa;
import br.com.kaintegrados.utils.Validacao;

public class PessoaDAO implements BasicCommandsDB {
	
	private Pessoa p = null;

	/**
	 * Adiciona uma pessoa
	 * 
	 * Verifica se o e-mail já existe, se existir não cadastra
	 * 
	 * @return boolean 
	 */
	@Override
	public boolean add(Object o) {

		if (o instanceof Pessoa)
			p = (Pessoa) o;
		else
			return false;

		Pessoa pessoa = null;

		try {
			pessoa = getPessoaEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (pessoa != null)
			return false;

		manager.getTransaction().begin();
		manager.persist(p);
		manager.getTransaction().commit();

		return true;
	}

	/**
	 * Atualiza uma pessoa
	 * 
	 * Verifica se o e-mail alterado já está cadastrado na base (e que pertence
	 * a outra pessoa), se já estiver não faz a alteração.
	 * 
	 * @return List<Pessoa>
	 */
	@Override
	public boolean update(Object o) {

		if (o instanceof Pessoa)
			p = (Pessoa) o;
		else
			return false;

		Pessoa pessoa = null;
		pessoa = manager.find(Pessoa.class, p.getId());

		// se o e-mail não foi alterado, altera apenas o nome
		if (p.getEmail().equals(pessoa.getEmail())) {
			manager.getTransaction().begin();
			pessoa.setNome(p.getNome());
			manager.getTransaction().commit();
		} else {

			// se o e-mail foi alterado, verifica se existe o e-mail no bd
			try {
				pessoa = getPessoaEmail();
			} catch (Exception e) {
				pessoa = null;
				e.printStackTrace();
			}

			// se existir, retorna null, senão, realiza um novo cadastro
			if (pessoa != null) {
				return false;
			} else {
				pessoa = manager.find(Pessoa.class, p.getId());

				manager.getTransaction().begin();
				pessoa.setNome(p.getNome());
				pessoa.setEmail(p.getEmail());
				manager.getTransaction().commit();
			}
		}

		return true;
	}

	/**
	 * Seleciona todas as pessoas cadastradas
	 * 
	 * @return List<Pessoas>
	 */
	@Override
	public List select() {

		Session session = (Session) manager.getDelegate();
		Criteria resultado = session.createCriteria(Pessoa.class);

		return resultado.list();
	}

	/**
	 * Deleta uma pessoa
	 */
	@Override
	public boolean delete(Object o) {

		if (o instanceof Pessoa)
			p = (Pessoa) o;
		else
			return false;

		Pessoa pessoaDel = null;
		
		try {
			pessoaDel = manager.find(Pessoa.class, p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(pessoaDel == null)
			return false;

		manager.getTransaction().begin();
		manager.remove(pessoaDel);
		manager.getTransaction().commit();
		
		return true;
	}

	/**
	 * Seleciona uma pessoa através do ID
	 * 
	 * @return Pessoa
	 */
	@Override
	public Object select(int id) {
		Pessoa p = manager.find(Pessoa.class, id);
		return p;
	}

	private Pessoa getPessoaNomeEmail() throws Exception {

		TypedQuery<Pessoa> query = manager
				.createQuery(
						"SELECT p FROM Pessoa p WHERE p.email = :email AND p.nome = :nome",
						Pessoa.class);

		query.setParameter("nome", p.getNome());
		query.setParameter("email", p.getEmail());
		return query.getSingleResult();

	}

	/**
	 * Retorna uma lista de pessoas.
	 * 
	 * Busca os registros executando uma query utilizando no WHERE o nome LIKE%valor%
	 * 
	 * @return List<Pessoa>
	 * @throws Exception
	 */
	private List<Pessoa> getPessoaNome() throws Exception {
		
		manager.getTransaction().begin();

		Query query = manager.createQuery("SELECT p FROM Pessoa p WHERE p.nome LIKE :nome");
		query.setParameter("nome", "%" + p.getNome() + "%");

		List<Pessoa> listaPessoas = query.getResultList();
		
		manager.getTransaction().commit();
		
		return listaPessoas;

	}

	/**
	 * Retorna um registro de pessoa baseado em seu e-mail
	 * 
	 * @return Pessoa
	 * @throws Exception
	 */
	private Pessoa getPessoaEmail() throws Exception {
		TypedQuery<Pessoa> query = manager.createQuery(
				"SELECT p FROM Pessoa p WHERE p.email = :email", Pessoa.class);
		return query.setParameter("email", p.getEmail()).getSingleResult();
	}

	/**
	 * Retorna uma lista de pessoas baseado no que foi preenchido
	 * nos campos de busca
	 * 
	 * Verifica se foi preenchido o nome, ou o e-mail, ou ambos
	 * 
	 * No caso de preencher somente do nome, um SELECT utilizando o LIKE %valor%
	 * é realizado.
	 * 
	 * Apenas retorna uma lista com mais de um elemento no caso de preenchimento
	 * somente do nome.
	 * 
	 * @return List<Pessoa>
	 */
	@Override
	public List select(Object o) {

		if (o instanceof Pessoa)
			p = (Pessoa) o;
		else
			return null;

		Pessoa pessoa = null;
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();

		try {
			if (!Validacao.isNullIsEmpty(p.getNome()) && !Validacao.isNullIsEmpty(p.getEmail())) {
				pessoa = getPessoaNomeEmail();
				listaPessoas.add(pessoa);
			} else {

				if (!Validacao.isNullIsEmpty(p.getNome()))
					listaPessoas = getPessoaNome();
				else if (!Validacao.isNullIsEmpty(p.getEmail())) {
					pessoa = getPessoaEmail();
					listaPessoas.add(pessoa);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listaPessoas;
	}

}
