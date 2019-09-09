import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Window extends JPanel implements ActionListener {
	
	public static JFrame Frame;//window
	public static final int HEIGHT = 20,WIDTH = 20,SCALE = 30;
	public static int freq = 125;
//	public static int livetime = 5;  
	
	Snake snake = new Snake(5,5,6,5);
	javax.swing.Timer alarm = new javax.swing.Timer(freq,this);// provides actionevents in resolved intervals
	Object apple  = new Object(Math.abs((int) (Math.random() * Window.WIDTH - 1)),Math.abs((int) (Math.random() * Window.HEIGHT - 1)));
//	java.util.Timer getapple = new java.util.Timer();
//	 TimerTask task = new TimerTask() {
//		    public void run() {
//		    	try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		    	apple.createObj();//again creates an apple
//		    }    
//		  };
	
	public Window(){
		alarm.start();//start the keyboard alarm
		Frame.addKeyListener(new KeyListener(){//interface  KeyListener
			@Override
			public void keyPressed(KeyEvent event) {
				// TODO Auto-generated method stub
				int key = event.getKeyCode();// event from the keyboard
				switch (key) {
					case KeyEvent.VK_D:
						case KeyEvent.VK_RIGHT:
							if (snake.dir != 3)	snake.dir = 1;
							break;
					case KeyEvent.VK_A:
						case KeyEvent.VK_LEFT:
							if (snake.dir != 1) snake.dir = 3;
							break;
					case KeyEvent.VK_W:
						case KeyEvent.VK_UP:
							if (snake.dir != 2) snake.dir = 0;
							break;
					case KeyEvent.VK_S:
						case KeyEvent.VK_DOWN:	
							if (snake.dir != 0) snake.dir = 2;
							break;
					default:
						break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyTyped(KeyEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		setFocusable(true);
	}
	
	public void paint(Graphics grph) {
		//draw a panel
		grph.setColor(Color.blue);
		grph.clearRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);//clear the window
		grph.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);//clear the window
//		grph.setColor(Color.orange);
//		for (int x = 0; x < SCALE*WIDTH;x+=SCALE) {
//			grph.drawLine(x, 0, x, HEIGHT*SCALE);
//		}
//		for (int y = 0; y<SCALE*HEIGHT;y+=SCALE) {
//			grph.drawLine(0, y ,WIDTH*SCALE, y);
//		}
		grph.setColor(Color.green);
		//draw a snake
		for (int len = 0;len<snake.length;len++) {
			grph.fillRect(snake.dX[len]*SCALE + 3, snake.dY[len]*SCALE + 3, SCALE - 3, SCALE - 3);//one square
		}
		grph.setColor(Color.magenta);
		grph.fillOval(apple.posX*SCALE, apple.posY*SCALE, SCALE - 1, SCALE - 1);
	}
	
	public static void main(String args[]) {
		Frame = new JFrame("Snake");
		Frame.setSize(WIDTH*SCALE,HEIGHT*SCALE);
		Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Frame.setResizable(false);
		Frame.setLocationRelativeTo(Frame);
		Frame.add(new Window());//constructor, drawer
		Frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {//actionevents
		snake.move();//call method which 
		if ((snake.dX[0] == apple.posX) && (snake.dY[0] == apple.posY)) {//if snake's head reached an apple
//			getapple.scheduleAtFixedRate(task,0,5);
			snake.length++;//enlarge the snake
			apple.createObj();
		}
		for (int len = 1;len <= snake.length;len++) {
			if ((snake.dX[0] == snake.dX[len]) && (snake.dY[0] == snake.dY[len])) {
				alarm.stop();
				JOptionPane.showMessageDialog(Frame,"You have lost!");
				Frame.setVisible(false);
				System.exit(0);
			}
		}
		Frame.repaint();//updates the window
		Frame.requestFocus();//focus on our Frame
	}
}

