package br.com.kaintegrados.utils;

import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.kaintegrados.dto.AmigoSecreto;

import com.dumbster.smtp.SimpleSmtpServer;

public class EnviarEmail {

	private static final int PORTA_SMTP = 1025;
	private static final String EMAIL_ASSUNTO = "Amigo Secreto - Avaliacao focusnetworks";
	private static final String EMAIL_REMET = "comercial@focusnetworks.com.br";
	private static final String NOME_REMET = "focusnetworks";

	/**
	 * Cria todos os parâmetros necessários para o envio de e-mail, como:
	 * destinatário, nome_destinatário e mensagem
	 * 
	 * @param listaAmigosSecreto
	 * @return String
	 */
	public static String enviarEmail(List<AmigoSecreto> listaAmigosSecreto) {

		StringBuilder msg = new StringBuilder();

		StringBuilder sBuilder = new StringBuilder("Prezado(a) ");

		for (AmigoSecreto aSecreto : listaAmigosSecreto) {

			String[] info = new String[3];

			info[0] = aSecreto.getPessoaUm().getEmail(); // email destinatario
			info[1] = aSecreto.getPessoaUm().getNome(); // nome destinatario

			sBuilder.append(info[1])
					.append(", deves presentear a seguinte pessoa: ")
					.append(aSecreto.getPessoaDois().getNome())
					.append("! A presenteie com algo legal este ano!");

			info[2] = sBuilder.toString();

			// se o retorno do método enviar for igual a 0, significa que houve um problema
			// a variável msg guarda para qual destinatário ocorreu o problema
			if (enviar(info) == 0)
				msg.append("Ocorreu um problema ao enviar o e-mail ao destinatário: ").append(info[0]).append("\n");

			sBuilder = new StringBuilder();
		}
		return msg.toString();
	}

	/**
	 * Realiza o envio de e-mail utilizando um Fake SMTP
	 * 
	 * @param info
	 * @return int
	 */
	private static int enviar(String[] info) {

		SimpleSmtpServer server = SimpleSmtpServer.start(PORTA_SMTP);

		SimpleEmail email = new SimpleEmail();
		email.setHostName("127.0.0.1"); // o servidor SMTP para envio do e-mail

		try {

			email.addTo(info[0], info[1]); // destinatario: email - nome
			email.setFrom(EMAIL_REMET, NOME_REMET); // remetente: email - nome
			email.setSubject(EMAIL_ASSUNTO); // assunto
			email.setMsg(info[2]); // conteudo do e-mail
			email.setSmtpPort(PORTA_SMTP);
			email.send();

		} catch (EmailException e) {
			e.printStackTrace();
			return 0;
		}

		server.stop();

		// se o retorno é 1, então tudo deu certo, senão, algum problema aconteceu
		if (server.getReceivedEmailSize() == 1)
			return 1;
		else
			return 0;

		/*
		 * Posso utilizar o objeto sMessage abaixo para verificar se o e-mail
		 * que foi enviado estava com alguns parametros corretos, como assunto,
		 * mensagem e etc
		 */

		// Iterator emailIter = server.getReceivedEmail();
		// SmtpMessage sMessage = (SmtpMessage) emailIter.next();

		// System.out.println(sMessage.getHeaderValue("Subject"));
		// System.out.println(sMessage.getBody());
	}
}
