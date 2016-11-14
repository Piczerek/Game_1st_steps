import java.util.Vector;

public class Palyer extends Object {


	public static int plane_width=30;;
	public static int plane_height=30;
	public boolean keys[];
	public Vector<Bullet> bullets;
	
	
	
	public Palyer(){
		posx=Board.WIDTH/2-plane_width/2;
		posy=Board.HEIGHT/2-plane_height/2;
		
		keys = new boolean[]{false, false, false, false};//w s a d 
		
		bullets=new Vector<>();
		
	}
	public Palyer(int x, int y){
		posx=x;
		posy=y;
		keys = new boolean[]{false, false, false, false};//w s a d 
		bullets=new Vector<>();
		
	}
	
	public void moveBullets(double translation){
		for (Bullet bullet:bullets){
			bullet.move(0,-translation);
		}
		
	}


	
	
	public void shot(){
	bullets.add(new Bullet(this.getCenterX(),posy));
	
		
	}
	
	private double getCenterX(){
		return posx+plane_width/2;
	}
	
	private double getCenterY(){
		return posy+plane_height/2;
	}
	
	public Vector<Bullet> get_bullets(){
		return bullets;
		
	}
	
	
	
}
