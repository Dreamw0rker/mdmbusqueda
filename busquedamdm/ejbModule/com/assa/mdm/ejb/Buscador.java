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
import com.sap.mdm.search.AttributeSearchDimension;
import com.sap.mdm.search.FieldSearchDimension;
import com.sap.mdm.search.KeywordSearchConstraint;
import com.sap.mdm.search.KeywordSearchDimension;
import com.sap.mdm.search.Search;
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
	public List<SubItem> findProducts(Map<Product, String> parametrosBusqueda, String partida) throws MdmException {
		UserSessionContext userCtx = mdmConnection.getUserContext();
		List<Product> products = initializeFields(userCtx);
		
		RetrieveLimitedRecordsCommand limitedRecordsCommand = getAndConfigureCommand(parametrosBusqueda, 
				userCtx, products);
		
		return extractSubitems(limitedRecordsCommand, userCtx);
	}

	private RetrieveLimitedRecordsCommand getAndConfigureCommand(Map<Product, String> parametrosBusqueda, 
			UserSessionContext userCtx, List<Product> products) throws MdmException {
		RetrieveLimitedRecordsCommand limitedRecordsCommand = commandFactory
				.getLimitedRecordsCommand(userCtx, Product.TABLE_NAME.toString());
		ResultDefinitionEx resultDefinition = (ResultDefinitionEx) limitedRecordsCommand.getResultDefinition();
		for (Product product : products) {
			resultDefinition.addSelectField(product.toString());
		}
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
//		AttributeId attributeId = new AttributeId("A4_291288");
		Atributo.SUBSTANCIA_ACTIVA.initAttributeId(repository, userCtx);
		AttributeSearchDimension attributeSearchDimension = new AttributeSearchDimension(Product.FIELD_TAX_GRUPO_ART.getFieldId(), 
				Atributo.SUBSTANCIA_ACTIVA.getAttributeId());
		search.addSearchItem(attributeSearchDimension, new TextSearchConstraint(
				"Aciclovir", TextSearchConstraint.CONTAINS));
		search.addSearchItem(new FieldSearchDimension(Product.FIELD_TIPO_MATERIAL.getFieldId()),
				new TextSearchConstraint(PRODUCTO_BASE_IND, TextSearchConstraint.STARTS_WITH));
		loc.debugT("Before executing command");
		return limitedRecordsCommand;
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

	@SuppressWarnings("unused")
	private void addDebug(UserSessionContext userCtx, List<SubItem> result) {
		SubItem standard = new SubItem();
		Item standardItem = new Item();
		standardItem.setCategoria(repository.getAttribute(userCtx, Product.TABLE_NAME.toString())[3].getCode());
		standardItem.setClaveProducto(Arrays.toString(repository.getTax(userCtx)));
		standard.setItemPadre(standardItem);
		result.add(standard);
	}

	private List<Product> initializeFields(UserSessionContext userCtx) {
		List<Product> productFields = new ArrayList<Product>();
		for (Product product : Product.values()) {
			if (product != Product.TABLE_NAME) {
				productFields.add(product);
				product.initFieldId(repository, userCtx);
			}
		}
		return productFields;
	}

	private Record[] getResultRecords(RetrieveLimitedRecordsCommand command)
			throws CommandException {
		command.execute();
		RecordResultSet resultSet = command.getRecords();
		return resultSet.getRecords();
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
