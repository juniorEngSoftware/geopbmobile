package br.edu.ufcg.dsc.lsi.geopb.mobile.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.micromata.opengis.kml.v_2_2_0.Kml;




/**
 * Servlet implementation class GeoPBMobileServlet
 */
public class GeoPBMobileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String str;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeoPBMobileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		Kml kmlRoot = new Kml();
//		NetworkLink netWorkLink = new NetworkLink();
//		Folder folder = new Folder();
//		kmlRoot.addFolder(folder);
//		kmlRoot.addNetworkLink(netWorkLink);
//		KMLParser parser = new KMLParser();
//		parser.
		
		
		
		//PRECISEI COPIAR O JAVAAPIFOR KML P/ PASTA TOMCAT/LIB
		Kml ourKml = new Kml();
		ourKml.createAndSetPlacemark()
		   .withName("London, UK").withOpen(Boolean.TRUE)
		   .createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
		ourKml.marshal(new File("HelloKml.kml"));
		
//		ServletOutputStream outputStream = response.getOutputStream();
//		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				"<kml>" +
//				"   <Folder>      " +
//				"<NetworkLink>" +
//				"         <name>tce:obras_municipais</name>" +
//				"         <open>1</open>         " +
//				"<visibility>1</visibility>         " +
//				"<Url>            " +
//				"<href><![CDATA[http://localhost/geopbmobile/kmlService?]]></href>            " +
//				"<viewRefreshMode>onStop</viewRefreshMode>            " +
//				"<viewRefreshTime>1</viewRefreshTime>         " +
//				"</Url>      " +
//				"</NetworkLink>      <LookAt>         <longitude>-37.23141714255796</longitude>" +
//				"<latitude>-7.074256298939872</latitude>" +
//				"        <altitude>0</altitude>" +
//				"         <range>455596.64940826077</range>" +
//				"         <tilt>0</tilt>" +
//				"         <heading>0</heading>" +
//				"         <altitudeMode>clampToGround</altitudeMode>" +
//				"      </LookAt>" +
//				"   </Folder>" +
//				"</kml>";
//		String str2 = "teste";
		response.getWriter().print(ourKml);
//		
//		byte[] bytes = ourKml.toString().getBytes();
//		outputStream.write(bytes);
//		
//		String bbox = request.getParameter("BBox");
//		str = "false";
//
//		if (bbox != null) {
//			str = "true";
//			saveState();
//			String[] coords = bbox.split(",");
//			for (int i = 0; i < coords.length; i++) {
//				System.out.println("coord " + i + ": " + coords[i]);
//			}
//			
//			Kml ourKml = new Kml();
//			ourKml.createAndSetPlacemark()
//			   .withName("London, UK").withOpen(Boolean.TRUE)
//			   .createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
//			ourKml.marshal(new File("HelloKml.kml"));
//			
//			ServletOutputStream outputStream = response.getOutputStream();
//			outputStream.print(ourKml.toString());
			
		
//		String bbox = request.getParameter("BBox");
//		if (bbox != null) {
//			String[] coords = bbox.split(",");
//			for (int i = 0; i < coords.length; i++) {
//				System.out.println("coord " + i + ": " + coords[i]);
//			}
//		}
//		FileReader reader = new FileReader(new File("C:/Hugo/Projetos/TCEPB/workspace/GeoPBMobile/web/geopbmobile2.kml"));
//		ServletOutputStream outputStream = response.getOutputStream();
//		int i;
//		for (i = reader.read(); i >= 0; i = reader.read()) {
//			outputStream.write(i);
//		}
//		outputStream.flush();
//		outputStream.close();
//		reader.close();
//		String url = "height=1024&"
//				+ "width=1024&"
//				+ "layers=tce%3Aobras_municipais&"
//				+ "request=GetMap&"
//				+ "service=wms&"
//				+ "styles=obras2Style&"
//				+ "format_options=SUPEROVERLAY%3Afalse%3BKMPLACEMARK%3Afalse%3BKMSCORE%3A40%3BKMATTR%3Atrue%3B&"
//				+ "srs=EPSG%3A4326&"
//				+ "format=application%2Fvnd.google-earth.kmz%2Bxml&"
//				+ "transparent=false&version=1.1.1";
	}

//	
//	public void destroy() {
//		super.destroy();
//		saveState();
//	}
//	
//	private void saveState() {
//		FileWriter fileWriter = null;
//	    PrintWriter printWriter = null;
//	    try {                                                            
//	      fileWriter = new FileWriter(new File(getServletContext().getRealPath(getServletName())));
//	      printWriter = new PrintWriter(fileWriter);         
//	      printWriter.write(str);                                  
//	      printWriter.close();
//	      return;                                                        
//	    }                                                                
//	    catch (IOException e) {
//	    	
//	    }
//		
//	}

}
