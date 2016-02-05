
/**
 * 
 * @author Harry Kim
 * this class will store a set of edges for which friends are connected to one another.
 *
 * @param <T> Friend object of the profile.
 * @param <S> Friend object of the friend.
 */


public class Edge<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Edge<T,S>>{

	private T source;
	private S target;
	
	public Edge(T t, S s){
		source = t;
		target = s;
	}
	public void setSource(T part){
		source = part;
	}
	public void setTarget(S friend){
		target = friend;
	}
	public S getTarget(){
		return target;
	}
	
	public T getSource(){
		return source;
	}
	public String toString(){
		return source.toString()+target.toString();
	}
	
	public boolean equals(Friend t, Friend s){
		
		if(source.toString().equalsIgnoreCase(t.toString()) && target.toString().equalsIgnoreCase(s.toString())){
			return true;
		}
		else return false;
		
	}
	

	
	public boolean equals(T part){
		
		if(part.toString().equalsIgnoreCase(this.getSource().toString())){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int compareTo(Edge<T, S> o) {
		if(o.getTarget().compareTo(this.getTarget()) < 0 ){
			return -1;
		}
		if(o.getTarget().compareTo(this.getTarget()) > 0){
			return 1;
		}
		else{
			return 0;	
		}
		
		
	}
	
}