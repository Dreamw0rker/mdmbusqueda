package com.assa.mdm.ejb;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import com.assa.mdm.data.Atributo;
import com.assa.mdm.data.Product;
import com.sap.mdm.MdmException;
import com.sap.security.core.server.destinations.api.DestinationException;


@Local
public interface BuscadorLocal {
	List<SubItem> findProducts(Map<Product, String> parametrosBusqueda, Map<Atributo,String> atributos) throws MdmException, Exception;
	
}
