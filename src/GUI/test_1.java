/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author CRIS17
 */
public class test_1 extends JPanel {

    private int n, iCol, iRow, time;
    private JPanel chessBoard;
//    private static final String COLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final JButton[][] squares;// = new JButton[n][n];
    private int[][] nextSquare;
    private int count;
    private Knight k;
    private int preCol = -1, preRow = -1;
//Hàm khơi tạo để show lên bàn cờ lúc ban đầu

    public test_1(int n) {
        this.n = n;
        this.count = 0;
        squares = new JButton[n][n];
        nextSquare = new int[n][2];
        init(n);
    }

//Hàm này có thêm 2 đối số để chạy thuật toán, thật ra là k cần đâu, tại muốn viết thêm cho nó sinh động
    public test_1(int n, int iRow, int iCol, int time) {

        this.n = n;
        this.iRow = iRow;
        this.iCol = iCol;
        this.time= time;
        int t = 5000/time;
        squares = new JButton[n][n];
        nextSquare = new int[n][2];
        System.out.print(t);
        init(n);

        diTuan(iRow,iCol,true,t);
        
    }

    //vẽ board và set các layout cần thiết
    public void init(int n) {
//Gridlayout để kẻ bảng tạo layout cho bàn cờ, grid thì nằm trong Gridbag dưới có giải thích bằng tiếng anh
        GridLayout lt = new GridLayout(0, n + 1) {
            /**
             * Override the preferred size to return the largest it can, in a
             * square shape. Must (must, must) be added to a GridBagLayout as
             * the only component (it uses the parent as a guide to size) with
             * no GridBagConstaint (so it is centered).
             */
            public final Dimension getPreferredSize() {
                Dimension d = this.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int) d.getWidth(), (int) d.getHeight());
                } else if (c != null
                        && c.getWidth() > d.getWidth()
                        && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w > h ? h : w);
                return new Dimension(s, s);
            }
        };

        chessBoard = new JPanel();
        chessBoard.setLayout(lt);
        chessBoard.setSize(819, 808);
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.BLACK)));
        Color ochre = new Color(255, 255, 204);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        //create the chess board squares
        Insets Margin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(Margin);
                //set size cho cái icon ở đây
                ImageIcon icon = new ImageIcon(new BufferedImage(50, 50, BufferedImage.TYPE_4BYTE_ABGR));
                b.setIcon(icon);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                squares[i][j] = b;

            }
        }
        //đoạn này để dánh dấu cái cột vs hàng cho board 
        chessBoard.add(new JLabel(""));
        for (int i = 0; i < n; i++) {
            chessBoard.add(new JLabel("" + (i + 1), SwingConstants.CENTER));

        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1), SwingConstants.CENTER));
                    default:
                        chessBoard.add(squares[i][j]);

                }
            }
        }
        for (int moveNumber = 0; moveNumber < nextSquare.length; moveNumber++) {
            nextSquare[moveNumber][0] = -1;
            nextSquare[moveNumber][1] = -1;
        }

    }

    public JButton getSquares(int row, int col) {
        if (isAlive(row, col)) {
            return squares[row][col];
        } else {
            return null;
        }
    }

    public boolean isAlive(int row, int col) {
        return (row >= 0 && col >= 0) && (row < n && col < n);
    }

    public void mark(int row, int col) {
        if (isAlive(row, col) && isDied(row, col)) {
            count++;
            squares[row][col].setIcon(k.getIcon());
            squares[row][col].setBackground(Color.LIGHT_GRAY);

            if (preRow != -1 && preCol != -1) {
                squares[preRow][preCol].setText("" + (count - 1));
            }
            preRow = row;
            preCol = col;
        }
    }

    public boolean isDied(int row, int col) {
        return !(squares[row][col].getBackground() == Color.LIGHT_GRAY);
    }

    private int getAbility(int row, int col) {
        int ability = 0;

        for (int moveNum = 0; moveNum < k.MAX_MOVE_NUM; moveNum++) {
            int nRow = row + k.xMove[moveNum];
            int nCol = col + k.yMove[moveNum];

            if (isAlive(nRow, nCol) && isDied(nRow, nCol)) {
                ability++;
            }
        }
        return ability;
    }

    private int getMinAbility(int[][] nextSquare) {
        int min = k.MAX_MOVE_NUM;

        for (int moveNum = 0; moveNum < k.MAX_MOVE_NUM; moveNum++) {
            int row = nextSquare[moveNum][0];
            int col = nextSquare[moveNum][1];

            if (isAlive(row, col) && isDied(row, col)) {
                min = Math.min(min, getAbility(row, col));
            }
        }
        return min;
    }

    private int getBestMove(int[][] nextSquare, boolean bestSquare) {
        int min = k.MAX_MOVE_NUM;
        int bestMove = -1;

        for (int moveNum = 0; moveNum < k.MAX_MOVE_NUM; moveNum++) {
            int nextRow = nextSquare[moveNum][0];
            int nextCol = nextSquare[moveNum][1];;

            if (isAlive(nextRow, nextCol) && isDied(nextRow, nextCol)) {
                int score = getAbility(nextRow, nextCol);

                if (score < min) {
                    min = score;
                    bestMove = moveNum;
                } else if (bestSquare && score == min) {

                    int[][] currOptimalNextSquare = k.nextDestination(nextSquare[bestMove][0], nextSquare[bestMove][1]);

                    int[][] tiedSquareNextSquare = k.nextDestination(nextSquare[bestMove][0], nextSquare[bestMove][1]);

                    if (getMinAbility(tiedSquareNextSquare) < getMinAbility(currOptimalNextSquare)) {
                        bestMove = moveNum;
                    }
                }
            }

        }

        return bestMove;
    }

    private boolean Move(boolean flag) {

        nextSquare = k.nextDestination();

        int bestMove = getBestMove(nextSquare, flag);

        if (bestMove <= - 1) {
            JOptionPane.showMessageDialog(null, "No next move!", "Finished", JOptionPane.PLAIN_MESSAGE);
        } else {
            squares[k.getCurrentRow()][k.getCurrentCol()].setIcon(null);
            if (k.move(bestMove)) {
                mark(nextSquare[bestMove][0], nextSquare[bestMove][1]);
                return true;
            }
        }
        return false;
    }

    public void diTuan(int iRow, int iCol, boolean flag, int time) {
        k = new Knight("/icons/knight.png", iRow, iCol);
        mark(iRow, iCol);

        new Thread(() -> {
            while (count < (n * n) && Move(flag)) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (count >= (n * n)) {
                JOptionPane.showMessageDialog(null, "Done!", "Full Tour", JOptionPane.PLAIN_MESSAGE);
            }
        }).start();

    }
        public JPanel getChessBoard() {
        return chessBoard;
    }
}
