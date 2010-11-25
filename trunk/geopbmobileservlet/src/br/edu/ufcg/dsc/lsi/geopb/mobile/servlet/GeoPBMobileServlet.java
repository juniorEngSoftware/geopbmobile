package br.edu.ufcg.dsc.lsi.geopb.mobile.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import br.edu.ufcg.dsc.lsi.geopb.mobile.util.GeoPBMobileMessageManager;
import br.edu.ufcg.dsc.lsi.geopb.mobile.util.GeoPBMobileUtil;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * 
 * Servlet implementation class GeoPBMobileServlet
 * 
 * @author Caio Santos
 * @author Filipe Carolino
 * @author Kemerson Araujo
 * 
 */
public class GeoPBMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeoPBMobileServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType(GeoPBMobileMessageManager.KML_CONTENT_TYPE);

		InputStream kmlInputStream = GeoPBMobileUtil.getStreamOfConnection(GeoPBMobileMessageManager.URL_BBOX_OBRAS);

		Kml ourKml = Kml.unmarshal(kmlInputStream);
		editFeatures(ourKml);
		
		ServletOutputStream outputStream = response.getOutputStream();
		ourKml.marshal(outputStream);
		outputStream.flush();
		outputStream.close();
		
		kmlInputStream.close();
	}

	private void editFeatures(Kml ourKml) {
		for (Placemark placemark : GeoPBMobileUtil.getPlacemarks(ourKml)) {
			editDescription(placemark);
		}
	}

	private void editDescription(Placemark placemark) {
		placemark.setDescription(GeoPBMobileUtil.getPlacemarkDescription(placemark));
	}
	
}
