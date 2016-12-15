import java.util.*;

public class graphGen {
  public graphGen(int v, int e){

    int vertexAmount = v;
    int edgeAmount = e;
    adjecentMatrix = new int[vertexAmount][vertexAmount];

    for(int i = 0; i < vertexAmount; i++) {
      adjecentMatrix[i][i] = 2;
    }

    for(int i = 0; i < (Math.ceil(vertexAmount/2)); i++) {

      int randomrow = (int) (Math.random()*vertexAmount);
      boolean isTwo = true;
      while(isTwo) {
        if(adjecentMatrix[randomrow][i] == 2) {
          randomrow = (int) (Math.random()*vertexAmount);
          //System.err.println("Choosing row..." + randomrow);
        } else {
          isTwo = false;
        }
      }

      adjecentMatrix[randomrow][i] = 1;
      adjecentMatrix[i][randomrow] = 1;

      for (int j = 0; j < (vertexAmount); j++) {
        adjecentMatrix[randomrow][j] = 2;
        adjecentMatrix[i][j] = 2;
        adjecentMatrix[j][randomrow] = 2;
        adjecentMatrix[j][i] = 2;
        //System.err.println("Placing two's");
      }
      adjecentMatrix[randomrow][i] = 1;
      adjecentMatrix[i][randomrow] = 1;
      edgeAmount--;
     }

    for(int i = 0; i < edgeAmount; i++) {
      int edge1 = (int) (Math.random()*vertexAmount);
      int edge2 = (int) (Math.random()*vertexAmount);
      boolean sameEdge = true;

      while(sameEdge) {
        if(edge2 == edge1) {
          edge2 = (int) (Math.random()*vertexAmount);
        } else {
          sameEdge = false;
        }
      }
      //System.out.println(edge1 + " " + edge2);

      if(adjecentMatrix[edge1][edge2] == 1) {
        edgeAmount++;
      } else {
        adjecentMatrix[edge1][edge2] = 1;
        adjecentMatrix[edge2][edge1] = 1;
      }
    }

    for(int i = 0; i < adjecentMatrix.length; i++) {
      for(int j = 0; j < adjecentMatrix.length; j++) {
        if(adjecentMatrix[i][j] == 2) {
          adjecentMatrix[i][j] = 0;
        } else if(adjecentMatrix[i][j] == 0) {
          adjecentMatrix[i][j] = 1;
        }
      }
    }

  }

  public int[][] getMatrix() {
    return adjecentMatrix;
  }

  private int[][] adjecentMatrix;
}
