package bas.nl.boterkaaseneiren;

public class View {
    public boolean winnaar = false;

    public String draw(Bord bord, Speler[] spelers, Speler currentSpeler) {
        String result = "";
        result += "<html>" + "<head>" + "<script>" + "function startNewSpel() {";
        result += "	  var spelerOne = document.getElementById(\"nameSpelerOne\").value;";
        result += "	  var spelerTwo = document.getElementById(\"nameSpelerTwo\").value;";
        result += "	  var params = \"?spelerOne=\"+spelerOne+\"&\"+\"spelerTwo=\"+spelerTwo;";
        result += " if (spelerOne && spelerTwo) {" + " return \"/new-game/\"+params; " + "} " + "return \"#\";" + "	}";
        result += "</script>" + "<style>" + " a { " + "	display: block; " + " width: 113px; " + " margin: auto; ";
        result += " } " + "p { " + " text-align: center;" + "}" + "h1 { " + " text-align: center;" + "}" + "td { ";
        result += " width: 100px; " + " height: 100px; " + " }" + " table { " + " margin: 5px auto; " + " }";
        result += " .vert { " + " border-left: 2px solid black; " + " border-right: 2px solid black; " + " } ";
        result += " .hori { " + " border-top: 2px solid black; " + " border-bottom: 2px solid black; " + " } ";
        result += "tr: { 1px solid black }";
        result += "td: { 1px solid black }";
        result += "</style>" + "</head>" + "<body>" + "<h1>Tic Tac Toe</h1>";
        result += printCurrentSpeler(currentSpeler);
        result += printScore(spelers);
        result += drawWinner(currentSpeler, bord);
        result += printBoard(bord);
        result += drawReset();
        result += "</html>";

        return result;
    }

    private String drawReset() {
        String result = "";
        if (!winnaar) {
            result = "<a href=\"?row=1&column=1&potje=1" + "\"><h3>Reset game?</h3></a>";
        }
        return result;
    }

    private String printBoard(Bord bord) {
        String result = "";

        if (!winnaar) {
            result += "</br>";

            result += "<table>";

            for (int row = 0; row < bord.getBoard().length; row++) {
                result += "<tr>";

                for (int column = 0; column < bord.getBoard()[row].length; column++) {
                    result += "<td class=\"vert hori\">";

                    if (bord.getBoard()[row][column] == null)
                        result += "<a href=\"?row=" + row + "&column=" + column + "&potje=0" + "\"><h1>|---|</h1></a>";
                    else if (bord.getBoard()[row][column] == Stuk.OPIECE)
                        result += "<a href=\"?row=" + row + "&column=" + column + "&potje=0" + "\"><h1>|-O-|</h1></a>";
                    else if (bord.getBoard()[row][column] == Stuk.XPIECE)
                        result += "<a href=\"?row=" + row + "&column=" + column + "&potje=0" + "\"><h1>|-X-|</h1></a>";

                    result += "</td>";

                }

                result += "</tr>";
            }

            result += "</table>";

        } else {
            result += "<a href=\"?row=1&column=1&potje=1" + "\"><h1>Nieuw potje?</h1></a>";
        }
        return result;

    }

    private String printScore(Speler[] spelers) {
        String result = "";

        result += "<h4>Score:";

        for (Speler speler : spelers) {
            result += "[" + speler.getName() + " : " + speler.getScore().getScore() + "]";
        }

        result += "</br>";

        return result;
    }

    private String printCurrentSpeler(Speler currentSpeler) {

        String result = "";

        if (!winnaar) {
            result += "<h2>Je bent aan de beurt <b>" + currentSpeler.getName() + "</b> Doe een zet!!</h2></br>";
        }
        return result;
    }

    public String drawWinner(Speler currentSpeler, Bord bord) {
        String result = "";
        Speler speler = currentSpeler;
        if (winnaar) {
            result = "De winaar is: " + speler.getName();
        }

        return result;
    }
}
