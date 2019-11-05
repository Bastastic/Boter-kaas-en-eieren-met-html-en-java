package bas.nl.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpServer;

import bas.nl.boterkaaseneiren.Spel;

public class Server {

	private int portNumber;
	private HttpServer httpServer;
	
	public Server(int portNumber) {
		this.portNumber = portNumber;
	}

	public void startServer(Spel g) throws IOException {
		httpServer = HttpServer.create(new InetSocketAddress(portNumber), 0);

		setContextPages(g);

		httpServer.start();
	}

	private void setContextPages(Spel g) {
		httpServer.createContext("/", new IndexHandler());
		httpServer.createContext("/static/", new IndexHandler());
		httpServer.createContext("/new-game/", new NieuwSpelHandler(g));
		
		httpServer.setExecutor(null); // creates a default executor
	}

	/**
	 * returns the url parameters in a map
	 * 
	 * @param query
	 * @return map
	 */
	public static Map<String, String> queryToMap(String query) {
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split("&")) {
			String pair[] = param.split("=");
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}

}
