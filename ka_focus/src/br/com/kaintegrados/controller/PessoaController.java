package br.com.kaintegrados.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.kaintegrados.dao.PessoaDAO;
import br.com.kaintegrados.dto.Pessoa;
import br.com.kaintegrados.utils.Validacao;

/**
 * Erros
 * 
 * home - 0 - Pessoa cadastrada!
 * add_pessoa - 1 - Pessoa não cadastrada!
 * home - 2 - Pessoa alterada!
 * home - 3 - Impossível realizar sorteio!
 * home - 4 - ${email_error}
 * add_pessoa - 5 - Pessoa não alterada!
 * home - 6 - Pessoa não encontrada!
 * add_pessoa - 7 - E-mail inválido
 * @author katattau
 *
 */
@Controller
public class PessoaController {

	/**
	 * Index
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
	    return "forward:home";
	}
	
	/**
	 * Pega todas as pessoas cadastradas.
	 * Chama a página /home
	 * @param model
	 * @return
	 */
	@RequestMapping("home")
	public String lista(Model model) {
		List<Pessoa> pessoas = new PessoaDAO().select();
		model.addAttribute("pessoas", pessoas);
		return "home";
	}

	/**
	 * Adiciona ou altera uma pessoa
	 * Se o ID for menor que 0, significa que é uma alteração, senão, é um cadastro
	 * 
	 * Em caso de erro, chama a página /add_pessoa
	 * Se correr tudo ok, chama a página /home
	 * @param p
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("adicionaPessoa")
	public String adiciona(@Valid Pessoa p, BindingResult result, Model model) {

		if (result.hasFieldErrors("nome") || result.hasFieldErrors("email")) 
			return "add_pessoa";
		
		if(!Validacao.isEmailValido(p.getEmail())) {
			model.addAttribute("error", 7);
			return "add_pessoa";
		}
		
		if (p.getId() < 0) { // cadastrar

			if (!new PessoaDAO().add(p)) {
				model.addAttribute("error", 1);// ja existe
				return "add_pessoa";
			} else
				model.addAttribute("error", 0);// cadastra

		} else {
			if (new PessoaDAO().update(p))
				model.addAttribute("error", 2);// altera
			else {
				model.addAttribute("error", 5);
				return "add_pessoa";
			}
		}

		return "forward:home";
	}

	/**
	 * Deleta uma pessoa e chama a página /home
	 * @param p
	 * @return
	 */
	@RequestMapping("deletarPessoa")
	public String deletar(Pessoa p) {
		new PessoaDAO().delete(p);
		return "forward:home";
	}

	/**
	 * Seleciona a pessoa a ser alterada e chama a página /add_pessoa
	 * para inserção das alterações
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("alterarPessoa")
	public String mostra(int id, Model model) {
		Pessoa p = (Pessoa) new PessoaDAO().select(id);
		
		if(p == null)
			return "forward:home";
		
		model.addAttribute("pessoa", p);
		return "add_pessoa";
	}

	/**
	 * Chama a página /add_pessoa
	 * @return
	 */
	@RequestMapping("addPessoa")
	public String addPessoa() {
		return "add_pessoa";
	}
	
	/**
	 * Procura por uma pessoa através de seu nome ou e-mail
	 * Chama a página /home no fim
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("procurarPessoa")
	public String procurarPessoa(Pessoa p, Model model) {
		List<Pessoa> pessoas = new PessoaDAO().select(p);

		if (pessoas.isEmpty()) {
			model.addAttribute("error", 6);
			return "forward:home";
		}

		model.addAttribute("pessoas", pessoas);
		return "home";
	}
	

}
