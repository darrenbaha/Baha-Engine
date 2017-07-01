
public class PhysicsHandler {
	
	float GRAVITY = (float) 0.1; 

	ObjectHandler handler = new ObjectHandler();
	
	public PhysicsHandler(ObjectHandler handler) {
		this.handler = handler; 
	}
	
	public void Tick() {
		
	}
	
	public void Gravity() {
		for (int i = 0; i < handler.character.size(); i++) {
		if (handler.character.get(i).isFalling() == true && !(handler.character.get(i).GetID() == "Box")) {
                  handler.character.get(i).SetSpeedY(handler.character.get(i).speed_y + GRAVITY);
		}
	}	
	}
}
