package com.maypo.catalogoproductos.busqueda;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaAutomaticaCriteria;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaAutomaticaQuery;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosFault_Exception;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosQuery;
import mx.com.mypo.bpd.caf.catalogoproductos.BusquedaProductosServiceImplBean;
import mx.com.mypo.bpd.caf.catalogoproductos.DatosEntrada;
import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assa.mdm.data.ItemFactory;
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
	@Mock
	private ItemFactory itemFactory;
	private BusquedaProductosQuery busquedaProductosRequest = new BusquedaProductosQuery();
	private String descripcion = "ARZ";
	
	@Test
	public void shouldConfigureBusquedaWithDescripcion() throws Exception {
		busquedaProductosRequest.setDescripcion(descripcion);
		busquedaProductosRequest.setPartida("12");
		SubItem subItem = subItemsFound();
		
		Map<Product, String> parametrosBusqueda = executeTest();
		
		verifyParameter(parametrosBusqueda, Product.FIELD_DESC, descripcion);
		verify(itemFactory).addDatosEntrada(eq(subItem), isA(DatosEntrada.class));
	}

	private SubItem subItemsFound() throws MdmException {
		SubItem subItem = mock(SubItem.class);
		List<SubItem> subItems = Arrays.asList(new SubItem[] {subItem});
		when(buscador.findProducts(anyMap(), anyString())).thenReturn(subItems);
		return subItem;
	}

	private void verifyParameter(Map<Product, String> parametrosBusqueda, Product key, String value) {
		assertEquals(1, parametrosBusqueda.size());
		assertTrue(parametrosBusqueda.containsKey(key));
		assertTrue(parametrosBusqueda.containsValue(value));
	}

	private Map<Product, String> executeTest() throws BusquedaProductosFault_Exception, MdmException {
		busquedaService.buscarProductos(busquedaProductosRequest);
		ArgumentCaptor<Map> busquedaMap = ArgumentCaptor.forClass(Map.class);
		verify(buscador).findProducts(busquedaMap.capture(), eq(busquedaProductosRequest.getPartida()));
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
	public void shouldThrowBusquedaFaultExcepcion() throws Exception {
		busquedaProductosRequest.setDescripcion(descripcion);
		when(buscador.findProducts(isA(Map.class), anyString())).thenThrow(new MdmException());
		
		busquedaService.buscarProductos(busquedaProductosRequest);
	}
	
	@Test
	public void shouldSearchAutomatico() throws Exception {
		BusquedaAutomaticaQuery busquedaAutomaticaRequest = new BusquedaAutomaticaQuery();
		BusquedaAutomaticaCriteria criteria = new BusquedaAutomaticaCriteria();
		busquedaAutomaticaRequest.getCriterias().add(criteria );
		SubItem subItem = subItemsFound();
		BigInteger cantidad = new BigInteger("22");
		criteria.setCantidad(cantidad);
		String partida = "1";
		criteria.setPartida(partida);
		
		busquedaService.buscarProductosAuto(busquedaAutomaticaRequest);
		
		verify(buscador).findProducts(anyMap(), anyString());
		ArgumentCaptor<DatosEntrada> datosEntradaArgument = ArgumentCaptor.forClass(DatosEntrada.class);
		verify(itemFactory).addDatosEntrada(eq(subItem), datosEntradaArgument.capture());
		DatosEntrada datoEntrada = datosEntradaArgument.getValue();
		assertEquals(cantidad, datoEntrada.cantidad);
		assertEquals(partida, datoEntrada.partida);
		
	}
}
