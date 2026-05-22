package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public Window(String id) {
        super(id, "Tic Tac Toe"); 
        p1 = new Player("P-1", "Rafi", "X");
        p2 = new Player("P-2", "Rijby", "O");
        activeTurn = p1;

        engineLogic = new MainLogic(boxes);
        initLayout();
    }

    @Override
    public void initLayout() {
        this.setSize(450, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.setBackground(new Color(139, 0, 0)); // Matching crimson theme color shading

        turnLabel = new JLabel(activeTurn.getName() + "'s Turn", SwingConstants.CENTER);
        turnLabel.setFont(new Font("Arial", Font.BOLD, 28));
        turnLabel.setForeground(Color.BLACK);

        scoreLabel = new JLabel(p1.getName() + " : " + p1.getScore() + "  |  " + p2.getName() + " : " + p2.getScore(), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        scoreLabel.setForeground(Color.BLACK);

        topPanel.add(turnLabel);
        topPanel.add(scoreLabel);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 3));
        centerPanel.setBackground(Color.DARK_GRAY);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int r = i;
                final int c = j;
                boxes[r][c] = new JButton("");
                boxes[r][c].setFont(new Font("Arial", Font.BOLD, 70));
                boxes[r][c].setForeground(new Color(178, 34, 34)); 
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
        
        System.out.println("Match engine started. Active user instances created: " + Player.countPlayers);
        this.setVisible(true);
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
}
