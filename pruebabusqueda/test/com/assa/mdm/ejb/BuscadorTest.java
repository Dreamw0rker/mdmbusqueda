package com.assa.mdm.ejb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.com.mypo.bpd.caf.catalogoproductos.Item;
import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assa.mdm.command.CommandFactory;
import com.assa.mdm.connection.MDMConnection;
import com.assa.mdm.data.Atributo;
import com.assa.mdm.data.ItemFactory;
import com.assa.mdm.data.Product;
import com.assa.mdm.data.Repository;
import com.assa.test.BaseMockitoTest;
import com.sap.mdm.MdmException;
import com.sap.mdm.data.Record;
import com.sap.mdm.data.RecordResultSet;
import com.sap.mdm.data.commands.RetrieveLimitedRecordsCommand;
import com.sap.mdm.extension.data.ResultDefinitionEx;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.search.AttributeSearchDimension;
import com.sap.mdm.search.Search;
import com.sap.mdm.search.SearchConstraint;
import com.sap.mdm.search.SearchDimension;
import com.sap.mdm.session.UserSessionContext;
import com.sap.mdm.valuetypes.StringValue;

public class BuscadorTest extends BaseMockitoTest {
	@InjectMocks
	private Buscador buscador = new Buscador();
	@Mock
	private CommandFactory mockCommandFactory;
	@Mock
	private MDMConnection mockMdmConnection;
	@SuppressWarnings("unused")
	@Mock
	private Repository repository;
	@Mock
	private ItemFactory itemFactory;
	@Mock
	private RetrieveLimitedRecordsCommand command;
	@Mock
	private Search search;
	@Mock
	private UserSessionContext userCtx;
	@Mock
	private ResultDefinitionEx resultDefinition;
	@Mock
	private RecordResultSet recordSet;
	@Mock
	private Record record;
	@Mock
	private Item item;
	
	@Before
	public void configureMocks() throws MdmException {
		when(mockMdmConnection.getUserContext()).thenReturn(userCtx );
		when(mockCommandFactory.getLimitedRecordsCommand(userCtx, Product.TABLE_NAME.toString())).thenReturn(command );
		when(command.getSearch()).thenReturn(search);
		when(command.getResultDefinition()).thenReturn(resultDefinition);
		when(command.getRecords()).thenReturn(recordSet);
		Record[] records = new Record[] {record };
		when(recordSet.getRecords()).thenReturn(records );
		StringValue stringValue = mock(StringValue.class);
		when(record.getFieldValue(any(FieldId.class))).thenReturn(stringValue);
		when(itemFactory.toItemPadre(record)).thenReturn(item);
	}
	
	@Test
	public void shouldGetCommands() throws Exception {
		Map<Product, String> parametrosBusqueda = new HashMap<Product, String>();
		parametrosBusqueda.put(Product.FIELD_DESC, "AR");
		
		List<SubItem> products = buscador.findProducts(parametrosBusqueda, new HashMap<Atributo, String>());
		assertEquals(1, products.size());
		
		verify(mockMdmConnection).getUserContext();
		verify(mockCommandFactory).getLimitedRecordsCommand(eq(userCtx), anyString());
		verify(itemFactory, times(2)).toItemPadre(record);
		//two for padre, one for hijo
		verify(search, times(3)).addSearchItem(isA(SearchDimension.class), isA(SearchConstraint.class));
	}
	
	@Test
	public void shouldAddAllSearchParameters() throws Exception {
		Map<Product, String> parametrosBusqueda = new HashMap<Product, String>();
		parametrosBusqueda.put(Product.FIELD_DESC, "AR");
		parametrosBusqueda.put(Product.FIELD_EMPAQUE, "Caja");
		parametrosBusqueda.put(Product.FIELD_NUMERO_MATERIAL, "234");
		
		buscador.findProducts(parametrosBusqueda, new HashMap<Atributo, String>());
		//3 parameters, and 2 for being a father plus children
		verify(search, times(5)).addSearchItem(isA(SearchDimension.class), isA(SearchConstraint.class));
	}
	
	@Test
	public void shouldAddTwoSearchParameters() throws Exception {
		Map<Product, String> parametrosBusqueda = new HashMap<Product, String>();
		parametrosBusqueda.put(Product.FIELD_EMPAQUE, "Caja");
		parametrosBusqueda.put(Product.FIELD_NUMERO_MATERIAL, "234");
		
		buscador.findProducts(parametrosBusqueda, new HashMap<Atributo, String>());
		//2 parameters, and 2 for being a father plus children
		verify(search, times(4)).addSearchItem(isA(SearchDimension.class), isA(SearchConstraint.class));
	}
	
	@Test
	public void shouldSearchForAttributes() throws Exception {
		Map<Atributo, String> atributos = new HashMap<Atributo, String>();
		Atributo attribute = Atributo.SUBSTANCIA_ACTIVA;
		atributos.put(attribute, "Zoxil");
		
		buscador.findProducts(new HashMap<Product, String>(), atributos);
		ArgumentCaptor<SearchDimension> searchDimensionArg = ArgumentCaptor.forClass(SearchDimension.class);
		verify(search, times(3)).addSearchItem(searchDimensionArg.capture(), isA(SearchConstraint.class));
		
		SearchDimension searchDimension = searchDimensionArg.getAllValues().get(0);
		assertTrue(searchDimension instanceof AttributeSearchDimension);
		AttributeSearchDimension attributeSearchDimension = (AttributeSearchDimension) searchDimension;
		assertEquals(attribute.getAttributeId(), attributeSearchDimension.getAttributeId());
		FieldId[] fieldPath = attributeSearchDimension.getFieldPath();
		assertEquals(1, fieldPath.length);
		assertEquals(Product.FIELD_TAX_GRUPO_ART.getFieldId(), fieldPath[0]);
	}
	
	@Test
	public void shouldAddTwoSearchAttributes() throws Exception {
		Map<Atributo, String> atributos = new HashMap<Atributo, String>();
		atributos.put(Atributo.SUBSTANCIA_ACTIVA, "Zoxil");
		atributos.put(Atributo.CONCENTRACION, "50 mg");
		
		buscador.findProducts(new HashMap<Product, String>(), atributos);
		
		verify(search, times(4)).addSearchItem(isA(SearchDimension.class), isA(SearchConstraint.class));
	}
	
	
}
