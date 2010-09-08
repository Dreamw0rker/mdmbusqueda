package com.assa.mdm.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import com.assa.mdm.data.Product;
import com.sap.mdm.MdmException;


@Local
public interface BuscadorLocal {
	List<SubItem> findProducts(Map<Product, String> parametrosBusqueda, String partida) throws MdmException;
	
}
