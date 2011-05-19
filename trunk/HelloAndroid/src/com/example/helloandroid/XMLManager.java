package com.example.helloandroid;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XMLManager {

	private InputStream xmlStream;

	public XMLManager(InputStream streamOfConnection) {
		this.xmlStream = streamOfConnection;
	}

	public String readXml() throws Exception, IOException {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(xmlStream);

		Element rootElement = document.getRootElement();
		return rootElement.getName();

		// List list = rootElement.getChildren();

	}

}
