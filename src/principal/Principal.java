package principal;

import control.Ejercicios;
import modelo.Persona;

public class Principal {

	public static void main(String[] args) {
		Ejercicios ejercicios = new Ejercicios();

		// Persona juan;
		// juan = new Persona("4545455X", "Juan Luis", 47,null);
		// Persona persona1 = new Persona();

		// Persona juan = new Persona();
		// int pasos = juan.caminar(20);

		// Ejercicios.pruebasAPI(); //Si el método fuera static
		// new Ejercicios().pruebasAPI();

		// int x = 10;
		// int y = 30;
		// ejercicios.listaIntervaloEnteros(x, y);

		// Act: 10/10/2018
		// System.out.println(converString("8fgf"));

		// serieFibonacci(8);

		// factorial(5);

		// int a = 1, b = 10, c = 4, d = 12;
		// int menor = Ejercicios.calNumMenor(a, b, c, d);
		// int menor = ejercicios.calNumMenor(a, b, c, d);
		// System.out.println("el menor es : " + ejercicios.calNumMenor(a, b, c));
		// System.out.println("el menor es : " + ejercicios.calNumMenor(x, y, z, w));

		// ejercicios.imprimeAleatorios(500);
		// ejercicios.imprimeAleatorios(6, 1, 6);
		// ejercicios.generaListaAleatorios(16, 1, 6);
		// ejercicios.sumaAleatorios(10, 12, 15);

		// int[] estadisticaDado= ejercicios.generaEstadisticaAparicion(10, 1, 6);
		// int[] listaAleatorios= ejercicios.generaListaAleatorios(20, 1, 5000);
		// int[] listaAleatorios1= ejercicios.generaEstadisticaAparicion1(6, 1, 6);

		// Persona[] lista = ejercicios.ListaPersonas(3);

		// ------24-10-2018
		// float[] movimientos = { 85.5f, 60.4f, -25.8f, -70.6f, 52.20f };
		// float saldo = 200f;
		// System.out.println("El saldo final es: "+ejercicios.calculaSaldo(movimientos,
		// saldo));

		// System.out.println(ejercicios.pruebaCadena("Las Palmas de Gran
		// Canariasssss"));
		// ejercicios.pruebaCadena();

		// ------ 25-10-2018
		// System.out.println("No coincidencias: "+(ejercicios.compararCadenas("abcd",
		// "abcdddddddd")));
		// ejercicios.compararCadenas();

		// ------ Prueba
		// String[] diasSemana = {"Lunes",
		// "Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"+"\n"};
		// ejercicios.listaDiasSemana(diasSemana);

		// ------ 31/10/2018
		float[][] ventasYear = { { 12.5f, 13.5f, 8.5f, 0f, 10.5f, 9.5f, 20.5f, 18.5f, 6.5f, 30.5f, 12.5f, 7.5f },
				{ 10.5f, 15.5f, 4.5f, 6f, 10.5f, 8.5f, 14.5f, 4.5f, 8.5f, 12.5f, 15.5f, 14.5f },
				{ 8.5f, 14.5f, 16.5f, 7f, 9.5f, 10.5f, 6.5f, 14.5f, 4.5f, 20.5f, 10.5f, 5.5f } };
		
		// 31/10/2018
		float[] resumenVendedor;
		//resumenVendedor = ejercicios.resumenVendedor(ventasYear);

		float[] resumenVentasPorMes;
		//ejercicios.resumenVentasPorMes(ventasYear);

		// 05/11/2018
		String[] cadena = { "123", "634", "101", "25M" };
		// int[] numeros =ejercicios.convierteCadenas(cadena);

		// 06/11/2018/
		//float[] resumen = ejercicios.resumenVentasPorMes(ventasYear);
		//ejercicios.mostrarHora();
		
		// 08/11/20018
		//System.out.println(ejercicios.esPrimo(17));
		
		int inicio = 1;
		int fin = 100;
		//ejercicios.listarPrimos(inicio, fin);
		
		//ejercicios.listarPrimos2(500, 5);
		int desde = 500;
		int cuantos = 5;
		//int[] resultado = ejercicios.listarPrimos3(desde, cuantos);
		// 13/11/2018
		
		int[] numeros= {1,9,6,10,50,2,6,20,7};
		//ejercicios.ordenaArrayNumeros(numeros);
		
		// 14/11/2018
		String[] cadenas= {"sábado ", "lunes ","viernes ","miércoles ","martes "};
		//ejercicios.ordenaArrayCadenas(cadenas);
		
		// 14/11/2018
		//int[] lista = ejercicios.generaListaAleatorios(1000, 1, 5000); //crear variable apara recoger la lista
		//ejercicios.ordenaArrayNumeros(lista);
		
		int[][] datos= {
				{1,4}, 
				{6},
				{5,2,3},
				{4,2,5,1,9,0,3},
				{5,6,1,3}};
		
		//int[] acumuladoColumnas=ejercicios.sumaColumnasMatrizHeterogenea(datos);
		 
		//Ejercicio 2
		
		int[] lista1 = {2,3,5,8,9,1,4};
		ejercicios.invertirLista(lista1);
		
		int[] l1 = {2,3,5,8,9,1,4};
		int[] l2 = {5,4,8,7,9,6,3,6};
		int[] resultado = ejercicios.mezclaListasOrdenadas(l1, l2);
		
		
		//Ejercicio 3
		String cadena1 = "sé verla al revés";
		System.out.println(ejercicios.invertirCaracteres(cadena1));
		
		System.out.println();
		System.out.println("FIN DEL PROGRAMA");
	}
	
	
 
}
