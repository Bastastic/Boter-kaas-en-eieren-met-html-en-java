package bas.nl.boterkaaseneiren;

public class Score {
    private int currentScore;

    public Score() {
        currentScore = 0;
    }

    public void countScore() {
        currentScore++;
    }

    public int getScore() {
        return currentScore;
    }
}
