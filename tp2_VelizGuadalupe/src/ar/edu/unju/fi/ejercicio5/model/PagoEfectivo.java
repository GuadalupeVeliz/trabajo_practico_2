package ar.edu.unju.fi.ejercicio5.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ar.edu.unju.fi.ejercicio5.interfaces.Pago;

public class PagoEfectivo implements Pago {
	private LocalDate fechaPago;
	private Double montoPagado;
	
	public PagoEfectivo() {
	}

	public PagoEfectivo(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
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
		double descuento=monto*0.10;
		montoPagado=monto-descuento;
	}

	@Override
	public void imprimirRecibo() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yy");
		String fechaFormateada=fechaPago.format(formatoFecha);
		System.out.println("Recibo: ");
		System.out.println("Fecha de Pago: "+fechaFormateada);
		System.out.println("Monto: "+montoPagado);
	}

}
