import java.awt.*;
import javax.swing.*;

class MyFrame extends JFrame{
	JPanel parentPanel = new JPanel();
	JPanel panel = new JPanel();
	JScrollPane scroll = new JScrollPane(panel);

	public MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 750);
		this.setLocation(250, 250);


		GridLayout grid = new GridLayout(100, 2);
		//panel.setPreferredSize(new Dimension(450,500));
		scroll.setPreferredSize(new Dimension(500,500));
		scroll.setWheelScrollingEnabled(true);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		panel.setLayout(grid);

		//grid.setRows(1);
		//grid.setColumns(1);
for(int i = 0; i < 100; i = i + 1){
			JLabel tempLabel01 = new JLabel(new String("" + i));
			tempLabel01.setPreferredSize(new Dimension(100, 100));
			panel.add(tempLabel01);
			JLabel tempLabel02 = new JLabel(new String("" + i * i));
			tempLabel02.setPreferredSize(new Dimension(100, 100));
			panel.add(tempLabel02);
			//panel.add(new JLabel(new String("" + i)).setPreferredSize(250,100));
			//panel.add(new JLabel(new String("" + i)).setPreferredSize(250,100));
			//panel.add(new JLabel(new String("" + i * i)).setPreferredSize(250,100));
		}

		//this.add(this.panel);
		//this.add(this.scroll);
		
		this.parentPanel.add(scroll);

		//this.parentScroll.setWheelScrollingEnabled(true);
		this.add(this.parentPanel);

		this.setVisible(true);
	}

}

public class Practice{
	public static void main(String[] args){
		
		new MyFrame();
	
	}
}
