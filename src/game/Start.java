package game;

import javax.swing.JFrame;

public abstract class Start extends JFrame {
    protected final String uiCode;

    public Start(String id, String title) {
        super(title);
        this.uiCode = id;
    }

    public abstract void initLayout();
}
