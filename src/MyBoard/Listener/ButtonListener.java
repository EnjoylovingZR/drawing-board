package MyBoard.Listener;

import MyBoard.initFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    public initFrame iF;

    public ButtonListener(initFrame frame) {
        iF = frame;
    }

    public void actionPerformed(ActionEvent e) {

        Button bt = (Button) e.getSource();
        Color c = bt.getBackground();
        iF.c = c;
        iF.bt.setBackground(c);
    }

}
