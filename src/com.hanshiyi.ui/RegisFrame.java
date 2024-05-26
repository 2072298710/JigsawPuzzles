import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisFrame extends JFrame implements MouseListener {

    JTextField userField = new JTextField();// 用户名输入框
    JPasswordField passwordField = new JPasswordField();// 密码输入框
    JPasswordField confirmPasswordField = new JPasswordField();// 确认密码输入框

    JButton registerButton = new JButton();// 注册按钮
    JButton resetButton = new JButton();// 重置按钮

    // 定义变量获取用户名
    String username = userField.getText();
    // 定义变量获取密码
    String password = new String(passwordField.getPassword());
    // 定义变量获取确认密码
    String confirmPassword = new String(confirmPasswordField.getPassword());
    
    public RegisFrame() {
        // 初始化界面
        initJFrame();
        // 初始化视图
        initView();
        // 设置页面是否显示
        this.setVisible(true);
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

    private void initView() {
        // 添加用户用户名图片
        JLabel userJLabel = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        userJLabel.setBounds(90, 134, 79, 17);
        this.getContentPane().add(userJLabel);
        // 添加注册用户输入框
        userField.setBounds(195, 130, 200, 30);
        this.getContentPane().add(userField);

        // 添加用户密码图片
        JLabel passwordJLabel = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordJLabel.setBounds(90, 184, 79, 17);
        this.getContentPane().add(passwordJLabel);
        // 添加注册密码输入框
        passwordField.setBounds(195, 180, 200, 30);
        this.getContentPane().add(passwordField);
        // 添加用户确认密码图片
        JLabel confirmPasswordJLabel = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        confirmPasswordJLabel.setBounds(83, 234, 96, 17);
        this.getContentPane().add(confirmPasswordJLabel);
        // 添加注册确认密码输入框
        confirmPasswordField.setBounds(195, 230, 200, 30);
        this.getContentPane().add(confirmPasswordField);
        // 添加用户注册按钮
        registerButton.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        registerButton.setBounds(100, 280, 128, 47);
        this.getContentPane().add(registerButton);
        // 添加鼠标监听器
        registerButton.addMouseListener(this);
        // 去除按钮的默认边框
        registerButton.setBorderPainted(false);
        // 去除按钮的默认背景
        registerButton.setContentAreaFilled(false);
        // 添加用户重置按钮
        resetButton.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        resetButton.setBounds(295, 280, 128, 47);
        this.getContentPane().add(resetButton);
        // 添加鼠标监听器
        resetButton.addMouseListener(this);
        // 去除按钮的默认边框
        resetButton.setBorderPainted(false);
        // 去除按钮的默认背景
        resetButton.setContentAreaFilled(false);

        // 添加背景图片
        JLabel bgJLabel = new JLabel(new ImageIcon("image\\register\\background.png"));
        bgJLabel.setBounds(0, 0, 488, 430);
        this.getContentPane().add(bgJLabel);
    }

    private void initJFrame() {
        this.setSize(488, 430);
        // 设置页面标题
        this.setTitle("拼图注册");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == registerButton) {
            // 判断用户名和密码是否为空
            if (username.equals("")) {
                showErrorDialog("用户名或密码不能为空！");
            } else if (!password.equals(confirmPassword)) {
                showErrorDialog("两次输入的密码不一致！");
            } else {
                // 关闭注册页面
                this.dispose();
                // 显示登录页面
                new LoginFrame();
            }
        } else if (e.getSource() == resetButton) {
            // 重置输入框
            userField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
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
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\register\\注册按下.png"));
        }else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image\\register\\重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        }else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        }
    }
}
