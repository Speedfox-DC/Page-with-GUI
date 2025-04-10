import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Page1 {
    public Page1() {
        JFrame frame = new JFrame();
        JLabel label = new JLabel();

        ImageIcon IG = new ImageIcon("Scene.jpg");
        Image cut = IG.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon Icon = new ImageIcon(cut);

        label.setText("This is on experimental purpose");
        label.setIcon(Icon);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        label.setFont(new Font("My Boli", Font.BOLD, 25));

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Page1(); 
    }
}
