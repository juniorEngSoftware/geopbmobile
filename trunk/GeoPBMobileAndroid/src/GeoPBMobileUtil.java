

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;

public class GeoPBMobileUtil {

	/*
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
	*/
	
	public static InputStream getStreamOfConnection(String requestUrl) throws MalformedURLException, IOException {
		URL url = new URL(requestUrl.toString());
		URLConnection connection = url.openConnection();
		return connection.getInputStream();
	}
	
}