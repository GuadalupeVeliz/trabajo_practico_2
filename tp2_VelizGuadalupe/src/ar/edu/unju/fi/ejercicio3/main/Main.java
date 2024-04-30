package ar.edu.unju.fi.ejercicio3.main;

import ar.edu.unju.fi.ejercicio3.constantes.Provincia;

public class Main {

	public static void main(String[] args) {
		Provincia[] provincias = Provincia.values();
		for (int i = 0; i < provincias.length; i++) {
			System.out.println("-----------------------------");
			System.out.println("Provincia: " + provincias[i].name());
			System.out.println("Poblacion: " + provincias[i].getPoblacion() + " habitantes");
			System.out.println("Superficie: " + provincias[i].getSuperficie() + "km2");
			System.out.println("Densidad Poblacional: " + provincias[i].calcularDensidadPoblacional());
	    }
	}
}
