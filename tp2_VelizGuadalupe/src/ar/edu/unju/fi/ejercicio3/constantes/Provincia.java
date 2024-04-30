package ar.edu.unju.fi.ejercicio3.constantes;

public enum Provincia {
	JUJUY(811611, 53219),
	SALTA(1441351, 155488),
	TUCUMAN(1687305, 22524),
	CATAMARCA(429562, 102602),
	LA_RIOJA(383865, 89680),
	SANTIAGO_DEL_ESTERO(1060906, 136351);
	
	private int poblacion;
	private int superficie;
	
	private Provincia(int poblacion, int superficie) {
		this.poblacion = poblacion;
		this.superficie = superficie;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	
	public float calcularDensidadPoblacional() {
		return poblacion / superficie;
	}

}
