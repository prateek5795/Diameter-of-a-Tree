/*
 *	Prateek Sarna - pxs180012
 * 	Akash Chand - axc173730
 */

package rbk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import rbk.Graph.Vertex;

public class SP12 {

	/**
	 * calls BFS twice to calculate maximum distance, which is the diameter
	 * 
	 * @param g
	 *            input graph
	 * @return diameter of input graph
	 */
	public int diameter(Graph g) {
		Vertex src = g.getVertex(1); // Initial chosen source vertex is 1. Could be anything though
		BFSOO bfs = BFSOO.breadthFirstSearch(g, src);
		Vertex max = maxVertex(bfs, g);
		bfs = BFSOO.breadthFirstSearch(g, max);
		max = maxVertex(bfs, g);
		return bfs.getDistance(max);
	}

	/**
	 * traverses the graph to find the distance between the current and source
	 * vertex
	 * 
	 * @param bfs
	 *            BFSOO class object
	 * @param g
	 *            input graph
	 * @return Vertex farthest from the chosen source
	 */
	public Vertex maxVertex(BFSOO bfs, Graph g) {
		Vertex max = null;
		int maxDistance = 0;
		for (Vertex u : g) {
			if (bfs.getDistance(u) > maxDistance) {
				maxDistance = bfs.getDistance(u);
				max = u;
			}
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		// Precondition : Graph has to be a tree
		String string = "5 5   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1	1";
		Scanner in;
		// If there is a command line argument, use it as file from which
		// input is read, otherwise use input from string.
		in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);
		// Read graph from input
		Graph g = Graph.readGraph(in);
		int s = in.nextInt();
		SP12 sp = new SP12();
		g.printGraph(false);
		System.out.println("Diameter : " + sp.diameter(g));
	}

}
