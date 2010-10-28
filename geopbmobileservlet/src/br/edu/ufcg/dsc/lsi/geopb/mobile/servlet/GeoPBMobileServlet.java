package br.edu.ufcg.dsc.lsi.geopb.mobile.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Link;
import de.micromata.opengis.kml.v_2_2_0.LookAt;
import de.micromata.opengis.kml.v_2_2_0.NetworkLink;
import de.micromata.opengis.kml.v_2_2_0.ViewRefreshMode;




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
		
		response.setContentType("application/vnd.google-earth.kml+xml");
		
		Kml ourKml = new Kml();
		
		NetworkLink networkLink = new NetworkLink();
		networkLink.setName("tce:obras_municipais");
		networkLink.setOpen(true);
		networkLink.setVisibility(true);
		
		Link link = new Link();
		link.setHref("![CDATA[http://buchada.dsc.ufcg.edu.br:80/geoserver/wms?height=1024&width=1024&layers=tce:obras_municipais&request=GetMap" +
				"&service=wms&styles=obras2Style&format_options=SUPEROVERLAY:false;KMPLACEMARK:false;KMSCORE:40;KMATTR:true;" +
				"&srs=EPSG:4326&format=application/vnd.google-earth.kmz+xml&transparent=false&version=1.1.1]");
		link.setViewRefreshMode(ViewRefreshMode.ON_STOP);
		link.setViewRefreshTime(1);
		
		networkLink.setUrl(link);
		
		LookAt lookAt = new LookAt();
		lookAt.withLongitude(37.23141714255796);
		lookAt.withLatitude(-7.074256298939872);
		lookAt.withAltitude(0);
		lookAt.setRange(455596.64940826077);
		lookAt.setTilt(0);
		lookAt.setHeading(0);
		lookAt.setAltitudeMode(AltitudeMode.CLAMP_TO_GROUND);
		
		Folder folder = new Folder();
		folder.addToFeature(networkLink);
		folder.setAbstractView(lookAt);
		
		ourKml.setFeature(folder);
		ourKml.marshal(response.getOutputStream());
		
	
	}


}
