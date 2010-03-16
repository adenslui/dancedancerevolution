import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

public class Arrow extends JLayeredPane implements ActionListener{
	
	protected Image arrowImage;
	protected int xPos;
	private Timer timer;
	public int pub_dir = -1;
	protected int scalingFactor = 17;
	boolean notAdded = true;

	public enum Direction{
		LEFT, UP, DOWN, RIGHT
	}
				
	public Arrow(int dir, int initialYposition) throws InterruptedException{
		pub_dir = dir;
		Runner.getPanel().requestFocus();
		this.setFocusable(false);	
		try {
			switch(dir){
	    		case 0:
	    			arrowImage =ImageIO.read(new File("images/arrow_left_trans.png"));
	    			xPos = 0;
	    			break;
	    		case 1:
	    			arrowImage =ImageIO.read(new File("images/arrow_up_trans.png"));
	    			xPos = 100;
	    			break;
	    		case 2:
	    			arrowImage =ImageIO.read(new File("images/arrow_down_trans.png"));
	    			xPos = 200;
	    			break;
	    		case 3:
	    			arrowImage =ImageIO.read(new File("images/arrow_right_trans.png"));
	    			xPos = 300;
	    			break;
	    		default:
	    			System.out.println("error");
	    	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		timer = new Timer(10, this);
		timer.setInitialDelay(initialYposition*scalingFactor);
		timer.start();
		
		this.setSize(100, 100);
		this.setVisible(true);
		this.setEnabled(true);
		this.setLocation(xPos, 600);
	}
	
	public int getDir(){
		return this.pub_dir;
	}
	
	public void paint(Graphics g) {
		g.drawImage(arrowImage, 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		this.setLocation(getLocation().x, getLocation().y-3);
		if(getLocation().y<-100){
			int a = CreateGUI.arrowlistener.findArrow(this.pub_dir);
			if(a != -1){
				CreateGUI.score.setScore(-5);
				CreateGUI.arrowlistener.removeArrow(a);
			}
			this.timer.stop();
		}
		else if(getLocation().y<100 && notAdded){
			CreateGUI.arrowlistener.addArrow(this.pub_dir);
			notAdded = false;
		}
	}
 }
