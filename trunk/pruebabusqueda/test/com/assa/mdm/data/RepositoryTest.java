package com.assa.mdm.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assa.test.BaseMockitoTest;
import com.sap.mdm.extension.MetadataManager;
import com.sap.mdm.extension.schema.AttributeSchema;
import com.sap.mdm.ids.AttributeId;
import com.sap.mdm.session.UserSessionContext;


public class RepositoryTest extends BaseMockitoTest {
	@InjectMocks
	private Repository repository = new Repository();
	@Mock
	private MetadataManager metadataManager;
	
	@Test
	public void shouldGetAttributeId() throws Exception {
		UserSessionContext userCtx = mock(UserSessionContext.class);
		AttributeSchema attributeSchema = mock(AttributeSchema.class);
		String taxonomyTable = "PROD_TAX";
		when(attributeSchema.getTaxonomyTableCodes()).thenReturn(new String[] {taxonomyTable});
		AttributeId attributeId = mock(AttributeId.class);
		when(attributeSchema.getAttributeId(anyString(), anyString())).thenReturn(attributeId);
		when(metadataManager.getAttributeSchema(userCtx)).thenReturn(attributeSchema);
		
		String attributeCode = "Clave farmaceutica";
		assertEquals(attributeId, repository.getAttributeId(userCtx, attributeCode));
		
		verify(metadataManager).getAttributeSchema(userCtx);
		verify(attributeSchema).getAttributeId(taxonomyTable, attributeCode);
	}
}
