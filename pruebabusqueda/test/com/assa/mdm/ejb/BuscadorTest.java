package com.assa.mdm.ejb;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import com.assa.mdm.command.CommandFactory;
import com.assa.mdm.connection.MDMConnection;
import com.sap.mdm.session.UserSessionContext;

public class BuscadorTest {
	@InjectMocks
	private Buscador buscador = new Buscador();
	@Mock
	private CommandFactory mockCommandFactory;
	@Mock
	private MDMConnection mockMdmConnection;
	
	@Before 
	public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

	
	@Test
	public void shouldGetCommands() throws Exception {
		UserSessionContext userCtx = mock(UserSessionContext.class);
		when(mockMdmConnection.getUserContext()).thenReturn(userCtx );
		
		buscador.findProducts("AR");
		
		verify(mockMdmConnection).getUserContext();
		verify(mockCommandFactory).getLimitedRecordsCommand(eq(userCtx), anyString());
	}
}
