package br.edu.ufcg.dsc.lsi.geopb.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class GeoPBMobilePlacemark {
	
	private Placemark placemark;
	
	public GeoPBMobilePlacemark(Placemark placemark) {
		this.placemark = placemark;
	}

	public void editDescription() {		

		String managementUnit = getManagementUnit();
		String buildNumber = getBuildNumber();

		String infos = GeoPBMobileMessageManager.NOT_DETAILED_BUILD;
		if( !(getBuildNumber().equals("00012010")) ) {
			try {
				infos = editBuildInformations();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.placemark.setDescription(GeoPBMobileMessageManager.getFormattedDescription(managementUnit, buildNumber, infos));
	}

	private String getManagementUnit() {
		return this.placemark.getId().split("\\.")[1];
	}

	private String getBuildNumber() {
		return this.placemark.getId().split("\\.")[2];
	}

	private String editBuildInformations() throws IOException {

		String requestUrl = GeoPBMobileMessageManager.getBuildDetailsRequest(getManagementUnit(), getBuildNumber());

		InputStream uc = null;
		try {
			uc = GeoPBMobileUtil.getStreamOfConnection(requestUrl);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} 

		StringBuilder strBuilder = new StringBuilder();
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document document = null;

		try {
			document = builder.build(uc);	
		} catch (JDOMException e) {
			e.printStackTrace();
		} finally {
			uc.close();
		}

		Element rootElement = document.getRootElement();
		List<Element> elementsList = rootElement.getChildren();
		strBuilder.append("<table border=1>");
		for (Element element : elementsList) {
			strBuilder.append("<tr><td><b>" + element.getChild("name").getValue() + "</b></td>");
			strBuilder.append("<td>" + element.getChild("value").getValue() + "</td></tr>");
		}
		strBuilder.append("</table>");

		return strBuilder.toString();
	}
	
	public Placemark getOriginalPlacemark() {
		return this.placemark;
	}

}
