/**
 * 
 */
package mx.com.mypo.bpd.caf.catalogoproductos;

import java.math.BigInteger;

public class DatosEntrada {
	public String partida;
	public String unidadMedida;
	public BigInteger cantidad;
	
	public DatosEntrada(String partida, String unidadMedida, BigInteger cantidad) {
		super();
		this.partida = partida;
		this.unidadMedida = unidadMedida;
		this.cantidad = cantidad;
	}
	
}