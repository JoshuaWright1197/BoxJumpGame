package BoxJump;

import java.awt.Color;

class Block
{
	private int sizeX;
	private int sizeY;
	private int rate;
	private Color color;
	
	public Block()
	{
		rate = 900;	
		sizeX = 30;
		sizeY = 60;
		color = Color.DARK_GRAY;
		
	}
	
	public void setColor(Color c)
	{
		this.color = c;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public int getSizeX()
	{
		return sizeX;
	}

	public void RateReset()
	{
		this.rate = 900;
	}
	
	public void setSizeX(int sizeX)
	{
		this.sizeX = sizeX;
	}

	public int getSizeY()
	{
		return sizeY;
	}

	public void setSizeY(int sizeY)
	{
		this.sizeY = sizeY;
	}

	public int getRate()
	{
		return rate;
	}

	public void SetRate(int rate)
	{
		this.rate = rate;
	}
}
