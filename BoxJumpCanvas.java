package BoxJump;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;




public class BoxJumpCanvas extends JPanel
{
	private int Delay = 25;
	private int boxX;
	private int boxY;
	private int floor;
	private int speed;
	private int xContact;
	private int yContact;
	private long startTime;
	private long stopTime;
	private long eTime;
	
	
	protected final int JUMPMAX = 380;
	
	protected boolean jump;
	protected boolean start;
	private boolean end;
	private boolean win;
	
	protected Timer jumpTimer;
	protected Timer gameTime;
	private Timer speedTime;
	private Block enemy;
	private Block user;
	
	
	public BoxJumpCanvas()
	{
		boxX = 10;
		boxY = 500;
		floor = 540;
		speed = 20;
		xContact = 0;
		yContact = 0;
		
		
		this.setFocusable(true);
		this.setBackground(Color.WHITE);
		this.addKeyListener(new KeyHandle());
		
		enemy = new Block();
		enemy.setColor(Color.RED);
		
		user = new Block();
		user.setColor(Color.CYAN);
		user.setSizeX(boxX);
		user.setSizeY(boxY);
		
	
		
		speedTime = new Timer(speed,new SpeedHnalde());
		jumpTimer = new Timer(Delay,new Jump());
		
		end  = false;
		jump = false;
		start = false;
		win  = false;
		
	}
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font(Font.SANS_SERIF,16,19));
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, floor,900,120);	
		g.setColor(Color.black);
		g.drawRect(-1, floor,900,120);
		
		if(start == false)
		{
			g.setColor(Color.BLACK);
			g.drawString("Press SpaceBar to Begin", 600,50);
		}
		else
		{
			g.setColor(enemy.getColor());
			g.fillRect(enemy.getRate(),floor - enemy.getSizeY(),enemy.getSizeX() ,enemy.getSizeY());	
			g.setColor(Color.black);
			g.drawRect(enemy.getRate(),floor - enemy.getSizeY(),enemy.getSizeX() ,enemy.getSizeY());	
		}
		
		
		if(win == true)
		{
			g.drawString("Your Win", 300,250);
		}
		else if(end == true)
		{
			g.setColor(user.getColor());
			g.fillRect(xContact,yContact,40,40);
			
			g.setColor(Color.black);
			g.drawRect(xContact,yContact,40,40);
			
			g.drawString("Your Score is " + (eTime/1000), 300,150);
			enemy.RateReset();
			user.setSizeX(boxX);
		}
		else 
		{
			g.setColor(user.getColor());
			g.fillRect(user.getSizeX(),user.getSizeY(),40,40);
			
			g.setColor(Color.black);
			g.drawRect(user.getSizeX(),user.getSizeY(),40,40);	
		}
		
	}
	
	
	private class KeyHandle implements KeyListener
	{
		
		@Override
		public void keyTyped(KeyEvent e){}
	
		@Override
		public void keyReleased(KeyEvent e){}
		
		@Override
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == 32)
			{
				if(start == false)
				{
					user.setSizeX(user.getSizeX() + 50);
					speedTime.start();
					startTime = System.currentTimeMillis();
					speedTime.setDelay(speed);
				}
				end = false;
				start = true;
				win = false;
				
				
		
				if(!jumpTimer.isRunning())
				{
					jump = true;
					jumpTimer.start();	
				}	
			}
		}

	}
	
	
	
	private class Jump implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(jump == true)
			{
				user.setSizeY(user.getSizeY() - 10);
				if(user.getSizeY() == JUMPMAX)
				{
					jump = false;
				}	
			}
			else if(jump == false)
			{
				user.setSizeY(user.getSizeY() + 10);
				if(user.getSizeY() == 500)
				{
					jumpTimer.stop();
				}	
			}
			repaint();
		}		
	}// end class	
	
	
	
	private class SpeedHnalde implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			if(user.getSizeY() >= floor - enemy.getSizeY() 
					&& (user.getSizeX() == enemy.getRate() || (user.getSizeX() + 40) == enemy.getRate()))
			{
				speedTime.stop();
				xContact = enemy.getRate();
				yContact = user.getSizeY();
				
				end = true;
				start = false;
			}
			
			
			stopTime = System.currentTimeMillis();
			eTime = stopTime - startTime;
			
			
			
			
			if(enemy.getRate() == 0)
			{
				enemy.RateReset();
				level();
			}
			
			enemy.SetRate(enemy.getRate() - 5);
			
			repaint();
		}	
		
		
		public void level()

		{
			if(eTime > 120000)
			{
				win = true;
				end = true;
			}
			else if(eTime > 90000)
			{
				speedTime.setDelay(2);
			}
			else if(eTime > 80000)
			{
				speedTime.setDelay(4);
			}
			else if(eTime > 70000)
			{
				speedTime.setDelay(6);
			}
			else if(eTime > 60000)
			{
				speedTime.setDelay(8);
			}
			else if(eTime > 50000)
			{
				speedTime.setDelay(10);
			}
			else if( eTime > 40000 )
			{
				speedTime.setDelay(12);
			}
			else if(eTime > 30000)
			{
				speedTime.setDelay(14);
			}
			else if(eTime > 20000)
			{
				speedTime.setDelay(16);
			}
			else if(eTime > 10000)
			{
				speedTime.setDelay(18);
			}
		}
		
	}//end class
	
	
}// end class

