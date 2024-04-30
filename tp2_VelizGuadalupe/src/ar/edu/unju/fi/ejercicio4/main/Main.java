package ar.edu.unju.fi.ejercicio4.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio4.constantes.Posicion;
import ar.edu.unju.fi.ejercicio4.model.Jugador;


public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList <Jugador> jugadores = new ArrayList<>();
		int op=6;
		while(op!=5) {
			op=menu(sc);
			switch(op) {
			case 1:
				guardarJugador(jugadores,sc);
				break;
			case 2: 
				mostrarJugador(jugadores,sc);
				break;
			case 3: 
				modificarPosicion(jugadores,sc);
				break;
			case 4: 
				eliminarJugador(jugadores,sc);
				break;
			case 5: 
				System.out.println("Fin del menu :)");
				break;
			default:
				System.out.println("El numero ingresado no es valido");
			}
		}
		sc.close();
	}

	public static int menu(Scanner sc) {
		int opcion=9;
		System.out.println("*********** Menu *******");
		System.out.println("1) Alta de Jugador");
		System.out.println("2) Mostrar todos los Jugadores");
		System.out.println("3) Modificiar Posicion de un Jugador");
		System.out.println("4) Eliminar un Jugador");
		System.out.println("5) Salir del Menu");
		try {
			opcion=sc.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("ERROR se esperaba que ingrese un numero entero");
			sc.nextLine();
		}
		return opcion;
	}
	public static void guardarJugador(ArrayList <Jugador>jugadores,Scanner sc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String nombre,apellido,nacionalidad,fecha;
		Posicion [] posicion= Posicion.values();
		int opcion;
		float estatura,peso;
		try {
		System.out.println("Ingrese nombre: ");
		nombre=sc.next();
		System.out.println("Ingrese Apellido: ");
		apellido=sc.next();
		System.out.println("Ingrese fecha de nacimiento dia/mes/año:  ");
		fecha=sc.next();
		System.out.println("Ingrese Nacionalidad: ");
		nacionalidad=sc.next();
		System.out.println("Ingrese estatura: ");
		estatura=sc.nextFloat();
		System.out.println("Ingrese peso: ");
		peso=sc.nextFloat();
		opcion=elegirPosicion(sc);
		Jugador jugador=new Jugador(nombre,apellido,LocalDate.parse(fecha,formatter),nacionalidad,estatura,peso,posicion[opcion-1]);
		jugadores.add(jugador);
		}catch(InputMismatchException e) {
			System.out.println("ERROR: se esperaba que ingrese un numero en los campos estatura o peso");
			sc.nextLine();
		}catch(DateTimeParseException e) {
	    	System.out.println("ERROR: Debe ingresar numeros en el campo de fecha");
	    }
		
	}
	public static int elegirPosicion(Scanner sc) {
		int op=7;
		do {
			System.out.println("Posicion: ");
			System.out.println("1)Delantero");
			System.out.println("2)Medio");
			System.out.println("3)Defensa");
			System.out.println("4)Arquero");
			System.out.println("Ingrese una opcion: ");
			try {
			op=sc.nextInt();
			}catch (InputMismatchException e) {
				System.out.println("ERROR se esperaba que ingrese un numero entero");
				sc.nextLine();
			}
		}while(op<1 || op>4);
		return op;
	}
	public static void mostrarJugador(ArrayList<Jugador> jugadores, Scanner sc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		for(Jugador jugador : jugadores) {
			System.out.println("Datos del jugador: ");
			System.out.println("Nombre: "+jugador.getNombre());
			System.out.println("Apellido: "+jugador.getApellido());
			System.out.println("Fecha de nacimiento: "+jugador.getFechaNacimiento().format(formatter));
			System.out.println("Edad: "+jugador.calcularEdad());
			System.out.println("Nacionalidad: "+jugador.getNacionalidad());
			System.out.println("Estatura: "+jugador.getEstatura());
			System.out.println("Peso: "+jugador.getPeso());
			System.out.println("Posicion: "+jugador.getPosicion());		
			System.out.println("********************************************************+");
		}
	}
	
	public static void modificarPosicion(ArrayList<Jugador> jugadores,Scanner sc) {
		Posicion [] posicion= Posicion.values();
		String nombre,apellido;
		int opcion;
		boolean band=false;
		System.out.println("Ingrese nombre: ");
		nombre=sc.next();
		System.out.println("Ingrese apellido: ");
		apellido=sc.next();
		for(Jugador jugador : jugadores) {
			if(jugador.getNombre().equals(nombre)) {
				if(jugador.getApellido().equals(apellido)) {
					System.out.println("Ingrese nueva posicion: ");
					band=true;
					opcion=elegirPosicion(sc);
					jugador.setPosicion(posicion[opcion-1]);
				}
			}
		}
		if(band==false) {
			System.out.println("No existe un jugador registrado con ese nombre y apellido");
		}
	}
	
	public static void eliminarJugador(ArrayList<Jugador> jugadores, Scanner sc) {
		String nombre,apellido;
		boolean band=false;
		System.out.println("Ingrese nombre: ");
		nombre=sc.next();
		System.out.println("Ingrese apellido: ");
		apellido=sc.next();
		Iterator <Jugador> iterator =jugadores.iterator();
		while(iterator.hasNext()) {
			Jugador j= iterator.next();
			if(j.getNombre().equals(nombre) && j.getApellido().equals(apellido)) {
				iterator.remove();
				band=true;
				System.out.println("El jugador fue eliminado con éxito");
			}
		}
		if(band==false) {
			System.out.println("No existe un jugador registrado con ese nombre y apellido");
		}
	}

}
