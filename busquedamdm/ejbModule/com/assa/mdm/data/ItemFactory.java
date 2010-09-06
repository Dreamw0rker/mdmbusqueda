package com.assa.mdm.data;

import mx.com.mypo.bpd.caf.catalogoproductos.Item;

import com.sap.mdm.data.Record;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.valuetypes.StringValue;

public class ItemFactory {

	public Item toItem(Record product) {
		Item item = new Item();
		FieldId fieldId = Product.FIELD_NUMERO_MATERIAL.getFieldId();
		StringValue stringValue = (StringValue) product.getFieldValue(fieldId);
		item.setClaveProducto(stringValue.getString());
		return item;		
	}

}
