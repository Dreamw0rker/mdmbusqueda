package com.assa.mdm.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import mx.com.mypo.bpd.caf.catalogoproductos.Item;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.assa.test.BaseMockitoTest;
import com.sap.mdm.data.Record;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.session.UserSessionContext;
import com.sap.mdm.valuetypes.StringValue;

public class ItemFactoryTest extends BaseMockitoTest {
	@Mock
	private Record productRecord;
	@Mock
	private UserSessionContext userCtx;
	@Mock
	private Repository repository;
	ItemFactory itemFactory = new ItemFactory();
	private FieldId numeroMaterialFieldId;
	private FieldId descLargaFieldId;
	private FieldId precioCompraFieldId;
	private FieldId presentacionFieldId;
	private FieldId empaqueField;
	private FieldId unidadField;
	private FieldId provedorField;
	private FieldId categoriaField;
	
	String numeroMaterial = "123";
	String descLarga = "Azomatlin, 42 mg";
	String precioMinimo = "223";
	String presentacion = "Comercial";
	String empaque = "Empaque";
	String unidad = "Caja";
	String provedor = "1043";
	String categoria = "cat";
	
	@Test
	public void shouldConvertRecordToItem() throws Exception {
		initFields();
		
		Item item = itemFactory.toItemPadre(productRecord);

		assertNotNull(item);
		verify(productRecord).getFieldValue(numeroMaterialFieldId);
		verify(productRecord).getFieldValue(descLargaFieldId);
		verify(productRecord).getLookupDisplayValue(precioCompraFieldId);
		verify(productRecord).getLookupDisplayValue(presentacionFieldId);
		verify(productRecord).getLookupDisplayValue(empaqueField);
		verify(productRecord).getLookupDisplayValue(unidadField);
		verify(productRecord).getFieldValue(provedorField);
		verify(productRecord).getLookupDisplayValue(categoriaField);
		assertEquals(numeroMaterial, item.getClaveProducto());
		assertEquals(descLarga, item.getDescripcion());
		assertEquals(precioMinimo, item.getPMR());
		assertEquals(presentacion, item.getPresentacion());
		assertEquals(empaque, item.getPresentacionEmpaque());
		assertEquals(unidad, item.getUnidadMedida());
		assertEquals(provedor, item.getProvedor());
		assertEquals(categoria, item.getCategoria());
	}

	@Before
	public void initFields() {
		numeroMaterialFieldId = mockStringValue(Product.FIELD_NUMERO_MATERIAL, numeroMaterial);
		descLargaFieldId = mockStringValue(Product.FIELD_DESC, descLarga);
		precioCompraFieldId = mockLookup(Product.FIELD_PREC_COMPRA, precioMinimo);
		presentacionFieldId = mockLookup(Product.FIELD_PRESENTACION, presentacion);
		empaqueField = mockLookup(Product.FIELD_EMPAQUE, empaque);
		unidadField = mockLookup(Product.FIELD_UNIDAD, unidad);
		provedorField = mockStringValue(Product.FIELD_PROVEDOR, provedor);
		categoriaField = mockLookup(Product.FIELD_CATEGORIA, categoria);
	}
	
	@Test
	//should not get empaque, so one lookup less
	public void shouldConvertItemHijo() throws Exception {
		itemFactory.toItemHijo(productRecord);
		
		verify(productRecord, times(3)).getFieldValue(isA(FieldId.class));
		verify(productRecord, times(4)).getLookupDisplayValue(isA(FieldId.class));
	}

	private FieldId mockStringValue(Product product, String value) {
		FieldId numeroMaterialFieldId = getFieldId(product);
		when(productRecord.getFieldValue(numeroMaterialFieldId)).thenReturn(new StringValue(value));
		return numeroMaterialFieldId;
	}

	private FieldId getFieldId(Product product) {
		FieldId numeroMaterialFieldId = mock(FieldId.class);
		when(repository.getFieldId(userCtx, Product.TABLE_NAME.toString(), product.toString())).
			thenReturn(numeroMaterialFieldId);
		product.initFieldId(repository, userCtx);
		return numeroMaterialFieldId;
	}
	
	private FieldId mockLookup(Product product, String value) {
		FieldId fieldId = getFieldId(product);
		when(productRecord.getLookupDisplayValue(fieldId)).thenReturn(value);
		return fieldId;
	}
}
