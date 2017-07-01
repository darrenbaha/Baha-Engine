import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Character {
   
	String id; 
	String name; 
	double hp;
	double mp;
	double exp;
	int gender;
	int Class;
	int stamina; 
	float speed_x;
	float speed_y; 
	int pos_x = 0;
	int pos_y = 0;
	int width;
	int height;
	boolean falling;
	boolean jumping; 
	boolean active; //For NPC's
	Image img1 = Toolkit.getDefaultToolkit().getImage("floor.png");
	
	public Character(String id, int pos_x, int pos_y, int width, int height) {
          this.id = id; 
          this.pos_x = pos_x; 
          this.pos_y = pos_y; 
          this.width = width;
          this.height = height;
		  
	}
	
	public void Render(Graphics g) {
		
	if (this.id == "Player") {
		g.setColor(Color.GREEN);
		g.fillRect(this.pos_x, this.pos_y, width, height);
        
	} 
	
	if (this.id == "Box") {
		g.setColor(Color.WHITE);
		g.fillRect(this.pos_x, this.pos_y, width, height);
	}
	
	if (this.id == "Enemy_1") {
		g.setColor(Color.RED);
		g.fillRect(this.pos_x, this.pos_y, width, height);
	}
	
	}
	
	public void Tick() {
		
	if (this.id == "Player" || this.id == "Enemy_1") {
		this.pos_x += speed_x;
		this.pos_y += speed_y; 
	}
		
	}
	
	public Rectangle GetBoundary() {
		return new Rectangle(this.pos_x, this.pos_y, this.width, this.height); 
	}
	
	public Rectangle GetBoundaryBOT() {
		return new Rectangle(this.pos_x + 7, this.pos_y + 83, this.width - 15, this.height / 15); 
	}
	
	public Rectangle GetBoundaryTOP() {
		return new Rectangle(this.pos_x + 7, this.pos_y, this.width - 15, this.height / 15); 
	}
	
	public Rectangle GetBoundaryLEFT() {
		return new Rectangle(this.pos_x, this.pos_y + 20, this.width / 8, this.height / 2); 
	}
	
	public Rectangle GetBoundaryRIGHT() {
		return new Rectangle(this.pos_x + 35, this.pos_y + 20, this.width / 8, this.height / 2); 
	}
	
	public String GetID() {
		return this.id; 
	}
	
	public String GetName() {
		return this.name;
	}
	
	public double GetHp() {
		return this.hp;
	}
	
	public double GetMp() {
		return this.mp;
	}
	
	public double GetExp() {
		return this.exp;
	}
	
	public int GetGender() {
       return this.gender; 
	}
	
	public int GetClass() {
		return this.Class; 
	}
	
	public int GetStamina() {
		return this.stamina; 
	}
	
	public float GetSpeedX() {
		return this.speed_x;
	}
	
	public float GetSpeedY() {
		return this.speed_y;
	}
	
	public void SetName(String Name) {
	    this.name = Name; 
	}
	
	public int SetClass(int Class) {
		return this.Class = Class; 
	}
	
	public int SetGender(int Gend) {
		return this.gender = Gend; 
	}
	
	public void SetID(String ID) {
		this.id = ID; 
	}
	
	public float SetSpeedX(float speed) {
		return this.speed_x = speed;  
	}
	
	public float SetSpeedY(float speed) {
		return this.speed_y = speed;
	}
	
	public boolean isFalling() {
		
		return this.falling; 
	}
	
	public boolean setFalling(boolean fall) {
		return this.falling = fall;
	}
	
	public boolean setJumping(boolean jump) {
		return this.jumping = jump; 
	}
	
	public boolean isJumping() {
		return this.jumping; 
	}
}
