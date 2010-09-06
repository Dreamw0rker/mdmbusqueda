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
		stringValue = (StringValue) product.getFieldValue(Product.FIELD_DESC_LARGA.getFieldId());
		item.setDescripcion(stringValue.getString());
		item.setPMR(product.getLookupDisplayValue(Product.FIELD_PREC_COMPRA.getFieldId()));
		item.setPresentacion(product.getLookupDisplayValue(Product.FIELD_PRESENTACION.getFieldId()));
		item.setPresentacionEmpaque(product.getLookupDisplayValue(Product.FIELD_EMPAQUE.getFieldId()));
		item.setUnidadMedida(product.getLookupDisplayValue(Product.FIELD_UNIDAD.getFieldId()));
		return item;		
	}

}
