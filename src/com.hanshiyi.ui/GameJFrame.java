import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    // 创建整个的菜单对象
    JMenuBar jMenuBar = new JMenuBar();
    // 创建菜单上面的两个选项的对象（功能 关于我们）
    JMenu switchJMenu = new JMenu("切换图片");
    JMenu functionJMenu = new JMenu("功能");
    JMenu aboutJMenu = new JMenu("关于我们");
    // 创建选项下的条目选项

    JMenuItem personItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新开始");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem exitItem = new JMenuItem("退出游戏");
    JMenuItem aboutItem = new JMenuItem("微信");

    // 创建二维数组，用来管理数据，加载图片的时候会根据里面的数据进行加载
    int[][] data = new int[4][4];
    // 记录编号为0的图片的位置
    int x;
    int y;

    // 定义一个变量，记录图片的路径
    String path = "image\\girl\\girl1\\";

    // 定义一个二维数组，存储正确的数据
    int[][] correctData = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 }
    };
    // 定义一个变量，记录游戏步数
    int step = 0;

    // 定义一个随机数，用来切换游戏图片
    Random random = new Random();

    GameJFrame() {
        super();
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initMenuBar();

        // 初始化数据（打乱图片）
        initData();

        // 初始化图片
        initImage();

        // 设置页面是否显示
        this.setVisible(true);
        // 判断游戏是否胜利
    }

    // data初始化，打乱图片
    private void initData() {
        // 1.创建数组，存放图片的编号
        // 图片的编号是从0到15，分别对应16张图片
        int[] temper = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        // 2.打乱数组中的数据的顺序
        // 遍历数组，获取每一个数据，随机获取一个索引，然后跟这个索引上的数据进行交换
        Random random = new Random();
        for (int i = 0; i < temper.length; i++) {
            // 随机获取一个索引
            int index = random.nextInt(temper.length);
            // 拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = temper[i];
            temper[i] = temper[index];
            temper[index] = temp;
        }
        // 3.将打乱后的数组赋值给data
        for (int i = 0; i < temper.length; i++) {
            if (temper[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = temper[i];
        }
    }

    // 初始化图片
    private void initImage() {
        // 清空原本已经加载的图片
        this.getContentPane().removeAll();
        // 先加载的图片在最上面，后加载的图片在最下面
        if (victory()) {
            // 如果游戏胜利，加载胜利的图片
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        // 添加图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 获取当前图片的编号
                int number = data[i][j];
                // 创建一个图片ImageIcon对象，并设置图片路径
                // 创建一个JLabel对象（管理容器），并设置图片
                JLabel label = new JLabel(new ImageIcon(path + number + ".jpg"));
                // 指定图片的位置
                label.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                // 0：表示让图片凸起来
                // 1：表示让图片凹下去
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 把管理器添加到界面中
                // this.add(label);
                this.getContentPane().add(label);
            }
        }
        // 添加背景图片
        JLabel backgroundLabel = new JLabel(new ImageIcon("image\\background.png"));
        backgroundLabel.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backgroundLabel);

        // 刷新界面
        this.getContentPane().repaint();
    }

    private void initMenuBar() {
        // 将每一个选项下面的条目添加到菜单中
        switchJMenu.add(personItem);
        switchJMenu.add(animalItem);
        switchJMenu.add(sportItem);
        functionJMenu.add(switchJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(exitItem);
        aboutJMenu.add(aboutItem);

        // 为每一个条目添加事件监听
        personItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        exitItem.addActionListener(this);
        aboutItem.addActionListener(this);

        // 将菜单里面的两个选项添加到菜单栏中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 设置菜单栏
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // 设置页面的宽高
        this.setSize(603, 680);
        // 设置页面标题
        this.setTitle("拼图游戏");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(3);
        // 取消默认的居中放置方式，只有取消了才会按照XY轴的方法加载组件
        this.setLayout(null);
        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    // 按下不松开的键盘事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == 65) {
            // 把界面中所有的图片全部删除
            this.getContentPane().removeAll();
            // 加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 添加背景图片
            JLabel backgroundLabel = new JLabel(new ImageIcon("image\\background.png"));
            backgroundLabel.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backgroundLabel);

            // 刷新界面
            this.getContentPane().repaint();
        }
    }

    // 松开键盘事件
    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            // 如果游戏胜利，不做任何事情
            return;
        }
        // 对上下左右键进行处理
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        if (keyCode == 37) {
            System.out.println("向左");
            if (y == 3) {
                // 表示空白方块已经在最右边
                return;
            }
            // 把空白图片右方的图片左移，空白块右移
            // 首先判断空白图片的位置
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            // 每移动一次，步数+1
            step++;
            // 调用方法重写加载图片
            initImage();
        } else if (keyCode == 38) {
            System.out.println("向上");
            if (x == 3) {
                // 表示空白方块已经在最下面
                return;
            }
            // 把空白图片下方的图片上移，空白块下移
            // 首先判断空白图片的位置
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            // 每移动一次，步数+1
            step++;
            // 调用方法重写加载图片
            initImage();
        } else if (keyCode == 39) {
            System.out.println("向右");
            if (y == 0) {
                // 表示空白方块已经在最左边
                return;
            }
            // 把空白图片左方的图片右移，空白块左移
            // 首先判断空白图片的位置
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            // 每移动一次，步数+1
            step++;
            // 调用方法重写加载图片
            initImage();
        } else if (keyCode == 40) {
            System.out.println("向下");
            if (x == 0) {
                // 表示空白方块已经在最上面
                return;
            }
            // 把空白图片上方的图片下移，空白块上移
            // 首先判断空白图片的位置
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            // 每移动一次，步数+1
            step++;
            // 调用方法重写加载图片
            initImage();
        } else if (keyCode == 65) {
            initImage();
        } else if (keyCode == 87) {
            data = correctData;
            initImage();
        }
    }

    // 按下键盘事件
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // 判断游戏是否胜利
    // 如果data和correctData相同，则表示游戏胜利
    private boolean victory() {
        for (int i = 0; i < data.length; i++) {
            // i：依次表示二维数组data里面的索引
            // data[i]：依次表示每一个一维数组
            for (int j = 0; j < data[i].length; j++) {
                // 只要有一个数据不一样，返回false
                if (data[i][j] != correctData[i][j]) {
                    return false;
                }
            }
        }
        // 如果全部数据相同，返回true
        return true;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的选项
        Object source = e.getSource();
        // 重新开始游戏
        if (source == replayItem) {
            // 重置步数
            step = 0;
            // 再次打乱二维数据中的数据
            initData();
            // 加载图片
            initImage();
        } else if (source == reLoginItem) {
            // 重新登陆
            // 退出当前界面
            this.dispose();
            // 重新打开登录界面
            new LoginFrame();
        } else if (source == exitItem) {
            // 退出游戏
            System.exit(0);
        } else if (source == aboutItem) {
            // 创建一个弹窗对象
            JDialog aboutDialog = new JDialog(this, "关于我们");
            // 创建一个管理图片的JLabel对象
            JLabel aboutLabel = new JLabel(new ImageIcon("image\\about.jpg"));
            // 设置位置和宽高
            aboutLabel.setBounds(0, 0, 258, 258);
            // 给弹窗添加管理图片的JLabel对象
            aboutDialog.add(aboutLabel);
            // 设置弹窗的宽高
            aboutDialog.setSize(285, 387);
            // 设置弹窗居中
            aboutDialog.setLocationRelativeTo(null);
            // 弹框不关闭则无法进行其他操作
            aboutDialog.setModal(true);
            // 显示弹窗
            aboutDialog.setVisible(true);
        }else if (source == personItem) {
            int which = random.nextInt(1,12);
            path = "image\\girl\\girl" + which + "\\";
            step = 0;
            initData();
            initImage();
        }else if (source == animalItem) {
            // 切换图片
            int which = random.nextInt(1,9);
            path = "image\\animal\\animal" + which + "\\";
            step = 0;
            initData();
            initImage();
        }else if (source == sportItem) {
            // 切换图片
            int which = random.nextInt(1,11);
            path = "image\\sport\\sport" + which + "\\";
            step = 0;
            initData();
            initImage();
        }
    }
}
