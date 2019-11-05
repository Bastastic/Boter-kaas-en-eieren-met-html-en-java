package bas.nl.boterkaaseneiren;

import bas.nl.http.Server;

import java.io.IOException;

public class App {

    public App() {

        Stuk p = Stuk.OPIECE;
        p.getStuk();
    }

    public static void main(String[] args) throws IOException {
        Server httpServer = new Server(8080);
        httpServer.startServer(new Spel());

//		new App();

    }

    public static void clearScreen() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }
}
