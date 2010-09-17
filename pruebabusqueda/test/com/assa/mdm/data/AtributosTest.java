package com.assa.mdm.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.sap.mdm.ids.AttributeId;
import com.sap.mdm.session.UserSessionContext;


public class AtributosTest {
	@Test
	public void shouldInitAttributeId() throws Exception {
		Repository repository = mock(Repository.class);
		UserSessionContext userCtx = mock(UserSessionContext.class);
		AttributeId attributeId = mock(AttributeId.class);
		when(repository.getAttributeId(eq(userCtx), anyString())).thenReturn(attributeId);
		Atributo atributo = Atributo.SUBSTANCIA_ACTIVA;
		atributo.initAttributeId(repository, userCtx);
		
		verify(repository).getAttributeId(userCtx, atributo.toString());
		assertEquals(attributeId, atributo.getAttributeId());
	}
}
