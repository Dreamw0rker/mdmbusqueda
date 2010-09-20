package com.assa.mdm.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import mx.com.mypo.bpd.caf.catalogoproductos.Item;
import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import com.assa.mdm.command.CommandFactory;
import com.assa.mdm.connection.MDMConnection;
import com.assa.mdm.data.Atributo;
import com.assa.mdm.data.ItemFactory;
import com.assa.mdm.data.Product;
import com.assa.mdm.data.Repository;
import com.sap.mdm.MdmException;
import com.sap.mdm.commands.CommandException;
import com.sap.mdm.data.Record;
import com.sap.mdm.data.RecordResultSet;
import com.sap.mdm.data.commands.RetrieveLimitedRecordsCommand;
import com.sap.mdm.extension.data.ResultDefinitionEx;
import com.sap.mdm.schema.AttributeProperties;
import com.sap.mdm.search.AttributeSearchDimension;
import com.sap.mdm.search.FieldSearchDimension;
import com.sap.mdm.search.KeywordSearchConstraint;
import com.sap.mdm.search.KeywordSearchDimension;
import com.sap.mdm.search.Search;
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
	private ItemFactory itemFactory = new ItemFactory();

	@Override
	public List<SubItem> findProducts(Map<Product, String> parametrosBusqueda, Map<Atributo,String> atributos) throws MdmException {
		UserSessionContext userCtx = mdmConnection.getUserContext();
		
		RetrieveLimitedRecordsCommand limitedRecordsCommand = getAndConfigureCommand(parametrosBusqueda, atributos, userCtx);
		
		return extractSubitems(limitedRecordsCommand, userCtx);
	}

	private RetrieveLimitedRecordsCommand getAndConfigureCommand(Map<Product, String> parametrosBusqueda, 
			Map<Atributo, String> atributos, UserSessionContext userCtx) throws MdmException {
		RetrieveLimitedRecordsCommand limitedRecordsCommand = commandFactory
				.getLimitedRecordsCommand(userCtx, Product.TABLE_NAME.toString());
		ResultDefinitionEx resultDefinition = (ResultDefinitionEx) limitedRecordsCommand.getResultDefinition();
		initProducts(userCtx, resultDefinition);
		
		configureSearch(parametrosBusqueda, atributos, userCtx, limitedRecordsCommand);
		return limitedRecordsCommand;
	}

	private void initProducts(UserSessionContext userCtx, ResultDefinitionEx resultDefinition) {
		for (Product product : Product.values()) {
			if (product != Product.TABLE_NAME) {
				product.initFieldId(repository, userCtx);
				resultDefinition.addSelectField(product.toString());
			}
		}
	}

	private void configureSearch(Map<Product, String> parametrosBusqueda, Map<Atributo, String> atributos,
			UserSessionContext userCtx, RetrieveLimitedRecordsCommand limitedRecordsCommand) {
		Search search = limitedRecordsCommand.getSearch();
		if (parametrosBusqueda.containsKey(Product.FIELD_DESC)) {
			search.addSearchItem(new KeywordSearchDimension(), new KeywordSearchConstraint(parametrosBusqueda.get(
					Product.FIELD_DESC), KeywordSearchConstraint.CONTAINS));
			parametrosBusqueda.remove(Product.FIELD_DESC);
		}
		for (Product product : parametrosBusqueda.keySet()) {
			search.addSearchItem(new FieldSearchDimension(product.getFieldId()), new TextSearchConstraint(
					parametrosBusqueda.get(product), TextSearchConstraint.CONTAINS));
		}
		for (Atributo atributo : atributos.keySet()) {
			SearchDimension attributeSearchDimension = new AttributeSearchDimension(
					Product.FIELD_TAX_GRUPO_ART.getFieldId(), atributo.initAttributeId(repository, userCtx));
			search.addSearchItem(attributeSearchDimension, new TextSearchConstraint(
					atributos.get(atributo), TextSearchConstraint.CONTAINS));
		}
		search.addSearchItem(new FieldSearchDimension(Product.FIELD_TIPO_MATERIAL.getFieldId()),
				new TextSearchConstraint(PRODUCTO_BASE_IND, TextSearchConstraint.STARTS_WITH));
	}

	private List<SubItem> extractSubitems(RetrieveLimitedRecordsCommand limitedRecordsCommand, UserSessionContext userCtx) 
		throws CommandException {
		List<SubItem> result = new ArrayList<SubItem>();
		Record[] records = getResultRecords(limitedRecordsCommand);
		loc.debugT("Found records: " + records.length);
		for (Record record : records) {
			loc.debugT(record.getLookupDisplayValue(Product.FIELD_TIPO_MATERIAL.getFieldId()));
			SubItem subitem = new SubItem();
			result.add(subitem);
			subitem.setItemPadre(itemFactory.toItemPadre(record));
			Collection<? extends Record> childRecords = addChildRecords(record, limitedRecordsCommand);
			List<Item> subItems = subitem.getSubItems();
			loc.debugT("Children: " + childRecords.size());
			for (Record child : childRecords) {
				subItems.add(itemFactory.toItemPadre(child));
			}
		}
//		addDebug(userCtx, result);
		return result;
	}

	private Record[] getResultRecords(RetrieveLimitedRecordsCommand command)
			throws CommandException {
		command.execute();
		RecordResultSet resultSet = command.getRecords();
		return resultSet.getRecords();
	}
	
	@SuppressWarnings("unused")
	private void addDebug(UserSessionContext userCtx, List<SubItem> result) {
		SubItem standard = new SubItem();
		Item standardItem = new Item();
		AttributeProperties[] attributeProps = repository.getAttribute(userCtx);
		standardItem.setCategoria(attributeProps[3].getCode());
		String[] tax = repository.getTax(userCtx);
		standardItem.setClaveProducto(Arrays.toString(tax));
		AttributeProperties attributeConcentracion = attributeProps[0];
		char[] charArray = attributeConcentracion.toString().toCharArray();
		StringBuilder builder = new StringBuilder();
		for (char c : charArray) {
			builder.append(c);
			builder.append(":");
			builder.append(Integer.toHexString(c));
			builder.append("; ");
		}
		standardItem.setDescripcion(builder.toString());
		standardItem.setPMR(attributeConcentracion.getCode());
		standard.setItemPadre(standardItem);
		result.add(standard);
	}

	private Collection<? extends Record> addChildRecords(Record record,
			RetrieveLimitedRecordsCommand command)
			throws CommandException {
		Search search = command.getSearch();
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

}
