package bas.nl.boterkaaseneiren;

import java.util.Map;
import java.util.Scanner;

public class Speler {

    private String name;
    private Stuk stuk;
    private Score score;

    public Speler(String name, Stuk stuk) {
        this.name = name;
        this.name = this.name.replace("<", "").replace(">", "").replace("/", "").replace("!", "").replace("#", "").replace("{", "").replace("}", "").replace(";", "");
        this.stuk = stuk;

        score = new Score();
    }

    public String getName() {
        return name;
    }

    public int[] doMove(Map<String, String> params) throws NumberFormatException {
        int row = Integer.parseInt(params.get("row"));
        int column = Integer.parseInt(params.get("column"));

        int[] result = {row, column};
        return result;
    }

    public Score getScore() {
        return score;
    }

    public Stuk getStuk() {
        return stuk;
    }

    public boolean exitSpel(Scanner in) {
        System.out.println("Wil je het spel afsluiten?");
        return Boolean.parseBoolean(in.nextLine());
    }
}
