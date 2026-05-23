
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

    private boolean isGameStarted = false;
    private Image startImg;
    
    private Font customFont = new Font("Impact", Font.PLAIN, 14); 
    private final Color themeRed = new Color(220, 20, 20); 

    public Window(String id) {
        super(id, "Tic Tac Toe"); 
        p1 = new Player("P-1", "Rafi", "X");
        p2 = new Player("P-2", "Rijby", "O");
        activeTurn = p1;

        try {
            startImg = new ImageIcon(getClass().getResource("starting.png")).getImage();
        } catch (Exception e) {
            System.out.println("Starting image load failed!");
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
                if (!isGameStarted && (e.getKeyCode() == KeyEvent.VK_A)) {
                    isGameStarted = true;
                    showGameInterface(); 
                }
            }
        });
        this.setFocusable(true);

        StartPanel startPanel = new StartPanel();
        this.add(startPanel);

        System.out.println("Match engine started. Active user instances created: " + Player.countPlayers);
        this.setVisible(true);
    }

    private void showGameInterface() {
        this.getContentPane().removeAll(); 
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(themeRed); 

        turnLabel = new JLabel(activeTurn.getName() + "'s Turn", SwingConstants.CENTER);
        turnLabel.setFont(customFont.deriveFont(Font.BOLD, 36f)); 
        turnLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel(p1.getName() + " : " + p1.getScore() + "  |  " + p2.getName() + " : " + p2.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(customFont.deriveFont(Font.PLAIN, 18f));
        scoreLabel.setForeground(Color.WHITE);

        topPanel.add(turnLabel);
        topPanel.add(scoreLabel);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 3));
        centerPanel.setBackground(themeRed); 

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int r = i;
                final int c = j;
                boxes[r][c] = new JButton("");
                boxes[r][c].setFont(customFont.deriveFont(Font.BOLD, 90f)); 
                boxes[r][c].setForeground(themeRed); 
                boxes[r][c].setBackground(Color.WHITE);
                boxes[r][c].setFocusPainted(false);
                boxes[r][c].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (boxes[r][c].getText().equals("")) {
                            boxes[r][c].setText(activeTurn.getSign());
                            if (engineLogic.checkWinner(activeTurn.getSign())) {
                                activeTurn.updateScore();
                                JOptionPane.showMessageDialog(null, "Match finished! " + activeTurn.getName() + " wins!");
                                refreshGame();
                            } else if (engineLogic.checkDraw()) {
                                JOptionPane.showMessageDialog(null, "Match finished! The layout is a Draw!");
                                refreshGame();
                            } else {
                                activeTurn = (activeTurn == p1) ? p2 : p1;
                                turnLabel.setText(activeTurn.getName() + "'s Turn");
                            }
                        }
                    }
                });
                centerPanel.add(boxes[r][c]);
            }
        }
        this.add(centerPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void refreshGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boxes[i][j].setText("");
            }
        }
        activeTurn = p1; 
        turnLabel.setText(activeTurn.getName() + "'s Turn");
        scoreLabel.setText(p1.getName() + " : " + p1.getScore() + "  |  " + p2.getName() + " : " + p2.getScore());
    }

    private class StartPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (startImg != null) {
                g2d.drawImage(startImg, 0, 0, 600, 600, this);
            } else {
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, 600, 600);
            }

            g2d.setFont(customFont.deriveFont(Font.BOLD, 30f));
            g2d.setColor(themeRed);

            String text = "Press A to start the game";
            int stringWidth = g2d.getFontMetrics().stringWidth(text);
            int x = (600 - stringWidth) / 2;
            
            g2d.drawString(text, x, 500); 
        }
    }
}