import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class ControlHandler extends KeyAdapter {
	
	ObjectHandler handle = new ObjectHandler(); 
	
	public ControlHandler(ObjectHandler handle) {
		this.handle = handle;  
	}
	
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    	
      if (e.getKeyCode() == KeyEvent.VK_RIGHT && handle.character.get(0).GetID() == "Player") {
    	  //handle.character.get(0).SetSpeedX(3); 
    		  handle.character.get(0).SetSpeedX(3); 
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT && handle.character.get(0).GetID() == "Player") {

    		  handle.character.get(0).SetSpeedX(-3); 
      }
      if (e.getKeyCode() == KeyEvent.VK_UP && handle.character.get(0).GetID() == "Player") {
      }
      if (e.getKeyCode() == KeyEvent.VK_DOWN && handle.character.get(0).GetID() == "Player") {
      }
      if (e.getKeyCode() == KeyEvent.VK_SPACE && handle.character.get(0).GetID() == "Player" && handle.character.get(0).isJumping() == false) {
    	  handle.character.get(0).setJumping(true); 
    	  handle.character.get(0).SetSpeedY(-4); 
    	  handle.character.get(0).setFalling(true); 
    	  
    	  System.out.println("Player : " + handle.character.get(0).isFalling());
    	  System.out.println("NPC : " + handle.character.get(1).isFalling());
      }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && handle.character.get(0).GetID() == "Player") {
      	  handle.character.get(0).SetSpeedX(0); 
      	  //System.out.println("X : " + handle.character.get(0).pos_x + "Y : " + handle.character.get(0).pos_y);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && handle.character.get(0).GetID() == "Player") {
      	  handle.character.get(0).SetSpeedX(0); 
      	  //System.out.println("X : " + handle.character.get(0).pos_x + "Y : " + handle.character.get(0).pos_y);
        }
    }

	
	
}