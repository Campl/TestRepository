import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
<<<<<<< HEAD
//change_yanze
=======
//changes_1
>>>>>>> origin/yanze_2

public class SaoleiUse {
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {  
            @Override  
            public void run() {  
            	SaoleiMain s=new SaoleiMain();
            	s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	s.setVisible(true);
            }  
        });
	}
}
