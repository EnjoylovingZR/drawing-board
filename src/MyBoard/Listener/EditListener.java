package MyBoard.Listener;

import MyBoard.initFrame;
import MyBoard.Shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditListener implements ActionListener {

    public ArrayList<MyBoard.Shape.Shape> list;
    public ArrayList<Shape> trash;
    public initFrame initFrame;

    public EditListener(initFrame initFrame) {
        this.list = initFrame.list;
        this.trash = initFrame.trash;
        this.initFrame = initFrame;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("撤销".equals(command)) {
            if (list.size() > 0) {
                String temp = list.get(list.size() - 1).stamp;
                while (list.size() >= 0 && list.get(list.size() - 1).stamp == temp) {
                    list.remove(list.size() - 1);
                }
                initFrame.panelcenter.paint((Graphics2D) initFrame.panelcenter.getGraphics());
            } else {
                JOptionPane.showMessageDialog(null, "撤回到头啦");
            }
        } else if ("清屏".equals(command)) {
            list.clear();
            initFrame.panelcenter.paint((Graphics2D) initFrame.panelcenter.getGraphics());
        }

    }
}
