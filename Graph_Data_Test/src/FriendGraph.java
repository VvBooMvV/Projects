

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;



/**
 * @author Harry Kim
 * this program will allow user to organize a set of list of friends and edges.
 * The root interface in the graph hierarchy. A mathematical graph-theory graph
 * object G(V,E) contains a set V of vertices and a set
 * E of edges. Each edge e=(v1,v2) in E connects vertex v1 to vertex v2.
 *
 * Through generics, a graph can be typed to specific classes for vertices
 * V and edges E<T>. Such a graph can contain
 * vertices of type V and all sub-types and Edges of type
 * E and all sub-types.
 */

public class FriendGraph implements GraphInterface<Friend, Edge<Friend, Friend>>{
	
	LinkedList<Friend> participant = new LinkedList<Friend>();
	ArrayList<Edge<Friend, Friend>> edges = new ArrayList<Edge<Friend, Friend>>();
	TreeSet<Friend> allfriends = new TreeSet<Friend>();
	TreeMap<Friend, Friend> listoffriends = new TreeMap<Friend, Friend>();
	
    /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	

	public Edge<Friend, Friend> getEdge(Friend sourceVertex, Friend targetVertex) {
		
			Edge<Friend, Friend> test = new Edge<Friend, Friend>(sourceVertex, targetVertex);
			
			
			for(int i = 0; i < edges.size(); i++){
				if(edges.get(i).toString().equalsIgnoreCase(test.toString())){
					return edges.get(i);
				}
				else{
					
				}
			}
			return null;

	}
	
    /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	
	public Edge<Friend, Friend> addEdge(Friend sourceVertex, Friend targetVertex) {
		


		if(sourceVertex == null || targetVertex == null){
			throw new NullPointerException();
		}
		Edge<Friend, Friend> dude = new Edge<Friend, Friend>(sourceVertex, targetVertex);
		for(int i = 0; i < edges.size(); i++){
			if(edges.get(i).getSource().getName().equalsIgnoreCase(dude.getSource().getName()) 
					&& edges.get(i).getTarget().getName().equalsIgnoreCase(dude.getTarget().getName() )){
				return null;
			}

		}
		
			edges.add(dude);
			
			sourceVertex.addFriendtoList(targetVertex);

			
			return dude;			
		


		
		
	}
    /**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */

	public boolean addVertex(Friend v) {
		if(participant.contains(v)){
			return false;
		}
		else{
			participant.add(v);
			return true;
		}
	}
    /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */

	public boolean containsEdge(Friend sourceVertex, Friend targetVertex) {
		
			Edge<Friend, Friend> test = new Edge<Friend, Friend>(sourceVertex, targetVertex);
			
			for(int i = 0; i < edges.size(); i++){
				if(edges.get(i).toString().equalsIgnoreCase(test.toString())){
					return true;
				}
			
			}

			return false;

		
	}
    /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */

	public boolean containsVertex(Friend v) {

		if(participant.contains(v)){
				return true;
		}
		
		else return false;
		
	
	}
	

	   /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	
	

	public Set<Edge<Friend, Friend>> edgeSet() {
		
		Set<Edge<Friend,Friend>> out = new TreeSet<Edge<Friend,Friend>>();
		for(int i = 0; i<edges.size();i++){
			out.add(edges.get(i));
		}
		
		
		return out;
	}
    /**
     * Returns a set of all edges touching the specified vertex. If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	
	public LinkedList<Friend> edgeSetList(Friend v){
		
		LinkedList<Friend> out = new LinkedList<Friend>();
		for(int i = 0; i < edges.size(); i++){
			if(edges.get(i).getSource().getLname().equalsIgnoreCase(v.getLname()) 
					&& !out.contains(edges.get(i).getTarget())){
				
				out.add(edges.get(i).getTarget());
			}
			else if(edges.get(i).getTarget().getLname().equalsIgnoreCase(v.getLname()) 
					&& !out.contains(edges.get(i).getTarget())){
				out.add(edges.get(i).getSource());
			}
		}
		
		return out;
		
	}
	
	public Set<Edge<Friend, Friend>> edgesOf(Friend vertex) {
		
		Set<Edge<Friend, Friend>> out = new TreeSet<Edge<Friend, Friend>>();
		for(int i = 0; i < edges.size(); i++){
			if(edges.get(i).getSource().getLname().equalsIgnoreCase(vertex.getLname())){
				out.add(edges.get(i));
			}
		}
		
		return out;
	}
    /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param targetVertex target vertex of the edge.
     *
     * @return The removed edge, or null if no edge removed.
     */

	public Edge<Friend, Friend> removeEdge(Friend sourceVertex, Friend targetVertex) {
		Edge<Friend, Friend> out = new Edge<Friend, Friend>(sourceVertex, targetVertex);
		
				
			
				System.out.println("HE" + out.toString());
				sourceVertex.removefriend(targetVertex);
				edges.contains(out);
				
			return out;
				
	}
    /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */

	public boolean removeVertex(Friend v) {
		
		if(!participant.contains(v)){
			return false;
		}
		else{
			//remove all edges of friend
			//from each friend list of other friends
			for(int i = 0; i<edges.size(); i++){
				if(edges.get(i).getSource().toString().equalsIgnoreCase(v.toString()) 
					|| edges.get(i).getTarget().toString().equalsIgnoreCase(v.toString())){
					
					edges.get(i).getSource().removefriend(v);
					edges.get(i).getTarget().removefriend(v);
					edges.remove(i);
					
					
				}
			}
			
			//then remove participant
			participant.remove(v);
			return true;
		}

	}
    /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	public LinkedList<Friend> getVertices(){
		
		return participant;
	}
	
	public Set<Friend> vertexSet() {
		TreeSet<Friend> out = new TreeSet<Friend>();
		
		for(int i = 0; i< participant.size(); i++){
			
			out.add(participant.get(i));
			
		}
		return out;
	}
	
	public boolean isVertexEmpty(){
		boolean out = false;
		if(!participant.isEmpty()){
			out = true;
		}
		
		return out;
	}

	public boolean isEdgeEmpty(){
		boolean out = false;
		if(!edges.isEmpty()){
			out = true;
		}
		
		return out;
	}
}
