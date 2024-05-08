package ar.edu.unju.fi.ejercicio7.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ar.edu.unju.fi.ejercicio5.model.Producto;
import ar.edu.unju.fi.ejercicio5.model.Producto.Categoria;
import ar.edu.unju.fi.ejercicio5.model.Producto.origenFabricacion;

public class Main {
	private static List <Producto> productos = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int opcion;
		cargar();
		do {
			mostrarMenu();
			opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				mostrarProductos();
				break;
			case 2:
				mostrarProductosFaltantes();
				break;
			case 3:
				incrementarPrecio();
				break;
			case 4:
				mostrarElectrohogarDisponible();
				break;
			case 5:
				ordenarProductosPorPrecioDescendente();
				break;
			case 6:
				mostrarNombresEnMayusculas();
				break;
			case 7:
				System.out.println("Fin del Menu :)");
				break;
			default:
				System.out.println("Opcion Ingresada NO valida");
			}
		}while(opcion!= 7);
		sc.close();
	}
	public static void cargar() {
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
	
	public static void mostrarMenu() {
		System.out.println("************** MENU **************");
		System.out.println("1)Mostrar Productos");
		System.out.println("2)Mostrar los Productos Faltantes");
		System.out.println("3)Incrementar los precios un 20%");
		System.out.println("4)Mostrar los productos que corresponden a la categoría Electrohogar y estén disponibles para la venta.");
		System.out.println("5)Ordenar los productos por precio de forma descendente.");
		System.out.println("6)Mostrar los productos con los nombres en mayúsculas.");
		System.out.println("7)Salir del Menu");
		System.out.println("Elegir opcion: ");
	}
	
	public static void mostrarProductos() {
		Consumer<Producto> printConsumer=producto -> { if(producto.isEstado()) {
			System.out.println(producto);
		}};
		productos.forEach(printConsumer);
	}
	public static void mostrarProductosFaltantes() {
		Predicate<Producto> filterFalse = producto -> !producto.isEstado();
		productos.stream().filter(filterFalse).forEach(System.out::println);;
	}
	public static void incrementarPrecio() {
		List <Producto> productosIncrementados= new ArrayList<>();
		Function<Producto,Producto> functionIncrementar = (producto) -> {
			double resultado = producto.getPrecioUnitario()+ (producto.getPrecioUnitario() * 0.2);
			producto.setPrecioUnitario(resultado);
			return producto;
		};
		productosIncrementados= productos.stream().map(functionIncrementar).collect(Collectors.toList());
		productosIncrementados.forEach(System.out::println);
	}
	public static void mostrarElectrohogarDisponible() {
		Predicate<Producto> filterFalse = producto -> producto.isEstado() && producto.getCategoria().equals(Categoria.ELECTROHOGAR);
		productos.stream().filter(filterFalse).forEach(System.out::println);;
	}
	public static void ordenarProductosPorPrecioDescendente() {
		Comparator<Producto> comparator = Comparator.comparing(Producto::getPrecioUnitario).reversed();
        productos.stream().sorted(comparator).forEach(System.out::println);
    }

    public static void mostrarNombresEnMayusculas() {
        Function<Producto, Producto> functionMayusculas = producto -> {
        	producto.setDescripcion(producto.getDescripcion().toUpperCase());
            return producto;
        };
        productos.stream().map(functionMayusculas).forEach(System.out::println);
    }
	
}
