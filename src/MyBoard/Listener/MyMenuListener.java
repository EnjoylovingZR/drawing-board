package MyBoard.Listener;

import MyBoard.Bubble;
import MyBoard.initFrame;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class MyMenuListener implements MenuListener {

    public initFrame initFrame;

    public MyMenuListener(initFrame initFrame) {
        this.initFrame = initFrame;
    }

    @Override
    public void menuSelected(MenuEvent e) {

        if (e.getSource().toString().contains("颜色")) {
            Color selected = JColorChooser.showDialog(this.initFrame, "颜色选择", Color.white);
            this.initFrame.c = selected;
        } else if (e.getSource().toString().contains("线宽")) {
            Integer w;
            w = Integer.parseInt(JOptionPane.showInputDialog("输入线宽"));
            this.initFrame.w = w;
        } else if (e.getSource().toString().contains("字号")) {
            Integer f;
            f = Integer.parseInt(JOptionPane.showInputDialog("输入字号"));
            this.initFrame.f = f;
        } else if (e.getSource().toString().contains("弹性泡泡")) {

            if (!this.initFrame.popStatus) {
                this.initFrame.popStatus = !this.initFrame.popStatus;
                this.initFrame.trash = this.initFrame.list;
                this.initFrame.list.clear();
                initFrame.panelcenter.paint((Graphics2D) initFrame.panelcenter.getGraphics());
            } else {
                for (Bubble bubble : initFrame.bubbles) {
                    bubble.getjButton().setVisible(false);
                    bubble.setRUN_STATE(false);
                }
                this.initFrame.bubbles.clear();
                this.initFrame.popStatus = !this.initFrame.popStatus;
                this.initFrame.list = this.initFrame.trash;
                this.initFrame.trash.clear();
                //initFrame.panelcenter.paint((Graphics2D)initFrame.panelcenter.getGraphics());
            }
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
