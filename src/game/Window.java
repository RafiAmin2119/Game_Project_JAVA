package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends Start implements Restart {
    private JButton[][] boxes = new JButton[3][3];
    private Player p1;
    private Player p2;
    private Player activeTurn;
    private JLabel turnLabel;
    private JLabel scoreLabel;
    private MainLogic engineLogic;

    private int screenMode = 0; 
    private Image imgStart;
    private Image imgGameOver;
    
    private int totalMatches = 0;
    private Font myFont = new Font("Impact", Font.BOLD | Font.ITALIC, 14); 
    private Color mainRed = new Color(220, 20, 20); 

    public Window(String id) {
        super(id, "Tic Tac Toe"); 
        p1 = new Player("P-1", "Rafi", "X");
        p2 = new Player("P-2", "Rijby", "O");
        activeTurn = p1;

        try {
            imgStart = new ImageIcon(getClass().getResource("StartingImage.png")).getImage();
            imgGameOver = new ImageIcon(getClass().getResource("GameOver.png")).getImage();
        } catch (Exception e) {
            System.out.println("Image loading error!");
        }

        engineLogic = new MainLogic(boxes);
        initLayout();
    }

    @Override
    public void initLayout() {
        this.setSize(600, 600); 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (screenMode == 0 && e.getKeyCode() == KeyEvent.VK_A) {
                    screenMode = 1;
                    loadGameUi(); 
                } else if (screenMode == 2 && e.getKeyCode() == KeyEvent.VK_Z) {
                    totalMatches = 0;
                    screenMode = 1;
                    loadGameUi();
                }
            }
        });
        this.setFocusable(true);

        loadMenuScreen();

        System.out.println("Match engine started. Active user instances created: " + Player.countPlayers);
        this.setVisible(true);
    }

    private void loadMenuScreen() {
        this.getContentPane().removeAll();
        this.setLayout(new BorderLayout());
        MenuPanel menuPanel = new MenuPanel();
        this.add(menuPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    private void loadGameUi() {
        this.getContentPane().removeAll(); 
        this.setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new GridLayout(2, 1));
        topBar.setBackground(mainRed); 

        turnLabel = new JLabel(activeTurn.getName() + "'s Turn", SwingConstants.CENTER);
        turnLabel.setFont(myFont.deriveFont(42f)); 
        turnLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel(p1.getName() + " : " + p1.getScore() + "  |  " + p2.getName() + " : " + p2.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(myFont.deriveFont(20f)); 
        scoreLabel.setForeground(Color.WHITE);

        topBar.add(turnLabel);
        topBar.add(scoreLabel);
        this.add(topBar, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        gridPanel.setBackground(mainRed); 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                boxes[row][col] = new JButton("");
                boxes[row][col].setFont(myFont.deriveFont(90f)); 
                boxes[row][col].setForeground(mainRed); 
                boxes[row][col].setBackground(Color.WHITE);
                boxes[row][col].setFocusPainted(false);
                boxes[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (boxes[row][col].getText().equals("")) {
                            boxes[row][col].setText(activeTurn.getSign());
                            
                            if (engineLogic.checkWinner(activeTurn.getSign())) {
                                activeTurn.updateScore();
                                JOptionPane.showMessageDialog(null, "Match finished! " + activeTurn.getName() + " wins!");
                                totalMatches++;
                                checkRounds();
                            } else if (engineLogic.checkDraw()) {
                                JOptionPane.showMessageDialog(null, "Match finished! The layout is a Draw!");
                                totalMatches++;
                                checkRounds();
                            } else {
                                activeTurn = (activeTurn == p1) ? p2 : p1;
                                turnLabel.setText(activeTurn.getName() + "'s Turn");
                            }
                        }
                    }
                });
                gridPanel.add(boxes[row][col]);
            }
        }
        
        this.add(gridPanel, BorderLayout.CENTER);
        
        refreshGame(); 
        
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    private void checkRounds() {
        if (totalMatches >= 3) {
            screenMode = 2;
            loadMenuScreen();
            this.requestFocusInWindow();
        } else {
            refreshGame();
        }
    }

    @Override
    public void refreshGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boxes[i][j] != null) {
                    boxes[i][j].setText("");
                }
            }
        }
        activeTurn = p1; 
        if (turnLabel != null) turnLabel.setText(activeTurn.getName() + "'s Turn");
        if (scoreLabel != null) scoreLabel.setText(p1.getName() + " : " + p1.getScore() + "  |  " + p2.getName() + " : " + p2.getScore());
    }

    private class MenuPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            String txt = "";
            if (screenMode == 0) {
                if (imgStart != null) {
                    g2.drawImage(imgStart, 0, 0, 600, 600, this);
                } else {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(0, 0, 600, 600);
                }
                txt = "Press A to start the game";
            } else if (screenMode == 2) {
                if (imgGameOver != null) {
                    g2.drawImage(imgGameOver, 0, 0, 600, 600, this);
                } else {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(0, 0, 600, 600);
                }
                txt = "Press Z to restart the game"; 
            }

            g2.setFont(myFont.deriveFont(32f)); 
            g2.setColor(mainRed);

            int txtWidth = g2.getFontMetrics().stringWidth(txt);
            int posX = (600 - txtWidth) / 2;
            g2.drawString(txt, posX, 500); 
        }
    }
}