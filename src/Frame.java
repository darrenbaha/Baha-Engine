import java.awt.Canvas;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends Canvas {
	
	static int temp_class = 0;
    static int temp_gender = 0;
    static String temp_name = new String(); 
	
	static JFrame frame = new JFrame();
	static CardLayout cl = new CardLayout();
	static JPanel mm = new JPanel();
	static JPanel cc = new JPanel();
	static JPanel container = new JPanel();
	static JButton createchar = new JButton();
	static JButton settings = new JButton();
	static JButton lc = new JButton();
	static JButton back = new JButton();
	static JButton quit = new JButton();
	static JTextField name = new JTextField();
	static JCheckBox male = new JCheckBox();
	static JCheckBox female = new JCheckBox();
	static JComboBox Class = new JComboBox();
	static JButton create = new JButton();
	static JLabel error = new JLabel();
	static Font basic = new Font("ariel", Font.BOLD, 100); 
	static JLabel mmTitle = new JLabel();
	static JFrame frame1;
	
public Frame(int x, int y, String title, Main game) { 
	
	container.setLayout(cl);
	
	mm.add(Box.createRigidArea(new Dimension(200, 0)));
	mm.add(mmTitle);
	mm.setLayout(new BoxLayout(mm, BoxLayout.PAGE_AXIS));
	mm.add(Box.createRigidArea(new Dimension(640, 360)));
	mm.add(createchar);
	mm.add(Box.createRigidArea(new Dimension(0, 30)));
	mm.add(lc);
	mm.add(Box.createRigidArea(new Dimension(0, 30)));
	mm.add(settings);
	mm.add(Box.createRigidArea(new Dimension(0, 30)));
	mm.add(quit);
	mm.setBackground(Color.BLACK);
	
	
	mmTitle.setText("Baha Engine");
	mmTitle.setFont(basic);
	
	Class.addItem("Warrior");
	Class.addItem("Magician");
	Class.addItem("Thief");
	Class.addItem("Bowmen");
	create.setText("Create Character");
	
	cc.add(back);
	cc.add(name);
	cc.add(male);
	cc.add(female);
	cc.add(Class);
	cc.add(create);
	cc.add(error);
	
	frame.add(container);
	
	name.setPreferredSize(new Dimension(150, 20));
	
	cc.setBackground(Color.WHITE);
	cl.show(container, "1");
	container.add(mm, "1");
	container.add(cc, "2");
	
	createchar.setText("Create Character");
	lc.setText("Load Character");
	settings.setText("Settings");
	quit.setText("Quit");
	
	back.setText("Back");
	
	frame.setTitle(title);
	frame.setSize(x, y);
	
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setFocusable(true);
	frame.add(game);
	
	game.start();
	
	male.addActionListener(new ActionListener()
	{
	  public void actionPerformed(ActionEvent e)
	  {

		    if (female.isSelected() == true) {
		    	female.setSelected(false);
		    } 
	  }
	});
	
	female.addActionListener(new ActionListener()
	{
	  public void actionPerformed(ActionEvent e)
	  {

		    if (male.isSelected() == true) {
		    	male.setSelected(false);
		    } 
	  }
	});
	
	create.addActionListener(new ActionListener()
	{
	  public void actionPerformed(ActionEvent e)
	  {

		  CreateCharacter();
		  
	  }
	});
	
	createchar.addActionListener(new ActionListener()
	{
	  public void actionPerformed(ActionEvent e)
	  {

		  cl.show(container, "2");
		  
	  }
	});
	
	back.addActionListener(new ActionListener()
	{
	  public void actionPerformed(ActionEvent e)
	  {

		  cl.show(container, "1");
		  
	  }
	});
	
	quit.addActionListener(new ActionListener()
	{
	  public void actionPerformed(ActionEvent e)
	  {

		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	  }
	});
    
}

public static void CreateCharacter() {
 
	
    if (name.getText().length() == 0) {
    	error.setText("Please enter a name!");
    } else if ((male.isSelected() == false) && (female.isSelected() == false)) {
    	error.setText("Please select a gender!");
    } else {
    
    
	temp_name = name.getText();
	
    if (male.isSelected() == true) {
    	temp_gender = 0;
    } else if (female.isSelected() == true) {
    	temp_gender = 1;
    }
    
    if (Class.getSelectedItem() == "Warrior") {
    	temp_class = 1;
    } else if (Class.getSelectedItem() == "Magician") {
    	temp_class = 2;
    } else if (Class.getSelectedItem() == "Thief") {
    	temp_class = 3;
    } else if (Class.getSelectedItem() == "Bowmen") {
        temp_class = 4;
    } 
    
    System.out.println("Baha Engine : New player created successfully!");;
    Main data = new Main();
    data.SetInitialStats();
    error.setText("Player Created Successfully!");
    
    }
}
    
public static String GetCCName() {
	return temp_name;
}

public static int GetCCGender() {
	return temp_gender;
}

public static int GetCCClass() {
	return temp_class; 
}

}
