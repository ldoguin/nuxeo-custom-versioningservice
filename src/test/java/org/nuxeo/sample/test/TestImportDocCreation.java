package org.nuxeo.sample.test;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.storage.sql.SQLRepositoryTestCase;

public class TestImportDocCreation extends SQLRepositoryTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		deployBundle("custom-versioningservice");
		openSession();
	}

	public void testDocumentCreation() throws Exception {
		DocumentModel doc = session.createDocumentModel("/", "test", "File");
		doc.putContextData("comesFromImport", true);
		doc = session.createDocument(doc);
		assertEquals("12.0+", doc.getVersionLabel());
		doc = session.createDocumentModel("/", "test2", "File");
		doc = session.createDocument(doc);
		assertEquals("0.0", doc.getVersionLabel());
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		closeSession();
	}
}
