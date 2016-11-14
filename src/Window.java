import javax.swing.JFrame;

public class Window extends JFrame{
	
	
	Board board;
	
	public Window(){
		setTitle("Client");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		board= new Board(this);
		add(board);
		
		
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		board.start();
	
	}
	

}
