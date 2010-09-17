package com.assa.mdm.data;

import com.sap.mdm.extension.MetadataManager;
import com.sap.mdm.extension.schema.AttributeSchema;
import com.sap.mdm.extension.schema.RepositorySchemaEx;
import com.sap.mdm.ids.AttributeId;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.ids.TableId;
import com.sap.mdm.schema.AttributeProperties;
import com.sap.mdm.session.UserSessionContext;

public class Repository {

	private RepositorySchemaEx repository;
	private MetadataManager metadataMan = MetadataManager.getInstance();
	private AttributeSchema attributeSchema;
	private String taxonomyTable;

	public TableId getTableId(UserSessionContext userCtx, String table) {
		return getRepository(userCtx).getTableId(table);
	}
	
	private RepositorySchemaEx getRepository(UserSessionContext userCtx) {
		if (repository == null) {
			repository = metadataMan.getRepositorySchema(userCtx);
		}
		return repository;
	}
	
	public FieldId getFieldId(UserSessionContext userCtx, String tableName, String fieldName) {
		return getRepository(userCtx).getFieldId(tableName, fieldName);
	}
	
	public AttributeProperties[] getAttribute(UserSessionContext userCtx, String tableName) {
		AttributeSchema attributeSchema = getAttributeSchema(userCtx);
		return attributeSchema.getAttributes("MDM_GRUPO_ART");
	}

	private AttributeSchema getAttributeSchema(UserSessionContext userCtx) {
		if (attributeSchema == null) {
			attributeSchema = metadataMan.getAttributeSchema(userCtx);
			taxonomyTable = attributeSchema.getTaxonomyTableCodes()[0];
		}
		return attributeSchema;
	}
	
	public String[] getTax(UserSessionContext userCtx) {
		AttributeSchema attributeSchema = getAttributeSchema(userCtx);
		return attributeSchema.getTaxonomyTableCodes();
	}
	
	public AttributeId getAttributeId(UserSessionContext userCtx, String attributeCode) {
		return getAttributeSchema(userCtx).getAttributeId(taxonomyTable, attributeCode);
	}

}
