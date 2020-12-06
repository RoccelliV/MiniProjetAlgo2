package main;

import java.util.ArrayList;
import java.util.List;

import graph.Color;
import graph.Graph;
import graph.Vertex;

public class Main {
	public static void main(String[] args) throws Exception {
		Graph g = new Graph();
		g.generateGraph(5, 1.0, 0.5);
//		g.print();
//		List<Vertex> vertices = g.getVertices();
//		for(Vertex v : vertices) {
//			if(v.getColor().equals(Color.RED)) {
//				g.removeVertex(v);
//				break;
//			}
//		}
//		g.print();

		int count = 0;
		List<Score> scores = new ArrayList<Score>();

		// HEURISTIQUE 1
//		for(double redVertexProb = 0; redVertexProb <=1 ; redVertexProb += 0.1) {
//			for(double blueEdgeProb = 0; blueEdgeProb <= 1; blueEdgeProb += 0.1) {
//				List<Integer> redChainScores = new ArrayList<Integer>();
//				for(int i = 0; i < 100; i++) {
//					List<Vertex> redChain = new ArrayList<>();
//					g.generateGraph(100, redVertexProb, blueEdgeProb);
//					List<Vertex> vertices = g.getVertices();
//					while(true) {
//						VertexAndScore bestVertex = new VertexAndScore(null, -201);
//						for(Vertex v : vertices) {
//							if(v.getColor() == Color.RED) {
//								int score = v.getNbRedOutcoming() + v.getNbBlueIncoming() - v.getNbRedIncoming() - v.getNbBlueOutcoming();
//								if(score > bestVertex.score) {
//									bestVertex.vertex = v;
//									bestVertex.score = score;
//								}
//							}
//						}
//						if(bestVertex.vertex == null) {//All remaining vertices are blue
//							System.out.println(count++);
//							break;
//						} else {
//							redChain.add(bestVertex.vertex);
//							g.removeVertex(bestVertex.vertex);
//						}
//					}
//					redChainScores.add(redChain.size());
//				}
//				scores.add(new Score(redVertexProb, blueEdgeProb, redChainScores));
//			}
//		}
//		
//		new CSVPrinter(getScoresAsStringTabList(scores)).printCSV();

		count = 0;
		scores.clear();
		// HEURISTIQUE 2
		for (double redVertexProb = 0; redVertexProb <= 1; redVertexProb += 0.1) {
			for (double blueEdgeProb = 0; blueEdgeProb <= 1; blueEdgeProb += 0.1) {
				List<Integer> redChainScores = new ArrayList<Integer>();
				for (int i = 0; i < 100; i++) {
					List<Vertex> redChain = new ArrayList<>();
					g.generateGraph(100, redVertexProb, blueEdgeProb);
					List<Vertex> vertices = g.getVertices();
					while (true) {
						VertexAndScore bestVertex = new VertexAndScore(null, Integer.MIN_VALUE);
						for (Vertex v : vertices) {
							if (v.getColor() == Color.RED) {
								int score = v.getScoreForHeristic2();
								if (score > bestVertex.score) {
									bestVertex.vertex = v;
									bestVertex.score = score;
								}
							}
						}
						if (bestVertex.vertex == null) {// All remaining vertices are blue
							System.out.println(count++);
							break;
						} else {
							redChain.add(bestVertex.vertex);
							g.removeVertex(bestVertex.vertex);
						}
					}
					redChainScores.add(redChain.size());
				}
				scores.add(new Score(redVertexProb, blueEdgeProb, redChainScores));
			}
		}

		new CSVPrinter(getScoresAsStringTabList(scores)).printCSV();
	}

//	HEURISTIQUE BRUT FORCE --> pas bien
//	for(double redVertexProb = 0; redVertexProb <=1 ; redVertexProb += 0.1) {
//		for(double blueEdgeProb = 0; blueEdgeProb <= 1; blueEdgeProb += 0.1) {
//			List<Integer> redChainScores = new ArrayList<Integer>();
//			for(int i = 0; i < 100; i++) {
//				g.generateGraph(20, redVertexProb, blueEdgeProb);
//				
//				redChainScores.add(secondHeuristicRecursiveCall(g, 0));
//				System.out.println(count++ +"\n==============================");
//			}
//			scores.add(new Score(redVertexProb, blueEdgeProb, redChainScores));
//		}
//	}
//	new CSVPrinter(getScoresAsStringTabList(scores)).printCSV();	
//	private static int secondHeuristicRecursiveCall(Graph g, int size) throws Exception {
//		List<Vertex> vertices = g.getVertices();
//		List<Integer> redChains = new ArrayList<Integer>();
//		for (Vertex v : vertices) {
//			if (v.getColor() == Color.RED) {
//				Graph g1 = new Graph(g);
//				g1.removeVertex(vertices.indexOf(v));
//				redChains.add(secondHeuristicRecursiveCall(g1, size + 1));
//			}
//		}
//		if (redChains.size() == 0) { // THEY ALL BLUE
//			return size;
//		}
//		int best = 0;
//		for (Integer contender : redChains) {
//			if (contender > best) {
//				best = contender;
//			}
//		}
//		return best;
//	}

	private static List<String[]> getScoresAsStringTabList(List<Score> scores) {
		List<String[]> dataLines = new ArrayList<String[]>();
		for (Score score : scores) {
			dataLines.add(score.asStringTab());
		}
		return dataLines;
	}

}
