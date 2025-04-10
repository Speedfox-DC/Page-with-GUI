import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameAdd implements ActionListener{

    ImageIcon ic=new ImageIcon("CIcon.png");
    ImageIcon d=new ImageIcon("dodge.jpg");
    JMenuBar menuBar=new JMenuBar();
    JMenu option;
    JMenu games;
    JMenu edit;
    JFrame frame=new JFrame();
    JButton button=new JButton("Click Here");
    JMenuItem calc;
    JMenuItem dodgeBlocks;

    FrameAdd(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);

        option=new JMenu("Options");
        games=new JMenu("Games");
        edit=new JMenu("Edit");
        calc=new JMenuItem("Calculator");
        dodgeBlocks=new JMenuItem("Dodge the Blocks");

        //ImageSection

        Image icn=ic.getImage().getScaledInstance(15,15, Image.SCALE_SMOOTH);
        ImageIcon icon=new ImageIcon(icn);

        Image dg=d.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon dodg=new ImageIcon(dg);

        menuBar.add(option);
        menuBar.add(games);
        menuBar.add(edit);

        option.add(calc);

        games.add(dodgeBlocks);

        calc.addActionListener(this);
        calc.setIcon(icon);

        dodgeBlocks.addActionListener(this);
        dodgeBlocks.setIcon(dodg);

        button.addActionListener(this);

        frame.setJMenuBar(menuBar);
        button.setBounds(100,100,100,50);
        frame.add(button);
        frame.setVisible(true);
    } 

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==calc){
            Calc open1 = new Calc();
        }

        if (e.getSource() == dodgeBlocks) {
            JFrame gameFrame = new JFrame("Dodge the Blocks");
            DodgeBlockss gamePanel = new DodgeBlockss();
            gameFrame.add(gamePanel);
            gameFrame.pack();
            gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // So it doesn't close the main app
            gameFrame.setLocationRelativeTo(null);
            gameFrame.setVisible(true);
        }

        if(e.getSource()==button){
            Page1 open2=new Page1();
        }
    }
}
