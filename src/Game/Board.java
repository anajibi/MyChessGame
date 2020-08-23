package Game;

import MenusGUI.GameMenu;
import MenusGUI.MainMenu;
import Pieces.Location;
import Pieces.Piece;

import java.util.ArrayList;

public class Board {
    private static Board Instance;
    private Location[][] board;
    private int selectedX, selectedY;
    private boolean pieceSelected;
    private Player[] playersArr;
    private int turn;
    private int movesDone;
    private int[] previousPosition;
    private ArrayList<Move> killedMoves;
    private ArrayList<Move> allMoves;
    private int xPointer, yPointer;
    private boolean gameIsFinished;
    private int movesLimit;
    private String winner;

    public Board(Player blackPlayer, Player whitePlayer, int limit) {
        Instance = this;
        movesLimit = limit;
        playersArr = new Player[2];
        gameIsFinished = false;
        pieceSelected = false;
        playersArr[1] = whitePlayer;
        playersArr[0] = blackPlayer;
        turn = 1;
        movesDone = 0;
        killedMoves = new ArrayList<Move>();
        allMoves = new ArrayList<Move>();
        previousPosition = new int[2];
        board = new Location[8][8];
        int j = 0;
        for (int i = 0; i < 2; i++) {
            if (i == 1)
                j = 6;
            board[i + j][0] = new Location(i + j, 0, new Piece(Piece.WHITE - i, Piece.ROCK));
            board[i + j][1] = new Location(i + j, 1, new Piece(Piece.WHITE - i, Piece.KNIGHT));
            board[i + j][2] = new Location(i + j, 2, new Piece(Piece.WHITE - i, Piece.BISHOP));
            board[i + j][3] = new Location(i + j, 3, new Piece(Piece.WHITE - i, Piece.QUEEN));
            board[i + j][4] = new Location(i + j, 4, new Piece(Piece.WHITE - i, Piece.KING));
            board[i + j][5] = new Location(i + j, 5, new Piece(Piece.WHITE - i, Piece.BISHOP));
            board[i + j][6] = new Location(i + j, 6, new Piece(Piece.WHITE - i, Piece.KNIGHT));
            board[i + j][7] = new Location(i + j, 7, new Piece(Piece.WHITE - i, Piece.ROCK));
        }
        j = 0;
        for (int i = 1; i < 3; i++) {
            if (i == 2)
                j = 4;
            for (int k = 0; k < 8; k++) {
                board[i + j][k] = new Location(i + j, k, new Piece(Piece.WHITE - i + 1, Piece.PAWN));
            }
        }

        for (int i = 2; i < 6; i++) {
            for (int k = 0; k < 8; k++) {
                board[i][k] = new Location(i, k, null);
            }

        }
        playersArr[0].setKing(board[7][4].getPiece());
        playersArr[1].setKing(board[0][4].getPiece());
    }

    public static Board getInstance() {
        return Instance;
    }

    public void selectLocation(int x, int y) {
        if (x < 1 || y < 1 || x > 8 || y > 8)
            throw new RuntimeException("wrong coordination");
        else if (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor() != playersArr[turn].getColor())
            throw new RuntimeException("you can only select one of your pieces");
        else if (board[x - 1][y - 1].getPiece() == null)
            throw new RuntimeException("no piece on this spot");
        else {

            selectedX = x;
            selectedY = y;
            pieceSelected = true;
        }
    }

    public boolean isGameIsFinished() {
        return gameIsFinished;
    }



