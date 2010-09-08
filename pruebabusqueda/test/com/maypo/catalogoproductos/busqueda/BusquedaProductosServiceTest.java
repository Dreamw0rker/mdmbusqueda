package com.maypo.catalogoproductos.busqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosQuery;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosServiceImplBean;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assa.mdm.data.Product;
import com.assa.mdm.ejb.BuscadorLocal;
import com.assa.test.BaseMockitoTest;
import com.sap.mdm.MdmException;


@SuppressWarnings("unchecked")
public class BusquedaProductosServiceTest extends BaseMockitoTest {
	@InjectMocks
	private BusquedaProductosServiceImplBean busquedaService = new BusquedaProductosServiceImplBean();
	@Mock
	private BuscadorLocal buscador;
	private BusquedaProductosQuery busquedaProductosRequest = new BusquedaProductosQuery();
	private String descripcion = "ARZ";
	
	@Test
	public void shouldConfigureBusquedaWithDescripcion() throws Exception {
		busquedaProductosRequest.setDescripcion(descripcion);
		busquedaProductosRequest.setPartida("12");
		Map<Product, String> parametrosBusqueda = executeTest();
		verifyParameter(parametrosBusqueda, Product.FIELD_DESC, descripcion);
	}

	private void verifyParameter(Map<Product, String> parametrosBusqueda, Product key, String value) {
		assertEquals(1, parametrosBusqueda.size());
		assertTrue(parametrosBusqueda.containsKey(key));
		assertTrue(parametrosBusqueda.containsValue(value));
	}

	private Map<Product, String> executeTest() throws BusquedaProductosFault_Exception, MdmException {
		busquedaService.buscarProductos(busquedaProductosRequest);
		ArgumentCaptor<Map> busquedaMap = ArgumentCaptor.forClass(Map.class);
		verify(buscador).findProducts(busquedaMap.capture(), busquedaProductosRequest.getPartida());
		Map<Product, String> parametrosBusqueda = busquedaMap.getValue();
		return parametrosBusqueda;
	}
	
	@Test
	public void shouldCallBusquedaWithClave() throws Exception {
		String clave = "345";
		busquedaProductosRequest.setClave(clave);
		Map<Product, String> parametrosBusqueda = executeTest();
		verifyParameter(parametrosBusqueda, Product.FIELD_NUMERO_MATERIAL, clave);
	}
	
	@Test
	public void shouldCallBusquedaWithEmpaque() throws Exception {
		String empaque = "Caja";
		busquedaProductosRequest.setEmpaque(empaque);
		Map<Product, String> parametrosBusqueda = executeTest();
		verifyParameter(parametrosBusqueda, Product.FIELD_EMPAQUE, empaque);
	}
	
	@Test
	public void shouldCallBusquedaWithThreeParameters() throws Exception {
		busquedaProductosRequest.setDescripcion(descripcion);
		busquedaProductosRequest.setClave("345");
		busquedaProductosRequest.setEmpaque("Caja");
		
		Map<Product, String> parametrosBusqueda = executeTest();
		assertEquals(3, parametrosBusqueda.size());
		assertTrue(parametrosBusqueda.containsKey(Product.FIELD_NUMERO_MATERIAL));
	}
	
	@Test(expected=BusquedaProductosFault_Exception.class)
	public void shouldThrowBusquedaFaultExcecion() throws Exception {
		busquedaProductosRequest.setDescripcion(descripcion);
		when(buscador.findProducts(isA(Map.class), anyString())).thenThrow(new MdmException());
		
		busquedaService.buscarProductos(busquedaProductosRequest);
	}
}
