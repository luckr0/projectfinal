import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.io.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;

public class Drawer extends JPanel{
	
	private VertexShape[] vertecies;
	private EdgeShape[] edges;
	
	private static JLabel chrField = new JLabel();
	private static JTextField vertexField = new JTextField(5);
	private static JTextField edgeField = new JTextField(5);
	private static JButton startButton = new JButton("generate");
	private static JPanel graphicPanel;
	
	private static int chromatic_number = 0;
	
	public Drawer() {
		
		graphicPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try{
					BufferedImage image = ImageIO.read(new File("Resources/background5.jpeg"));
					g.drawImage(image, 0, 0, null);
				}catch(IOException e) {e.printStackTrace();}
            }
        };
		
		class StartButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent event){
				
				Graphics g = graphicPanel.getGraphics();
				Graphics2D g_2D = (Graphics2D) g;
				
				drawBackground();
				
				//collecting the input
				int e = Integer.parseInt(edgeField.getText());
				int v = Integer.parseInt(vertexField.getText());
				
				//generating random graph
				graphGen randomgraph = new graphGen(v, e);
				int[][] adjacencyMatrix = randomgraph.getMatrix();
				
				//bruteforcing solution
				
				
				//defining the new set
				GraphShape graph = new GraphShape(v,e,adjacencyMatrix);
				vertecies = graph.getVertecies();
				edges = graph.getEdges();
				
				//drawing the edges
				for(int i=0;i<edges.length;i++){
					drawEdge(edges[i]);
				}
				
				//drawing the vertecies
				for(int i=0;i<vertecies.length;i++){
					drawVertex(vertecies[i]);
				}
			}
		}
		//listener
		class MousePressListener implements MouseListener{

			public void mousePressed(MouseEvent event) {

				changeColor(event);
				updateHint();
			}
			public void mouseReleased(MouseEvent event) {}
			public void mouseClicked(MouseEvent event){}
			public void mouseEntered(MouseEvent event){}
			public void mouseExited(MouseEvent event) {}
		}
		startButton.addActionListener(new StartButtonListener());
		graphicPanel.addMouseListener(new MousePressListener());
	}
	
	public void drawBackground(){
		
		//accessing the graphicPanel
		Graphics g = graphicPanel.getGraphics();
		Graphics2D g_2D = (Graphics2D) g;
		
		g_2D.setStroke(new BasicStroke( 4.0F ));
				
		//redrawing empty background
		try{
			BufferedImage image = ImageIO.read(new File("Resources/background5.jpeg"));
			g.drawImage(image, 0, 0, null);
		}catch(IOException ex) {ex.printStackTrace();}
		
	}
	
	public void drawVertex( VertexShape vertex ){
		
		//accessing the graphicPanel
		Graphics g = graphicPanel.getGraphics();
		Graphics2D g_2D = (Graphics2D) g;
		
		//drawing shapes
		g_2D.setColor(Color.black);
		g_2D.draw(vertex.getShape());
		g_2D.setColor(vertex.getColor());
		g_2D.fill(vertex.getShape());
		
	}
	
	public void drawEdge( EdgeShape edge ){
		
		//accessing the graphicPanel
		Graphics g = graphicPanel.getGraphics();
		Graphics2D g_2D = (Graphics2D) g;
		
		//drawing shapes
		g_2D.setStroke(new BasicStroke( 10.0F ));
		if(edge.getVertexA().getColor() != edge.getVertexB().getColor()){
			g_2D.setColor(Color.green);
			g_2D.draw(edge.getShape());
			g_2D.setStroke(new BasicStroke( 4.0F ));
		}else{
			g_2D.setColor(Color.red);
			g_2D.draw(edge.getShape());
			g_2D.setStroke(new BasicStroke( 4.0F ));
		}
	}
	
	public void changeColor(MouseEvent event){
				
		//accessing the graphic panel
		Graphics g = graphicPanel.getGraphics();
		Graphics2D g_2D = (Graphics2D) g;
		
		VertexShape vertexToChange = null;
				
		//drawing shapes
		for(int i=0;i<vertecies.length;i++){
			if(vertecies[i].withinBorders(event.getX(),event.getY())){
				System.out.println("Vertex "+i+" to change");
				vertexToChange = vertecies[i];
				vertexToChange.nextColor();
				drawVertex( vertexToChange );
				break;
			}
		}
	}
	
	public void updateHint(){
		
		//accessing the graphic panel
		Graphics g = graphicPanel.getGraphics();
		Graphics2D g_2D = (Graphics2D) g;
		
		//redrawing the shapes
		for(int i=0;i<edges.length;i++){
			drawEdge(edges[i]);
		}
		for(int i=0;i<vertecies.length;i++){
			drawVertex(vertecies[i]);
		}
	}
	
	public void solve(){
		for(int i=0;i<vertecies.length;i++){
			for(int j=0;j<edges.length;j++){
				if(vertecies[i] == edges[j].getVertexA() ||
				   vertecies[i] == edges[j].getVertexB()    ){
					   
				}
			}
		}
	}
	
	public static void main(String[] args){
		
		Drawer d = new Drawer();
		
		JLabel vertexLabel = new JLabel("Vertecies: ");
		JLabel edgeLabel = new JLabel("Edges: ");
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(vertexLabel);
		controlPanel.add(vertexField);
		controlPanel.add(edgeLabel);
		controlPanel.add(edgeField);
		controlPanel.add(startButton);
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(controlPanel, BorderLayout.SOUTH);
		contentPanel.add(graphicPanel, BorderLayout.CENTER);
		contentPanel.setPreferredSize(new Dimension(900, 723));
		
		JFrame frame = new JFrame();
		
		frame.setSize(1100, 723);
		frame.add(contentPanel);
		frame.setVisible(true);
		frame.setResizable(false);
	}	
}