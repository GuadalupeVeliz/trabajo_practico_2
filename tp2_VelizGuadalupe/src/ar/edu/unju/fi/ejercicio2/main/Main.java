package ar.edu.unju.fi.ejercicio2.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio2.constantes.Mes;
import ar.edu.unju.fi.ejercicio2.model.Efemeride;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		ArrayList <Efemeride> efemerides =new ArrayList<>();
		int opcion=-1;
		do {
			try {
				mostrarMenu();
				opcion=sc.nextInt();
				switch(opcion) {
				case 1:
					crearEfemeride(efemerides,sc);
					break;
				case 2:
					mostrarEfemerides(efemerides);
					break;
				case 3:
					eliminarEfemeride(efemerides,sc);
					break;
				case 4:
					modificarEfemeride(efemerides,sc);
					break;
				case 5: 
					System.out.println("Fin del menu :)");
					break;
				default:
					System.out.println("Opcion ingresada NO valida");
				}
			}catch(InputMismatchException e) {
				System.out.println("ERROR debe ingresar un numero como opcion");
				sc.nextLine();
			}	
		}while(opcion!=5);
	}

	public static void mostrarMenu() {
		System.out.println("********MENU*********");
		System.out.println("1)Crear Efemeride");
		System.out.println("2)Mostrar Efemeride");
		System.out.println("3)Eliminar Efemeride");
		System.out.println("4)Modificar Efemeride");
		System.out.println("5)Salir");
		System.out.println("Elegir opcion: ");
	}

	public static void crearEfemeride(ArrayList<Efemeride> efemerides, Scanner sc) {
		String codigo,detalle;
		int mes,dia;
		Mes [] meses = Mes.values();
		try {
		System.out.println("Ingrese codigo");
		codigo=sc.next();
		mes= elegirMes(sc);
		dia= elegirDia(sc,mes);
		System.out.println("Ingrese detalle: ");
		sc.nextLine();
		detalle=sc.nextLine();
		Efemeride efemeride = new Efemeride(codigo,meses[mes-1],dia,detalle);
		efemerides.add(efemeride);
		}catch(InputMismatchException e) {
			System.out.println("ERROR debe ingresar un numero en los campos de opcion,mes y dia ");
			 sc.next();
		}
	}
	public static int elegirMes(Scanner sc) {
		int opcion;
		do {
			mostrarMeses();
			opcion=sc.nextInt();
		}while(opcion<1 || opcion>12);
		return opcion;
	}
	public static void mostrarMeses() {
		System.out.println("1)Enero           2)Febrero            3)Marzo");
		System.out.println("4)Abril           5)Mayo               6)Junio");
		System.out.println("7)Julio           8)Agosto             9)Septiembre");
		System.out.println("10)Octubre        11)Noviembre         12)Diciembre");
		System.out.println("Elegir mes: ");
	}
	public static int elegirDia(Scanner sc, int mes) {
		int opcion=-1;
		boolean bandera;
		do {
			bandera=true;
			System.out.println("Ingrese dia: ");
			opcion=sc.nextInt();
			if(mes==2 && opcion>28) {
				bandera=false;
				System.out.println("El mes seleccionado tiene 28 dias");
			}
			if((mes== 4 || mes== 6 ||mes==9 || mes==11) && opcion>30) {
					bandera=false;
					System.out.println("El mes seleccionado tiene 30 dias");
			}
		}while(opcion<1 || opcion>31|| bandera==false);
		return opcion;
	}

	public static void mostrarEfemerides(ArrayList<Efemeride> efemerides) {
		for(Efemeride efemeride: efemerides) {
			System.out.println("Codigo: "+efemeride.getCodigo());
			System.out.println("Mes: "+efemeride.getMes());
			System.out.println("Dia: "+efemeride.getDia());
			System.out.println("Detalle: "+efemeride.getDetalle());
			System.out.println("***************************************");
		}
	}
	
	public static void eliminarEfemeride(ArrayList<Efemeride> efemerides,Scanner sc) {
		String codigo;
		boolean bandera=false;
		System.out.println("Ingrese codigo de la Efemeride a eliminar: ");
		codigo=sc.next();
		Iterator <Efemeride> iterator=efemerides.iterator();
		while(iterator.hasNext()) {
			Efemeride e=iterator.next();
			if(e.getCodigo().equals(codigo)) {
				iterator.remove();
				bandera=true;
				System.out.println("La Efemeride se eliminó con exito");
			}
		}
		if(bandera==false) {
			System.out.println("El codigo ingresado NO pertenece a ninguna Efemeride");
		}
	}
	public static void modificarEfemeride(ArrayList<Efemeride> efemerides,Scanner sc) {
		String codigo,detalle;
		int opcion=-1,mes,dia;
		boolean bandera=false;
		Mes [] meses = Mes.values();
		System.out.println("Ingrese codigo de la Efemeride a Modificar: ");
		codigo=sc.next();
		for(Efemeride efemeride: efemerides) {
			if(efemeride.getCodigo().equals(codigo)) {
				bandera=true;
				do {
					try {
						mostrarMenuModificar();
						opcion=sc.nextInt();
						switch(opcion) {
						case 1:
							mes=elegirMes(sc);
							efemeride.setMes(meses[mes-1]);
							break;
						case 2:
							mes=buscarMes(efemeride);
							dia=elegirDia(sc,mes);
							efemeride.setDia(dia);
							break;
						case 3:
							System.out.println("Ingrese nuevo detalle: ");
							detalle=sc.nextLine();
							efemeride.setDetalle(detalle);
							break;
						default:
							System.out.println("Opcion Ingresada NO valida");
						}
					}catch(InputMismatchException e) {
						System.out.println("ERROR debe ingresar un numero en el campo de opcion ");
						 sc.next();
					}		
				}while(opcion<1 || opcion >3);		
			}
		}
		if(bandera==false) {
			System.out.println("El codigo ingresado NO pertenece a ninguna Efemeride");
		}
		
	}
	public static int buscarMes(Efemeride efemeride) {
		int mes=-1;
		switch(efemeride.getMes()) {
		case ENERO:
			mes=1;
			break;
		case FEBRERO:
			mes=2;
			break;
		case MARZO:
			mes=3;
			break;
		case ABRIL:
			mes=4;
			break;
		case MAYO:
			mes=5;
			break;
		case JUNIO:
			mes=6;
			break;
		case JULIO:
			mes=7;
			break;
		case AGOSTO:
			mes=8;
			break;
		case SEPTIEMBRE:
			mes=9;
			break;
		case OCTUBRE:
			mes=10;
			break;
		case NOVIEMBRE:
			mes=11;
			break;
		case DICIEMBRE:
			mes=12;
			break;
		}
		return mes;
	}
	public static void mostrarMenuModificar() {
		System.out.println("1)Modificar mes");
		System.out.println("2)Modifcar dia");
		System.out.println("3)Modificar Detalle");
	}
}
