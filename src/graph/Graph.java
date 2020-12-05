package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {
	private List<Vertex> vertices;
	
	public Graph() {
		vertices = new ArrayList<>();
	}
	
	public void generateGraph(int nbVertices, double redVertexProb, double blueEdgeProb) {
		vertices.clear();
		for(int i = 0; i < nbVertices; i++) {
			addVertex(redVertexProb, blueEdgeProb);
		}
	}
	
	public void addVertex(double redVertexProb, double blueEdgeProb) {
		Vertex newVertex = new Vertex(getColorFromProb(redVertexProb));
		for(Vertex v : vertices) {
			addEdge(newVertex, v, blueEdgeProb);
		}
		vertices.add(newVertex);
	}

	private void addEdge(Vertex newVertex, Vertex v, double blueEdgeProb) {
		new Edge(getColorFromProb(1-blueEdgeProb), newVertex, v);
		new Edge(getColorFromProb(1-blueEdgeProb), v, newVertex);
	}
	
	public void removeVertex(Vertex v) throws Exception {
		if(v.getColor() == Color.BLUE)
			throw new Exception("TRYING TO DELETE BLUE VERTEX");
		vertices.remove(v);
		v.delete();
	}
	
	private Color getColorFromProb(double prob) {
		Random r = new Random();
		if(r.nextDouble() < prob)
			return Color.RED;
		return Color.BLUE;
	}
	
	public void print() {
		String tripleTab = "\t\t\t";
		for(Vertex v : vertices) {
			System.out.println(tripleTab + v.color);
			for(Edge e : v.outcoming) {
				System.out.println(tripleTab+"\t---"+(e.getColor() == Color.RED ? "R" : "B")+"--->"+e.getDst().color);
			}
		}
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}
}
