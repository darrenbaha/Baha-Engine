import java.util.LinkedList;

public class ObjectHandler {

	LinkedList<Character> character = new LinkedList<Character>(); 
	
	public ObjectHandler() {
		
	}
	
	
	public void addObject(Character i) {
		character.add(i);
	}
	
	
	public void removeObject(Character i) {
		character.remove(i);
	}
}
