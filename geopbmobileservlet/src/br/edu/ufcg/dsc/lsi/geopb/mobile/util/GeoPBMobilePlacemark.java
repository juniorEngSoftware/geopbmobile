package br.edu.ufcg.dsc.lsi.geopb.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * 
 * @author Caio Santos
 * @author Filipe Carolino
 * @author Kemerson Araujo
 *
 */
public class GeoPBMobilePlacemark {
	
	private Placemark placemark;
	private String managementUnit;
	private String buildNumber; 
	
	public GeoPBMobilePlacemark(Placemark placemark) {
		this.placemark = placemark;
		this.managementUnit = placemark.getId().split("\\.")[1];
		this.buildNumber = placemark.getId().split("\\.")[2];
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

	public String getManagementUnit() {
		return managementUnit;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public String editBuildInformations() throws IOException {

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

	//CONSTRUTOR E MÉTODOS DESTINADOS PARA TESTE
	public GeoPBMobilePlacemark(String managementUnit, String buildNumber) {
		this.managementUnit = managementUnit;
		this.buildNumber = buildNumber;
	}
	
	public Placemark getOriginalPlacemark() {
		return this.placemark;
	}
	
	public void setManagementUnit(String managementUnit) {
		this.managementUnit = managementUnit;
	}
	
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

}
