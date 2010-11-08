package br.edu.ufcg.dsc.lsi.geopb.mobile.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/vnd.google-earth.kml+xml");

//		String requestUrl = "http://buchada.dsc.ufcg.edu.br:80/geoserver/wms?height=1024&width=1024&layers=tce:obras_municipais" +
//				"&request=GetMap&service=wms&styles=obras2Style&format_options=SUPEROVERLAY:false;KMPLACEMARK:false;KMSCORE:40;KMATTR:true;" +
//				"&srs=EPSG:4326&format=application/vnd.google-earth.kml+XML&transparent=false&version=1.1.1&bbox=-38.662,-7.36,-35.81,-6.784";

		
		String requestUrl = "http://dl.dropbox.com/u/8510487/obras_municipais.kml";
		
		URL url = new URL(requestUrl.toString());
		URLConnection uc = url.openConnection();
		uc.setDoOutput(true);
		uc.setDoInput(true);
		uc.setUseCaches(false);

		File file = new File("webapps/GeoPBMobile/teste.kml");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fileWriter);
		
		InputStreamReader in = new InputStreamReader(uc.getInputStream());
		BufferedReader br = new BufferedReader(in);
		
		String str = null;
		while ((str = br.readLine()) != null) {
			bw.write(str);
		}
		br.close();
		bw.flush();
		bw.close();
		
		// Kml ourKml = Kml.unmarshal(new
		// File("C:/Users/caio/Documents/testeKML/obras_municipais.kml"));
		Kml ourKml = Kml.unmarshal(file);

		Document document = (Document) ourKml.getFeature();

		List<Feature> list = document.getFeature();

		for (int j = 0; j < list.size(); j++) {
			Placemark placemark = (Placemark) list.get(j);
			placemark.setDescription(
					"<![CDATA[<h4>Obras municipais</h4>" +
							"<ul>" +
								"<li><a href=\"http://dl.dropbox.com/u/14469229/info_obras.html\">Exibir Informacoes da Obra</a></li><br>" +
								"<li><a href=\"http://dl.dropbox.com/u/14469229/denuncia.html\">Realizar Denuncia</a></li><br>" +
								"<li><a href=\"http://dl.dropbox.com/u/14469229/upload_img.html\">Upload de Imagem</a></li>" +
							"</ul>]]>");
		}

		ourKml.marshal(response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
