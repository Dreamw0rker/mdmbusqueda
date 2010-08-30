package com.assa.mdm.connection;

import com.sap.mdm.session.SessionManager;
import com.sap.mdm.session.SessionTypes;
import com.sap.mdm.session.UserSessionContext;

public class MDMConnection {
	
	enum ServerConfig {
		SERVER_NAME("srvmdm"), REPOSITORY_NAME("DEV_Productos"), REPOSITORY_USER("Admin"), 
		REPOSITORY_PASS("sapmdm1908"), APP_NAME("BUSCADOR");
		
		private final String value;

		private ServerConfig(String value) {
			this.value = value;
		}
	}

	private UserSessionContext userSessionContext;

	public UserSessionContext getUserContext() {
		this.userSessionContext = new UserSessionContext(ServerConfig.SERVER_NAME.value, 
				ServerConfig.REPOSITORY_NAME.value, "", ServerConfig.REPOSITORY_USER.value);
		// Set application name to be displayed in the MDM Console
		userSessionContext.setApplicationName(ServerConfig.APP_NAME.value);
		SessionManager.getInstance().createSession(userSessionContext, SessionTypes.USER_SESSION_TYPE, 
				ServerConfig.REPOSITORY_PASS.value);
		return userSessionContext;
	}

}
