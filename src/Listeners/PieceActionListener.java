package Listeners;

import Components.Button;
import Game.Board;
import MenusGUI.BoardMenu;
import MenusGUI.GameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PieceActionListener  extends MouseAdapter {
    public PieceActionListener() {

    }

    Component firstEntered;
    Component lastEntered;


    public void mouseClicked(MouseEvent event) {

    }

    public void mouseEntered(MouseEvent event) {
        lastEntered = event.getComponent();
    }


    public void mouseExited(MouseEvent event) {

    }


    public void mousePressed(MouseEvent event) {
        firstEntered = event.getComponent();

        try {
            Board.getInstance().selectLocation((int)((Button) firstEntered).getMyLoc().getX()+1,(int)((Button) firstEntered).getMyLoc().getY()+1);
            BoardMenu.getInstance().availableMoves(event.getComponent());
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Invalid Action",JOptionPane.ERROR_MESSAGE);
        }

    }


    public void mouseReleased(MouseEvent event) {
        if (firstEntered == lastEntered){
            BoardMenu.getInstance().updateBoard();
            return;
        }
        try {
            if (firstEntered instanceof Button && lastEntered instanceof Button){
                Board.getInstance().movePiece((int)((Button) lastEntered).getMyLoc().getX()+1,(int)((Button) lastEntered).getMyLoc().getY()+1);
                BoardMenu.getInstance().updateBoard();
            }else
                throw new Exception("you have not dragged a piece or you released it to some invalid location");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null,exception.getMessage(),"Invalid Action",JOptionPane.ERROR_MESSAGE);
        }finally {
            BoardMenu.getInstance().updateBoard();
        }

    }
}
