package com.assa.mdm.command;

import com.assa.mdm.data.Repository;
import com.sap.mdm.MdmException;
import com.sap.mdm.data.commands.RetrieveLimitedRecordsCommand;
import com.sap.mdm.extension.data.ResultDefinitionEx;
import com.sap.mdm.ids.TableId;
import com.sap.mdm.search.Search;
import com.sap.mdm.session.UserSessionContext;
import com.sap.tc.logging.Location;

public class CommandFactory {
	
	private Location loc = Location.getLocation(this.getClass());
	
	private Repository repository = new Repository();

	public RetrieveLimitedRecordsCommand getLimitedRecordsCommand(UserSessionContext userCtx, 
			String table) throws MdmException {
		loc.entering();
		ResultDefinitionEx resultDefinition = new ResultDefinitionEx(table, userCtx);
		resultDefinition.setLoadAttributes(true);
		TableId tableId = repository.getTableId(userCtx, table);
		Search search = new Search(tableId);
		RetrieveLimitedRecordsCommand command = new RetrieveLimitedRecordsCommand(userCtx);
		command.setSearch(search);
		command.setResultDefinition(resultDefinition);
		loc.exiting();
		return command;
	}

}
