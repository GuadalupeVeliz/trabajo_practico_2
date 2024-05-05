package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

public class PagoTarjeta implements Pago {
	private String numeroTarjeta;
	private LocalDate fechaPago;
	private Double montoPagado;
	
	public PagoTarjeta() {
	}

	public PagoTarjeta(String numeroTarjeta, LocalDate fechaPago) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.fechaPago = fechaPago;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	@Override
	public void realizarPago(double monto) {
		montoPagado=monto*1.15;
	}

	@Override
	public void imprimirRecibo() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yy");
		String fechaFormateada=fechaPago.format(formatoFecha);
		System.out.println("Recibo: ");
		System.out.println("Numero de Tarjeta: "+numeroTarjeta);
		System.out.println("Fecha de pago: "+fechaFormateada);
		System.out.println("Monto Pagado: "+montoPagado);
	}

}
