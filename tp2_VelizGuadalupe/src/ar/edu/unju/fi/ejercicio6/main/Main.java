package ar.edu.unju.fi.ejercicio6.main;

import ar.edu.unju.fi.ejercicio6.interfaces.funcionales.Converter;
import ar.edu.unju.fi.ejercicio6.model.FelinoDomestico;
import ar.edu.unju.fi.ejercicio6.model.FelinoSalvaje;

public class Main {

	public static void main(String[] args) {
		FelinoSalvaje tanner= new FelinoSalvaje("Tanner",(byte)20,186f);
		if (Converter.isNotNull(tanner)) {
            Converter<FelinoSalvaje,FelinoDomestico> converter = x -> new FelinoDomestico(x.getNombre(),x.getEdad(), x.getPeso());
    		FelinoDomestico felino1=converter.convert(tanner);
    		converter.mostrarObjeto(felino1);
        }else {
            System.out.println("El objeto a convertir ES nulo.");
        }
		
	}
}
