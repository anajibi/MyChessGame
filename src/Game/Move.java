package Game;

import Pieces.Piece;

public class Move {
    private Piece destroyedPiece;
    private boolean pieceKilled;
    private String output;
    private String killOutput;

    public Move(int[] from, int[] to, String piece, Piece destroyedPiece, boolean pieceKilled) {
        this.destroyedPiece = destroyedPiece;
        if (destroyedPiece == null)
            output = piece + " " + from[0] + "," + getConverted(from[1]) + " to " + to[0] + "," + getConverted(to[1]);
        else {
            output = piece + " " + from[0] + "," + getConverted(from[1]) + " to " + to[0] + "," + getConverted(to[1]) + " destroyed " + destroyedPiece.toString();
            killOutput = destroyedPiece.toString() + " killed in spot " + to[0] + "," + to[1];
        }
    }

    private String getConverted(int i) {
        switch (i) {
            case 8:
                return "H";
            case 7:
                return "G";
            case 6:
                return "F";
            case 5:
                return "E";
            case 4:
                return "D";
            case 3:
                return "C";
            case 2:
                return "B";
            default:
                return "A";
        }
    }

    @Override
    public String toString() {
        return output;
    }

    public boolean pieceKilled() {
        return pieceKilled;
    }

    public void setPieceKilled(boolean pieceKilled) {
        this.pieceKilled = pieceKilled;
    }

    public Piece getDestroyedPiece() {
        return destroyedPiece;
    }

    public String getKillOutput() {
        return killOutput;
    }
}


