import javax.swing.JButton;
import javax.swing.JFrame;

public class Test {
     public static void main(String[] args) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);
        JButton button = new JButton("Click me!");
        button.setBounds(0, 0, 100, 50);
        button.addActionListener(e -> System.out.println("Button clicked!"));
        
        frame.getContentPane().add(button);
     }
}
