package br.com.kaintegrados.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author katattau
 *
 */
@Entity
@Table(name="pessoa")
public class Pessoa {

	@Id
	@GeneratedValue
	private int id = -1;

	@NotNull(message="Nome inválido") 
	@Size(min=3, message="Nome inválido")
	private String nome;
	
	@NotNull(message="E-mail inválido") 
	@Size(min=6, message="E-mail inválido")
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
