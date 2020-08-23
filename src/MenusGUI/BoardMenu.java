/*
 * Created by JFormDesigner on Sat May 23 18:00:19 IRDT 2020
 */

package MenusGUI;

import Components.Button;
import Game.Board;
import Listeners.PauseListener;
import Listeners.PieceActionListener;
import Pieces.Location;
import Resources.Locator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author unknown
 */
public class BoardMenu extends JFrame {
    private static BoardMenu Instance;
    private final int boardDimension = 560;
    private final int buttonSize = 70;
    private final int defaultTimer = 60 * 60 * 1000;
    private final int showKillsAll = 0;
    private final int showMovesAll = 1;
    private final int showMoves = 3;
    private final int showKills = 2;
    private Button[][] buttons = new Button[8][8];
    private Thread[] threads = new Thread[2];
    private int whiteCounter = defaultTimer;
    private int blackCounter = defaultTimer;
    private boolean hasTimer;
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JComboBox comboBox1;
    private JTextField whitePlayer;
    private JTextField blackPlayer;
    private JLabel undoLeftLable;
    private JButton undoButton;
    private JButton forfeit;
    private JButton nextTurn;
    private JButton pausePlayBttn;
    private JLabel whiteTimer;
    private JLabel blackTimer;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;

    public BoardMenu(boolean hasTimer) {
        Instance = this;
        this.hasTimer = hasTimer;
        initComponents();
        initComboBox();
        initFrame();
        initButtons();
        initUsersLabels();
        initUndoLabel();
        addActionListener();
        initTimers(hasTimer);
    }

    public static BoardMenu getInstance() {
        return Instance;
    }

    private void initUndoLabel() {
        undoLeftLable.setText(Board.getInstance().undoLeft());
    }

    private void initUsersLabels() {
        blackPlayer.setText("Black: " + Board.getInstance().getPlayersArr()[0].getUser().getUsername());
        whitePlayer.setText("White: " + Board.getInstance().getPlayersArr()[1].getUser().getUsername());
    }

    private void initTimers(boolean hasTimer) {
        if (!hasTimer) {
            whiteTimer.setVisible(false);
            blackTimer.setVisible(false);
            whitePlayer.setBackground(new Color(88, 241, 52));
        } else {
            threads[1] = new Thread(() -> createTimer(whiteTimer, whiteCounter));
            threads[0] = new Thread(() -> createTimer(blackTimer, blackCounter));
            threads[1].start();
            whiteTimer.setText(normalTime(whiteCounter));
            blackTimer.setText(normalTime(blackCounter));
        }

    }

    private void createTimer(JLabel jLabel, int counter) {
        while (true) {
            jLabel.setBackground(new Color(88, 241, 52));
            jLabel.setOpaque(true);
            long start = System.currentTimeMillis();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            counter -= System.currentTimeMillis() - start;
            jLabel.setText(normalTime(counter));
            if (counter <= 0) {
                gameFinishedWithTimer();
                Thread.currentThread().stop();
            }
        }
    }

    private void gameFinishedWithTimer() {
        if (!Board.getInstance().GameIsFinished()) {
            if (Board.getInstance().getTurn() == 1)
                JOptionPane.showMessageDialog(this, "Game has finished and Player With Color White won the game.");
            else
                JOptionPane.showMessageDialog(this, "Game has finished and Player With Color Black won the game.");
            Board.getInstance().gameIsFinished();
        }
    }

