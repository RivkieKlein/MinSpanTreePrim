package MinSpanHW;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class MinSpanTreePrim {

	public static String getMinSpan(Graph graph) {
		LinkedList<Vertex> notVisited = (LinkedList) graph.getVertexes();
		LinkedList<Vertex> visited = new LinkedList<Vertex>();

		StringBuilder pathOfMinSpan = new StringBuilder();

		LinkedList<Edge> edges = (LinkedList<Edge>) graph.getEdges();

		Edge nextMin;// the next cheapest edge coming out of a visited node
		boolean sourceDestination = true;// is this edge being used with source and dest. meaning what they are or
											// opposite (because undirected so can use either way)

		visited.add(notVisited.removeFirst());// can start from random vertex (starting from first one in list)

		while (!notVisited.isEmpty()) {

			// reset to null
			nextMin = null;
			// reset to regular
			sourceDestination = true;

			// find the next shortest by iterating through linked list of edges based on
			for (Edge edge : edges) {
				
				//if it originates in a visited
				if (visited.contains(edge.getSource())) {
					
					// make sure no cycle
					if (!visited.contains(edge.getDestination())) {
						//then see if it is the min
						if (edge.getWeight() < (nextMin = nextMin == null ? edge : nextMin).getWeight()) {
							nextMin=edge;
							sourceDestination = true;
						}
					}
				} 
				//other way it can originate in a visited
				else if (visited.contains(edge.getDestination())) {
					
					// make sure no cycle
					if (!visited.contains(edge.getSource())) {
						
						if (edge.getWeight() <=(nextMin = nextMin == null ? edge : nextMin).getWeight()) {
							nextMin=edge;
							sourceDestination = false;
						}
					}
				}

			}

			// for the next minimum edge need to do the following:

			//System.out.println(nextMin);// print just for debugging

			pathOfMinSpan.append(" ["+nextMin+"]");// add it to the string builder

			visited.add(sourceDestination? nextMin.getDestination():nextMin.getSource());// based on the flag add the correct side to visited

			notVisited.remove(sourceDestination? nextMin.getDestination():nextMin.getSource());// and remove the correct side from not visited
		}
		return pathOfMinSpan.toString();

	}
}
