<?xml version="1.0" encoding="UTF-8"?>
<public-part
		version="1.0.5"
		xmlns="http://xml.sap.com/2002/11/PublicPart"
		xmlns:IDX="urn:sap.com:PublicPart:1.0"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://xml.sap.com/2002/11/PublicPart ppdef.xsd">
	<name>client</name>
	<purpose>compilation</purpose>
	<entities>
		<entity>
			<name>Buscador</name>
			<package>com/assa/mdm/ejb</package>
			<entity-type>Java Class</entity-type>
			<entity-sub-type>Class</entity-sub-type>
		</entity>
		<entity>
			<name>BuscadorLocal</name>
			<package>com/assa/mdm/ejb</package>
			<entity-type>Java Class</entity-type>
			<entity-sub-type>Class</entity-sub-type>
		</entity>
	</entities>
	<access-control-list>
		<grant forwarding-allowed="true">
			<dc-ref>
				<name>*</name>
				<vendor>*</vendor>
			</dc-ref>
		</grant>
	</access-control-list>
</public-part>