    private String normalTime(int counter) {
        return String.format("%02d:%02d", (counter / (1000 * 60)), (counter % (1000 * 60) / 1000));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        comboBox1 = new JComboBox();
        whitePlayer = new JTextField("", 0);
        blackPlayer = new JTextField("", 0);
        whitePlayer.setEditable(false);
        blackPlayer.setEditable(false);
        undoLeftLable = new JLabel("", 0);
        undoButton = new JButton();
        forfeit = new JButton();
        nextTurn = new JButton();
        pausePlayBttn = PauseListener.getInstance().getBtn();
        whiteTimer = new JLabel("", 0);
        blackTimer = new JLabel("", 0);
        label2 = new JLabel("", 0);
        label3 = new JLabel("", 0);
        label4 = new JLabel("", 0);
        label5 = new JLabel("", 0);
        label6 = new JLabel("", 0);
        label7 = new JLabel("", 0);
        label8 = new JLabel("", 0);
        label9 = new JLabel("", 0);
        label10 = new JLabel("", 0);
        label11 = new JLabel("", 0);
        label12 = new JLabel("", 0);
        label13 = new JLabel("", 0);
        label14 = new JLabel("", 0);
        label15 = new JLabel("", 0);
        label16 = new JLabel("", 0);
        label17 = new JLabel("", 0);

        //======== this ========
        var contentPane = getContentPane();

        //======== panel1 ========
        {

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
            );
            panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                            .addGap(0, 553, Short.MAX_VALUE)
            );
        }

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textArea1);
        }

        //---- whitePlayer ----
        whitePlayer.setText("White: ");

        //---- blackPlayer ----
        blackPlayer.setText("Black: ");

        //---- undoLeftLable ----
        undoLeftLable.setText("Undo left: ");

        //---- undoButton ----
        undoButton.setText("Undo");

        //---- forfeit ----
        forfeit.setText("Forfeit");

        //---- nextTurn ----
        nextTurn.setText("Next Turn");


        //---- whiteTimer ----
        whiteTimer.setToolTipText("White player Timer");
        whiteTimer.setFont(new Font("Segoe UI Semilight", Font.BOLD, 18));

        //---- blackTimer ----
        blackTimer.setToolTipText("Black player Timer");
        blackTimer.setFont(new Font("Segoe UI Semilight", Font.BOLD, 18));

        //---- label2 ----
        label2.setText("H");

        //---- label3 ----
        label3.setText("G");

        //---- label4 ----
        label4.setText("F");

        //---- label5 ----
        label5.setText("E");

        //---- label6 ----
        label6.setText("D");

        //---- label7 ----
        label7.setText("C");

        //---- label8 ----
        label8.setText("B");

        //---- label9 ----
        label9.setText("A");

        //---- label10 ----
        label10.setText("1");

        //---- label11 ----
        label11.setText("2");

        //---- label12 ----
        label12.setText("3");

        //---- label13 ----
        label13.setText("5");

        //---- label14 ----
        label14.setText("4");

        //---- label15 ----
        label15.setText("7");

        //---- label16 ----
        label16.setText("6");

        //---- label17 ----
        label17.setText("8");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                        .addGap(12, 12, 12)
                                                        .addGroup(contentPaneLayout.createParallelGroup()
                                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(undoLeftLable, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(forfeit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(nextTurn, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(whiteTimer, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                                                .addComponent(whitePlayer, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                .addComponent(blackTimer, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                                                                .addComponent(blackPlayer, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)))))
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(label10, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label11, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label12, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label14, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label13, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label16, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label15, GroupLayout.Alignment.TRAILING)
                                        .addComponent(label17, GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addGroup(contentPaneLayout.createSequentialGroup()
                                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(pausePlayBttn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(comboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pausePlayBttn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(undoLeftLable, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(undoButton))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(forfeit)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextTurn)
                                                .addGap(41, 41, 41)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(whiteTimer, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blackTimer, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(whitePlayer)
                                                        .addComponent(blackPlayer)))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                                .addComponent(label17, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label15, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label16, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label13, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label14, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label12, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label11, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(label10, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label6, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label7, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(label9, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private void initComboBox() {
        comboBox1.addItem("Show Kills -all");
        comboBox1.addItem("Show Moves -all");
        comboBox1.addItem("Show Kills ");
        comboBox1.addItem("Show Moves");
        comboBox1.addActionListener(actionEvent -> updateMoves());
    }

    private void initFrame() {
        setTitle("Easy Chess");
        super.setIconImage(new ImageIcon(new Locator().getURL("Icons\\icons8-queen-48.png")).getImage());
        try {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            //UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[0].getClassName());
            Dimension size = new Dimension(900, 650);
            setPreferredSize(size);
            setResizable(false);
            this.setMinimumSize(size);
            this.setMaximumSize(size);
        } catch (/*ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException*/Exception e) {
            e.printStackTrace();
        }
    }

    private void initButtons() {
        MouseAdapter ms = new PieceActionListener();
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {

                Button currButton = new Button(null, new Location(Math.abs(j - 7), i, null));
                buttons[Math.abs(j - 7)][i] = currButton;
                currButton.setIcon(null);
                currButton.addMouseListener(ms);
                setBackground(currButton);
                currButton.setBounds(i * buttonSize, j * buttonSize, buttonSize, buttonSize);
                panel1.add(currButton);
            }
        }
        updateBoard();
    }

    public void addActionListener() {
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Board.getInstance().undo();
                    initUndoLabel();
                    updateBoard();
                } catch (Exception exception) {
                    showError(exception.getMessage());
                }
            }
        });
        forfeit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, Board.getInstance().forfeit(), "Game is finished", JOptionPane.INFORMATION_MESSAGE);
                Board.getInstance().gameIsFinished();
            }
        });
        nextTurn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nextTurn();
            }
        });
    }

    private void nextTurn() {
        try {
            Board.getInstance().nextTurn();
            if (hasTimer) {
                threads[~Board.getInstance().getTurn()+2].suspend();
                blackTimer.setBackground(null);
                whiteTimer.setBackground(null);
                if (threads[Board.getInstance().getTurn()].getState().equals(Thread.State.NEW))
                    threads[Board.getInstance().getTurn()].start();
                else
                    threads[Board.getInstance().getTurn()].resume();
            } else {
                Board.getInstance().nextTurn();
                swapBgColor();
            }
            updateBoard();
        } catch (Exception exception) {
            showError(exception.getMessage());
        }
    }

    private void swapBgColor() {
        Color color = blackPlayer.getBackground();
        blackPlayer.setBackground(whitePlayer.getBackground());
        whitePlayer.setBackground(color);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Invalid action", JOptionPane.ERROR_MESSAGE);
    }

    public void updateBoard() {
        if (Board.getInstance().GameIsFinished()) {
            if (!Board.getInstance().getWinner().equals("Draw"))
                JOptionPane.showMessageDialog(this, "Game has finished and Player With Color " + Board.getInstance().getWinner() + " Won the game, Better Luck next time.");
            else
                JOptionPane.showMessageDialog(this, "Game has finished with a draw");
            try {
                UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
            } catch (Exception e) {
            }
            Board.getInstance().gameIsFinished();
            return;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setButtonIcon(Board.getInstance().getBoard()[i][j], buttons[i][j]);
                setBackground(buttons[i][j]);
            }
        }
        updateMoves();
        initUndoLabel();

    }


    private void updateMoves() {
        switch (comboBox1.getSelectedIndex()) {
            case showMovesAll:
                textArea1.setText(Board.getInstance().showMovesAll());
                break;
            case showKills:
                textArea1.setText(Board.getInstance().showKilled());
                break;
            case showKillsAll:
                textArea1.setText(Board.getInstance().showKilledAll());
                break;
            case showMoves:
                textArea1.setText(Board.getInstance().showMoves());
                break;
        }
    }

    private void setButtonIcon(Location location, Button button) {
        if (location.getPiece() == null)
            button.setIcon(null);
        else
            button.setIcon(location.getPiece().getIcon());
    }

    public void setBackground(Button button) {
        button.setOpaque(true);
        if (((int) button.getMyLoc().getX() + (int) button.getMyLoc().getY()) % 2 == 0)
            button.setBackground(new Color(59, 59, 59));
        else
            button.setBackground(Color.white);
    }

    public void availableMoves(Component component) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.getInstance().canMoveTo(i, j))
                    buttons[i][j].setBackground(new Color(150, 243, 147, 255));
            }
        }
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
