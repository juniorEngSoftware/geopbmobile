package br.edu.ufcg.dsc.lsi.geopb.mobile.util;

public class GeoPBMobileMessageManager {

	public static final String NOT_DETAILED_BUILD = "não há detalhes para essa obra";
	public static final String KML_CONTENT_TYPE = "application/vnd.google-earth.kml+xml";
	public static final String URL_BBOX_OBRAS = "http://buchada.dsc.ufcg.edu.br:80/geoserver/wms?height=1024&width=1024&layers=tce:obras_municipais"
		+ "&request=GetMap&service=wms&styles=obras2Style&format_options=SUPEROVERLAY:false;KMPLACEMARK:false;KMSCORE:40;KMATTR:true;"
		+ "&srs=EPSG:4326&format=application/vnd.google-earth.kml+XML&transparent=false&version=1.1.1&bbox=-38.662,-7.36,-35.81,-6.784";
	
	public static String getFormattedDescription(String managementUnit, String buildNumber, String infos) {
		return infos
				+ "<br> "
				+ "<form name=\"formInfo\" action=\"http://buchada.dsc.ufcg.edu.br/GeoPBMobile/photographics.jsp\" target=\"_blank\">"
				+ "<input type=\"hidden\" name=unidadeGestora value=\"" + managementUnit + "\">"
				+ "<input type=\"hidden\" name=numeroObra value=\"" + buildNumber + "\">"
				+ "<input type=\"submit\" value=\"Visualizar Fotos da Obra\" +>"
				+ "</form>"
				+ "<form name=\"formDenuncia\" action=\"http://buchada.dsc.ufcg.edu.br/george/jsp/denuncia/denuncia.jsp?nuObra=" + buildNumber + "&uGestora=" + managementUnit + "\" target=\"_blank\">"
				+ "<input type=\"submit\" value=\"Realizar Denuncia\">"
				+ "</form>"
				+ "<form name=\"formUpload\" action=\"http://buchada.dsc.ufcg.edu.br/GeoPBMobile/auditoria.jsp\" target=\"_blank\">"
				+ "<input type=\"submit\" value=\"Auditoria\">"
				+ "</form>";
	}
	
	public static String getBuildDetailsRequest(String unidadeGestora, String numeroObra) {
		return "http://buchada.dsc.ufcg.edu.br/george/detalharObraXML?unidadeGestora="+ unidadeGestora + "&numeroObra=" + numeroObra;
	}
	
}
