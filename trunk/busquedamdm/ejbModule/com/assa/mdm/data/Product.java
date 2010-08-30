package com.assa.mdm.data;

import com.sap.mdm.ids.FieldId;
import com.sap.mdm.session.UserSessionContext;

public enum Product {
	TABLE_NAME("MDM_PRODUCTS"), FIELD_TIPO_MATERIAL("MDM_PRODUCT_TIPO"), FIELD_PADRE("MDM_MAT_PADRE"), 
	FIELD_NUMERO_MATERIAL("MDM_PRODUCT_NUMBER");
	
	private final String sapName;
	
	private FieldId fieldId;

	private Product(String sapName) {
		this.sapName = sapName;
	}
	
	@Override
	public String toString() {
		return sapName;
	}
	
	public void initFieldId(Repository repository, UserSessionContext userCtx) {
		this.fieldId = repository.getFieldId(userCtx, TABLE_NAME.toString(), toString());
	}
	
	public FieldId getFieldId() {
		return fieldId;
	}
}
