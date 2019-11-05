package bas.nl.boterkaaseneiren;

import java.util.Map;
import java.util.Scanner;

public class Spel {

    public Bord bord;
    public Speler[] spelers;
    public Speler currentSpeler;
    public View view;
    private Scanner scan = new Scanner(System.in);

    public Spel() {
        spelers = new Speler[2];
    }

    public void setSpelers(String spelerOne, String spelerTwo) {
        spelers[0] = new Speler(spelerOne, Stuk.XPIECE);
        spelers[1] = new Speler(spelerTwo, Stuk.OPIECE);
    }

    public Speler getCurrentSpeler() {
        return currentSpeler;
    }

    public Bord getBord() {
        return bord;
    }

    public String getSpelView() {
        return view.draw(bord, spelers, currentSpeler);
    }

    public void startSpel() {
        currentSpeler = spelers[0];
        bord = new Bord(3, 3);
        view = new View();
    }
    public void tijdensSpel(Map<String, String> params) throws InvalidMoveException {
        boolean eersteBeurt = false;
        int[] move = null;
        try {
            move = currentSpeler.doMove(params);
        } catch (NumberFormatException nfe) {
            //do nothing
        } catch (NullPointerException npe) {
            //do nothing
        }

        if (checkNieuw(params)) {
            clear();
            eersteBeurt = true;
        }
        if (getValidSpelerMove(move) && !eersteBeurt) {
            getBord().updateBoard(move, getCurrentSpeler().getStuk());
        }
        if(!checkWinner()){
            swapSpelerTurn();
        }
    }


    public void swapSpelerTurn() {
        if (currentSpeler == spelers[0])
            currentSpeler = spelers[1];
        else
            currentSpeler = spelers[0];
    }

    private boolean getValidSpelerMove(int [] resultaat) {
        int[] result = null;
        boolean validMove = false;

            try {
                result = resultaat;
                if (bord.isValidMove(result))
                    return true;
            } catch (NumberFormatException nfe) {
                //do nothing
            } catch (InvalidMoveException ime) {
                System.out.println(ime.getMessage());
            } finally {
                System.out.println("");
            }


        return false;
    }


    public boolean checkWinner() {
        Stuk[][] bord = this.bord.getBoard(); // [row][column]
        Stuk currentSpelerStuk = currentSpeler.getStuk();

        if (bord[0][0] == currentSpelerStuk && bord[0][1] == currentSpelerStuk && bord[0][2] == currentSpelerStuk) {
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else if (bord[1][0] == currentSpelerStuk && bord[1][1] == currentSpelerStuk && bord[1][2] == currentSpelerStuk){
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else if (bord[2][0] == currentSpelerStuk && bord[2][1] == currentSpelerStuk && bord[2][2] == currentSpelerStuk){
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else if (bord[0][0] == currentSpelerStuk && bord[1][0] == currentSpelerStuk && bord[2][0] == currentSpelerStuk){
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else if (bord[0][1] == currentSpelerStuk && bord[1][1] == currentSpelerStuk && bord[2][1] == currentSpelerStuk){
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else if (bord[0][2] == currentSpelerStuk && bord[1][2] == currentSpelerStuk && bord[2][2] == currentSpelerStuk){
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else if (bord[0][0] == currentSpelerStuk && bord[1][1] == currentSpelerStuk && bord[2][2] == currentSpelerStuk){
            view.winnaar = true;
            currentSpeler.getScore().countScore();
            return true;
        }
        else return bord[0][2] == currentSpelerStuk && bord[1][1] == currentSpelerStuk && bord[2][0] == currentSpelerStuk;
    }

    public void clear(){
        view.winnaar = false;
        bord = new Bord(3, 3);
    }

    public boolean checkNieuw(Map<String, String> params){
        try {
            if (Integer.parseInt(params.get("potje")) == 1) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            return  false;
        } catch (NullPointerException npe){
            return false;
        }
        return false;
    }
}
