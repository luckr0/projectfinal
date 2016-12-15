import java.awt.geom.*;
import java.awt.*;

public class EdgeShape{
		
	private Line2D.Double line;
	private VertexShape A;
	private VertexShape B;
	
	public EdgeShape(VertexShape A,VertexShape B){
		
		this.A = A;
		this.B = B;
		
		line = new Line2D.Double();
		Point2D.Double p1 = new Point2D.Double();
		p1.setLocation(A.getCenterX(),A.getCenterY());
		Point2D.Double p2 = new Point2D.Double();
		p2.setLocation(B.getCenterX(),B.getCenterY());
		line.setLine(p1,p2);	
	}
	
	public Line2D.Double getShape(){
		return line;
	}
	
	public VertexShape getVertexA(){
		return A;
	}
	public VertexShape getVertexB(){
		return B;
	}
}