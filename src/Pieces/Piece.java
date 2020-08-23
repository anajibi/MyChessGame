package Pieces;


import Game.Board;
import Resources.Locator;


import javax.swing.*;

public class Piece {
    public static final int WHITE = 1;
    public static final int BLACK = 0;
    public static final String PAWN = "P";
    public static final String ROCK = "R";
    public static final String KNIGHT = "N";
    public static final String KING = "K";
    public static final String QUEEN = "Q";
    public static final String BISHOP = "B";

    private String role;
    private int color;
    private boolean isAlive;

    public Piece(int color, String role) {
        this.color = color;
        this.role = role;
        this.isAlive = true;
    }

    public int getColor() {
        return color;
    }

    public boolean canMoveTo(int destX, int destY, int currX, int currY, Board board) {
        if (destX == currX && destY == currY || (board.getBoard()[destX][destY].getPiece() != null && board.getBoard()[destX][destY].getPiece().getColor() == color))
            return false;
        switch (role) {
            case PAWN:
                return canMoveToPawn(destX, destY, currX, currY, board);
            case ROCK:
                return canMoveToRock(destX, destY, currX, currY, board);
            case KING:
                return canMoveToKing(destX, destY, currX, currY, board);
            case KNIGHT:
                return canMoveToKnight(destX, destY, currX, currY, board);
            case QUEEN:
                return canMoveToRock(destX, destY, currX, currY, board) || canMoveToBishop(destX, destY, currX, currY, board);
            case BISHOP:
                return canMoveToBishop(destX, destY, currX, currY, board);
        }
        return false;
    }

    public void moveToSquare(Location location, Location currLocation) {
        currLocation.setPiece(null);
        location.setPiece(this);
    }

    public void undoMove(Location destLocation, Location currLocation) {
        moveToSquare(destLocation, currLocation);
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        if (color == 1)
            return role + "w";
        else
            return role + "b";
    }

    public boolean pathIsEmptyPawn(int destX, int destY, int currX, int currY, Board board) {
        int dif = destX - currX;
        if (Math.abs(dif) == 2)
            return board.getBoard()[currX + (dif / 2)][currY].getPiece() == null && board.getBoard()[currX + dif][currY].getPiece() == null;
        else
            return board.getBoard()[currX + dif][currY].getPiece() == null;
    }

    public boolean canMoveToPawn(int destX, int destY, int currX, int currY, Board board) {
        int dif = destX - currX;
        if (board.getBoard()[destX][destY].getPiece() != null &&board.getBoard()[destX][destY].getPiece().getColor() == this.getColor() )
            return false;
        switch (color) {
            case WHITE:
                if (currX == 1) {
                    if (dif < 3 && (currY == destY) && 0 <dif) {
                        return pathIsEmptyPawn(destX, destY, currX, currY, board);
                    } else if (dif == 1 && Math.abs(currY - destY) == 1 && board.getBoard()[destX][destY].getPiece() != null) {
                        return true;
                    }
                } else {
                    if (dif == 1 && (currY == destY)) {
                        return pathIsEmptyPawn(destX, destY, currX, currY, board);
                    } else if (dif == 1 && Math.abs(currY - destY) == 1 && board.getBoard()[destX][destY].getPiece() != null) {
                        return true;
                    }
                }
                return false;
            case BLACK:
                if (currX == 6) {
                    if ( dif < 0 && -3 < dif && (currY == destY)) {
                        return pathIsEmptyPawn(destX, destY, currX, currY, board);
                    } else if (dif == -1 && Math.abs(currY - destY) == 1 && board.getBoard()[destX][destY].getPiece() != null) {
                        return true;
                    }
                } else {
                    if (dif == -1 && (currY == destY)) {
                        return pathIsEmptyPawn(destX, destY, currX, currY, board);
                    } else if (dif == -1 && Math.abs(currY - destY) == 1&& board.getBoard()[destX][destY].getPiece() != null) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }

    public boolean pathIsEmptyRock(int currX, int currY, Board board, int number, int xStep, int yStep) {
        for (int i = 1; i < number; i++) {
            if (board.getBoard()[currX + i * xStep][currY + i * yStep].getPiece() != null)
                return false;
        }
        return true;
    }

    public boolean canMoveToRock(int destX, int destY, int currX, int currY, Board board) {
        int dif;
        if (destX == currX) {
            dif = destY - currY;
            return pathIsEmptyRock(currX, currY, board, Math.abs(dif), 0, dif / Math.abs(dif));
        } else if (destY == currY) {
            dif = destX - currX;
            return pathIsEmptyRock(currX, currY, board, Math.abs(dif), dif / Math.abs(dif), 0);
        } else
            return false;

    }

    public boolean canMoveToBishop(int destX, int destY, int currX, int currY, Board board) {

        int dif = Math.abs(currX - destX);
        if (Math.abs(destX - currX) == Math.abs(destY - currY))
            if (destX < currX)
                if (destY < currY)
                    return pathIsEmptyBishop(dif, -1, -1, board, currX, currY);
                else
                    return pathIsEmptyBishop(dif, -1, 1, board, currX, currY);
            else {
                if (destY < currY)
                    return pathIsEmptyBishop(dif, 1, -1, board, currX, currY);
                else
                    return pathIsEmptyBishop(dif, 1, 1, board, currX, currY);
            }
        return false;
    }

    public boolean pathIsEmptyBishop(int number, int xStep, int yStep, Board board, int currX, int currY) {
        for (int i = 1; i < number; i++) {
            if (board.getBoard()[currX + xStep * i][currY + yStep * i].getPiece() != null)
                return false;
        }
        return true;
    }

    public boolean canMoveToKing(int destX, int destY, int currX, int currY, Board board) {
        return Math.abs(destX - currX) < 2 && Math.abs(destY - currY) < 2;
    }

    public boolean canMoveToKnight(int destX, int destY, int currX, int currY, Board board) {
        if (Math.abs(currX - destX) == 1 && Math.abs(currY - destY) == 2)
            return true;
        else return Math.abs(currX - destX) == 2 && Math.abs(currY - destY) == 1;
    }

    public String getRole() {
        return role;
    }
    public static Icon iconCreator(String role, String color){
        return new ImageIcon(new Locator().getURL("Images\\"+role+"-"+color+".png"));
    }

    public Icon getIcon() {
        String color ;
        if (this.color == Piece.BLACK)
            color = "black";
        else
            color = "white";
        return iconCreator(role,color);
    }
}

