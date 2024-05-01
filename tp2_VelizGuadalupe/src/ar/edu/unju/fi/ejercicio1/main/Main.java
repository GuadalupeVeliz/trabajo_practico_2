package ar.edu.unju.fi.ejercicio1.main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio1.model.Producto;
import ar.edu.unju.fi.ejercicio1.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio1.model.Producto.origenFabricacion;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		ArrayList<Producto> productos = new ArrayList<>();
		int opcion=0;
		while(opcion!=4) {
			try {
				mostrarMenu();
				opcion=sc.nextInt();
				switch(opcion) {
				case 1:
					crearProducto(productos,sc);
					break;
				case 2:
					mostrarProductos(productos);
					break;
				case 3:
					modificarProducto(productos,sc);
					break;
				case 4:
					System.out.println("Fin del Menu :)");
					break;
				default:
					System.out.println("Opcion ingresada NO valida");
				}
			}catch(InputMismatchException e) {
				System.out.println("ERROR debe ingresar un numero como opcion");
				sc.next();
			}
		}
	}

	private static void crearProducto(ArrayList<Producto> productos, Scanner sc) {
		origenFabricacion origen=origenFabricacion.valueOf("ARGENTINA");
		Categoria categoria=Categoria.valueOf("TELEFONIA"); 
		String codigo,descripcion;
		Double precio;
		System.out.println("Ingrese codigo del Producto: ");
		codigo=sc.next();
		sc.nextLine();
		System.out.println("Ingrese Descripcion del Producto: ");
		descripcion=sc.nextLine();
		try {
		System.out.println("Ingrese Precio Unitario: ");
		precio=sc.nextDouble();
		origen=elegirOrigen(origen,sc);	
		categoria=elegirCategoria(categoria,sc);
		Producto producto= new Producto(codigo,descripcion,precio,origen,categoria);
		productos.add(producto);
		}catch(InputMismatchException e) {
			System.out.println("ERROR debe ingresar un numero como opcion");
			 sc.next();
		}
	}
	
	public static origenFabricacion elegirOrigen(origenFabricacion origen,Scanner sc) {
		int opcion=-1;
		do {
			try {
			mostrarOrigen();
			opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				origen=origenFabricacion.ARGENTINA;
				break;
			case 2:
				origen=origenFabricacion.CHINA;
				break;
			case 3:
				origen=origenFabricacion.BRASIL;
				break;
			case 4:
				origen=origenFabricacion.URUGUAY;
				break;
			default:
				System.out.println("Opcion ingrsada NO valida");
			}
		}catch(InputMismatchException e) {
			System.out.println("ERROR debe ingresar un numero como opcion");
			 sc.next();
		}
		}while(opcion<1 || opcion>4);
	
		return origen;
	}
	public static Categoria elegirCategoria(Categoria categoria,Scanner sc) {
		int opcion=-1;
		do {
			try {
			mostrarCategoria();
			opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				categoria=Categoria.TELEFONIA;
				break;
			case 2:
				categoria=Categoria.INFORMATICA;
				break;
			case 3:
				categoria=Categoria.ELECTROHOGAR;
				break;
			case 4:
				categoria=Categoria.HERRAMIENTAS;
				break;
			}
			}catch(InputMismatchException e) {
				System.out.println("ERROR debe ingresar un numero como opcion");
				 sc.next();
			}
		}while(opcion <1 || opcion >4);	
		return categoria;
	}
	public static void mostrarMenu() {
		System.out.println("*********** Menú *********");
		System.out.println("1)Crear Producto");
		System.out.println("2)Mostrar Productos");
		System.out.println("3)Modificar Producto");
		System.out.println("4)Salir");
	}
	public static void mostrarOrigen() {
		System.out.println("-----Origen de Fabricacion------");
		System.out.println("1-Argentina");
		System.out.println("2-China");
		System.out.println("3-Brasil");
		System.out.println("4-Uruguay");
		System.out.println("Elija una opcion: ");
	}
	public static void mostrarCategoria() {
		System.out.println("----- Categoria -----");
		System.out.println("1- Telefonia");
		System.out.println("2- Informatica");
		System.out.println("3- Electro hogar");
		System.out.println("4- Herramientas");
		System.out.println("Elija una opcion: ");
	}
	
	private static void mostrarProductos(ArrayList<Producto> productos) {
		for(Producto producto: productos) {
			System.out.println("Codigo: "+producto.getCodigo());
			System.out.println("Descripcion: "+producto.getDescripcion());
			System.out.println("Precio Unitario: "+producto.getPrecioUnitario());
			System.out.println("Origen de Fabricacion: "+producto.getOrigen());
			System.out.println("Categoria: "+producto.getCategoria());
		}
		
	}
	private static void modificarProducto(ArrayList<Producto> productos,Scanner sc) {
		int opcion;
		boolean band=false;
		String codigo;
		System.out.println("Ingrese codigo del Producto a Modificar: ");
		codigo=sc.next();
		for (Producto producto:productos) {
			if(producto.getCodigo().equals(codigo)){
				do {
					mostrarMenuModificacion();
					band=true;
					opcion=sc.nextInt();
					switch(opcion) {
					case 1:
						modificarDescripcion(producto,sc);
						break;
					case 2:
						modificarPrecio(producto,sc);
						break;
					case 3:
						modificarOrigen(producto,sc);
						break;
					case 4:
						modificarCategoria(producto,sc);
						break;
					default:
						System.out.println("Opcion ingresada NO valida");
					}
				}while(opcion<1 || opcion>4);
			}
		}
		if(!band) 
			System.out.println("No existe un Producto que pertenezca al codigo Ingresado");
	}

	public static void mostrarMenuModificacion() {
		System.out.println("Modificar: ");
		System.out.println("1)Descripcion");
		System.out.println("2)Precio Unitario");
		System.out.println("3)Origen de Fabricacion");
		System.out.println("4)Categoria");
		System.out.println("Elija una opcion: ");
	}
	
	private static void modificarDescripcion(Producto producto,Scanner sc) {
		String descripcion;
		sc.nextLine();
		System.out.println("Ingrese nueva Descripcion: ");
		descripcion=sc.nextLine();
		producto.setDescripcion(descripcion);
	}
	private static void modificarPrecio(Producto producto, Scanner sc) {
		Double precio;
		System.out.println("Ingrese nuevo Precio Unitario: ");
		precio=sc.nextDouble();
		producto.setPrecioUnitario(precio);		
	}
	private static void modificarOrigen(Producto producto, Scanner sc) {
		int opcion=-1;
		System.out.println("Elegir nuevo Origen de Fabricacion: ");
		do {
			try {
				mostrarOrigen();
				opcion=sc.nextInt();
				switch(opcion) {
				case 1:
					producto.setOrigen(origenFabricacion.ARGENTINA);
					break;
				case 2:
					producto.setOrigen(origenFabricacion.CHINA);
					break;
				case 3:
					producto.setOrigen(origenFabricacion.BRASIL);
					break;
				case 4:
					producto.setOrigen(origenFabricacion.URUGUAY);
					break;
				default:
					System.out.println("Opcion ingresada NO valida");
				}	
			}catch(InputMismatchException e) {
				System.out.println("ERROR debe ingresar un numero como opcion");
				 sc.next();
			}
		}while(opcion<1 || opcion>4);
	}
	
	private static void modificarCategoria(Producto producto, Scanner sc) {
		int opcion=-1;
		System.out.println("Elegir nueva Categoria: ");
		do {
			try {
				mostrarCategoria();
				opcion=sc.nextInt();
				switch(opcion) {
				case 1:
					producto.setCategoria(Categoria.TELEFONIA);
					break;
				case 2:
					producto.setCategoria(Categoria.INFORMATICA);
					break;
				case 3:
					producto.setCategoria(Categoria.ELECTROHOGAR);
					break;
				case 4: 
					producto.setCategoria(Categoria.HERRAMIENTAS);
					break;
				default:
					System.out.println("Opcion ingresada NO valida");
				}
			}catch(InputMismatchException e) {
				System.out.println("ERROR debe ingresar un numero como opcion");
				 sc.next();
			}
		}while(opcion<1 || opcion > 4);
	}

}
