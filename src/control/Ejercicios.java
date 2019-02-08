package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.Equipo;
import modelo.Estudiante;
import modelo.Jugador;
import modelo.Partido;
import modelo.Persona;

public class Ejercicios {

	// ...................... 2º TRIMESTRE.....................

	// Devolver la clasificacion de equipo con puntos totales,
	// num de (partidos ganados empatados y perdidos) y num de (goles a favor y
	// goles en contra)
	HashMap<String, ArrayList<Integer>> creaClasificacion(String rutaFicheroPartidos) {
		HashMap<String, ArrayList<Integer>> clasificacion = new HashMap<String, ArrayList<Integer>>();

		HashMap<String, Equipo> mapaEquipos = crearMapaEquipos("ficheros/equipos.txt");
		HashMap<String, ArrayList<Integer>> mapaClasif = victoriasEmpatesDerrotas(rutaFicheroPartidos);

		/// Obtener todas las claves
		for (String clave : mapaClasif.keySet()) {
			ArrayList<Integer> listado = getArrayListClasificacion(mapaClasif.get(clave));
			clasificacion.put(mapaEquipos.get(clave).getNombre(), listado);

		}

		return clasificacion;
	}

	private ArrayList<Integer> getArrayListClasificacion(ArrayList<Integer> arrayList) {

		return null;
	}

