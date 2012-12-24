/**
 * TagAnime shows an animation at the top of the detail panel.
 * It shows the book cover in the center of this panel, and all the tags of this book
 * moving to a random generated location aside the cover.
 * The tags enters on either side of the panel with a 50 percent possibility.
 * Both the tags's location and color are random generated. The tag that is most frequently
 * used is set to size 40. Font size decreases by 3 in order of the tags list.
 */
package visual;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Tag;

public class TagAnime extends JPanel implements Runnable{
	private List<Tag> tags;
	int HEIGHT;
	int WIDTH;
	int picW;
	
	public TagAnime(List<Tag> tags, Image pic)
	{
		super();
		setVisible(true);
		this.setLayout(null);
				
		HEIGHT = pic.getHeight(null);//The height of this panel should be the same as the cover.
		WIDTH = 658;//The width of the panel is fixed to 658.
		picW = pic.getWidth(null);//The width of the cover
		setSize(HEIGHT, WIDTH);

		JLabel picL =new JLabel(new ImageIcon(pic));
		add(picL);
		picL.setBounds((WIDTH-picW)/2, 0, picW, HEIGHT);//Set the cover to the center
		
		this.tags = tags;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		
		for(int i = tags.size()-1; i>=0; i--)
		{
			String str = tags.get(i).getName();
			
			int fontSize = 40-(i*3);//set font size.
			
			int wid = fontSize*str.length();//calc the width of the label
			int len = fontSize+10;//the length of the label
			//if the lable only contains english letters and numbers, its width should be reduced by a factor 0.6
			if(str.matches("[\\w\\.\\-\\ ]+"))
			{
				wid = (int) (wid*0.6);
			}
			
			JLabel label = new JLabel(str);
			label.setFont(new java.awt.Font("Î¢ÈíÑÅºÚ", 1, fontSize));
			//generate random color for the label
			int r = (int) (Math.random()*255);
			int g = (int) (Math.random()*255);
			int b = (int) (Math.random()*255);
			label.setForeground(new Color(r, g, b));
			add(label,0);//set label to the top
			
			int space = (WIDTH-picW) / 2;//marks the empty space on one side
			
			double random = Math.random();//determine which side of the panel should the label ge
			if(random < 0.5)
			{
				int x = (int) (Math.random()*space-20);//the x coordinate of the label
				if(x+wid>space-20) x=space-wid;//reset x if the label is going to overlap with the cover
				if(x<10)x=10;//reset x if the label is too near to the border
				int y = (int) (Math.random()*HEIGHT-20);//the y coordinate of the label
				if(y<10)y=10;//reset y if the label is too near to the border
	
				label.setBounds(0, y, wid*2, len);//set the label to (0, y)
				
				int xx = label.getX();
	
				//the label enters the panel at (0. y) moves to (x, y) at 1 pixel/ 10 milliseconds
				while(true)
				{

					xx = label.getX();
					if(xx>x) break;
					
					label.setLocation(xx+1, y);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
			
			else
			{
				
				int x = (int) (Math.random()*space) + picW + space;
				if(x+wid > WIDTH) x = WIDTH-wid;
				int y = (int) (Math.random()*HEIGHT-20);
				if(y<10)y=10;
	
				label.setBounds(WIDTH, y, wid*2, len);
				
				int xx = label.getX();
	
				//the label enters the panel at (WIDTH. y) moves to (x, y) at 1 pixel/ 8 milliseconds
				/* the distance is usually a little longer when tags enter from the right side of the
				 * screen. So the speed should be slightly faster.
				 */
				while(true)
				{

					xx = label.getX();
					if(xx<x) break;
					
					label.setLocation(xx-1, y);
					
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
			}
			
		}
		
	}

}
