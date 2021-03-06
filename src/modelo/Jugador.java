package modelo;

public class Jugador extends Persona {

	private int id;
	private int dorsal;
	private int idEquipo;

	public Jugador() {
	
	}

	public Jugador(int id, String nif, String nombre, int longitudPaso, String fecha_nac, char sexo, int dorsal, int idEq) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);
		this.id = id;
		this.dorsal = dorsal;
		this.idEquipo = idEq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

}
