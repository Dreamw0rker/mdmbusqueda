package com.assa.mdm.ejb;

import java.util.List;

import javax.ejb.Local;

import mx.com.mypo.bpd.caf.catalogoproductos.SubItem;

import com.sap.mdm.MdmException;


@Local
public interface BuscadorLocal {
	List<SubItem> findProducts(String name) throws MdmException;
	
}
