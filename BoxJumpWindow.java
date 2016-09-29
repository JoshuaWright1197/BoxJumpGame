package BoxJump;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BoxJumpWindow extends JFrame
{
	protected JLabel label;
	
	public BoxJumpWindow()
	{
		super("Box Jump");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900,700);
		setLocationRelativeTo(null);
		BoxJumpCanvas b = new BoxJumpCanvas();
		add(b);
		
		setVisible(true);

	}
}
