package br.com.kaintegrados.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author katattau
 *
 */
public class Validacao {

	/**
	 * Valida o e-mail
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmailValido(String email) {
		if ((email == null) || (email.trim().length() == 0))
			return false;

		String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
		Pattern pattern = Pattern.compile(emailPattern,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * Validação de um campo em específico Verifica se está null, se é vazio e
	 * se seu tamanho é 0 (zero)
	 * 
	 * @param valor
	 * @return boolean
	 */
	public static boolean isNullIsEmpty(String valor) {

		if (valor == null)
			return true;

		if (valor.isEmpty())
			return true;

		if (valor.length() == 0)
			return true;

		return false;
	}
}
