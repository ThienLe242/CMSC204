import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
   This component draws two car shapes.
*/
public class CarPanel extends JComponent
{  
	private Car car1;
	private int x,y, delay;
	private CarQueue carQueue;
	private int direction;
	
	CarPanel(int x1, int y1, int d, CarQueue queue)
	{
		delay = d;
        x=x1;
        y=y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
	}

	public void startAnimation()
	{
		class AnimationRunnable implements Runnable
		{
			public void run()
			{
				try
				{
					for (int i = 0; i < 50; i++)
					{
						direction = carQueue.deleteQueue();

						switch (direction)
						{
							case 0: // up
								y -= 10;
								break;
							case 1: // down
								y += 10;
								break;
							case 2: // right
								x += 10;
								break;
							case 3: // left
								x -= 10;
								break;
						}

						// boundary checking (simple)
						if (x < 0) x = 0;
						if (y < 0) y = 0;
						if (x > getWidth() - 60) x = getWidth() - 60;
						if (y > getHeight() - 40) y = getHeight() - 40;

						repaint();
						Thread.sleep(delay * 1000);
					}
				}
				catch (InterruptedException e)
				{
				}
			}
		}

		Thread t = new Thread(new AnimationRunnable());
		t.start();
	}
	
   public void paintComponent(Graphics g)
   {  
      Graphics2D g2 = (Graphics2D) g;

      car1.draw(g2,x,y);    
   }
}