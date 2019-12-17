package logic;

import javafx.scene.image.ImageView;

public abstract class Character extends ImageView{
	protected int velocity;
	protected int life;
	protected int characterRadius;
	protected String type;
	
	public abstract void setVelocity();
	public abstract void setLifepoint();

	public int getVelocity() {
		return this.velocity;
	}
	public int getLifepoint() {
		return this.life;
	}
	public String getType() {
		return this.type;
	}


}
