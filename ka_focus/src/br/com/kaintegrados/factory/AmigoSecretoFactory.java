package br.com.kaintegrados.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.kaintegrados.dto.AmigoSecreto;
import br.com.kaintegrados.dto.Pessoa;

/**
 * 
 * @author katattau
 *
 */
public class AmigoSecretoFactory {
	
	private static List<AmigoSecreto> amigosecretoList = null;

	/**
	 * Faz o sorteio das pessoas para o amigo secreto
	 * 
	 * @param lista1
	 * @return
	 */
	public static List<List<Pessoa>> sorteio(List<Pessoa> lista1) {

		List<Pessoa> lista2 = new ArrayList<Pessoa>(lista1);

		Collections.shuffle(lista1);
		Collections.shuffle(lista2);

		int sizeOrig = lista1.size();
		int size = 0;

		while (sizeOrig != size) {

			size = 0;
			
			for (int i = 0; i < lista1.size(); i++) {

				if (lista1.get(i).getId() == lista2.get(i).getId()) {
					System.err.println("list1=" + lista1.get(i).getId() + " - list2=" + lista2.get(i).getId());
					Collections.shuffle(lista2);
					break;
				}
				size++;
			}
		}
		
		List<List<Pessoa>> listaAmigos = new ArrayList<List<Pessoa>>();
		listaAmigos.add(lista1);
		listaAmigos.add(lista2);

		return listaAmigos;
	}
	
	/**
	 * Percorre todo o List<List<Pessoa>> recebido por parâmetro
	 * O parâmetro tem duas posições e ambas estão com duas List<Pessoas>
	 * Pega cada uma das List<Pessoa> e a percorre, pegando todos os objetos de Pessoa
	 * 
	 * Cada objeto pego, é salvo em um novo objeto chamado AmigoSecreto.
	 * Este objeto guarda o relacionamento entre as pessoas.
	 * 
	 * @param listaPessoas
	 * @return
	 */
	public static List<AmigoSecreto> gerarListaAmigoSecreto(List<List<Pessoa>> listaPessoas) {
		
		List<Pessoa> pessoasUm = listaPessoas.get(0);
		List<Pessoa> pessoasDois = listaPessoas.get(1);
		
		amigosecretoList = new ArrayList<AmigoSecreto>();
		
		for(int i = 0; i < pessoasUm.size(); i++) {
			AmigoSecreto amigoSecreto = new AmigoSecreto();
			amigoSecreto.setPessoaUm(pessoasUm.get(i));
			amigoSecreto.setPessoaDois(pessoasDois.get(i));
			amigosecretoList.add(amigoSecreto);
		}
		
		return amigosecretoList;
	}
	
	/**
	 * Retorna a lista do amigo secreto
	 * @return
	 */
	public static List<AmigoSecreto> getListaAmigoSecreto() {
		return amigosecretoList;
	}

}
