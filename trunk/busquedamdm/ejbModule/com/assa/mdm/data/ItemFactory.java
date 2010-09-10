package com.assa.mdm.data;

import mx.com.mypo.bpd.caf.catalogoproductos.DatosEntrada;
import mx.com.mypo.bpd.caf.catalogoproductos.Item;
import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import com.sap.mdm.data.Record;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.valuetypes.StringValue;

public class ItemFactory {

	public Item toItemPadre(Record product) {
		Item item = toItemHijo(product);
		item.setPresentacionEmpaque(product.getLookupDisplayValue(Product.FIELD_EMPAQUE.getFieldId()));
		return item;		
	}

	public Item toItemHijo(Record product) {
		Item item = new Item();
		FieldId fieldId = Product.FIELD_NUMERO_MATERIAL.getFieldId();
		StringValue stringValue = (StringValue) product.getFieldValue(fieldId);
		item.setClaveProducto(stringValue.getString());
		stringValue = (StringValue) product.getFieldValue(Product.FIELD_DESC.getFieldId());
		item.setDescripcion(stringValue.getString());
		item.setPMR(product.getLookupDisplayValue(Product.FIELD_PREC_COMPRA.getFieldId()));
		item.setPresentacion(product.getLookupDisplayValue(Product.FIELD_PRESENTACION.getFieldId()));
		item.setUnidadMedida(product.getLookupDisplayValue(Product.FIELD_UNIDAD.getFieldId()));
		stringValue = (StringValue) product.getFieldValue(Product.FIELD_PROVEDOR.getFieldId());
		item.setProvedor(stringValue.getString());
		item.setCategoria(product.getLookupDisplayValue(Product.FIELD_CATEGORIA.getFieldId()));
		return item;
	}

	public void addDatosEntrada(SubItem subItem, DatosEntrada datosEntrada) {
		copyDatosEntradaAItem(datosEntrada, subItem.getItemPadre());
		for (Item hijo : subItem.getSubItems()) {
			copyDatosEntradaAItem(datosEntrada, hijo);
		}
	}

	private void copyDatosEntradaAItem(DatosEntrada datosEntrada, Item item) {
		item.setCantidad(datosEntrada.cantidad);
		item.setPartida(datosEntrada.partida);
		item.setUnidadMedida(datosEntrada.unidadMedida);
	}

}
