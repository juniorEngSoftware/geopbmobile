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

		response.setContentType("application/vnd.google-earth.kml+xml");

		String requestUrl = "http://buchada.dsc.ufcg.edu.br:80/geoserver/wms?height=1024&width=1024&layers=tce:obras_municipais"
				+ "&request=GetMap&service=wms&styles=obras2Style&format_options=SUPEROVERLAY:false;KMPLACEMARK:false;KMSCORE:40;KMATTR:true;"
				+ "&srs=EPSG:4326&format=application/vnd.google-earth.kml+XML&transparent=false&version=1.1.1&bbox=-38.662,-7.36,-35.81,-6.784";

		InputStream kmlInputStream = GeoPBMobileUtil.getStreamOfConnection(requestUrl);

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
		String[] ids = placemark.getId().split("\\.");

		String infos = "não há detalhes para essa obra";
		if( !(ids[2].equals("00012010")) ) {
			try {
				infos = editWorkInformations(ids[1], ids[2]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		placemark.setDescription(infos
						+ "<br> "
						+ "<form name=\"formInfo\" action=\"photographics.jsp\" target=\"_blank\">"
						+ "<input type=\"hidden\" name=unidadeGestora value=\"" + ids[1] + "\">"
						+ "<input type=\"hidden\" name=numeroObra value=\"" + ids[2] + "\">"
						+ "<input type=\"submit\" value=\"Visualizar Fotos da Obra\" +>"
						+ "</form>"
						+ "<form name=\"formDenuncia\" action=\"http://buchada.dsc.ufcg.edu.br/george/jsp/denuncia/denuncia.jsp?nuObra=" + ids[1] + "&uGestora=" + ids[0]+ "\" target=\"_blank\">"
						+ "<input type=\"submit\" value=\"Realizar Denuncia\">"
						+ "</form>"
						+ "<form name=\"formUpload\" action=\"http://buchada.dsc.ufcg.edu.br/GeoPBMobile/auditoria.jsp\" target=\"_blank\">"
						+ "<input type=\"submit\" value=\"Auditoria\">"
						+ "</form>");

	}

	private String editWorkInformations(String unidadeGestora, String numeroObra) throws IOException {

		String requestUrl = "http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora="+ unidadeGestora + "&numeroObra=" + numeroObra;
		
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
