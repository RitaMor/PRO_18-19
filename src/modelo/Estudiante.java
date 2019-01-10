package modelo;

import java.time.LocalDate;

public class Estudiante extends Persona {

	private String curso;
	private String matricula;
	private char turn; // 'M', 'T', 'N'

	public Estudiante(String nif, String nombre, int longitudPaso, LocalDate fecha_nac, char sexo) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);

	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public char getTurn() {
		return turn;
	}

	public void setTurn(char turn) {
		this.turn = turn;
	}
	
}
