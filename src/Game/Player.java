package Game;

import Menus.User;
import Pieces.Piece;

import java.util.ArrayList;

public class Player {
    private User user;
    private int color;
    private boolean moved;
    private int undoLeft;
    private Piece king;
    private boolean undoThisTurn;
    private ArrayList<Move> moves;

    public Player(User user, int color,int undoLeft) {
        this.user = user;
        this.color = color;
        this.undoLeft = undoLeft;
        moved = false;
        moves = new ArrayList<Move>();
    }

    public boolean isUndoThisTurn() {
        return undoThisTurn;
    }

    public void setUndoThisTurn(boolean undoThisTurn) {
        this.undoThisTurn = undoThisTurn;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public Piece getKing() {
        return king;
    }

    public void setKing(Piece king) {
        this.king = king;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public User getUser() {
        return user;
    }

    public int getColor() {
        return color;
    }


    public int getUndoLeft() {
        return undoLeft;
    }

    public void undo() {
        undoLeft--;
        moves.remove(moves.size() - 1);
    }

    public void movePiece(Move move) {
        moves.add(move);
    }


}
