
public class Object {
	protected double posx;
	protected double posy;
	
	public double getPosx() {
		return posx;
	}



	public void setPosx(double posx) {
		this.posx = posx;
	}



	public double getPosy() {
		return posy;
	}



	public void setPosy(double posy) {
		this.posy = posy;
	}
	

	

	public void move(double x, double y){
		posx+=x;
		posy+=y;
		
	}

}
