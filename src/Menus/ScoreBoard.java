package Menus;

public class ScoreBoard implements Comparable {
    private String username;
    private int score;
    private int winNum;
    private int loseNum;
    private int drawNum;

    public ScoreBoard(String username, int score, int winNum, int loseNum, int drawNum) {
        this.username = username;
        this.score = score;
        this.winNum = winNum;
        this.loseNum = loseNum;
        this.drawNum = drawNum;
    }

    public int getScore() {
        return score;
    }

    public int getWinNum() {
        return winNum;
    }

    public int getDrawNum() {
        return drawNum;
    }

    @Override
    public String toString() {
        return String.format("Score:%d   Win num:%d   Draw num:%d   Lose num:%s", score,winNum , drawNum , loseNum);
    }

    @Override
    public int compareTo(Object o) {
        ScoreBoard object = (ScoreBoard) o;
        if (score != object.getScore())
            return object.getScore() - score;
        else if (winNum != object.getWinNum())
            return object.getWinNum() - winNum;
        else if (drawNum != object.getDrawNum())
            return object.getDrawNum() - drawNum;
        else if (loseNum != object.loseNum)
            return loseNum - object.loseNum;
        else
            return this.username.compareTo(object.username);
    }

    public void win() {
        winNum++;
    }

    public void lose() {
        loseNum++;
    }

    public void draw() {
        drawNum++;
    }

    public void changeScore(int temp) {
        score += temp;
    }
}
