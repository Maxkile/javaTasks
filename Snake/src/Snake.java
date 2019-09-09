public class Snake {
	
	public int length = 2;//snake length
	public int dir = 0;//snake direction
	
	public  int dX[] = new int[Window.WIDTH*Window.SCALE];
	public  int dY[] = new int[Window.HEIGHT*Window.SCALE];
	//dX[0],dY[0] - snake's head,dX[length -1],dY[length - 1] - snake's end
	public Snake(int x0,int y0, int x1, int y1) {//object constructor, enter snake's end and head coordinates
		dX[0] = x0;
		dX[length - 1] = x1;
		dY[0] = y0;
		dY[length - 1] = y1;
	}
	public void move() {//snake move
			for (int len = length;len>0;len--) {
				dX[len] = dX[len-1];
				dY[len] = dY[len-1];
			}
			switch (dir) {
				//up
				case 0: 
					dY[0]--; 
					break;
				//right
				case 1: 
					dX[0]++;
					break;
				//down
				case 2: 
					dY[0]++;
					break;
				//left
				case 3: 
					dX[0]--;
					break;
				default:
					break;
			}
			if (dY[0] > Window.HEIGHT - 1) dY[0] = 0;
			if (dY[0] < 0) dY[0] = Window.HEIGHT - 1;
			if (dX[0] > Window.WIDTH -1) dX[0] = 0;
			if (dX[0] < 0) dX[0] = Window.WIDTH - 1;
		}
	
}
