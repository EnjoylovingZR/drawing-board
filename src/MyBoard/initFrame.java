package MyBoard;

import MyBoard.Listener.*;
import MyBoard.Shape.Shape;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class initFrame extends JFrame {

    public Color c = Color.BLACK;
    public Button bt;
    public JPanel panelcenter;
    public Graphics2D g;
    public int w = 1;
    public int f = 10;
    public JComboBox stytles;
    public String[] fontName;
    public String font = "微软雅黑";
    public int flag = 0;
    public boolean popStatus = false;
    public Vector<Bubble> bubbles;
    public ArrayList<MyBoard.Shape.Shape> list = new ArrayList<MyBoard.Shape.Shape>();
    public ArrayList<MyBoard.Shape.Shape> trash = new ArrayList<MyBoard.Shape.Shape>();

    public initFrame() {
        bubbles = new Vector<Bubble>();
    }

    public void initFrame() throws Exception {

        //设置窗体相关属性
        this.setSize(1000, 700);
        this.setTitle("画板");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icon.png"));
        this.setIconImage(icon);

        //菜单条对象创建
        JMenuBar bar = new JMenuBar();
        //菜单创建
        JMenu menu = new JMenu("文件");
        JMenu menu1 = new JMenu("编辑");
        JMenu menu2 = new JMenu("线宽");
        JMenu menu4 = new JMenu("颜色");
        JMenu menu5 = new JMenu("字号");
        JMenu menu6 = new JMenu("线型");
        JMenu menu7 = new JMenu("弹性泡泡");

        //菜单项监听器创建
        MyMenuListener ll = new MyMenuListener(this);
        FileListener fl = new FileListener(this);
        EditListener el = new EditListener(this);
        LineListener l = new LineListener(this);
        //创建三个菜单项

        ImageIcon item0Icon = new ImageIcon(this.getClass().getResource("/Empty_document_new_16px_1184801_easyicon.net.png"));
        JMenuItem item0 = new JMenuItem("新建");
        item0.setIcon(item0Icon);
        ImageIcon itemIcon = new ImageIcon(this.getClass().getResource("/open_project_16px_15384_easyicon.net.png"));
        JMenuItem item = new JMenuItem("打开工程");
        item.setIcon(itemIcon);
        ImageIcon item1Icon = new ImageIcon(this.getClass().getResource("/disk_pen_save_as_write_16px_400_easyicon.net.png"));
        JMenuItem item1 = new JMenuItem("保存工程");
        item1.setIcon(item1Icon);
        ImageIcon item_1Icon = new ImageIcon(this.getClass().getResource("/Bmp_16px_1186163_easyicon.net.png"));
        JMenuItem item_1 = new JMenuItem("打开位图");
        item_1.setIcon(item_1Icon);
        ImageIcon item1_1Icon = new ImageIcon(this.getClass().getResource("/save_16px_1085567_easyicon.net.png"));
        JMenuItem item1_1 = new JMenuItem("保存位图");
        item1_1.setIcon(item1_1Icon);
        ImageIcon item2Icon = new ImageIcon(this.getClass().getResource("/Undo_16px_1186327_easyicon.net.png"));
        JMenuItem item2 = new JMenuItem("撤销");
        item2.setIcon(item2Icon);
        ImageIcon item3Icon = new ImageIcon(this.getClass().getResource("/Clear_16px_1186688_easyicon.net.png"));
        JMenuItem item3 = new JMenuItem("清屏");
        item3.setIcon(item3Icon);

        JMenuItem item4 = new JMenuItem("实线");
        JMenuItem item5 = new JMenuItem("虚线");
        JMenuItem item6 = new JMenuItem("点线");

        //给每个菜单项添加监听器
        menu4.addMenuListener(ll);
        menu2.addMenuListener(ll);
        menu5.addMenuListener(ll);
        menu7.addMenuListener(ll);

        item0.addActionListener(fl);
        item.addActionListener(fl);
        item1.addActionListener(fl);
        item_1.addActionListener(fl);
        item1_1.addActionListener(fl);

        item2.addActionListener(el);
        item3.addActionListener(el);

        item4.addActionListener(l);
        item5.addActionListener(l);
        item6.addActionListener(l);
        //将菜单条添加到窗体上
        this.setJMenuBar(bar);
        //将菜单添加到菜单条上
        bar.add(menu);
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu6);
        bar.add(menu5);
        bar.add(menu4);
        bar.add(menu7);
        menu.add(item0);
        menu.add(item);
        menu.add(item1);
        menu.add(item_1);
        menu.add(item1_1);
        menu1.add(item2);
        menu1.add(item3);
        menu6.add(item4);
        menu6.add(item5);
        menu6.add(item6);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();//计算机上字体可用的名称
        fontName = ge.getAvailableFontFamilyNames();
        stytles = new JComboBox(fontName);
        FontListener fol = new FontListener(this);
        stytles.addItemListener(fol);
        stytles.setMaximumSize(new Dimension(120, 20));//设置下拉列表的最大尺寸
        stytles.setMinimumSize(new Dimension(100, 20));
        stytles.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));//设置字体
        bar.add(stytles);


        //窗体添加主面板
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        this.add(panel);

        panelcenter = new JPanel() {
            public void paint(Graphics g1) {
                g = (Graphics2D) g1;
                super.paint(g);
                for (int i = 0; i < list.size(); i++) {
                    MyBoard.Shape.Shape shape = (Shape) list.get(i);
                    shape.Draw(g);
                }
            }
        };
        panelcenter.setBackground(Color.white);
        panel.add(panelcenter);

        //主面板添加左面板
        JPanel panelNorth = new JPanel();
        panelNorth.setPreferredSize(new Dimension(0, 35));
        panelNorth.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelNorth.setBackground(Color.gray);
        panel.add(panelNorth, BorderLayout.NORTH);

        //面板中添加按钮
        //按钮归类，统一管路
        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < 16; i++) {
            JRadioButton jrb = new JRadioButton();
            jrb.setBackground(Color.gray);
            //给按钮添加图片
            String picString1="/draw" + i + ".png";
            String picString2="/draw" + i + "-1.png";
            String picString3="/draw" + i + "-3.png";
            String picString4="/draw" + i + "-3.png";
            ImageIcon img1 = new ImageIcon(this.getClass().getResource(picString1));
            ImageIcon img2 = new ImageIcon(this.getClass().getResource(picString2));
            ImageIcon img3 = new ImageIcon(this.getClass().getResource(picString3));
            ImageIcon img4 = new ImageIcon(this.getClass().getResource(picString4));
            jrb.setIcon(img1);
            jrb.setRolloverIcon(img2);
            jrb.setPressedIcon(img3);
            jrb.setSelectedIcon(img4);
            jrb.setBorder(null);
            if (i == 0 || i == 1 || i == 5 || i == 11) {
                jrb.setVisible(false);
            }
            //设置默认选中的按钮
            if (i == 10) {
                jrb.setSelected(true);
            }
            jrb.setActionCommand("pic" + i);
            bg.add(jrb);
            panelNorth.add(jrb);
        }

        //主面板添加下方面板
        JPanel paneldown = new JPanel();
        paneldown.setPreferredSize(new Dimension(0, 60));
        paneldown.setLayout(null);
        paneldown.setBackground(Color.gray);
        panel.add(paneldown, BorderLayout.SOUTH);

        //下方面板添加子面板
        JPanel paneldownchild = new JPanel();
        paneldownchild.setBackground(Color.cyan);
        paneldownchild.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        paneldownchild.setBounds(10, 10, 280, 40);
        paneldown.add(paneldownchild);

        //按钮特效
        BevelBorder bb = new BevelBorder(0, Color.gray, Color.white);
        JPanel left = new JPanel();
        left.setBackground(Color.white);
        left.setLayout(null);
        left.setBorder(bb);
        left.setPreferredSize(new Dimension(40, 40));
        bt = new Button();
        bt.setBounds(5, 5, 20, 20);
        bt.setBackground(Color.black);
        bt.setSize(20, 20);
        Button bt1 = new Button();
        bt1.setBounds(15, 15, 20, 20);
        left.add(bt);
        left.add(bt1);

        //右面板
        JPanel right = new JPanel();
        right.setBackground(Color.BLUE);
        right.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        right.setPreferredSize(new Dimension(240, 40));
        paneldownchild.add(left);
        paneldownchild.add(right);

        ButtonListener bl = new ButtonListener(this);
        Color[] colors = {new Color(0, 56, 67), new Color(89, 3, 14), new Color(189, 3, 14)
                , new Color(89, 93, 14), new Color(89, 113, 14), new Color(89, 73, 14)
                , new Color(89, 3, 14), new Color(89, 3, 14), new Color(29, 83, 14)
                , new Color(89, 3, 184), new Color(189, 233, 14), new Color(89, 253, 14)
                , new Color(89, 93, 14), new Color(89, 89, 94), new Color(1, 3, 14)
                , new Color(9, 83, 94), new Color(89, 178, 147), new Color(9, 33, 164)
                , new Color(34, 23, 14), new Color(89, 173, 154), new Color(8, 193, 194)
                , new Color(9, 253, 76), new Color(89, 240, 104), new Color(199, 73, 4)};
        for (int i = 0; i < 24; i++) {
            Button bt3 = new Button();
            Color c = new Color(i * 10, 30 - i, i * 7 + 50);
            bt3.setBackground(colors[i]);
            bt3.setPreferredSize(new Dimension(20, 20));
            bt3.addActionListener(bl);
            right.add(bt3);
        }
        this.setVisible(true);
        g = (Graphics2D) panelcenter.getGraphics();
        PanelListener dl = new PanelListener(g, bg, this, list);
        panelcenter.addMouseListener(dl);
        panelcenter.addMouseMotionListener(dl);
    }

}
