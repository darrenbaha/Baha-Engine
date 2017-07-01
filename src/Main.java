import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {
	
	
	static int WIN_X = 1280;
	static int WIN_Y = 720;
	static String GAME_TITLE = "Baha Engine ";
	static double VERSION = 0.4; 
	private Thread thread; 
	private boolean running = false;
	private int tickcount;
	static Character MainChar = new Character("Player", 200, 200, 40, 90);
	static Character NPC = new Character("Enemy_1", 400, 200, 40, 90); 
	static Character MainChar2 = new Character("Box", 0, 650, 1300, 50); 
	static ObjectHandler handle = new ObjectHandler(); 
	static PhysicsHandler physics = new PhysicsHandler(handle); 
	
    private enum STATE {
    	MAIN_MENU,
    	GAME
    };
    
    private static STATE state = STATE.MAIN_MENU;
    
    public synchronized void start() {
    	thread = new Thread(this);
    	thread.start();
    	running = true; 
    	
    }
    
    public synchronized void stop() {
    	try{
    		thread.join();
    		running = false; 
    	}catch(Exception e) {
    		
    	}
    	
    }
    
    public void run() {
    	long lastTime = System.nanoTime();
    	double amountOfTicks = 60.0;
    	double ns = 1000000000 / amountOfTicks;
    	double delta = 0;
    	long timer = System.currentTimeMillis();
    	int frames = 0;
    	while(running) 
    	{
    		long now = System.nanoTime();
    		delta += (now - lastTime) / ns; 
    		lastTime = now; 
    		while(delta >= 1) 
    		{
    			tick();
    			delta--; 
    		}
    		if(running) 
    			render();
    		frames++;
    		if(System.currentTimeMillis() - timer > 1000) 
    		{
    			timer += 1000;
    			System.out.println("FPS: " + frames);
    			frames = 0;
    		}
    	}
    	stop();
    }
    
    private void tick() {
    	tickcount++;  
    	
    if (state == STATE.GAME) {
    	
   
    	MainChar.Tick();
    	NPC.Tick();
    	Collision();
    	physics.Gravity();
    	NPCBrain(); 
    	
       }
    }
    
    LevelHandler level = new LevelHandler(); 
    
    private void render() {
    	BufferStrategy bs = this.getBufferStrategy(); 
    	if (bs == null) {
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	if (state == STATE.GAME) {
    	
    	g.fillRect(0, 0, WIN_X, WIN_Y);
    	g.setColor(Color.BLACK);

    	MainChar.Render(g);
    	MainChar2.Render(g);
    	NPC.Render(g); 
    	
    	}
    	
    	g.dispose();
    	bs.show();
    	
    }
    
    public Main() {
    	new Frame(WIN_X, WIN_Y, GAME_TITLE + VERSION +  " : Main Menu", this);
        this.addKeyListener(new ControlHandler(handle));
    	
    }
    
	public static void main(String[] args) {
	
		handle.addObject(MainChar);
		handle.addObject(MainChar2);
		handle.addObject(NPC);
		
	if (state == state.MAIN_MENU) {
		
			new Main();
			
	}
		
		System.out.println("**********************************");
		System.out.println("*                                *");
		System.out.println("*    Baha Engine V" + VERSION + "            *");
		System.out.println("*   created by Darren Bahadoor   *");
		System.out.println("*                                *");
		System.out.println("**********************************");

	    }
	
	public void SetInitialStats() {
				
		MainChar.Class = Frame.GetCCClass();
		MainChar.name = Frame.GetCCName();
		MainChar.gender = Frame.GetCCGender();
		
		MainChar.exp = 0; 
		if (MainChar.Class == 1) {
			MainChar.hp = 50; 
			MainChar.mp = 25; 
			MainChar.stamina = 20; 
		} else if (MainChar.Class == 2) {
			MainChar.hp = 25; 
			MainChar.mp = 50; 
			MainChar.stamina = 20; 
		} else if (MainChar.Class == 3) {
			MainChar.hp = 25; 
			MainChar.mp = 35; 
			MainChar.stamina = 30; 
		} else if (MainChar.Class == 4) {
			MainChar.hp = 35; 
			MainChar.mp = 20; 
			MainChar.stamina = 25; 
		}
		
		state = STATE.GAME;

		MainChar.setFalling(true);
		MainChar.setJumping(false);
		NPC.setFalling(true);
		NPC.setJumping(false); 
		NPC.active = true; 
		
		
	}
	
public void Collision() {
		
		if (MainChar.pos_x >= 1241) {
		    MainChar.pos_x = 1240;
		}
		if (MainChar.pos_x <= 0) {
			MainChar.pos_x = 1; 
		}
        if (MainChar.pos_y <= 0) {
        	MainChar.pos_y = 1;
        }
        if (MainChar.pos_y >= 639) {
        	MainChar.pos_y = 538; 

        }
		
        for (int i = 0; i < handle.character.size(); i++) {
        	if (MainChar.GetBoundary().intersects(handle.character.get(i).GetBoundary()) && !(handle.character.get(i).GetID() == "Player") && !(handle.character.get(i).GetID() == "Enemy_1")) {
        		if (MainChar.GetBoundaryBOT().intersects(handle.character.get(i).GetBoundary()) && !(handle.character.get(i).GetID() == "Player") && !(handle.character.get(i).GetID() == "Enemy_1")) {
        			
        			MainChar.SetSpeedY(0);
        			MainChar.setFalling(false); 
        			MainChar.setJumping(false);
        			
        			NPC.SetSpeedY(0);
        			NPC.setFalling(false); 
        			NPC.setJumping(false);
        			
        			MainChar.pos_y = MainChar.pos_y - 2;
        			//NPC.pos_y = NPC.pos_y - 2; 
        		}
        		if (MainChar.GetBoundaryTOP().intersects(handle.character.get(i).GetBoundary()) && !(handle.character.get(i).GetID() == "Player") && !(handle.character.get(i).GetID() == "Enemy_1")) {
                    MainChar.pos_y = MainChar.pos_y + 3;         		
        		}
        		if (MainChar.GetBoundaryLEFT().intersects(handle.character.get(i).GetBoundary()) && !(handle.character.get(i).GetID() == "Player") && !(handle.character.get(i).GetID() == "Enemy_1")) {
        			MainChar.pos_x = MainChar.pos_x + 3;
        		}
        		if (MainChar.GetBoundaryRIGHT().intersects(handle.character.get(i).GetBoundary()) && !(handle.character.get(i).GetID() == "Player") && !(handle.character.get(i).GetID() == "Enemy_1")) {
        			MainChar.pos_x = MainChar.pos_x - 3;
        		}
           }
	}
 }

public void NPCBrain() {
	
	if (NPC.id == "Enemy_1") {
          if (NPC.active == true) {
        	  if (handle.character.get(0).pos_x >= NPC.pos_x) {
        		  NPC.SetSpeedX(1);
        	  } else {
        		  NPC.SetSpeedX(-1);
        		 }
        	 }
         }
	} 
}