    public void movePiece(int x, int y) {
        if (playersArr[turn].isMoved())
            throw new RuntimeException("already moved");
        else if (x < 1 || y < 1 || x > 8 || y > 8)
            throw new RuntimeException("wrong coordination");
        else if (!pieceSelected)
            throw new RuntimeException("do not have any selected piece");
        else if ((x == selectedX && y == selectedY) || !board[selectedX - 1][selectedY - 1].getPiece().
                canMoveTo(x - 1, y - 1, selectedX - 1, selectedY - 1, this) ||
                (x == selectedX && y == selectedY))
            throw new RuntimeException("cannot move to the spot");
        else if (board[x - 1][y - 1].getPiece() != null) {
            if (board[x - 1][y - 1].getPiece().getColor() == playersArr[turn].getColor())
                throw new RuntimeException("cannot move to the spot");
            Move move = new Move(new int[]{selectedX, selectedY}, new int[]{x, y}, board[selectedX - 1][selectedY - 1].getPiece().toString(), board[x - 1][y - 1].getPiece(), true);
            killMove(x, y, move);
        } else {
            Move move = new Move(new int[]{selectedX, selectedY}, new int[]{x, y}, board[selectedX - 1][selectedY - 1].getPiece().toString(), null, false);
            pieceMoved(move, x, y);

        }
    }

    public void killMove(int x, int y, Move move) {
        board[x - 1][y - 1].getPiece().setAlive(false);
        move.setPieceKilled(true);
        killedMoves.add(move);
        pieceMoved(move, x, y);
        board[x - 1][y - 1].getPiece().setAlive(false);

    }

    public void pieceMoved(Move move, int x, int y) {
        playersArr[turn].movePiece(move);
        allMoves.add(move);
        board[selectedX - 1][selectedY - 1].getPiece().moveToSquare(board[x - 1][y - 1], board[selectedX - 1][selectedY - 1]);
        movesDone++;
        playersArr[turn].setMoved(true);
        setPreviousPosition(selectedX, selectedY);
        xPointer = x;
        yPointer = y;
    }

    public void nextTurn() {
        if (!playersArr[turn].isMoved())
            throw new RuntimeException("you must move then proceed to next turn");
        else {
            movesLimit--;
            playersArr[turn].setUndoThisTurn(false);
            pieceSelected = false;
            playersArr[turn].setMoved(false);
            turn = (~turn + 2);

            if (allMoves.get(allMoves.size() - 1).getDestroyedPiece() != null && killedMoves.get(killedMoves.size() - 1).getDestroyedPiece().getRole().equals(Piece.KING)) {
                if (killedMoves.get(killedMoves.size() - 1).getDestroyedPiece().getColor() == Piece.WHITE) {
                    //System.out.println(String.format("player %s with color black won", playersArr[0].getUser().getUsername()));
                    playerWin(Piece.BLACK);
                    winner = "Black";
                } else {
                    //System.out.println(String.format("player %s with color white won", playersArr[1].getUser().getUsername()));
                    playerWin(Piece.WHITE);
                    winner = "White";
                }
                gameIsFinished = true;
                return;
            }
            if (0 == movesLimit) {
                playerDraw();
                winner="Draw";
                gameIsFinished = true;
                return;
            }
        }
    }

    public void playerWin(int winner) {
        playersArr[winner].getUser().getScoreBoard().win();
        playersArr[winner].getUser().getScoreBoard().changeScore(3);
        playersArr[~winner + 2].getUser().getScoreBoard().lose();
    }

    public void playerDraw() {
        playersArr[Piece.BLACK].getUser().getScoreBoard().draw();
        playersArr[Piece.BLACK].getUser().getScoreBoard().changeScore(1);
        playersArr[Piece.WHITE].getUser().getScoreBoard().draw();
        playersArr[Piece.WHITE].getUser().getScoreBoard().changeScore(1);
    }

    public void undo() {
        if (playersArr[turn].getUndoLeft() == 0)
            throw new RuntimeException("you cannot undo anymore");
        else if (!playersArr[turn].isMoved())
            throw new RuntimeException("you must move before undo");
        else if (playersArr[turn].isUndoThisTurn())
            throw new RuntimeException("you have used your undo for this turn");
        else {
            undoMove(xPointer, yPointer);
        }
    }

