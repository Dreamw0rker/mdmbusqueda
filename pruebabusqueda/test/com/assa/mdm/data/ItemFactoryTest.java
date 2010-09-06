package com.assa.mdm.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import mx.com.mypo.bpd.caf.catalogoproductos.Item;

import org.junit.Test;

import com.sap.mdm.data.Record;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.session.UserSessionContext;
import com.sap.mdm.valuetypes.StringValue;

public class ItemFactoryTest {
	@Test
	public void shouldConvertRecordToItem() throws Exception {
		Record product = mock(Record.class);
		UserSessionContext userCtx = mock(UserSessionContext.class);
		Repository repository = mock(Repository.class);
		String numeroMaterial = "123";
		FieldId numeroMaterialFieldId = mockFieldId(product, userCtx, repository, Product.FIELD_NUMERO_MATERIAL, numeroMaterial);

		ItemFactory itemFactory = new ItemFactory();
		Item item = itemFactory.toItem(product);

		assertNotNull(item);
		verify(product).getFieldValue(numeroMaterialFieldId);
		assertEquals(numeroMaterial, item.getClaveProducto());
	}

	private FieldId mockFieldId(Record productRecord, UserSessionContext userCtx, Repository repository, Product product, String value) {
		FieldId numeroMaterialFieldId = mock(FieldId.class);
		when(repository.getFieldId(userCtx, Product.TABLE_NAME.toString(), product.toString())).
			thenReturn(numeroMaterialFieldId);
		StringValue stringValue = new StringValue(value);
		when(productRecord.getFieldValue(numeroMaterialFieldId)).thenReturn(stringValue);
		product.initFieldId(repository, userCtx);
		return numeroMaterialFieldId;
	}
}
