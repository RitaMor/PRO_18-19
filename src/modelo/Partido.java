package modelo;

public class Partido {

	

	private int id;
	private int jornada;
	private String eLocal;
	private int golesLocal;
	private String eVisitante;
	private int golesVisitante;

	public Partido() {

	}

	public Partido(int id, int jornada, String eLocal, int golesLocal, String eVisitante,
			int golesVisitante) {
		
		this.id = id;
		this.jornada = jornada;
		this.eLocal = eLocal;
		this.golesLocal = golesLocal;
		this.eVisitante = eVisitante;
		this.golesVisitante = golesVisitante;
	}

	
	@Override
	public String toString() {
		return "Partido [id=" + id + ", jornada=" + jornada + ", eLocal=" + eLocal + ", golesLocal=" + golesLocal
				+ ", eVisitante=" + eVisitante + ", golesVisitante=" + golesVisitante + "]";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJornada() {
		return jornada;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	public String geteLocal() {
		return eLocal;
	}

	public void seteLocal(String eLocal) {
		this.eLocal = eLocal;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public String geteVisitante() {
		return eVisitante;
	}

	public void seteVisitante(String eVisitante) {
		this.eVisitante = eVisitante;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}


	
	

}




