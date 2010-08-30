package com.assa.mdm.ejb;

import java.util.List;

import javax.ejb.Local;

import com.sap.mdm.data.Record;


@Local
public interface BuscadorLocal {
	List<Record> findProducts(String name);
	
}
