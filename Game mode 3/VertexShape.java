import java.awt.geom.*;
import java.awt.*;

public class VertexShape{
	
	private static int diameter = 50;
	
	private int X;
	private int Y;
	private int centerX;
	private int centerY;
	private Ellipse2D.Double circle;
	
	private static Color[] colors = {

		Color.red,
		Color.blue,
		Color.yellow,
		Color.green,
		Color.black,
		Color.cyan,
		Color.orange,
		Color.magenta,
		Color.white,
		Color.gray

	};
	
	private Color color;
	
	public VertexShape(int X, int Y){
		
		centerX = X;
		centerY = Y;
		this.X = X-diameter/2;
		this.Y = Y-diameter/2;
		circle = new Ellipse2D.Double(this.X,this.Y,diameter,diameter);
		this.color = colors[0];
		
	}
	
	public Ellipse2D.Double getShape(){
		return circle;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	public boolean withinBorders (int x, int y){
		double distance = Math.sqrt(Math.pow((x - centerX),2) + Math.pow((y-centerY),2));
		boolean value = distance < diameter/2;
		return	( value );
	}
	public void nextColor(){
		Color next;
		for(int i=0;i<colors.length;i++){
			if(this.color == colors[i] && i<9){
				this.color = colors[i+1];
				return;
			}
		}
		this.color = colors[0];
	}
	public int getCenterX(){
		return centerX;
	}
	public int getCenterY(){
		return centerY;
	}
	
}