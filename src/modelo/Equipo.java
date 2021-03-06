package modelo;

public class Equipo implements Comparable<Equipo>{
	
	

	private int id;
	private String nombreCorto;
	private String nombre;

	public Equipo(int id, String nombreCorto, String nombre) {
		super();
		this.id = id;
		this.nombreCorto = nombreCorto;
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Equipo: "  + this.getId() +"  "+ this.getNombre() + " " +this.getNombreCorto() ;
	}
	

	public Equipo() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int compareTo(Equipo o) {
		
		if(this.getId() < o.getId())
			return 1;
		
		else if(this.getId() > o.getId())
			return -1;
		else
			return 0;
		
	}

}
