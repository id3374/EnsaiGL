package app.server;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import app.client.GreetingService;
import app.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
GreetingService {

	public  String googleScrap(String recherche) {
		try {

			// On se connecte au site et on ch6arge le document html
			Document doc = Jsoup.connect("https://www.google.fr/search?client=ubuntu&channel=fs&q="+recherche).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
			Element nbResultats=doc.select("div#resultStats").first();
			String nbRes=nbResultats.text().replace("\u00a0","").replaceAll("[^\\d.]", ""); //le nb de résultats google, le 2e replace garde que les caractères de 0 à 9 d'une chaîne String
			return(nbRes);
		}

		catch(IOException e){
			return("pb connection");
		}
	}

}