    private void undoMove(int x, int y) {
        movesDone--;
        playersArr[turn].setUndoThisTurn(true);
        board[x - 1][y - 1].getPiece().undoMove(board[previousPosition[0] - 1][previousPosition[1] - 1], board[x - 1][y - 1]);
        if (allMoves.get(movesDone).pieceKilled()) {
            board[x - 1][y - 1].setPiece(allMoves.get(movesDone).getDestroyedPiece());
            allMoves.get(movesDone).getDestroyedPiece().setAlive(true);
            killedMoves.remove(killedMoves.size() - 1);
        } else {
            board[x - 1][y - 1].setPiece(null);
        }
        allMoves.remove(movesDone);
        playersArr[turn].undo();
        xPointer = previousPosition[0];
        yPointer = previousPosition[1];
        playersArr[turn].setMoved(false);
    }

    public void setPreviousPosition(int x, int y) {
        previousPosition[0] = x;
        previousPosition[1] = y;
    }

    public String undoLeft() {
        return "Undo left: " + playersArr[turn].getUndoLeft();
    }

    public String showMoves() {
        String output = "Your moves:\n";
        for (int i = 0; i < playersArr[turn].getMoves().size(); i++) {
            output += (i + 1) + ". " + playersArr[turn].getMoves().get(i).toString() + "\n";
        }
        return output;
    }

    public String showMovesAll() {
        String output = "All moves:\n";
        for (int i = 0; i < allMoves.size(); i++) {
            output += (i + 1) + ". " + allMoves.get(i).toString() + "\n";
        }
        return output;
    }

    public String showKilled() {
        String output = "show killed moves:\n";
        for (int i = 0; i < playersArr[~turn + 2].getMoves().size(); i++) {
            if (playersArr[~turn + 2].getMoves().get(i).pieceKilled())
                output += (i + 1) + ". " + playersArr[~turn + 2].getMoves().toString() + "\n";
        }
        return output;
    }

    public String showKilledAll() {
        String output = "All killed moves:\n";
        for (int i = 0; i < killedMoves.size(); i++) {
            if (killedMoves.get(i).pieceKilled())
                output += (i + 1) + ". " + killedMoves.get(i).toString() + "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++)
                output += board[i][j].toString() + "|";
            output += "\n";
        }
        return output;
    }

    public String forfeit() {
        playersArr[turn].getUser().getScoreBoard().lose();
        playersArr[turn].getUser().getScoreBoard().changeScore(-1);
        playersArr[~turn + 2].getUser().getScoreBoard().win();
        playersArr[~turn + 2].getUser().getScoreBoard().changeScore(2);
        gameIsFinished = true;
        return "you have forfeited\n"
                + String.format("player %s with color %s won", playersArr[~turn + 2].getUser().getUsername(), playersArr[~turn + 2].getColor() == 1 ? "white" : "black");

    }

    public Location[][] getBoard() {
        return board;
    }

    public boolean GameIsFinished() {
        return gameIsFinished;
    }

    public ArrayList<Move> getAllMoves() {
        return allMoves;
    }

    public Player[] getPlayersArr() {
        return playersArr;
    }

    public void setPlayersArr(Player[] playersArr) {
        this.playersArr = playersArr;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean canMoveTo(int x, int y) {
      /*  if ((location.getX() == button.getMyLoc().getX() && location.getY() == button.getMyLoc().getY()) || board[(int) button.getMyLoc().getX()][(int) button.getMyLoc().getY()].getPiece().
                canMoveTo((int) location.getX(), (int) location.getY(), (int) button.getMyLoc().getX(), (int) button.getMyLoc().getY(), this))
            return false;
        else if (location.getPiece() != null && location.getPiece().getColor() == playersArr[turn].getColor())
            return false;
        return true;*/

        if ((x == selectedX-1 && y == selectedY-1) || !board[selectedX - 1][selectedY - 1].getPiece().
                canMoveTo(x , y , selectedX - 1, selectedY - 1, this) ||
                (x == selectedX-1 && y == selectedY-1))
            return false;
        else if (board[x ][y ].getPiece() != null) {
            if (board[x ][y ].getPiece().getColor() == playersArr[turn].getColor())
                return false;

        }
        return true;
    }
    public void gameIsFinished(){
        MenusGUI.BoardMenu.getInstance().setVisible(false);
        MenusGUI.BoardMenu.getInstance().dispose();
        MenusGUI.GameMenu.getNewInstance();
        GameMenu.getInstance().setVisible(true);
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
