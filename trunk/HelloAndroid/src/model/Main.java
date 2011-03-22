package model;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String novosCB = "http://dl.dropbox.com/u/8923123/novosCheckBox.xml";
		String features = "http://dl.dropbox.com/u/8923123/features.xml";
		
		XStream xstream = new XStream();
				
		URL url = new URL(features);
		URLConnection connection = url.openConnection();
		InputStream input = connection.getInputStream();
		
		List outraLista = (List) xstream.fromXML(input);
		
		
		for (int i = 0; i < outraLista.size(); i++) {
			Feature feature = (Feature) outraLista.get(i);
			System.out.println(feature.toString());
			System.out.println();
		}
		
	}

}
