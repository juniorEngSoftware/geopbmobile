package br.edu.ufcg.dsc.lsi.geopb.mobile.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * 
 * Servlet implementation class GeoPBMobileServlet
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

		String requestUrl = "http://buchada.dsc.ufcg.edu.br:80/geoserver/wms?height=1024&width=1024&layers=tce:obras_municipais" +
		"&request=GetMap&service=wms&styles=obras2Style&format_options=SUPEROVERLAY:false;KMPLACEMARK:false;KMSCORE:40;KMATTR:true;" +
		"&srs=EPSG:4326&format=application/vnd.google-earth.kml+XML&transparent=false&version=1.1.1&bbox=-38.662,-7.36,-35.81,-6.784";
		
		URLConnection uc = createConnection(requestUrl);
		File file = createPhysicalFile(uc, "originalKml.kml");
		
		Kml ourKml = Kml.unmarshal(file);
		
		editFeatures(ourKml);
		
		ServletOutputStream outputStream = response.getOutputStream();
		ourKml.marshal(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	private void editFeatures(Kml ourKml) {
		Document document = (Document) ourKml.getFeature();

		List<Feature> list = document.getFeature();

		for (int j = 0; j < list.size(); j++) {
			Placemark placemark = (Placemark) list.get(j);
			//TODO colocar em arquivo de propriedade
			editDescription(placemark);
			
		}
	}

	private void editDescription(Placemark placemark) {
		String[] ids = placemark.getId().split("\\.");
		
		try {
			String infos = editWorkInformations(ids[1], ids[2]);
			
			placemark.setDescription(
					"<![CDATA[<h4>Obras municipais</h4>  <br> "+ infos + "<br> " +
					"<form name=\"formInfo\" action=\"http://dl.dropbox.com/u/14469229/info_obras.html\" target=\"_blank\">" +
						"<input type=\"submit\" value=\"Exibir Informacoes da Obra\">" +
					"</form>" +
					"<form name=\"formDenuncia\" action=\"http://dl.dropbox.com/u/14469229/denuncia.html\" target=\"_blank\">" +
						"<input type=\"submit\" value=\"Realizar Denuncia\">" +
					"</form>" +
					"<form name=\"formUpload\" action=\"http://dl.dropbox.com/u/14469229/upload_img.html\" target=\"_blank\">" +
						"<input type=\"submit\" value=\"Upload de Imagem\">" +
					"</form>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private String editWorkInformations(String unidadeGestora, String numeroObra)
			throws MalformedURLException, IOException, JDOMException {
		
		String requestUrl = "http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora="+unidadeGestora+"&numeroObra="+numeroObra;

		URLConnection uc = createConnection(requestUrl);
		File xmlFile = createPhysicalFile(uc, "infoObras.xml");
		
		StringBuilder strBuilder = new StringBuilder();
		
		SAXBuilder builder = new SAXBuilder();
		org.jdom.Document document = builder.build(xmlFile);
		Element rootElement = document.getRootElement();
		List<Element> elementsList = (List<Element>) rootElement.getChildren();
		
		for (Element element : elementsList) {
			strBuilder.append(element.getChild("name").getValue() + ": ");
			strBuilder.append(element.getChild("value").getValue() + "\n");
		}
		
		return strBuilder.toString();
	}

	private File createPhysicalFile(URLConnection connection, String fileName) throws IOException {
		//TODO colocar em arquivo de propriedades
		File file = new File("./" + fileName);
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fileWriter);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			bw.write(inputLine);
		}
		bw.flush();
		bw.close();
		
		return file;
	}

	private URLConnection createConnection(String requestUrl) throws MalformedURLException, IOException {
		//TODO colocar em arquivo de propriedades.
		
		URL url = new URL(requestUrl.toString());
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		return connection;
	}
	
	public static void main(String[] args) throws Exception {

		
		URL yahoo = new URL("http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora=201050&numeroObra=00012010");
		
		URLConnection yc = yahoo.openConnection();
        InputStream inputStream = yc.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String inputLine;

        File file = new File("./" + "caio.xml");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fileWriter);
		
		while ((inputLine = in.readLine()) != null) {
			bw.write(inputLine);
		}
		bw.flush();
		bw.close();
        
        SAXBuilder builder = new SAXBuilder();
		org.jdom.Document document = builder.build(file);
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
