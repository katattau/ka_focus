package br.com.kaintegrados.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.kaintegrados.dao.PessoaDAO;
import br.com.kaintegrados.dto.AmigoSecreto;
import br.com.kaintegrados.dto.Pessoa;
import br.com.kaintegrados.factory.AmigoSecretoFactory;
import br.com.kaintegrados.utils.EnviarEmail;

/**
 * 
 * @author katattau
 *
 */
@Controller
public class AmigoSecretoController {

	/**
	 * Realiza o sorteio das pessoas para o amigo secreto
	 * 
	 * São necessárias 3 ou mais pessoas para a brincadeira
	 * 
	 * Se tudo estiver ok, chama a página /amigo_secreto,
	 * senão, volta pra /home informando o problema.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("sorteio")
	public String soteio(Model model) {
		
		List<Pessoa> pessoas = new PessoaDAO().select();
		
		if(pessoas.size() <= 2){
			model.addAttribute("error", 3); // não se faz amigo secreto com menos de 3 pessoas
			return "forward:home";
		}
		
		List<List<Pessoa>> listaPessoas = AmigoSecretoFactory.sorteio(pessoas);
		
		model.addAttribute("amigos", AmigoSecretoFactory.gerarListaAmigoSecreto(listaPessoas));
		
		return "amigo_secreto";
	}
	
	/**
	 * Realiza o envio de e-mail
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("enviarEmail")
	public String enviarEmail(Model model) {
		
		List<AmigoSecreto> listaAmigosSecreto = AmigoSecretoFactory.getListaAmigoSecreto();
		String msg = EnviarEmail.enviarEmail(listaAmigosSecreto);
		
		// Se msg estiver com algum valor, quer dizer que aconteceu algum problema no envio de e-mail
		// A mensagem do problema é exibida em tela
		if(!msg.isEmpty()) {
			model.addAttribute("error", 4);
			model.addAttribute("email_error", msg);
		} else {
			model.addAttribute("error", 7);
		}
		
		return "forward:home";
	}
	
}
