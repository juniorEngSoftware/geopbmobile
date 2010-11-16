package br.edu.ufcg.dsc.lsi.geopb.mobile.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
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
	}

	private void editFeatures(Kml ourKml) {
		for (Placemark placemark : GeoPBMobileUtil.getPlacemarks(ourKml)) {
			editDescription(placemark);
		}
	}

	private void editDescription(Placemark placemark) {
		String[] ids = placemark.getId().split("\\.");

		String infos = null;
		if( !(ids[2].equals("00012010")) ) {
			infos = editWorkInformations(ids[1], ids[2]);
		}
		placemark.setDescription("<![CDATA[<h4>Obras municipais</h4>  <br> "
						+ infos
						+ "<br> "
						+ "<form name=\"formInfo\" action=\"info_obras.jsp\" target=\"_blank\">"
						+ "<input type=\"submit\" value=\"Exibir Informacoes da Obra\">"
						+ "</form>"
						+ "<form name=\"formDenuncia\" action=\"denuncia.jsp\" target=\"_blank\">"
						+ "<input type=\"submit\" value=\"Realizar Denuncia\">"
						+ "</form>"
						+ "<form name=\"formUpload\" action=\"upload_img.jsp\" target=\"_blank\">"
						+ "<input type=\"submit\" value=\"Upload de Imagem\">"
						+ "</form>");

	}

	private String editWorkInformations(String unidadeGestora, String numeroObra) {

		String requestUrl = "http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora="+ unidadeGestora + "&numeroObra=" + numeroObra;
		
		InputStream uc = null;
		try {
			uc = GeoPBMobileUtil.getStreamOfConnection(requestUrl);
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		StringBuilder strBuilder = new StringBuilder();
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document document = null;
			
		try {
			document = builder.build(uc);	
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Element rootElement = document.getRootElement();
		List<Element> elementsList = (List<Element>) rootElement.getChildren();
		for (Element element : elementsList) {
			strBuilder.append(element.getChild("name").getValue() + ": ");
			strBuilder.append(element.getChild("value").getValue() + "<br>");
		}

		return strBuilder.toString();
	}


	

	public static void main(String[] args) {

//		URL yahoo = new URL("http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora=201031&numeroObra=00102007"); // pega aqui e no browser n
																																																			// n
//		URL yahoo = null;
//		try {
//			yahoo = new URL("http://buchada.dsc.ufcg.edu.br:80/geoserver/wms?height=1024&width=1024&layers=tce:obras_municipais"
//					+ "&request=GetMap&service=wms&styles=obras2Style&format_options=SUPEROVERLAY:false;KMPLACEMARK:false;KMSCORE:40;KMATTR:true;"
//					+ "&srs=EPSG:4326&format=application/vnd.google-earth.kml+XML&transparent=false&version=1.1.1&bbox=-38.662,-7.36,-35.81,-6.784");
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		Kml ourKml = null;
//		try {
//			ourKml = Kml.unmarshal(yahoo.openConnection().getInputStream());
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		Document documentKml = (Document) ourKml.getFeature();
//		List<Feature> list = documentKml.getFeature();
//		for (int j = 0; j < list.size(); j++) {
//			Placemark placemark = (Placemark) list.get(j);
//			String[] ids = placemark.getId().split("\\.");
//			System.out.println("id[1] :" + ids[1] + " id[2]: " + ids[2] + " id[3]: " + ids[3] + "  obra ---> " + j);
//
//		}

		//TODO TENTAR EDITAR MELHOR A OBRA obras_municipais.201031.00242008.MULTILINESTRING">
		URL urlDetalhe = null;
		try {
			urlDetalhe = new URL ("http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora=201050&numeroObra=00012010");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}


		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document document = null;
		try {
			document = builder.build(urlDetalhe.openConnection().getInputStream());
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();

		List<Element> elementsList = (List<Element>) rootElement.getChildren();

		StringBuilder strBuilder = new StringBuilder();
		for (Element element : elementsList) {

			System.out.println(element.getChild("name").getValue());
			System.out.println(element.getChild("value").getValue());

			System.out.println(strBuilder.toString());
		}
	}
}
