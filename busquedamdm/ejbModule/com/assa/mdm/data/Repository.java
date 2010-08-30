package com.assa.mdm.data;

import com.sap.mdm.extension.MetadataManager;
import com.sap.mdm.extension.schema.RepositorySchemaEx;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.ids.TableId;
import com.sap.mdm.session.UserSessionContext;

public class Repository {

	private RepositorySchemaEx repository;

	public TableId getTableId(UserSessionContext userCtx, String table) {
		return getRepository(userCtx).getTableId(table);
	}
	
	private RepositorySchemaEx getRepository(UserSessionContext userCtx) {
		if (repository == null) {
			MetadataManager metadataMan = MetadataManager.getInstance();
			repository = metadataMan.getRepositorySchema(userCtx);
		}
		return repository;
	}
	
	public FieldId getFieldId(UserSessionContext userCtx, String tableName, String fieldName) {
		return getRepository(userCtx).getFieldId(tableName, fieldName);
	}

}
