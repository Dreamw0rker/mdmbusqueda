package com.assa.mdm.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;

import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import com.assa.mdm.command.CommandFactory;
import com.assa.mdm.connection.MDMConnection;
import com.assa.mdm.data.Product;
import com.assa.mdm.data.Repository;
import com.sap.mdm.MdmException;
import com.sap.mdm.commands.CommandException;
import com.sap.mdm.data.Record;
import com.sap.mdm.data.RecordResultSet;
import com.sap.mdm.data.commands.RetrieveLimitedRecordsCommand;
import com.sap.mdm.search.FieldSearchDimension;
import com.sap.mdm.search.KeywordSearchConstraint;
import com.sap.mdm.search.KeywordSearchDimension;
import com.sap.mdm.search.Search;
import com.sap.mdm.search.SearchConstraint;
import com.sap.mdm.search.SearchDimension;
import com.sap.mdm.search.TextSearchConstraint;
import com.sap.mdm.session.UserSessionContext;
import com.sap.mdm.valuetypes.StringValue;
import com.sap.tc.logging.Location;

/**
 * Session Bean implementation class Buscador
 */
@Stateless
//@Interceptors(SpringBeanAutowiringInterceptor.class)
public class Buscador implements BuscadorLocal {

	private static final String PRODUCTO_BASE_IND = "Z000";
//	@Autowired
	private MDMConnection mdmConnection = new MDMConnection();

	private CommandFactory commandFactory = new CommandFactory();

	private Repository repository = new Repository();

	private Location loc = Location.getLocation(this.getClass());

	@Override
	public List<SubItem> findProducts(String name) throws MdmException {
		UserSessionContext userCtx = mdmConnection.getUserContext();
		List<SubItem> result = new ArrayList<SubItem>();
		RetrieveLimitedRecordsCommand limitedRecordsCommand = commandFactory
				.getLimitedRecordsCommand(userCtx, Product.TABLE_NAME.toString());
		Search search = limitedRecordsCommand.getSearch();
		SearchDimension sd = new KeywordSearchDimension();
		SearchConstraint sc = new KeywordSearchConstraint(name,
				KeywordSearchConstraint.CONTAINS);
		search.addSearchItem(sd, sc);
		Product.FIELD_TIPO_MATERIAL.initFieldId(repository, userCtx);
		Product.FIELD_NUMERO_MATERIAL.initFieldId(repository, userCtx);
		search.addSearchItem(new FieldSearchDimension(Product.FIELD_TIPO_MATERIAL.getFieldId()),
				new TextSearchConstraint(PRODUCTO_BASE_IND, TextSearchConstraint.STARTS_WITH));
		loc.debugT("Before executing command");
		Record[] records = getResultRecords(limitedRecordsCommand);
		loc.debugT("Found records: " + records.length);
		Product.FIELD_PADRE.initFieldId(repository, userCtx);
		for (Record record : records) {
			loc.debugT(record.getLookupDisplayValue(Product.FIELD_TIPO_MATERIAL.getFieldId()));
			result.add(new SubItem());
			Collection<? extends Record> childRecords = addChildRecords(record, limitedRecordsCommand, search);
			loc.debugT("Children: " + childRecords.size());
//			result.addAll(childRecords);
		}
		return result;
	}

	private Record[] getResultRecords(
			RetrieveLimitedRecordsCommand limitedRecordsCommand)
			throws CommandException {
		limitedRecordsCommand.execute();
		RecordResultSet resultSet = limitedRecordsCommand.getRecords();
		Record[] records = resultSet.getRecords();
		return records;
	}

	private Collection<? extends Record> addChildRecords(Record record,
			RetrieveLimitedRecordsCommand command, Search search)
			throws CommandException {
		loc.entering();
		search.clear();
		StringValue numeroMaterialField = (StringValue) record
				.getFieldValue(Product.FIELD_NUMERO_MATERIAL.getFieldId());
		TextSearchConstraint searchConstr = new TextSearchConstraint(
				numeroMaterialField.getString(), TextSearchConstraint.EQUALS);
		search.addSearchItem(new FieldSearchDimension(Product.FIELD_PADRE.getFieldId()), 
				searchConstr);
		loc.exiting();
		return Arrays.asList(getResultRecords(command));
	}

	public void setMdmConnection(MDMConnection mdmConnection) {
		this.mdmConnection = mdmConnection;
	}

	public void setCommandFactory(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}

}
