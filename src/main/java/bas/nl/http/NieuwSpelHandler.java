package bas.nl.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import bas.nl.boterkaaseneiren.*;

public class NieuwSpelHandler implements HttpHandler {
	private Spel g;
	public NieuwSpelHandler(Spel g) {
		this.g = g;
	}

	private String page(Map<String, String> params) {
		try {
            String result = g.getSpelView();
            return result;
        }
		catch (NullPointerException e){
        }
		return "";
	}

	public void setupSpelers(Map<String, String> params) {
		try{
	    g.setSpelers(params.get("spelerOne"), params.get("spelerTwo"));
		g.startSpel();}
		catch (NullPointerException npe){
        }
	}
	
	@Override
	public void handle(HttpExchange t) throws IOException {
		try {

			Map<String, String> params = null;
			try {
				params = Server.queryToMap(t.getRequestURI().getQuery());
			} catch (Exception e) {
				// do nothing
			}
            if(params != null) {
                if (g.getCurrentSpeler() == null) {
                    setupSpelers(params);
                } else {
                    try {
                        g.tijdensSpel(params);
                    } catch (Exception e){
                    }
                }
            }

			String response = page(params);

			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
