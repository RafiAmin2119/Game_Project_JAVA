package game;

import javax.swing.JButton;

public class MainLogic {
    private JButton[][] boxes;

    public MainLogic(JButton[][] boxes) {
        this.boxes = boxes;
    }

    public boolean checkWinner(String mark) {

        for (int i = 0; i < 3; i++) {
            if (boxes[i][0].getText().equals(mark) && boxes[i][1].getText().equals(mark) && boxes[i][2].getText().equals(mark)) return true;
            if (boxes[0][i].getText().equals(mark) && boxes[1][i].getText().equals(mark) && boxes[2][i].getText().equals(mark)) return true;
        }

        if (boxes[0][0].getText().equals(mark) && boxes[1][1].getText().equals(mark) && boxes[2][2].getText().equals(mark)) return true;
        if (boxes[0][2].getText().equals(mark) && boxes[1][1].getText().equals(mark) && boxes[2][0].getText().equals(mark)) return true;

        return false;
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boxes[i][j].getText().equals("")) return false;
            }
        }
        return true;
    }
}
