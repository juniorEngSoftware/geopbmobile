<%@ page language="java"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="javax.xml.parsers.DocumentBuilder"%>
<%@ page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="br.edu.ufcg.dsc.lsi.geopb.mobile.util.GeoPBMobileUtil"%>
<%
	String unity = request.getParameter("unidadeGestora");
	String number = request.getParameter("numeroObra");
	String requestFotosUrl = "http://buchada.dsc.ufcg.edu.br/george/createXMLPhotos.action?unidadeGestora=" + unity +"&numeroObra=" + number;
	InputStream input = GeoPBMobileUtil.getStreamOfConnection(requestFotosUrl);
	
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(input);
    
    
    NodeList nl = doc.getElementsByTagName("photo");
    
%>
<html>
	<head>
		<title>Fotografias da Obra </title>
	</head>

	<body>
	<%

	for(int i = 0; i < nl.getLength(); i++) {
		NodeList dateNlc = doc.getElementsByTagName("data");
		Element dateElements = (Element)dateNlc.item(i);
		String dateTagValue = dateElements.getChildNodes().item(0).getNodeValue();
		
		NodeList imageNlc = doc.getElementsByTagName("source");
		Element imageElements = (Element)imageNlc.item(i);
		String imageTagValue = imageElements.getChildNodes().item(0).getNodeValue();
		
		out.println("<p><img src =\"" + imageTagValue + "\" </img><br>");
		out.println("Data da Obra: " + dateTagValue + "</p>");    

	}
	%>

	</body>
</html>