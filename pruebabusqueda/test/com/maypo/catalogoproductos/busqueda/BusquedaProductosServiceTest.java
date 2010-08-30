package com.maypo.catalogoproductos.busqueda;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosQuery;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosServiceImplBean;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assa.mdm.ejb.BuscadorLocal;
import com.assa.test.BaseMockitoTest;
import com.sap.mdm.MdmException;


public class BusquedaProductosServiceTest extends BaseMockitoTest {
	@InjectMocks
	private BusquedaProductosServiceImplBean busquedaService = new BusquedaProductosServiceImplBean();
	@Mock
	private BuscadorLocal buscador;
	
	@Test(expected=BusquedaProductosFault_Exception.class)
	public void shouldThrowBusquedaFaultExcecion() throws Exception {
		BusquedaProductosQuery busquedaProductosRequest = new BusquedaProductosQuery();
		String descripcion = "ARZ";
		busquedaProductosRequest.setDescripcion(descripcion);
		when(buscador.findProducts(descripcion)).thenThrow(new MdmException());
		
		busquedaService.buscarProductos(busquedaProductosRequest);
	}
}
