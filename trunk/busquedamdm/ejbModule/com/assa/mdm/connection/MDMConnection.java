package com.assa.mdm.connection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sap.engine.services.configuration.appconfiguration.ApplicationPropertiesAccess;
import com.sap.mdm.session.SessionManager;
import com.sap.mdm.session.SessionTypes;
import com.sap.mdm.session.UserSessionContext;
import com.sap.security.core.server.destinations.api.Destination;
import com.sap.security.core.server.destinations.api.DestinationService;
import com.sap.security.core.server.destinations.api.DestinationServiceLocator;

//@Repository
public class MDMConnection {
	
	enum ServerConfig {
		SERVER_NAME("serverName"), REPOSITORY_NAME("repositoryName"), REPOSITORY_USER("mdm.repositoryUser"), 
		REPOSITORY_PASS("mdm.repositoryPassword"), APP_NAME("BUSCADOR");
		
		private String value;

		private ServerConfig(String value) {
			this.value = value;
		}
		
	}
	
	enum ConnectionProperties {
		DESTINATION_TYPE("MDM"), DESTINATION_NAME("MDM_Dest"), PROPERTY_METHOD("getSimpleProperty"), VALUE_METHOD("getValue");
		
		private final String value;

		private ConnectionProperties(String value) {
			this.value = value;
		}
	}

	private UserSessionContext userSessionContext;
	private Destination destination;

	//TODO: lazy initialisation
	public UserSessionContext getUserContext() throws Exception {
		DestinationService destinationService = DestinationServiceLocator.getInstance();
		destination = destinationService.getDestination(ConnectionProperties.DESTINATION_TYPE.value, 
				ConnectionProperties.DESTINATION_NAME.value);
		getServerConfig(destination);
		getCredentials();
		this.userSessionContext = new UserSessionContext(ServerConfig.SERVER_NAME.value, 
				ServerConfig.REPOSITORY_NAME.value, "", ServerConfig.REPOSITORY_USER.value);
		// Set application name to be displayed in the MDM Console
		userSessionContext.setApplicationName(ServerConfig.APP_NAME.value);
		SessionManager.getInstance().createSession(userSessionContext, SessionTypes.USER_SESSION_TYPE, 
				ServerConfig.REPOSITORY_PASS.value);
		return userSessionContext;
	}
	
	private void getCredentials() throws NamingException {
		ApplicationPropertiesAccess appCfgProps = (ApplicationPropertiesAccess) 
			new InitialContext().lookup("ApplicationConfiguration");
		Properties applicationProperties = appCfgProps.getApplicationProperties();
		getApplicationProperty(applicationProperties, ServerConfig.REPOSITORY_USER);
		getApplicationProperty(applicationProperties, ServerConfig.REPOSITORY_PASS);
	}

	private void getApplicationProperty(Properties applicationProperties, ServerConfig serverConfig) {
		serverConfig.value = applicationProperties.getProperty(serverConfig.value);
	}

	private void getServerConfig(Destination destination) throws Exception {
		Method propertyMethod = destination.getClass().getMethod(ConnectionProperties.PROPERTY_METHOD.value, 
				String.class);
		setServerConfig(destination, propertyMethod, ServerConfig.SERVER_NAME);
		setServerConfig(destination, propertyMethod, ServerConfig.REPOSITORY_NAME);
	}


	private void setServerConfig(Destination destination, Method propertyMethod, ServerConfig serverConfig)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object propertyValue = propertyMethod.invoke(destination, serverConfig.value);
		Method valueMethod = propertyValue.getClass().getMethod(ConnectionProperties.VALUE_METHOD.value);
		serverConfig.value = (String) valueMethod.invoke(propertyValue);
	}


	public Destination getDestination() {
		return destination;
	}

}
