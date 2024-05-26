import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginFrame extends JFrame implements MouseListener {
    // LoginFrame 表示登录界面
    // 以后所有跟登录相关的代码，都写在这

    // 创建一个集合存储用户的登录信息
    public static ArrayList<User> userList = new ArrayList<User>();
    static {
        // 假设有两个用户
        userList.add(new User("admin", "admin123"));
        userList.add(new User("user", "user123"));
    }
    


    // 8.添加登录和注册按钮
    JButton login = new JButton();
    JButton register = new JButton();

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField verCodeField = new JTextField();
    JLabel rightCode = new JLabel();

    public LoginFrame() {
        // 初始化界面
        initJFrame();

        // 在界面中添加组件
        initView();
        this.setVisible(true);
    }
    

    private void initView() {
        // 1.添加用户名文字
        JLabel usernameLabel = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameLabel.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameLabel);

        // 2.添加用户名输入框
        usernameField.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameField);

        // 3.添加密码文字
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordLabel.setBounds(124, 195, 32, 16);
        this.getContentPane().add(passwordLabel);

        // 4.添加密码输入框
        passwordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordField);

        // 5.添加验证码文字

        JLabel verCodeLabel = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        verCodeLabel.setBounds(116, 256, 50, 30);
        this.getContentPane().add(verCodeLabel);

        // 6.添加验证码输入框
        verCodeField.setBounds(195, 256, 100, 30);
        this.getContentPane().add(verCodeField);

        // 7.显示生成的验证码
        String verCode = veCode.getCode();
        // 设置内容
        rightCode.setText(verCode);
        // 设置宽高
        rightCode.setBounds(300, 256, 50, 30);
        // 添加到界面
        this.getContentPane().add(rightCode);

        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        login.setBounds(123, 310, 128, 47);
        // 去除按钮的默认边框
        login.setBorderPainted(false);
        // 去除按钮的默认背景
        login.setContentAreaFilled(false);
        // 添加事件监听器
        login.addMouseListener(this);
        // 添加到界面
        this.getContentPane().add(login);

        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        register.setBounds(256, 310, 128, 47);
        // 去除按钮的默认边框
        register.setBorderPainted(false);
        // 去除按钮的默认背景
        register.setContentAreaFilled(false);
        // 添加事件监听器
        register.addMouseListener(this);
        // 添加到界面
        this.getContentPane().add(register);

        // 10.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 488, 430);
        this.getContentPane().add(background);

        // 刷新界面
        this.getContentPane().repaint();
    }

    private void initJFrame() {
        // 设置界面大小
        this.setSize(488, 430);
        // 设置页面标题
        this.setTitle("登录游戏");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(3);
        // 取消内部默认布局
        this.setLayout(null);
    }

    // 展示用户名或者密码错误的弹窗
    private static void showErrorDialog(String message) {
        // 创建一个对话框
        JDialog dialog = new JDialog();
        // 设置弹框大小
        dialog.setSize(200, 150);
        // 让弹框置顶
        dialog.setAlwaysOnTop(true);
        // 设置弹框居中
        dialog.setLocationRelativeTo(null);
        // 弹框不关闭，无法进行其他操作
        dialog.setModal(true);

        // 创建JLabel对象管理文字并添加到弹框中
        JLabel label = new JLabel(message);
        label.setBounds(0, 0, 200, 150);
        dialog.add(label);

        // 显示弹框
        dialog.setVisible(true);
    }

    // 判断用户是否存在
    private boolean isUserExist(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // 添加鼠标点击事件
    @Override
    public void mouseClicked(MouseEvent e) {
        // 如果点击的是登录按钮
        if (e.getSource() == login) {
            // 获取用户名和密码以及验证码
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String verCode = verCodeField.getText();

            // 创建一个User对象
            // User user = new User(username, password);

            if (verCode.isEmpty()) {
                // 判断验证码是否为空
                showErrorDialog("验证码不能为空");
            } else if (username.isEmpty() || password.isEmpty()) {
                // 判断用户名和密码是否为空
                showErrorDialog("用户名和密码不能为空");
            } else if (!verCode.equalsIgnoreCase(rightCode.getText())) {
                String Code = veCode.getCode();
                rightCode.setText(Code);
                showErrorDialog("验证码错误");
            } else if (isUserExist(username, password)) {
                // 用户存在则登录成功
                // 关闭登录界面
                this.dispose();
                // 打开游戏界面
                new GameJFrame();
            } else {
                // 用户不存在则登录失败
                showErrorDialog("用户名或密码错误");
            }
        } else if (e.getSource() == register) {
            // 如果点击的是注册按钮
            // 关闭登录界面
            this.dispose();
            // 打开注册界面
            new RegisFrame();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // 如果按下的是登录按钮，则更换登录按钮的图片
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // 如果释放的是登录按钮，则更换登录按钮的图片
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }
}