	public HashMap<String, ArrayList<Integer>> victoriasEmpatesDerrotasYMas(String rutaFichero) {
		// por cada equipo hay una lista de contadores
		// que representan VICTORIAS, EMPATES Y DERROTAS
		// ArrayList<Integer> valor = new ArrayList<Integer>(4);
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;

			HashMap<String, ArrayList<Integer>> equipos = new HashMap<String, ArrayList<Integer>>();

			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				if (campos[3].equals("")) {
					break;
				}

				String eL = campos[2];
				String gL = campos[3];
				String eV = campos[4];
				String gV = campos[5];

				// si no existe el equipo local lo añadimos
				if (!equipos.containsKey(eL)) {
					equipos.put(eL, new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0)));
				}
				// si no existe el equipo visitante lo añadimos
				if (!equipos.containsKey(eV)) {
					equipos.put(eV, new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0)));
				}

				// Cual fué el resultado ..?
				if (gL.compareTo(gV) > 0) { // Gana Local

					equipos.get(eL).set(2, equipos.get(eL).get(2) + 1);
					equipos.get(eV).set(4, equipos.get(eV).get(4) + 1);

				}

				else if (gL.compareTo(gV) < 0) { // Gana Visitante

					equipos.get(eL).set(4, equipos.get(eL).get(4) + 1);
					equipos.get(eV).set(2, equipos.get(eV).get(2) + 1);
				}

				else { // Empate, se suma uno a cada equipo
					equipos.get(eL).set(3, equipos.get(eL).get(3) + 1);
					equipos.get(eV).set(3, equipos.get(eV).get(3) + 1);
				}

				// total de partidos jugados
				equipos.get(eL).set(1, equipos.get(eL).get(2) + equipos.get(eL).get(3) + equipos.get(eL).get(4));
				equipos.get(eV).set(1, equipos.get(eV).get(2) + equipos.get(eV).get(3) + equipos.get(eV).get(4));

				// Total puntos
				equipos.get(eL).set(0, (equipos.get(eL).get(2) * 3) + equipos.get(eL).get(3));
				equipos.get(eV).set(0, (equipos.get(eV).get(2) * 3) + equipos.get(eV).get(3));

				// goles a favor
				/*
				 * if (Integer.parseInt(gL) >= 0) { // guarda goles Local
				 * 
				 * equipos.get(eL).set(5, equipos.get(eL).get(5) + Integer.parseInt(gL));
				 * equipos.get(eV).set(6, equipos.get(eV).get(6) + Integer.parseInt(gV));
				 * 
				 * }
				 * 
				 * else if (Integer.parseInt(gV) >= 0) { // guarda goles Visitante
				 * 
				 * equipos.get(eL).set(6, equipos.get(eL).get(6) + Integer.parseInt(gL));
				 * equipos.get(eV).set(5, equipos.get(eV).get(5) + Integer.parseInt(gV)); }
				 */
				// Goles a favor
				if (gV != null) {
					equipos.get(eL).set(5, equipos.get(eL).get(5) + Integer.parseInt(gL));
					equipos.get(eV).set(6, equipos.get(eV).get(6) + Integer.parseInt(gV));
				}
				
				// goles en contra
				if (gL != null) {
					equipos.get(eV).set(6, equipos.get(eV).get(6) + Integer.parseInt(gV));
					equipos.get(eL).set(5, equipos.get(eL).get(5) + Integer.parseInt(gL));
				}

			}
			System.out.println(equipos);

			fichero.close();
			System.out.println("Creado el MAPA de equipos");
			return equipos;

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;

	}

	public HashMap<String, Equipo> crearMapaEquipos(String rutaFichero) {

		try {
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);

			HashMap<String, Equipo> mapaEquipos = new HashMap<String, Equipo>();
			Equipo equipo;
			String registro;
			String[] campos;
			while ((registro = br.readLine()) != null) {
				campos = registro.split("#");
				try {
					equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
					mapaEquipos.put(campos[1], equipo);

				} catch (NumberFormatException e) {
					System.out.println("Se ha creado un error de conversión");
				}

			}
			fr.close();
			br.close();
			System.out.println("Creada la lista de equipos");
			return mapaEquipos;

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;

	}

	// Obtener un ArrayList ordenada por nombre Largo del equipo
	// a partir de la lista obtenida en el metodo crearListaEquipos

	public ArrayList<Equipo> equiposListaOrdenadaNombre(ArrayList<Equipo> equipo) {

		ArrayList<Equipo> lista;
		lista = crearListaEquipos("ficheros/equipos.txt");

		/*
		 * lista.sort(new Comparator<Equipo>() {
		 * 
		 * //Compara Nombre de Equipo y Ordena de la A a la Z
		 * 
		 * @Override public int compare(Equipo eq1, Equipo eq2) { // TODO Auto-generated
		 * method stub return eq1.getNombre().compareTo(eq2.getNombre()); }
		 * 
		 * @Override // Compara Id de Equipo y ordena de mayor a menor. public int
		 * compare(Equipo eq1, Equipo eq2) { /// TODO Auto-generated method stub
		 * if(eq1.getId()<eq2.getId()){ return 1; } else if(eq1.getId()>eq2.getId()){
		 * return -1; }else return 0;
		 * 
		 * 
		 * } });
		 */
		lista.sort(null);

		System.out.println(lista);
		return lista;
	}

	// 5-02/2019
	public void ordenarMapaPuntoEquipo(HashMap<String, Integer> puntosEquipos) {

		Set<Entry<String, Integer>> set = puntosEquipos.entrySet();
		List<Entry<String, Integer>> lista = new ArrayList<Entry<String, Integer>>(set);
		// Lamda
		// Collections.sort((list,( 01, 02) -> o1.getValue().compareTo(o2.getValue()));
		Collections.sort(lista, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue()); // ordena por valor
				// return o1.getValue().compareTo(o2.getValue()); Ordena de forma ascendente
				// return (o2.getKey()).compareTo(o1.getKey()); Ordena por clave
			}

		});

		System.out.println("Puntuación ordenada:");
		for (Entry<String, Integer> entry : lista) {
			System.out.println(entry.getKey() + ": " + entry.getValue());

		}
	}

	public HashMap<String, ArrayList<Jugador>> plantilla(String rutaEquipos, String rutaJugadores) {

		HashMap<String, ArrayList<Jugador>> plantilla = new HashMap<String, ArrayList<Jugador>>();
		ArrayList<Equipo> equipos = crearListaEquipos(rutaEquipos);
		ArrayList<Jugador> jugadores = crearListaJugadores(rutaJugadores);
		// Recorre el ArrayList de equipo
		for (int i = 0; i < equipos.size(); i++) {
			ArrayList<Jugador> jugadoresEquipo = new ArrayList<Jugador>();
			// Si no contiene la clave, la añade
			if (!plantilla.containsKey(equipos.get(i).getNombre())) {

				// puedo guardar todos los datos equipo si sustituyo la cadena String por el
				// objeto equipo
				plantilla.put(equipos.get(i).getNombre(), jugadoresEquipo);
			} else {
				System.out.println("Si entra aqui hay algo raro");
			}
			// recorre ArrayList de jugadores, comprueba idEquipo de ambos arrays y añade
			// donde coincide.
			for (int j = 0; j < jugadores.size(); j++) {
				if (equipos.get(i).getId() == jugadores.get(j).getIdEquipo()) {
					jugadoresEquipo.add(jugadores.get(j));
				}
			}
		}

		return plantilla;
	}

	public void mostrarPlantilla(HashMap<String, ArrayList<Jugador>> plantilla) {
		Iterator<Entry<String, ArrayList<Jugador>>> iterador = plantilla.entrySet().iterator();

		while (iterador.hasNext()) {
			Entry<String, ArrayList<Jugador>> indice = iterador.next();
			System.out.println(indice.getKey());

			for (int i = 0; i < indice.getValue().size(); i++) {
				System.out.println(indice.getValue().get(i).getIdEquipo() + "-" + indice.getValue().get(i).getNombre()
						+ "--" + indice.getValue().get(i).getDorsal());
			}

			System.out.println();
		}
	}

	public void mostrarPlantillaOrdenada(HashMap<String, ArrayList<Jugador>> plantilla) {
		HashMap<String, ArrayList<Jugador>> mapaOrdenadoPlantilla = new HashMap<String, ArrayList<Jugador>>();
		// Obtener la lista de claves (K)
		for (String clave : plantilla.keySet()) {
			ArrayList<Jugador> datos = plantilla.get(clave);
			mapaOrdenadoPlantilla.put(clave, datos);
		}

		// Ahora si ordenamos.. // Ordena e imprime plantilla
		System.out.println("Plantilla ordenada por clave:");
		mapaOrdenadoPlantilla.entrySet().stream().sorted(Map.Entry.<String, ArrayList<Jugador>>comparingByKey())
				.forEach(System.out::println);

	}

	public HashMap<Integer, Jugador> crearMapaJugadores(String rutaFichero) {

		try {

			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			String[] campos;
			HashMap<Integer, Jugador> mapaJugadores = new HashMap<Integer, Jugador>();
			while ((registro = fichero.readLine()) != null) {
				campos = registro.split("#");
				Jugador jugador;
				jugador = new Jugador(Integer.parseInt(campos[0]), campos[1], campos[2], Integer.parseInt(campos[3]),
						campos[4], campos[5].charAt(0), Integer.parseInt(campos[6]), Integer.parseInt(campos[7]));

			}
			fichero.close();
			System.out.println("Creado el MAPA de jugdores");
			return mapaJugadores;

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;
	}

	public ArrayList<Jugador> crearListaJugadores(String rutaFichero) {

		try {

			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			String[] campos;
			ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
			while ((registro = fichero.readLine()) != null) {
				campos = registro.split("#");
				Jugador jugador;
				jugador = new Jugador(Integer.parseInt(campos[0]), campos[1], campos[2], Integer.parseInt(campos[3]),
						campos[4], campos[5].charAt(0), Integer.parseInt(campos[6]), Integer.parseInt(campos[7]));
				listaJugadores.add(jugador);
			}

			fichero.close();
			System.out.println("Creada la LISTA de Jugadores");
			return listaJugadores;

		} catch (

		FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;

	}

	// 05-02-2019 Muestra puntos equipos desordenado
	public HashMap<String, Integer> muestraPuntosDesordenados(
			HashMap<String, ArrayList<Integer>> victoriasEmpatesDerrotas) {
		HashMap<String, Integer> mapaOrdenadoPuntos = new HashMap<String, Integer>();
		// Obtener la lista de claves (K)
		for (String clave : victoriasEmpatesDerrotas.keySet()) {
			ArrayList<Integer> datos = victoriasEmpatesDerrotas.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			mapaOrdenadoPuntos.put(clave, puntos);
			// System.out.println(clave + " => " + puntos);
		}

		return mapaOrdenadoPuntos;
	}

	// 31-01-2019 --------
	// Clasificacion Liga de futbol ordenada
	// Muestra puntos de equipo ordenado.
	public void muestraPuntosEquiposOrdenados(HashMap<String, ArrayList<Integer>> resultados) {
		// recorrer el HashMap previamente ordenado
		HashMap<String, Integer> mapaOrdenadoPuntos = new HashMap<String, Integer>();
		// Obtener la lista de claves (K)
		for (String clave : resultados.keySet()) {
			ArrayList<Integer> datos = resultados.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			mapaOrdenadoPuntos.put(clave, puntos);
			// System.out.println(clave + " => " + puntos);
		}

		// Ahora si ordenamos..

		Set<Entry<String, Integer>> set = mapaOrdenadoPuntos.entrySet();
		List<Entry<String, Integer>> lista = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(lista, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});

		System.out.println("Puntuación ordenada:");
		for (Entry<String, Integer> entry : lista) {
			System.out.println(entry.getKey() + ": " + entry.getValue());

		}
	}

	// 30-01-2019 ---------
	// Pruebita de SWING (MVC)
	// --------------------
	public void pruebaSWING() {

		JFrame ventana;// declara en java la variable ventana de la clase JFRAME
		ventana = new JFrame("Mi primer SWING");
		JButton boton = new JButton("PulsaMe");// Se crea un boton
		JPanel panel = new JPanel();// se crea un panel
		ventana.add(panel);

		ArrayList<Equipo> equipos = this.crearListaEquipos("ficheros/equipos.txt");

		Equipo[] arrayEquipos = equipos.toArray(new Equipo[equipos.size()]);

		JComboBox lista = new JComboBox(arrayEquipos); // Sirve para poder guardar elementos dentro de una lista
		panel.add(lista);
		panel.add(boton);
		// 05-02-2019
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Muestra datos seleccionados
				System.out.println(lista.getSelectedItem());

			}
		});
		ventana.pack();
		ventana.setVisible(true); // para hacerlo visible

	}

	// 29/01/2019
	// con método void, igual que el anterior pero no devuelve nada.
	public void muestraPuntosEquipos(HashMap<String, ArrayList<Integer>> resultados) {
		// recorrer el HashMap previamente ordenado
		// Obtener la lista de claves (K)
		for (String clave : resultados.keySet()) {
			ArrayList<Integer> datos = resultados.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);

			System.out.println(clave + " => " + puntos);
		}

	}

	// devuelve un HashMap con la puntuacion de cada equipo
	public HashMap<String, Integer> puntuacionEquipos(String rutaFichero) {
		HashMap<String, Integer> puntuaciones = new HashMap<String, Integer>();
		HashMap<String, ArrayList<Integer>> datos = victoriasEmpatesDerrotas(rutaFichero);
		// Para obtener todas las claves de un mapa
		Set<String> clavesMapa = datos.keySet();
		int puntos;

		// Recorremos cada clave del set
		for (String clave : clavesMapa) {
			puntos = (3 * datos.get(clave).get(0)) + datos.get(clave).get(1);
			puntuaciones.put(clave, puntos);
		}

		return puntuaciones;
	}

	// // 29/01/2019 ------- -------- 24/01/2019
	public HashMap<String, ArrayList<Integer>> victoriasEmpatesDerrotas(String rutaFichero) {
		// por cada equipo hay una lista de contadores
		// que representan VICTORIAS, EMPATES Y DERROTAS
		// ArrayList<Integer> valor = new ArrayList<Integer>(4);
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;

			HashMap<String, ArrayList<Integer>> equipos = new HashMap<String, ArrayList<Integer>>();

			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				if (campos[3].equals("")) {
					break;
				}

				String eL = campos[2];
				String gL = campos[3];
				String eV = campos[4];
				String gV = campos[5];

				// si no existe el equipo local lo añadimos
				if (!equipos.containsKey(eL)) {
					equipos.put(eL, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
				}
				// si no existe el equipo visitante lo añadimos
				if (!equipos.containsKey(eV)) {
					equipos.put(eV, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
				}

				// Cual fué el resultado ..?
				if (gL.compareTo(gV) > 0) { // Gana Local

					equipos.get(eL).set(0, equipos.get(eL).get(0) + 1);
					equipos.get(eV).set(2, equipos.get(eV).get(2) + 1);
				}

				else if (gL.compareTo(gV) < 0) { // Gana Visitante

					equipos.get(eL).set(2, equipos.get(eL).get(2) + 1);
					equipos.get(eV).set(0, equipos.get(eV).get(0) + 1);
				}

				else { // Empate, se suma uno a cada equipo
					equipos.get(eL).set(1, equipos.get(eL).get(1) + 1);
					equipos.get(eV).set(1, equipos.get(eV).get(1) + 1);
				}
			}

			fichero.close();
			System.out.println("Creado el MAPA de equipos");
			return equipos;

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;

	}

	public void mostrarPartidosJugadosTry(String rutaPartidos) {
		try {
			BufferedReader fichero = null;
			int contador = 0;

			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;

			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				try {
					Integer.parseInt(campos[3]);
					contador++;
				} catch (Exception e) {

				}

			}

			fichero.close();
			System.out.println("Se han jugado " + contador + " partidos");
			System.out.println("Creada la muestra de partidos jugados");

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");

		}
	}

	// -------------- 23/01/2019
	// mostrar partidos jugados
	public void mostrarPartidosJugados(String rutaPartidos) {
		try {

			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaPartidos));
			String registro;
			int contador = 0;

			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				if (!campos[3].equals("")) {
					Integer.parseInt(campos[3]);
					contador++;
				} else
					break;
			}
			System.out.println("Se han jugado " + contador + " partidos");
			fichero.close();
			System.out.println("Creada la muestra de partidos jugados");

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

	}

	// Crea una lista de equipos en un Mapa

	// Crea una lista de equipos en un ArrayList
	public ArrayList<Equipo> crearListaEquipos(String rutaFichero) {

		try {

			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			String[] campos;
			ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
			while ((registro = fichero.readLine()) != null) {
				campos = registro.split("#");
				Equipo equipo;
				equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
				listaEquipos.add(equipo);
			}

			fichero.close();
			System.out.println("Creada la LISTA de equipos");
			return listaEquipos;

		} catch (

		FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;

	}

	// ------------- 22/01/2019

	public HashMap<String, Integer> comprobarPartidos(String rutaFichero) {
		try {
			HashMap<String, Integer> mapaJugadores;
			mapaJugadores = new HashMap<String, Integer>();

			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			Integer numVeces = new Integer(1);
			while ((registro = fichero.readLine()) != null) {

				String[] campos = registro.split("#");
				for (int i = 2; i < campos.length; i += 2) {
					if (mapaJugadores.containsKey(campos[i])) {
						mapaJugadores.replace(campos[i], (mapaJugadores.get(campos[i]) + 1));
					} else {
						mapaJugadores.put(campos[i], 1);
					}

				}
				for (int i = 0; i < campos.length; i++)
					System.out.print(campos[i] + ", ");
				System.out.println("");

			}
			fichero.close();
			return mapaJugadores;

		} catch (FileNotFoundException excepcion) {
			System.out.println("fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;
	}

	// ------------- 15/01/2019
	// Crear lista de personas a partir de fichero de personas

	public ArrayList<Persona> creaListaPersonasDesdeFichero(String rutaFichero, String separador) {

		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			// Crear ArrayList donde guardar lista de personas
			ArrayList<Persona> listaPersonas;
			listaPersonas = new ArrayList<>();
			Persona persona;
			String registro;
			String[] campos;
			while ((registro = fichero.readLine()) != null) {
				// System.out.println(registro);

				// Romper la cadena registro
				campos = registro.split(separador);

				// Crear objeto de la clase Persona
				persona = new Persona(campos[0], campos[1], Integer.parseInt(campos[2]), campos[3],
						campos[4].charAt(0));
				listaPersonas.add(persona);
				for (int i = 0; i < campos.length; i++)
					System.out.print(campos[i] + ", ");
				System.out.println("");

			}
			fichero.close();
			System.out.println("Creada la lista de personas");

			return listaPersonas;
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;
	}

	// ------------- 10/01/2019
	// <<<<<<<<<<<<<<<<< Leer Fichero >>>>>>>>>>>>>>>>>>>>

	public void leerFichero(String rutaFichero) {
		// Abrir fichero
		try {
			BufferedReader fichero;
			fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				System.out.println(registro);

				// ................

			}

			fichero.close();
			System.out.println("Fin de la lectura del fichero");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

	}

	// ----------------- 09/01/2019
	// <<<<<<<<<<<<<<<<< HashMap >>>>>>>>>>>>>>>>>>>>
	// Declarar EL MAPA (HaspMap) que almacena
	// objetos de la clase Estudiante, la clave es el nif.
	// INICIALIZAR EL MAPA
	// INSERTAR una serie de estudiantes.
	public void introMapa() {

		HashMap<String, Estudiante> mapa = new HashMap<>();

		Estudiante estud1 = new Estudiante("11111111N", "Mario", 145, null, 'M');
		Estudiante estud2 = new Estudiante("22222222B", "Ana", 250, null, 'F');
		Estudiante estud3 = new Estudiante("33333333A", "Juan", 100, null, 'M');
		Estudiante estud5 = new Estudiante("001", "Juan", 100, null, 'M');
		mapa.put(estud1.getNif(), estud1);
		mapa.put(estud2.getNif(), estud2);
		mapa.put(estud3.getNif(), estud3);
		mapa.put(estud5.getNif(), estud3);
		mapa.put("44444444C", new Estudiante("44444444C", "Maria", 80, null, 'F'));

		/*
		 * if(!mapa.containsKey("1")) mapa.put("1", null);
		 */

		// recorrer mapa

		Set<String> clavesMapa = mapa.keySet();
		for (String clave : clavesMapa) {
			System.out.println(mapa.get(clave).getNombre());

		}

		System.out.println("Fin mapa");
	}
	// ----------------- 08/01/2019
	// <<<<<<<<<<<<<<<<< ARRAYLIST >>>>>>>>>>>>>>>>>>>>

	public void introListas() {

		ArrayList<Persona> listaPersonas;

		listaPersonas = new ArrayList<Persona>();

		Persona persona1 = new Persona();

		listaPersonas.add(persona1);

		listaPersonas.add(new Persona());

		listaPersonas.add(new Persona("44321654F", "Pepe", 145, null, 'M'));

		listaPersonas.add(1, new Persona("nuevoNif", "Pepe", 145, null, 'M'));

		// System.out.println(listaPersonas.get(1).getNombre());
		ArrayList<Object> listaGenerica = new ArrayList<Object>(10);

		// System.out.println("Lista generica tienes " + listaGenerica.size());

		listaGenerica.add("Gran Canaria");
		String nombre = "Pepe";
		listaGenerica.add(nombre);
		listaGenerica.add(Math.PI);
		listaGenerica.add(123.5f);
		listaGenerica.add(true);
		listaGenerica.add(new Persona());

		// for (Object elemento : listaGenerica)
		// System.out.println(elemento);

		for (int i = 0; i < listaGenerica.size(); i++)
			System.out.println(listaGenerica.get(i));

		System.out.println("Lista generica tienes " + listaGenerica.size());

		System.out.println("fin listas");
	}

	// crear un Estudiante

	private Persona[] personas;

	public void crearEstudiante() {

		Estudiante estudiante = new Estudiante("43781230V", "Pedro Garcia", 153, null, 'M');

	}

	// .................... 1º trimestre .................

	// --------------- 04/10/2018 ACTIVIDAD: BUSCAR EN LA API
	// -------------------------------

	public void pruebasAPI() {
		/*
		 * 1. imprime por consola el valor de 2^3 - Usa la clase estática Math y su
		 * método pow (Cuando es estático no hace falta el new)
		 * 
		 * 2. Imprime un valor aleatorio entre 1 y 100 - Usa la clase java.util.Random y
		 * el método rnd 3. En la cadena "LAS PALMAS DE GRAN CANARIA", imprime "GRAN"
		 * -Usa el método substring de la clase java.lang.String
		 */

		// 1.
		System.out.println("1. " + Math.pow(2, 3));

		// 2.
		Random rnd = new Random();
		System.out.println("2. " + rnd.nextInt(100));

		// 3.
		System.out.println("3. " + "LAS PALMAS DE GRAN CANARIA".substring(14, 18));
	}

	// ACTIVIDAD: Mostrar por consola los números comprendidos entre dos
	// enteros, a y b

	// 1Parte método:. prototipo o cabecera
	public void listaIntervaloEnteros(int primero, int ultimo) {
		// 2Parte método: cuerpo o implementación del método
		/*
		 * int i=primero; while(i < ultimo) System.out.println(++i); i++; i=i+1;
		 */
		for (int j = primero; j <= ultimo; j++)
			System.out.println(j++);

		// true /false
		boolean condicion = true;
		if (condicion) {
			// en caso de verdadero
		} else {
			// la otra opción, falso
		}

	}

	// ---------------------- ACTIVIDAD
	// --------------------- 10/10/2018
	/*
	 * Dado una “cadena” (String) devolver el valor en forma de número entero
	 * correspondiente. Ejempo String: “ab95f1”
	 */

	// String cadena ="ab95f1";
	/*
	 * public static int converString(String cadena) { int numeros =
	 * Integer.parseInt(cadena); return numeros; }
	 */

	// -------------------- ACTIVIDAD : Serie Fibonacci
	// -------------------- 11/10/2018

	public static void serieFibonacci(int cuantos) {
		int num1 = 0;
		int num2 = 1;
		int total = 1;

		System.out.print(num1 + " ," + num2);
		for (int i = 1; i < cuantos; i++) {
			total = num1 + num2;
			System.out.print(" ," + total);
			num1 = num2;
			num2 = total;
		}
	}

	// ---------------------- ACTIVIDAD : Factorial (1.-Deberes
	// Casa)----------------- 11/10/2018

	public static void factorial(int numFact) {
		int num1 = 1;
		if (numFact == 0 | numFact == 1) {
			System.out.println("El factorial de " + "'" + numFact + "'" + " es :  1");
		} else {
			for (int i = 2; i <= numFact; i++) {
				num1 = num1 * i;
			}
			System.out.println("El factorial de " + "'" + numFact + "'" + " es :" + num1);
		}
	}

	// ---------------------- ACTIVIDAD : Devolver el número menor de tres
	// (2.-Deberes Casa)----------- 11/10/2018

	public int calNumMenor(int x, int y, int z) {
		if (x < y)
			if (x < z)
				return x;
			else
				return z;
		else if (y < z)
			return y;
		else
			return z;

	}

	public static int calNumMenor(int x, int y, int z, int w) {
		if (x < y)
			if (x < z)
				if (x < w)
					return x;
				else
					return w;

			else if (z < w)
				return z;
			else
				return w;

		else if (y < z)
			if (y < w)
				return y;

			else if (z < w)
				return z;
		return w;

	}

	// ------------------------17/10/2018
	// Imprime por consola los n números enteros aleatorios
	// entre 1 y 100.

	public void imprimeAleatorios(int n) { // n, Cuantos números
		int numero = 0;
		Random rnd = new Random();

		for (int i = 0; i < n; i++) {
			numero = 1 + rnd.nextInt(6); // Números aleatorios del 1 al 6
			System.out.println(i + ".-" + numero);
		}
	}

	public void imprimeAleatorios(int n, int inferior, int superior) { // n, Cuantos números
		int numero = 0;
		Random rnd = new Random();

		for (int i = 0; i < n; i++) {
			numero = 1 + rnd.nextInt(superior - inferior + 1); // Números aleatorios del 1 al 6
			System.out.println(i + 1 + ".-" + numero);
		}
	}

	// Devuelve un array de números enteros aleatorios, basandonos en el método
	// anterior

	public int[] generaListaAleatorios(int n, int inferior, int superior) {

		int[] resultado; // DECLARACION EN LA TABLA DE SIMBOLOS
		resultado = new int[n]; // INICIALIAZACION
		Random rnd = new Random();
		int numero = 0;

		for (int i = 0; i < n; i++) // for (int i = 0; i < resultado.length; i++)

			System.out.println(resultado[i] = inferior + rnd.nextInt(superior - inferior + 1)); // Números entre
																								// inferior y superior.

		return resultado;

	}

	public int sumaAleatorios(int n, int inferior, int superior) {

		int resultado = 0;
		Random rnd = new Random();

		for (int i = 0; i < n; i++) {

			resultado += inferior + rnd.nextInt(superior - inferior + 1);
		}

		return resultado;
	}

	public int[] generaEstadisticaAparicion1(int n, int inferior, int superior) {
		int[] resultado = new int[superior - inferior + 1];
		int[] numero = this.generaListaAleatorios(n, inferior, superior);

		for (int i = 0; i < n; i++) {

			resultado[numero[i] - 1]++;
			System.out.println(
					i + ".- Ejer2. El número " + numero[i] + ": lleva " + resultado[numero[i] - 1] + " apariciones");
		}

		return resultado;
	}

	// ------------------------24/10/2018--
	// Crear metodo que devuelva lista de persona pasando número de personas por
	// parametro.
	// 1.- Crear el prototipo, 2.- Crear nombreMetodo, 3.- Implementación, 4.-
	// Ejecucion
	public Persona[] crearListaPersonas(int n) {

		Persona[] lista = new Persona[n];

		for (int i = 0; i < n; i++)

			lista[i] = new Persona();

		return lista;
	}
	// Crea un método que devuelva el saldo de una cuenta, partiendo de una lista de
	// movimientos y del saldo inicial.

	public float calculaSaldo(float[] movimientos, float SaldoInicial) {

		float saldoFinal = SaldoInicial;

		for (int i = 0; i < movimientos.length; i++)
			saldoFinal += movimientos[i];

		// System.out.println((i+".- "+saldoFinal+"+"+movimientos[i]+"= "+(saldoFinal +=
		// movimientos[i]))+", ");

		return saldoFinal;
	}

	// ------------------------25/10/2018--
	// --------------------Prueba cadenas--

	public void pruebaCadena() {
		String nombre = "Las Palmas de Gran Canaria";// declaramos el objeto tipo String
		// iterar en la cadena mostrando todos sus caracteres

		for (int i = 0; i < nombre.length(); i++) {
			System.out.print(nombre.charAt(i));
		}
	}

	// 2.- ------ El mismo ejercicio pasando parametros
	public String pruebaCadena(String cadena) {

		for (int i = 0; i < cadena.length(); i++) {
			cadena.charAt(i);
		}
		return cadena;
	}

	// -------------------compara cadenas--
	public void compararCadenas() {
		String cad1 = "abcd";
		String cad2 = "abcddddddd";
		// System.out.println(cad1.compareTo(cad2));
		System.out.println("No coinciden " + (cad2.compareToIgnoreCase(cad1)) + " caracteres");

	}

	// 2.- ------ El mismo ejercicio pasando parametros
	public int compararCadenas(String cad1, String cad2) {
		return cad2.compareToIgnoreCase(cad1);
	}

	// ----------- Prueba
	public String[] listaDiasSemana(String[] dias) {

		// String[] diasSemana = {"Lunes",
		// "Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
		for (int i = 0; i < dias.length; i++) {
			System.out.print(dias[i] + ", ");

		}

		return dias;
	}

	// ---------------------- 31/10/2018
	public float[] resumenVendedor(float[][] ventas) {

		float[] resultado = new float[ventas.length];
		// iterar por filas/columnas acumulando ventas de cada vendedor

		for (int i = 0; i < ventas.length; i++) {
			for (int j = 0; j < ventas[i].length; j++)

				resultado[i] += ventas[i][j];

			// System.out.println("El total de ventas del vendedor " + (i + 1) + " es: " +
			// resultado[i]);

		}
		return resultado;
	}

	// ----------------- 06/11/2018
	public void mostrarVentasVendedor() {

		String[] nombresVendedor = { "Juan Carlos", "Isabel", "Marta" };
		float[][] ventasYear = { { 12.5f, 13.5f, 8.5f, 0f, 10.5f, 9.5f, 20.5f, 18.5f, 6.5f, 30.5f, 12.5f, 7.5f },
				{ 10.5f, 15.5f, 4.5f, 6f, 10.5f, 8.5f, 14.5f, 4.5f, 8.5f, 12.5f, 15.5f, 14.5f },
				{ 8.5f, 14.5f, 16.5f, 7f, 9.5f, 10.5f, 6.5f, 14.5f, 4.5f, 20.5f, 10.5f, 5.5f } };
		float[] ventas = resumenVendedor(ventasYear); // llamada del metodo resumenVendedor.

		for (int i = 0; i < ventas.length; i++) {
			System.out.println(nombresVendedor[i] + " : " + ventas[i]);

		}

	}

	// ---------------- 31/10/2018
	public float[] resumenVentasPorMes(float[][] ventas) {

		float[] resultado = new float[ventas[0].length];

		float acumuladoMes = 0.0f;

		String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiempre",
				"octubre", "noviembre", "diciembre" };

		for (int i = 0; i < ventas[0].length; i++) { // Recorrer los meses del año

			System.out.println("Acumulado en el mes de " + meses[i]);// venta total por mes

			for (int j = 0; j < ventas.length; j++) {
				acumuladoMes += ventas[j][i];
				resultado[i] += ventas[j][i];
			}
			System.out.println(resultado[i] + " €");
		}

		return resultado;
	}

	/*
	 * //pasar cadena a numero String x="6x9"; int numero = Integer.parseInt(x);
	 */

	// ------------------ 5/11/2018

	public int[] convierteCadenas(String[] cadenas) {
		// El array de salida tiene el MISMO numero
		// de elementos que el de entrada
		// si un numero no es valido, devuelve -1.

		int[] numero = new int[cadenas.length];

		for (int i = 0; i < cadenas.length; i++)
			try {
				numero[i] = Integer.parseInt(cadenas[i]);
				System.out.println("Llega aqui");
			} catch (NumberFormatException ex) {
				// Numero no valido
				numero[i] = -1;

			}
		return numero;
	}

	// ------------------ 7/11/2018
	// Mostrar hora con retardo de un segundo
	public void mostrarHora() {

		for (int h = 0; h < 1; h++) {
			for (int m = 0; m < 60; m++) {
				for (int s = 0; s < 60; s++) {

					System.out.println(h + ": " + m + ": " + s);

					try { //
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}

	}

	// ------------------- 08/11/2018
	// Comprueba si número es primo
	public boolean esPrimo(int num) {// True o false: Número primo...

		for (int i = 2; i < num; i++) // itera desde 2 hasta número pasado por parametro
			if (num % i == 0) // comprueba si resto de división (módulo) es 0.
				return false;
		return true;
	}

	public void listarPrimos(int desde, int hasta) {

		for (int i = desde; i < hasta; i++) {
			if (esPrimo(i))
				;

			System.out.print(i + ", ");

		}
	}

	public void listarPrimos2(int desde, int cuantos) {

		int contador = 0;

		while (contador < cuantos) {
			if (esPrimo(desde)) {
				System.out.print(desde + ", ");
				contador++;
			}
			desde++;

		}

	}

	public int[] listarPrimos3(int desde, int cuantos) {

		int[] resultado = new int[cuantos];
		int contador = 0;

		while (contador < cuantos) {
			if (esPrimo(desde)) {
				resultado[contador] = desde;
				contador++;
				System.out.print(desde + " , ");
			}
			desde++;
		}
		return resultado;

	}

	public int[] listarPrimos4(int desde, int cuantos) {

		int[] resultado = new int[cuantos];
		int contador = 0;

		while (contador < cuantos) {
			if (esPrimo(desde)) {
				resultado[contador] = desde;
				contador++;
				System.out.print(desde + " , ");
			}
			desde++;
		}
		return resultado;

	}

	// --------------- 13/11/2018
	public void ordenaArrayNumeros(int[] numeros) {

		int auxiliar = 0;

		for (int i = 0; i < numeros.length - 1; i++) {
			for (int j = i + 1; j < numeros.length; j++) {

				if (numeros[i] > numeros[j]) {
					auxiliar = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = auxiliar;
				}
			}
		}
	}

	// ------------------- 14/11/2018
	public void ordenaArrayCadenas(String[] cadenas) {

		for (int i = 0; i < cadenas.length - 1; i++) {
			for (int j = i + 1; j < cadenas.length; j++)
				if (cadenas[i].compareTo(cadenas[j]) > 0) { // Si cadena i compara mas de 0 veces entra en siguiente.
					String aux = cadenas[i];
					cadenas[i] = cadenas[j];
					cadenas[j] = aux;
				}

		}
	}

	public int[] sumaColumnasMatrizHeterogenea(int[][] matriz) {

		int acumuladoColumna = 0;
		int numColMAX = 0;

		// Averigua número maximo de columnas.
		for (int i = 0; i < matriz.length; i++)
			if (matriz[i].length > numColMAX)
				numColMAX = matriz[i].length;
		System.out.println(acumuladoColumna);

		int[] resultado = new int[numColMAX];

		// recorrer matriz por columnas
		for (int j = 0; j < numColMAX; j++) {
			System.out.println("Columna   [" + j + "]");
			for (int i = 0; i < matriz.length; i++)
				try {
					System.out.println("Fila [" + i + "]:  " + matriz[i][j]);
					resultado[j] += matriz[i][j];
					acumuladoColumna += matriz[j][i]; // Suma de columnas

				} catch (ArrayIndexOutOfBoundsException e) {

				}

			System.out.println("          ---");
			System.out.println("    Total: " + resultado[j]);
			System.out.println();

		}
		return resultado;
	}

	// ------------------- 20/11/2018
	// ----------------- Ejercicios con arrays
	// 1. Dada una lista (array) de enteros (int), obtener la lista INVERTIDA sobre
	// si misma
	public void invertirLista(int[] lista) { // Hecho en clase
		int aux;
		for (int i = 0; i < lista.length / 2; i++) {
			aux = lista[i];
			lista[i] = lista[lista.length - i - 1];
			lista[lista.length - i - 1] = aux;

		}

	}

	// ------------------- 29/11/2018
	public int[] invertirLista2(int[] lista) { // Devuelve el resultado en otra lista.
		int[] resultado = new int[lista.length];

		for (int i = 0; i < lista.length; i++) {
			resultado[(lista.length - 1) - i] = lista[i];

		}
		return resultado;

	}

	// 2. Dadas dos listas PREVIAMENTE ORDENADAS, se pide obtener la lista MEZCLA de
	// ambas.

	public int[] mezclaListasOrdenadas(int[] l1, int[] l2) {
		ordenaArrayNumeros(l1);
		ordenaArrayNumeros(l2);
		int[] mezcla = new int[l1.length + l2.length];

		for (int i = 0; i < l1.length; i++) {
			mezcla[i] = l1[i];
		}
		for (int j = 0; j < l2.length; j++) {
			mezcla[l1.length + j] = l2[j];

		}
		ordenaArrayNumeros(mezcla);
		return mezcla;
	}

	// ------------------- 22/11/2018
	// Mismo ejercicio con otra solucion
	public int[] mezclaListasOrdenadas2(int[] l1, int[] l2) {
		int[] resultado = new int[l1.length + l2.length];
		int i = 0;
		int j = 0;
		int k = 0;

		while (k < l1.length + l2.length) {
			// while (i < l1.length || j < l2.length) {
			try {

				if (l1[i] < l2[j]) {
					resultado[k] = l1[i];
					i++;
				} else {
					resultado[k] = l2[j];
					j++;
				}
				k++;
			} catch (ArrayIndexOutOfBoundsException e) {
				if (i == l1.length) // fin de l1...
					l1[--i] = Integer.MAX_VALUE;
				else {
					l2[--j] = Integer.MAX_VALUE;

				}
			}
		}

		return resultado;
	}

	// 3. Dada una cadena , obtener la cadena INVIRTIENDO sus caracteres (char)
	// usar charAt() o toCharArray()

	public String invertirCaracteres(String cadena) {
		String listaInvertida = new String();
		for (int i = cadena.length() - 1; i >= 0; i--)
			// listaInvertida += cadena.charAt(i); // Solución con caracter.
			listaInvertida = listaInvertida.concat(cadena.substring(i, i + 1)); // Otra solución, con cadena y método
																				// "concat".
		return listaInvertida;

	}

	// ------------------- 21/11/2018
	// Ordena filas de matriz
	public void ordenaFilasMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			ordenaArrayNumeros(matriz[i]);
		}
	}

	// ------------------- 27/11/2018
	public int[] matrizToArrayOrdenado(int[][] matriz) {

		int[] resultado = new int[1];
		ordenaFilasMatriz(matriz);

		for (int i = 0; i < matriz.length; i++)
			// Se crea un nuevo array con la unión de matriz[i] y result
			// Y se guarda en result.

			resultado = mezclaListasOrdenadas(resultado, matriz[i]);

		return resultado;

	}

	// ------------------- 28/11/2018
	Persona persona1 = new Persona("25896347P", "Mario", 16, null, 'M');
	Persona persona2 = new Persona("25814563W", "Belen", 18, null, 'F');
	Persona[] hijosP1 = { persona1, persona2 };

	Persona persona3 = new Persona("25896347P", "Carlos", 16, null, 'M');
	Persona persona4 = new Persona("25814563W", "Sara", 18, null, 'F');
	Persona[] hijosP2 = { persona3, persona4 };

	Persona persona5 = new Persona("35896425F", "Ana", 25, null, 'F', hijosP2, persona1, persona2);
	Persona persona6 = new Persona("5289643L", "Andres", 33, null, 'M', null, persona3, persona4);
	Persona persona7 = new Persona("35896425F", "Juana", 12, null, 'F', null, persona2, persona1);
	Persona persona8 = new Persona("5289643L", "Antonio", 15, null, 'M', null, persona3, persona2);

	Persona persona9 = new Persona("25825825Ñ", "Pepe", 53, null, 'F', hijosP2, persona1, persona2);
	Persona persona10 = new Persona("365894P", "María", 33, null, 'F', hijosP1, persona3, persona4);

	private Persona[] persona = { persona1, persona2, persona3, persona4, persona5, persona6, persona7, persona8,
			persona9, persona10 };

	public void hijosPersonas() {

		for (int i = 0; i < persona.length; i++) {

			Persona[] hijos = persona[i].getHijosBiologicos();
			System.out.println("Padre o madre: " + persona[i].getNombre());

			if (hijos != null) {
				for (int j = 0; j < hijos.length; j++) {
					System.out.println("Hijos " + hijos[j].getNombre());

				}

			} else {
				System.out.println("No tiene hijos ");
			}
		}

	}

}
