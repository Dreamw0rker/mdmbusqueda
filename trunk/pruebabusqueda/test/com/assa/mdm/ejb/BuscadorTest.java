package com.assa.mdm.ejb;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assa.mdm.command.CommandFactory;
import com.assa.mdm.connection.MDMConnection;
import com.assa.mdm.data.Product;
import com.assa.mdm.data.Repository;
import com.assa.test.BaseMockitoTest;
import com.sap.mdm.data.Record;
import com.sap.mdm.data.RecordResultSet;
import com.sap.mdm.data.commands.RetrieveLimitedRecordsCommand;
import com.sap.mdm.ids.FieldId;
import com.sap.mdm.search.Search;
import com.sap.mdm.session.UserSessionContext;
import com.sap.mdm.valuetypes.StringValue;

public class BuscadorTest extends BaseMockitoTest {
	@InjectMocks
	private Buscador buscador = new Buscador();
	@Mock
	private CommandFactory mockCommandFactory;
	@Mock
	private MDMConnection mockMdmConnection;
	@SuppressWarnings("unused")
	@Mock
	private Repository repository;
	
	@Test
	public void shouldGetCommands() throws Exception {
		UserSessionContext userCtx = mock(UserSessionContext.class);
		when(mockMdmConnection.getUserContext()).thenReturn(userCtx );
		RetrieveLimitedRecordsCommand command = mock(RetrieveLimitedRecordsCommand.class);
		when(mockCommandFactory.getLimitedRecordsCommand(userCtx, Product.TABLE_NAME.toString())).thenReturn(command );
		Search search = mock(Search.class);
		when(command.getSearch()).thenReturn(search);
		RecordResultSet recordSet = mock(RecordResultSet.class);
		Record record = mock(Record.class);
		StringValue stringValue = mock(StringValue.class);
		when(record.getFieldValue(any(FieldId.class))).thenReturn(stringValue);
		Record[] records = new Record[] {record };
		when(recordSet.getRecords()).thenReturn(records );
		when(command.getRecords()).thenReturn(recordSet);
		
		List<SubItem> products = buscador.findProducts("AR");
		assertEquals(1, products.size());
		
		verify(mockMdmConnection).getUserContext();
		verify(mockCommandFactory).getLimitedRecordsCommand(eq(userCtx), anyString());
	}
	
}
