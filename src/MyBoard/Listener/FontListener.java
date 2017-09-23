package MyBoard.Listener;

import MyBoard.initFrame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FontListener implements ItemListener {
    public initFrame iF;

    public FontListener(initFrame frame) {
        this.iF = frame;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        iF.font = iF.fontName[iF.stytles.getSelectedIndex()];
    }
}
