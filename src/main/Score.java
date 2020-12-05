package main;

import java.util.List;

public class Score {
	double redVertexProb;
	double blueEdgeProb;
	double averageScore;
	
	public Score(double redVertexProb, double blueEdgeProb, List<Integer> scores) {
		this.redVertexProb = redVertexProb;
		this.blueEdgeProb = blueEdgeProb;
		double score = 0;
		for(Integer i : scores)
			score += i;
		score /= scores.size();
		this.averageScore = score;
	}

	public String[] asStringTab() {
		return new String[]{""+redVertexProb, ""+blueEdgeProb, ""+averageScore};
	}
}
