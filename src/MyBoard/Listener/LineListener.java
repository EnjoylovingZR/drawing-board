package MyBoard.Listener;

import MyBoard.initFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineListener implements ActionListener {
    private initFrame initFrame;

    public LineListener(initFrame initFrame) {
        this.initFrame = initFrame;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("实线".equals(command)) {
            initFrame.flag = 0;
            System.out.println("实线" + initFrame.flag);
        } else if ("虚线".equals(command)) {
            initFrame.flag = 1;
            System.out.println("虚线" + initFrame.flag);
        } else if ("点线".equals(command)) {
            initFrame.flag = -1;
        }
    }
}
