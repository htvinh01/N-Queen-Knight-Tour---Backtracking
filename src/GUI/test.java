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
public class test extends JPanel {

    private int n, iCol, iRow, time;
    private JPanel chessBoard;
//    private static final String COLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final JButton[][] squares;// = new JButton[n][n];
    private final int[] Queen;

//Hàm khơi tạo để show lên bàn cờ lúc ban đầu
    public test(int n) {
        this.n = n;
        squares = new JButton[n][n];
        Queen = new int[n];
        init(n);
    }

//Hàm này có thêm 2 đối số để chạy thuật toán, thật ra là k cần đâu, tại muốn viết thêm cho nó sinh động
    public test(int n, int iRow, int iCol, int time) {

        this.n = n;
        this.iRow = iRow;
        this.iCol = iCol;
        this.time = 1000/time;
        squares = new JButton[n][n];
        Queen = new int[n];
        System.out.print(n);
        init(n);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        solveNQueen();
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
            chessBoard.add(new JLabel(""+(i+1), SwingConstants.CENTER));

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

    }
//kiểm tra queen có thỏa điều kiện trên column hoặc đường chéo chính hoặc đường chéo phụ

    public boolean check(int row, int column) {
        int d1 = column - row;
        int d2 = column + row;
        for (int i = 0; i < n; i++) {
            if (Queen[i] == column // Check column
                    || Queen[i] == d1 // Check diagonals
                    || Queen[i] == d2) {
                return false; // There is a conflict
            }
            d1++;
            d2--;
        }
        return true;
    }
//xét các quân hậu tại row 0 xem xem row đó có đặt đc queen k, nếu đặt k đc thì row+1 đệ quy lên để tìm tiếp

    public boolean search(int q) {
        if (q >= n) {
            JOptionPane.showMessageDialog(null, "Done", "Infor", JOptionPane.PLAIN_MESSAGE);
            return true;
        }
        if (Queen[q] != -n) {
            return search(q + 1);
        } else {
            for (int col = 0; col < n; col++) {
                squares[q][col].setBackground(Color.YELLOW);

                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (check(q, col)) {
                    Queen[q] = col;
            
                    squares[q][col].setBackground(Color.GREEN);

                    if (search(q + 1)) {
                        return true;
                    } else {
                        Queen[q] = -n;

                    }
                }
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ((col % 2 == 1 && q % 2 == 1) || (col % 2 == 0 && q % 2 == 0)) {

                    squares[q][col].setBackground(Color.WHITE);
                } else {

                    squares[q][col].setBackground(Color.BLACK);
                }

            }
        }

        return false;
    }
//đùng để hiện thị lên quân cờ đã đc đặt tại vị trí hợp lý trên bàn cờ

    public void printChess() {

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (Queen[row] == col) {
                    squares[row][col].setBackground(Color.GREEN);//setIcon(new ImageIcon(getClass().getResource("/Icons/11.png")));
                    System.out.printf("1   ");
                } else {
                    System.out.printf("0  ");
                }
                setQueen(iRow, iCol);
            }
            System.out.printf("\n");
        }
    }
//hàm xử lý thuật toán

    void solveNQueen() {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // reset board
        for (int j = 0; j < n; ++j) {

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Queen[j] = -n;
        }

        setQueen(iRow, iCol);
        //If solution exist print otherwise show error message
        new Thread(() -> {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!search(0)) {
                System.out.println("No Solution.\n");
                JOptionPane.showMessageDialog(this, "Sorry no solution for this queen position!\nPlease try again with other position!", "Sorry", JOptionPane.INFORMATION_MESSAGE);
            }
        }).start();

    }

    public JPanel getChessBoard() {
        return chessBoard;
    }
//hàm dùng để setQueen tại 1 vị trí bằng cách đánh dấu nó lại và set màu cho ô đã đc chọn

    public void setQueen(int iRow, int iCol) {
        Queen[iRow - 1] = (iCol - 1);
        //đoạn này nếu muốn thay cái màu bằng hình 
        ImageIcon img = new ImageIcon(getClass().getResource("/img/queen.png"));
        int width = img.getIconWidth() / 8;
        int height = img.getIconHeight() / 8;
        Image scaled = scaleImage(img.getImage(), width, height);
        ImageIcon scaledIcon = new ImageIcon(scaled);

        squares[iRow - 1][iCol - 1].setIcon(scaledIcon);

    }

//     public void setQueenIcon(int iRow, int iCol) {
//        //đoạn này nếu muốn thay cái màu bằng hình 
//         if ((iCol % 2 == 1 && iRow % 2 == 1) || (iCol % 2 == 0 && iRow % 2 == 0)) {
//                    squares[iRow][iCol].setBackground(Color.WHITE);
//                } else {
//                    squares[iRow][iCol].setBackground(Color.BLACK);
//                }
//        ImageIcon img = new ImageIcon(getClass().getResource("/Icons/Queen.png"));
//        int width = img.getIconWidth() ;
//        int height = img.getIconHeight();
//        Image scaled = scaleImage(img.getImage(), width, height);
//        ImageIcon scaledIcon = new ImageIcon(scaled);
//
//        squares[iRow][iCol].setIcon(scaledIcon);
//
//    }

    private Image scaleImage(Image image, int w, int h) {
        Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        return scaled;
    }

}
