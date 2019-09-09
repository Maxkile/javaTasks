public class Object {
	
	public int posX;
	public int posY;
//	public boolean isexist;
	
	public  Object(int x, int y) {//constructor
		posX = x;
		posY = y;
	}
	
	public void createObj() {//creates apple in random position of panel
		posX = Math.abs((int) (Math.random() * Window.WIDTH - 1));
		posY = Math.abs((int) (Math.random() * Window.HEIGHT - 1));
//		isexist = true;
	}
	public void destroyObj() {
		posX = -1;
		posY = -1;
//		isexist = false;
	}
}
