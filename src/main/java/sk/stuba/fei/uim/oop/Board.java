package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private int[] x1;
    private int[] y1;
    private int highAndWidthOfBoard = 8;
    private PlayBoard p;
    private Player player;
    private Computer computer;
    private final Label sizeOfTheBoard;
    private final JLabel numberOfPlayersStones;
    private final JLabel numberOfComputersStones;
    private final MyFrame myFrame;
    private final JRadioButton sizeOf6x6;
    private final JRadioButton sizeOf8x8;
    private final JRadioButton sizeOf10x10;
    private final JRadioButton sizeOf12x12;
    private int playerOnTurn = 1;

    /*
    player has black color witch is indicated by number 1
    computer has white color witch is indicated by number 2
    and free spaces where you can place a rock are indicated by number 3
     */


    public Board(JRadioButton sizeOf6x6, JRadioButton sizeOf8x8, JRadioButton sizeOf10x10, JRadioButton sizeOf12x12, MyFrame myFrame, Label sizeOfTheBoard, JLabel numberOfPlayersStones, JLabel numberOfComputersStone) {
        this.myFrame = myFrame;
        this.sizeOfTheBoard = sizeOfTheBoard;
        this.numberOfPlayersStones = numberOfPlayersStones;
        this.numberOfComputersStones = numberOfComputersStone;


        this.sizeOf6x6 = sizeOf6x6;
        this.sizeOf8x8 = sizeOf8x8;
        this.sizeOf10x10 = sizeOf10x10;
        this.sizeOf12x12 = sizeOf12x12;

        addActionListeners();
        addMapAndPlayer();
    }

    public void start() throws InterruptedException {
        int blackStones;
        blackStones = numberOfBlackStones();
        if (playerOnTurn == 1 ) {
            player.playersMove(highAndWidthOfBoard,p);
            repaint();
            if (numberOfSpacesToPlaceRock() > 0) {
                player.flipWhiteStones(highAndWidthOfBoard,p);
                if (blackStones < numberOfBlackStones()) {
                    playerOnTurn = 2;
                    resetMoves();
                }
            } else {
                playerOnTurn = 2;
                System.out.println("Black player could not make a move");
            }
        }
        repaint();
        Thread.sleep(10);
        if (playerOnTurn == 2) {
            computer.computersMove(highAndWidthOfBoard,p);
            repaint();
            playerOnTurn = 1;
        }
        repaint();
        resetMoves();

    }

    public void addActionListeners() {
        sizeOf6x6.addActionListener(this);
        sizeOf8x8.addActionListener(this);
        sizeOf10x10.addActionListener(this);
        sizeOf12x12.addActionListener(this);

        addMouseListener(this);
        addMouseMotionListener(this);

    }

    protected void addMapAndPlayer() {
        p = new PlayBoard(highAndWidthOfBoard);
        player = new Player();
        computer = new Computer();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int y = 0; y < highAndWidthOfBoard; y++) {
            for (int x = 0; x < highAndWidthOfBoard; x++) {
                if (p.getPlayBoard(x, y) == 0 || p.getPlayBoard(x, y) == 2 || p.getPlayBoard(x, y) == 1 || p.getPlayBoard(x, y) == 3) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * 43, y * 43, 42, 42);
                }
                if (p.getPlayBoard(x, y) == 2) {
                    g.setColor(Color.WHITE);
                    g.fillOval(x * 43 + 3, y * 43 + 3, 35, 35);
                }
                if (p.getPlayBoard(x, y) == 1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(x * 43 + 3, y * 43 + 3, 35, 35);
                }
                if (p.getPlayBoard(x, y) == 3) {
                    g.setColor(Color.GRAY);
                    g.drawOval(x * 43 + 3, y * 43 + 3, 35, 35);
                }
                if (!(player.getMouseXPosition() == 0 && player.getMouseYPosition() == 0) && p.getPlayBoard(player.getMouseXPosition(), player.getMouseYPosition()) == 3) {
                    g.setColor(Color.RED);
                    g.fillOval(player.getMouseXPosition() * 43 + 18, player.getMouseYPosition() * 43 + 18, 6, 6);
                }
                if (p.getPlayBoard(x, y) == 4) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * 43, y * 43 + 38, 42, 5);
                }
                if (p.getPlayBoard(x, y) == 5) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * 43 + 38, y * 43, 5, 42);
                }
                if (p.getPlayBoard(x, y) == 7) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * 43, y * 43, 5, 42);
                }if (p.getPlayBoard(x, y) == 8) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * 43, y * 43, 42, 5);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getXAndYPositionOfFreeSpaceInArray();
        for (int i = 0; i < numberOfSpacesToPlaceRock(); i++) {
            if (e.getX() > x1[i] * 43 && e.getX() < (x1[i] + 1) * 43 && e.getY() > (y1[i]) * 43 && e.getY() < (y1[i] + 1) * 43 && p.getPlayBoard(x1[i], y1[i]) == 3) {
                p.setPlayBoard(x1[i], y1[i], 1);
                player.setxForPlayerRock(x1[i]);
                player.setyForPlayerRock(y1[i]);
                repaint();
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {
        int helperForXArray;
        int helperForYArray;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 3) {
                    helperForXArray = e.getX() / 43;
                    helperForYArray = e.getY() / 43;
                    player.setMouseXPosition(helperForXArray);
                    player.setMouseYPosition(helperForYArray);
                    repaint();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sizeOf6x6) {
            highAndWidthOfBoard = 8;
            myFrame.setSize(361, 438);
            sizeOfTheBoard.setText("6x6");
            this.reset();
        }
        if (e.getSource() == sizeOf8x8) {
            highAndWidthOfBoard = 10;
            myFrame.setSize(448, 525);
            sizeOfTheBoard.setText("8x8");
            this.reset();
        }
        if (e.getSource() == sizeOf10x10) {
            highAndWidthOfBoard = 12;
            myFrame.setSize(533, 610);
            sizeOfTheBoard.setText("10x10");
            this.reset();
        }
        if (e.getSource() == sizeOf12x12) {
            highAndWidthOfBoard = 14;
            myFrame.setSize(619, 696);
            sizeOfTheBoard.setText("12x12");
            this.reset();
        }
    }

    public void reset() {
        this.addMapAndPlayer();
        repaint();
        playerOnTurn = 1;

    }
    public int getFreeSpacesOnBoard() {
        int getFreeSpacesOnBoard = 0;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 0) {
                    getFreeSpacesOnBoard++;
                }
            }
        }
        return getFreeSpacesOnBoard;
    }
    public void getXAndYPositionOfFreeSpaceInArray() {
        x1 = new int[numberOfSpacesToPlaceRock()];
        y1 = new int[numberOfSpacesToPlaceRock()];
        int a = 0;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 3) {
                    x1[a] = x;
                    y1[a] = y;
                    a++;
                }

            }
        }
    }

    public int numberOfBlackStones() {
        int black = 0;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 1) {
                    black++;
                }
            }
        }
        return black;
    }
    public int numberOfWhiteStones(){
        int white = 0;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 2) {
                    white++;
                }
            }
        }
        return white;
    }
    public void labelOfNumbersOfStones() {
        int blackStones=0;
        int white = 0;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 1) {
                    blackStones++;
                    numberOfPlayersStones.setText("B: " + blackStones);
                } else if (p.getPlayBoard(x, y) == 2) {
                    white++;
                    numberOfComputersStones.setText("W: " + white);
                }
            }
        }
    }
    public int numberOfSpacesToPlaceRock() {
        int numberOfSpacesToPlaceRock=0;
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if (p.getPlayBoard(x, y) == 3) {
                    numberOfSpacesToPlaceRock++;
                }
            }
        }
        return numberOfSpacesToPlaceRock;
    }

    public void resetMoves(){
        for (int x = 0; x < highAndWidthOfBoard; x++) {
            for (int y = 0; y < highAndWidthOfBoard; y++) {
                if(p.getPlayBoard(x,y)==3){
                    p.setPlayBoard(x,y,0);
                }
            }
        }
        repaint();}
}

