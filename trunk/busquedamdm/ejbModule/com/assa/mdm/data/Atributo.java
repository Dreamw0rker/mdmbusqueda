package com.assa.mdm.data;

import com.sap.mdm.ids.AttributeId;
import com.sap.mdm.session.UserSessionContext;

public enum Atributo {
	SUBSTANCIA_ACTIVA("Sustancia Activa de Antivirales"), CONCENTRACION("Concentracio\u0301n de Antivirales");
	
	private final String attributeCode;
	private AttributeId attributeId;

	private Atributo(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	
	@Override
	public String toString() {
		return attributeCode;
	}
	
	public AttributeId initAttributeId(Repository repository, UserSessionContext userCtx) {
		return attributeId = repository.getAttributeId(userCtx, attributeCode);	
	}

	public AttributeId getAttributeId() {
		return attributeId;
	}
}
