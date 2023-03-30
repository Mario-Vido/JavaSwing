package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener, KeyListener {

   private final JButton resetButton;
   private final Board board;


   MyFrame() throws InterruptedException {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(361, 438);
      JPanel frame = new JPanel();
      frame.setLayout(new GridBagLayout());


      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.BOTH;
      c.weightx = 1;
      c.gridx = 0;
      c.gridy = 0;
      Label sizeOfTheBoard = new Label("6x6");
      frame.add(sizeOfTheBoard, c);
      c.gridx = 1;
      JRadioButton sizeOf6x6 = new JRadioButton("6x6");
      sizeOf6x6.setFocusable(false);
      frame.add(sizeOf6x6, c);
      c.gridx = 2;
      JRadioButton sizeOf8x8 = new JRadioButton("8x8");
      sizeOf8x8.setFocusable(false);
      frame.add(sizeOf8x8, c);
      c.gridx = 3;
      JRadioButton sizeOf10x10 = new JRadioButton("10x10");
      sizeOf10x10.setFocusable(false);
      frame.add(sizeOf10x10, c);
      c.gridx = 4;
      JRadioButton sizeOf12x12 = new JRadioButton("12x12");
      sizeOf12x12.setFocusable(false);
      frame.add(sizeOf12x12, c);

      c.gridx = 0;
      c.gridy = 1;
      this.resetButton = new JButton("reset");
      resetButton.setFocusable(false);
      resetButton.addActionListener(this);
      frame.add(resetButton, c);
      c.gridx = 2;
      JLabel numberOfPlayersStones = new JLabel();
      numberOfPlayersStones.setFocusable(false);
      frame.add(numberOfPlayersStones, c);
      c.gridx = 3;
      JLabel numberOfComputersStones = new JLabel();
      numberOfComputersStones.setFocusable(false);
      frame.add(numberOfComputersStones, c);
      c.gridx = 4;
      JLabel winner = new JLabel("Black");
      winner.setFocusable(false);
      frame.add(winner, c);

      ButtonGroup sizesOfBoard = new ButtonGroup();
      sizesOfBoard.add(sizeOf6x6);
      sizesOfBoard.add(sizeOf8x8);
      sizesOfBoard.add(sizeOf10x10);
      sizesOfBoard.add(sizeOf12x12);

      this.add(frame, BorderLayout.PAGE_START);
      board = new Board(sizeOf6x6, sizeOf8x8, sizeOf10x10, sizeOf12x12, this, sizeOfTheBoard, numberOfPlayersStones, numberOfComputersStones);

      this.add(board, BorderLayout.CENTER);
      this.addKeyListener(this);
      this.setLocationRelativeTo(null);
      this.setVisible(true);

      while (frame.isVisible()) {
            board.start();
            board.labelOfNumbersOfStones();
            winner.setText("black");
         while (board.getFreeSpacesOnBoard() == 0) {
            if (board.numberOfBlackStones() > board.numberOfWhiteStones()) {
               winner.setText("Winner black");
            } else if (board.numberOfWhiteStones() > board.numberOfBlackStones()) {
               winner.setText("Winner white");
            } else {
               winner.setText("Draw");
            }
         }
      }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource()==resetButton) {
         board.reset();
      }

   }
   @Override
   public void keyTyped(KeyEvent e) {

   }
   @Override
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode()==27){
         dispose();
      }
      if(e.getKeyCode()==82){
         board.reset();
      }
   }
   @Override
   public void keyReleased(KeyEvent e) {

   }
}
