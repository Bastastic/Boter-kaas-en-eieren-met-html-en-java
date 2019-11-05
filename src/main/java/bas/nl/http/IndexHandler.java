package bas.nl.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class IndexHandler implements HttpHandler {

	private String page(Map<String, String> params) {
		StringBuilder result = new StringBuilder();
		
		result.append("<html>"
				+ "<head>"
				+ "<script>"
				+ "function startNewSpel() {"
				+ "	  var spelerOne = document.getElementById(\"nameSpelerOne\").value;"
				+ "	  var spelerTwo = document.getElementById(\"nameSpelerTwo\").value;"
				+ "	  var params = \"?spelerOne=\"+spelerOne+\"&\"+\"spelerTwo=\"+spelerTwo;"
				+ " if (spelerOne && spelerTwo) {"
				+ " return \"/new-game/\"+params; "
				+ "} "
				+ "return \"#\";"
				+ "	}"
				+ "</script>"
				+ "<style>"
				+ " a { "
				+ "	display: block; "
				+ " width: 113px; "
				+ " margin: auto; "
				+ " } "
				+ "p { "
				+ " text-align: center;"
				+ "}"
				+ "h1 { "
				+ " text-align: center;"
				+ "}"
				+ "td { "
				+ " width: 100px; "
				+ " height: 100px; "
				+ " }"
				+ " table { "
				+ " margin: 5px auto; "
				+ " }"
				+ " .vert { "
				+ " border-left: 2px solid black; "
				+ " border-right: 2px solid black; "
				+ " } "
				+ " .hori { "
				+ " border-top: 2px solid black; "
				+ " border-bottom: 2px solid black; "
				+ " } "
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ "<h1>Tic Tac Toe</h1>"
				+ "<p>Speler one: <input id=\"nameSpelerOne\" type=\"text\"/></p>"
				+ "<p>Speler two: <input id=\"nameSpelerTwo\" type=\"text\"/></p>"
				+ "<table>"
				+ "  <tr>"
				+ "    <td></td>"
				+ "    <td class=\"vert\"></td>"
				+ "    <td></td>"
				+ "  </tr>"
				+ "  <tr>"
				+ "    <td class=\"hori\"></td>"
				+ "    <td class=\"vert hori\"></td>"
				+ "    <td class=\"hori\"></td>"
				+ "  </tr>"
				+ "  <tr>"
				+ "    <td></td>"
				+ "    <td class=\"vert\"></td>"
				+ "    <td></td>"
				+ "  </tr>"
				+ "</table>"
				+ "<a href=\"javascript:document.location.href=startNewSpel();\">new game</a>"
				+ "</body>"
				+ "</html>");

		return result.toString();
	}
	
	@Override
	public void handle(HttpExchange t) throws IOException {
		Map<String, String> params = null;
		try {
			params = Server.queryToMap(t.getRequestURI().getQuery());
		} catch (Exception e) {
			//do nothing
		}
		
		String response = page(params);
		
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}
