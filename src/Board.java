import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;



public class Board extends JPanel implements ActionListener{
	Window window;
	Timer timer= new Timer(33, this);
	Palyer player;
	Adapter adapter;
	enemy enemyy;
	long start_time;
	
	
	public final static int WIDTH=800;
	public final static int HEIGHT=600;
	public final static int player_speed=200;
	public final static int bullet_speed=300;
	
	
	public Board(Window window){
		
		this.window=window;
		
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player = new Palyer();
		adapter= new Adapter();
		enemyy=new enemy(100,100);
		this.addKeyListener(adapter);
		
		
	}
	
	
	
	public void start(){
		start_time=System.currentTimeMillis();
		timer.start();
	}
	
	
	
	private void drawPlayer(Graphics g){
		g.fillRect((int)player.getPosx(),(int) player.getPosy(), Palyer.plane_width, Palyer.plane_height);
		
		
	}
	
	private void drawBullet(Graphics g){
		for(Bullet bullet:player.get_bullets())
		{
			g.fillRect((int)bullet.getPosx(),(int) bullet.getPosy(), bullet.width, bullet.height);
		}	
	}
	
	private void drawEnemies(Graphics g){
		g.fillRect((int)enemyy.getPosx(),(int)enemyy.getPosy(), enemyy.plane_width, enemyy.plane_height);
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		drawPlayer(g);
		drawBullet(g);
		drawEnemies(g);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		long now=System.currentTimeMillis();
		long delta=now-start_time;
		start_time=now;
		double translation=(double)delta/1000*player_speed;
		double bullet_translation=(double)delta/1000*bullet_speed;
		
		
		servePlayer(translation);
		serveBullets(bullet_translation);
		serveEnemies(translation);
			
		repaint();
		
	}
	
	private void serveBullets(double translation){
		player.moveBullets(translation);
	}
	private void serveEnemies(double translation){
		if (enemyy.direction==enemy.DIR.RIGHT)
			{enemyy.move(translation,0);
			if(enemyy.getPosx()>=700)
			{
				enemyy.direction=enemy.DIR.LEFT;
				enemyy.setPosx(700);
			}
				
			}
		else
			{
			enemyy.move(-translation,0);
			if(enemyy.getPosx()<=100)
			{
			enemyy.direction=enemy.DIR.RIGHT;
			enemyy.setPosx(100);
			
			}
			}
			
	}
	
	
	private void servePlayer(double translation){
		int zmienna=0;
		for(boolean x:player.keys)
		{
			if(x)
				zmienna++;
		}
		if(zmienna==2)
		{
			translation=translation/2*Math.sqrt(2);
		}

		if(player.keys[0])
			player.move(0, -translation);
		if(player.keys[1])
			player.move(0, translation);
		if(player.keys[2])
			player.move(-translation, 0);
		if(player.keys[3])
			player.move(translation, 0);
	}
	
	
	class Adapter implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_W:
			player.keys[0]=true;
			System.out.println("w");
			break;
		case KeyEvent.VK_S:
			player.keys[1]=true;
			System.out.println("s");
			break;
		case KeyEvent.VK_A:
			player.keys[2]=true;
			System.out.println("a");
			break;
		case KeyEvent.VK_D:
			player.keys[3]=true;
			System.out.println("d");
			break;
		case KeyEvent.VK_SPACE:
			player.shot();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_W:
			player.keys[0]=false;
			System.out.println("w");
			player.move(0, -5);
			break;
		case KeyEvent.VK_S:
			player.keys[1]=false;
			System.out.println("s");
			player.move(0, 5);
			break;
		case KeyEvent.VK_A:
			player.keys[2]=false;
			System.out.println("a");
			player.move(-5, 0);
			break;
		case KeyEvent.VK_D:
			player.keys[3]=false;
			System.out.println("d");
			player.move(5, 0);
			break;
		
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
		
	}

	
	
	
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	}
}
