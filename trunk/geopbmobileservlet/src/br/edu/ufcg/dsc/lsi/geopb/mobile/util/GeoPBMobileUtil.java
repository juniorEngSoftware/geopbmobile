package br.edu.ufcg.dsc.lsi.geopb.mobile.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class GeoPBMobileUtil {

	public static List<Placemark> getPlacemarks(Kml ourKml) {
		List<Placemark> resultList = new LinkedList<Placemark>();
		
		Document document = (Document) ourKml.getFeature();

		List<Feature> list = document.getFeature();

		for (Feature feature : list) {
			Placemark placemark = (Placemark) feature;
			resultList.add(placemark);
		}
		
		return resultList;
	}
	
	public static InputStream getStreamOfConnection(String requestUrl) throws MalformedURLException, IOException {
		URL url = new URL(requestUrl.toString());
		URLConnection connection = url.openConnection();
		return connection.getInputStream();
	}
	
	public static String getPlacemarkDescription(Placemark placemark) {		
		
		String managementUnit = getManagementUnit(placemark);
		String buildNumber = getBuildNumber(placemark);
		
		String infos = GeoPBMobileMessageManager.NOT_DETAILED_BUILD;
		if( !(getBuildNumber(placemark).equals("00012010")) ) {
			try {
				infos = editBuildInformations(managementUnit, buildNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return GeoPBMobileMessageManager.getFormattedDescription(managementUnit, buildNumber, infos);
	}
	
	private static String getManagementUnit(Placemark placemark) {
		return placemark.getId().split("\\.")[1];
	}
	
	private static String getBuildNumber(Placemark placemark) {
		return placemark.getId().split("\\.")[2];
	}

	private static String editBuildInformations(String unidadeGestora, String numeroObra) throws IOException {
		
		String requestUrl = GeoPBMobileMessageManager.getBuildDetailsRequest(unidadeGestora, numeroObra);
		
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
		for (Element element : elementsList) {
			strBuilder.append(element.getChild("name").getValue() + ": ");
			strBuilder.append(element.getChild("value").getValue() + "<br>");
		}
		
		return strBuilder.toString();
	}
	
}
