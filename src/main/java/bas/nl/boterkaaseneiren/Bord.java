package bas.nl.boterkaaseneiren;

public class Bord {

    private Stuk[][] bord;

    public Bord(int column, int row) {
        bord = new Stuk[column][row];

    }

    public Stuk[][] getBoard() {
        return bord;
    }

    public boolean isValidMove(int[] move) throws InvalidMoveException {
        try {
			return bord[move[0]][move[1]] == null;
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new InvalidMoveException(aioobe.getMessage());
        }
    }


    public void updateBoard(int[] move, Stuk stuk) {
        bord[move[0]][move[1]] = stuk;
    }
}
