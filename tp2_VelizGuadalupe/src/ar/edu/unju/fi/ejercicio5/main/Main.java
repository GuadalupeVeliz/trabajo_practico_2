package ar.edu.unju.fi.ejercicio5.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import ar.edu.unju.fi.ejercicio5.model.PagoEfectivo;
import ar.edu.unju.fi.ejercicio5.model.PagoTarjeta;
import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio5.model.Producto.origenFabricacion;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ArrayList <Producto> productos = new ArrayList<>();
		int opcion=1;
		cargar(productos);
		do {
			try {
			menu();
			opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				mostrarProductos(productos);
				break;
			case 2:
				realizarCompra(productos,sc);
				break;
			case 3:
				System.out.println("Fin del Menu :)");
				break;
			default:
				System.out.println("Opcion ingresada NO valida");
			}
			}catch(InputMismatchException e) {
				System.out.println("ERROR debe ingresar un numero como opcion");
				 sc.next();
			}
		}while(opcion!=3);
		sc.close();
	}

	public static void cargar(ArrayList<Producto> productos) {
		Producto producto1=new Producto("001","Smartphone", 500.0 , origenFabricacion.ARGENTINA, Categoria.TELEFONIA,true);
		Producto producto2= new Producto("002","Laptop", 800.0 , origenFabricacion.CHINA , Categoria.INFORMATICA,false);
		Producto producto3= new Producto("003","Licuadora", 50.0 , origenFabricacion.BRASIL , Categoria.ELECTROHOGAR,true);
		Producto producto4= new Producto("004","Taladro", 70.0, origenFabricacion.URUGUAY, Categoria.HERRAMIENTAS,true);
		Producto producto5= new Producto("005","Smart TV", 700.0 ,origenFabricacion.CHINA,Categoria.ELECTROHOGAR,true);
		Producto producto6= new Producto("006","Martillo", 15.0, origenFabricacion.ARGENTINA,Categoria.HERRAMIENTAS,false);
		Producto producto7= new Producto("007","Monitor", 200.0 ,origenFabricacion.URUGUAY,Categoria.INFORMATICA,true);
		Producto producto8= new Producto("008","Aspiradora", 120.0, origenFabricacion.BRASIL,Categoria.ELECTROHOGAR,false);
		Producto producto9= new Producto("009","Cámara de seguridad", 80.0, origenFabricacion.CHINA,Categoria.ELECTROHOGAR,true);
		Producto producto10= new Producto("010","Router WiFi", 50.0, origenFabricacion.ARGENTINA, Categoria.INFORMATICA,true);
		Producto producto11= new Producto("011","Taladro inalámbrico", 90.0 , origenFabricacion.URUGUAY,Categoria.HERRAMIENTAS,false );
		Producto producto12= new Producto("012","Auriculares Bluetooth", 30.0, origenFabricacion.BRASIL, Categoria.TELEFONIA,true );
		Producto producto13= new Producto("013","Tablet", 150.0, origenFabricacion.CHINA, Categoria.INFORMATICA,false);
		Producto producto14= new Producto("014","Cafetera eléctrica", 40.0 , origenFabricacion.ARGENTINA , Categoria.ELECTROHOGAR,true);
		Producto producto15= new Producto("015","Destornillador", 10.0 , origenFabricacion.URUGUAY, Categoria.HERRAMIENTAS,true);
		productos.add(producto1);
		productos.add(producto2);
		productos.add(producto3);
		productos.add(producto4);
		productos.add(producto5);
		productos.add(producto6);
		productos.add(producto7);
		productos.add(producto8);
		productos.add(producto9);
		productos.add(producto10);
		productos.add(producto11);
		productos.add(producto12);
		productos.add(producto13);
		productos.add(producto14);
		productos.add(producto15);
		
	}

	public static void menu() {
		System.out.println("******* Menu *******");
		System.out.println("1)Mostrar Productos");
		System.out.println("2)Realizar Compra");
		System.out.println("3)Salir");
		System.out.println("Ingrese una Opcion: ");
	}
	public static void mostrarProductos(ArrayList<Producto> productos) {
		for(Producto producto: productos) {
			System.out.println("Codigo: "+producto.getCodigo());
			System.out.println("Descripcion: "+producto.getDescripcion());
			System.out.println("Precio Unitario: "+producto.getPrecioUnitario());
			System.out.println("Origen de Fabricacion: "+producto.getOrigen());
			System.out.println("Categoria: "+producto.getCategoria());
			if(producto.isEstado())
				System.out.println("Stock Disponible");
			else
				System.out.println("Sin stock Disponible");
			System.out.println("***************************");
		}
	}
	public static void realizarCompra(ArrayList<Producto> productos,Scanner sc) {
		ArrayList<Producto> productosElegidos=new ArrayList<>();
		double total;
		int opcion;
		productosElegidos=elegirProductos(productos,sc);
		if(productosElegidos.isEmpty())
			System.out.println("No ingreso ningun Producto");
		else {
			total=calcularTotal(productosElegidos);
			opcion=elegirMetodoPago(sc);
			if(opcion==1) {
				pagarEfectivo(productosElegidos,total);
			}
			else {
				pagarTarjeta(productosElegidos,sc,total);
			}
		}
	}
	
	public static ArrayList<Producto> elegirProductos(ArrayList<Producto> productos, Scanner sc) {
		ArrayList<Producto> productosElegidos=new ArrayList<>();
		String codigo;
		int opcion;
		boolean bandera;
		mostrarProductos(productos);
		do {
			bandera=false;
			System.out.println("Ingrese codigo del Producto a comprar");
			codigo=sc.next();
			for(Producto producto: productos) {
				if(producto.getCodigo().equals(codigo)) {
					bandera=true;
					if(producto.isEstado()){
						productosElegidos.add(producto);
					}
					else
						System.out.println("No hay stock disponible del Producto Ingresado ");
				}
			}
			if(bandera==false) {
				System.out.println("El codigo ingresado NO pertenece a ningun Producto Registrado");
			}
			System.out.println("Desea ingresar otro producto: 1) SI 2)NO");
			opcion=sc.nextInt();
			sc.nextLine();
		}while(opcion!= 2);
		return productosElegidos;
	}
	public static int elegirMetodoPago(Scanner sc) {
		int opcion;
		do {
			System.out.println("Ingrese forma de Pago: ");
			System.out.println("1)Pago en Efectivo");
			System.out.println("2)Pago con Tarjeta");
			opcion=sc.nextInt();
		}while(opcion<1 || opcion>2);
		return opcion;		
	}
	public static double calcularTotal(ArrayList <Producto> productosElegidos) {
		double total=0;
		for(Producto producto: productosElegidos) {
			total=producto.getPrecioUnitario()+total;
		}
		return total;
	}
	public static void pagarEfectivo(ArrayList <Producto> productosElegidos,double total) {
		PagoEfectivo pagoEfectivo= new PagoEfectivo(LocalDate.now());
		pagoEfectivo.realizarPago(total);
		pagoEfectivo.imprimirRecibo();
	}
	public static void pagarTarjeta(ArrayList <Producto> productosElegidos,Scanner sc,double total) {
		String tarjeta;
		System.out.println("Ingrese numero de Tarjeta:");
		tarjeta=sc.next();
		PagoTarjeta pagoTarjeta= new PagoTarjeta(tarjeta,LocalDate.now());
		pagoTarjeta.realizarPago(total);
		pagoTarjeta.imprimirRecibo();
	}
}
