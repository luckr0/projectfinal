import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class graphGen {
  
	private int[][] adjecentMatrix;
  
	public graphGen(int v, int e){

		int vertexAmount = v;
		int edgeAmount = e;
		adjecentMatrix = new int[vertexAmount][vertexAmount];
		int randomplaced = 0;
	
		while(randomplaced<e){
			
			int randomEdge1 = ThreadLocalRandom.current().nextInt(0, v );
			int randomEdge2 = ThreadLocalRandom.current().nextInt(0, v );
			
			if(		randomEdge1 != randomEdge2 &&
					adjecentMatrix[randomEdge1][randomEdge2] != 1 &&
					adjecentMatrix[randomEdge1][randomEdge2] != 1		){
					
					adjecentMatrix[randomEdge1][randomEdge2] = 1;
					adjecentMatrix[randomEdge2][randomEdge1] = 1;
					randomplaced++;
			}else{
				continue;
			}
		}
	}

	public int[][] getMatrix() {
		return adjecentMatrix;
	}
}
