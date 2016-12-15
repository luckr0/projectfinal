import java.awt.geom.*;
import java.awt.*;

public class GraphShape{
	
	private final int MAX_VERTECIES = 16;
	
	private final int MIDX = 550;
	private final int MIDY = 330;
	private final int radius = 300;
	private final int originX = MIDX-radius;
	private final int originY = MIDY-radius;
	private final double[][] POSITIONS = {
		
		{MIDX,MIDY-radius},
		{MIDX+0.4*radius,MIDY-0.9*radius},
		{MIDX+0.7*radius,MIDY-0.7*radius},
		{MIDX+0.9*radius,MIDY-0.4*radius},
		
		{MIDX+radius,MIDY},
		{MIDX+0.9*radius,MIDY+0.4*radius},
		{MIDX+0.7*radius,MIDY+0.7*radius},
		{MIDX+0.4*radius,MIDY+0.9*radius},
		
		{MIDX,MIDY+radius},
		{MIDX-0.4*radius,MIDY+0.9*radius},
		{MIDX-0.7*radius,MIDY+0.7*radius},
		{MIDX-0.9*radius,MIDY+0.4*radius},
		
		{MIDX-radius,MIDY},
		{MIDX-0.9*radius,MIDY-0.4*radius},
		{MIDX-0.7*radius,MIDY-0.7*radius},
		{MIDX-0.4*radius,MIDY-0.9*radius},
		
	};
	
	private int edgeCount;
	private int vertexCount;
	private int[][] matrix;
	
	private VertexShape[] vertecies;
	private EdgeShape[] edges;
	private int posStep;
	
	public GraphShape(int vertexCount, int edgeCount, int[][] matrix){
		
		this.edgeCount = edgeCount;
		this.vertexCount = vertexCount;
		System.out.println("matrix received");
		this.matrix = matrix;
		posStep = MAX_VERTECIES / vertexCount;
		
		vertecies = new VertexShape[vertexCount];
		
		if(vertecies.length<9){
			for(int j=0;j<vertecies.length;j++){
				vertecies[j] = new VertexShape((int)POSITIONS[j*posStep][0],(int)POSITIONS[j*posStep][1]);
			}
		}else{
			posStep++;
			for(int j=0;j<8;j++){
				vertecies[j] = new VertexShape((int)POSITIONS[j*posStep][0],(int)POSITIONS[j*posStep][1]);
			}
			int f = 1;
			for(int k=8;k<vertecies.length;k++){
				vertecies[k] = new VertexShape((int)POSITIONS[f][0],(int)POSITIONS[f][1]);
				f+=2;
			}
		}
		
		edges = new EdgeShape[edgeCount];
		
		int[][] lookupMatrix = matrix;
		int edge_id = 0;
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				
				if (lookupMatrix[i][j] == 1){
					if(edge_id<edgeCount){
						edges[edge_id] = new EdgeShape(vertecies[i],vertecies[j]);
						lookupMatrix[i][j] = 0;
						lookupMatrix[j][i] = 0;
						edge_id++;
					}
				}
			}
		}
	}
	
	
	public VertexShape[] getVertecies(){
		return vertecies;
	}
	public EdgeShape[] getEdges(){
		return edges;
	}
}