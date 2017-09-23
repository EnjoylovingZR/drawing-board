package MyBoard.Listener;

import MyBoard.initFrame;
import MyBoard.Shape.Shape;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileListener implements ActionListener {

    public initFrame paint;

    //构造方法
    public FileListener(initFrame paint) {
        this.paint = paint;
    }

    //监听器的具体实现逻辑
    public void actionPerformed(ActionEvent e) {

        //判断是哪个菜单项被按下
        String command = e.getActionCommand();
        if ("新建工程".equals(command)) {

            int value = JOptionPane.showConfirmDialog(null, "是否需要保存当前文件？", "提示信息", 0);
            if (value == 0) {
                saveFile();
            }
            if (value == 1) {
                paint.list.removeAll(paint.list);
                paint.panelcenter.repaint();
            }
        }
        else if ("打开工程".equals(command)) {
            int value = JOptionPane.showConfirmDialog(null, "是否需要保存当前文件？", "提示信息", 0);
            if (value == 0) {
                saveFile();
            }
            if (value == 1) {
                //清空容器里面的东西
                paint.list.removeAll(paint.list);
                paint.panelcenter.repaint();
                try {
                    JFileChooser chooser = new JFileChooser();
                    chooser.showOpenDialog(null);
                    File file = chooser.getSelectedFile();
                    if (file == null) {
                        JOptionPane.showMessageDialog(null, "没有选择文件");
                    } else {
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        ArrayList<MyBoard.Shape.Shape> list = (ArrayList<MyBoard.Shape.Shape>) ois.readObject();
                        for (int i = 0; i < list.size(); i++) {
                            MyBoard.Shape.Shape shape = (Shape) list.get(i);
                            paint.list.add(shape);
                            paint.panelcenter.repaint();
                        }
                        ois.close();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else if ("保存工程".equals(command)) {
            saveFile();
        } else if ("打开位图".equals(command)) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();
            if (file == null) {
                JOptionPane.showMessageDialog(null, "没有选择文件");
            } else {
                try {
                    if (!paint.list.isEmpty()) {
                        paint.list.clear();
                        paint.g.setColor(Color.white);
                        paint.g.fillRect(0, 0, 1000, 700);
                    }
                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedImage bufferedImage = ImageIO.read(fileInputStream);
                    paint.panelcenter.getGraphics().drawImage(bufferedImage, paint.panelcenter.getX(), paint.panelcenter.getY() - 35, paint.panelcenter.getWidth(), paint.panelcenter.getHeight(), paint.panelcenter);
                    fileInputStream.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if ("保存位图".equals(command)) {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showSaveDialog(null);
                File file = chooser.getSelectedFile();
                if (file == null) {
                    JOptionPane.showMessageDialog(null, "没有选择文件");
                } else {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    Robot robot = new Robot();
                    BufferedImage bufferedImage = robot.createScreenCapture(
                            new Rectangle(paint.getX() + 10, paint.getY() + 110, paint.panelcenter.getWidth(), paint.panelcenter.getHeight() - 60));
                    ImageIO.write(bufferedImage, "bmp", fileOutputStream);
                    JOptionPane.showMessageDialog(null, "保存成功！");
                    fileOutputStream.close();
                }
            } catch (AWTException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void saveFile() {
        //选择要保存的位置以及文件名字和信息
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "没有选择文件");
        } else {
            try {
                FileOutputStream fis = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fis);
                oos.writeObject(paint.list);
                JOptionPane.showMessageDialog(null, "保存成功！");
                oos.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}