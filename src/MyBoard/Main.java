package MyBoard;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        initFrame db = new initFrame();
        db.initFrame();

    }
}
