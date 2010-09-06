package com.assa.mdm.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import mx.com.mypo.bpd.caf.catalogoproductos.Item;

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
	
	@Test
	public void shouldConvertRecordToItem() throws Exception {
		String numeroMaterial = "123";
		String descLarga = "Azomatlin, 42 mg";
		String precioMinimo = "223";
		String presentacion = "Comercial";
		String empaque = "Empaque";
		String unidad = "Caja";
		
		FieldId numeroMaterialFieldId = mockStringValue(Product.FIELD_NUMERO_MATERIAL, numeroMaterial);
		FieldId descLargaFieldId = mockStringValue(Product.FIELD_DESC_LARGA, descLarga);
		FieldId precioCompraFieldId = mockLookup(Product.FIELD_PREC_COMPRA, precioMinimo);
		FieldId presentacionFieldId = mockLookup(Product.FIELD_PRESENTACION, presentacion);
		FieldId empaqueField = mockLookup(Product.FIELD_EMPAQUE, empaque);
		FieldId unidadField = mockLookup(Product.FIELD_UNIDAD, unidad);
		
		ItemFactory itemFactory = new ItemFactory();
		Item item = itemFactory.toItem(productRecord);

		assertNotNull(item);
		verify(productRecord).getFieldValue(numeroMaterialFieldId);
		verify(productRecord).getFieldValue(descLargaFieldId);
		verify(productRecord).getLookupDisplayValue(precioCompraFieldId);
		verify(productRecord).getLookupDisplayValue(presentacionFieldId);
		verify(productRecord).getLookupDisplayValue(empaqueField);
		verify(productRecord).getLookupDisplayValue(unidadField);
		assertEquals(numeroMaterial, item.getClaveProducto());
		assertEquals(descLarga, item.getDescripcion());
		assertEquals(precioMinimo, item.getPMR());
		assertEquals(presentacion, item.getPresentacion());
		assertEquals(empaque, item.getPresentacionEmpaque());
		assertEquals(unidad, item.getUnidadMedida());
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
