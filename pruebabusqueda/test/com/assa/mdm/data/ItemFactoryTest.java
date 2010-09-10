package com.assa.mdm.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigInteger;
import java.util.List;

import mx.com.mypo.bpd.caf.catalogoproductos.DatosEntrada;
import mx.com.mypo.bpd.caf.catalogoproductos.Item;
import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

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
	
	private String numeroMaterial = "123";
	private String descLarga = "Azomatlin, 42 mg";
	private String precioMinimo = "223";
	private String presentacion = "Comercial";
	private String empaque = "Empaque";
	private String unidad = "Caja";
	private String provedor = "1043";
	private String categoria = "cat";
	
	@Test
	public void shouldConvertRecordToItem() throws Exception {		
		Item item = getItemPadre();

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

	private Item getItemPadre() {
		return itemFactory.toItemPadre(productRecord);
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
		getItemHijo();
		
		verify(productRecord, times(3)).getFieldValue(isA(FieldId.class));
		verify(productRecord, times(4)).getLookupDisplayValue(isA(FieldId.class));
	}

	private Item getItemHijo() {
		return itemFactory.toItemHijo(productRecord);
	}
	
	@Test
	public void shouldAddDatosEntrada() throws Exception {
		SubItem subItem = new SubItem();
		Item itemPadre = getItemPadre();
		subItem.setItemPadre(itemPadre);
		BigInteger cantidad = new BigInteger("42");
		String partida = "1";
		String unidadMedida = "Pildora";
		Item hijo1 = getItemHijo();
		List<Item> hijos = subItem.getSubItems();
		hijos.add(hijo1);
		DatosEntrada datosEntrada = new DatosEntrada(partida, unidadMedida, cantidad);
		
		itemFactory.addDatosEntrada(subItem, datosEntrada);
		
		assertFalse(hijos.isEmpty());
		verifyItem(itemPadre, cantidad, partida, unidadMedida);
		verifyItem(hijo1, cantidad, partida, unidadMedida);
		
	}

	private void verifyItem(Item itemPadre, BigInteger cantidad, String partida, String unidadMedida) {
		assertEquals(cantidad, itemPadre.getCantidad());
		assertEquals(partida, itemPadre.getPartida());
		assertEquals(unidadMedida, itemPadre.getUnidadMedida());
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
