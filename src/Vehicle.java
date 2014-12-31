/**
 * Vehicle generates the movement of each player in a 2D plane.
 * 
 * @author kk2575@nyu.edu
 * 
 */

public class Vehicle{
	// The positions, so they can be accessed throughout the code
	private float xPosition;
	private float yPosition;
	
	// The current speed
	private float currentSpeed;
	
	// Minimum, maximum, and the direction
	private float maxSpeed = 5;
	private float minSpeed = -5;
	private int heading;
	
	/** 
	 * The no arg constructor
	 */
	Vehicle(){
		
	}
	
	/**
	 * The constructor that takes in the x, y, and heading.
	 * @param x : the x position
	 * @param y : the y position
	 * @param direction
	 */
	Vehicle(float x, float y, int direction){
		xPosition=x;
		yPosition=y;
		heading = direction;
	}
	
	/**
	 * Copy constructor
	 * @param v
	 */
	public Vehicle(Vehicle v) {
		this(v.getX(), v.getY(), v.getHeading());
	}
	
	
	/**
	 * Gets the x position
	 * @return yPosition : returns the x position
	 */
	public float getX() {
		return xPosition;
	}
	
	/**
	 * Gets the y position
	 * @return yPosition : returns the y position
	 */
	public float getY() {
		return yPosition;
	}
	
	public void setX(int xPos) {
		this.xPosition = xPos;
	}
	
	public void setY(int yPos) {
		this.yPosition = yPos;
	}
	
	/**
	 * Makes the vehicles move
	 */
	void move() {
		xPosition += Math.cos(Math.toRadians(heading)) * currentSpeed/maxSpeed;
        yPosition += Math.sin(Math.toRadians(heading)) * currentSpeed/maxSpeed;
	}
	
	/**
	 * Makes the vehicles accelerate
	 * Because in real life, it takes time for vehicles to accelerate, I put a 10 millisecond pause at the end of each loop,
	 * to make the vehicles take time before they speed up. 
	 * ** I did it for all accelerate / decelerate / turnRight / turnLeft functions
	 */
	public void accelerate() {
		// When you accelarate, add keep going higher until it reaches the fastest the vehicle can go. 
		if(currentSpeed < maxSpeed){
			currentSpeed++;
			// 10 millisecond pause at the end of each loop
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// If the current speed is more than the maximum speed, that's a problem, and the current speed should go back to the max.
			if(currentSpeed > maxSpeed){
				currentSpeed = maxSpeed;
			}
		}	
	}
	
	/**
	 * Makes the vehicles decelerate, or slow down. See accelarate for more details. 
	 */
	public void decelerate(){
		if(currentSpeed >minSpeed){
			currentSpeed--;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(currentSpeed<minSpeed){
				currentSpeed=minSpeed;
			}
		}
	}
	
	/**
	 * Makes the vehicles turn right (when the respective keys are pressed) 
	 */
	public void turnRight(){
		// If the direction is less than 360, or a full revolution, add 1. 
		if (heading < 360) {
			heading += 1;
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// If it's more than 360, begin again. 
		else
			heading = 0;
	}
	
	/**
	 * Makes the vehicles turn left (when the respective keys are pressed) 
	 */
	public void turnLeft() {
		if (heading > 0){
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			heading -= 1;
		}
		else
			heading = 360;
	}
	
	/**
	 * The direction 
	 * @param heading
	 */
	public void setHeading(int heading) {
		this.heading = heading;
		
	}
	
	/**
	 * Returns the heading (or direction)
	 * @return heading
	 */
	public int getHeading() {
		return heading;
	}
	
	/**
	 * getSpeed : returns the current speed of the vehicle
	 * @return the speed the vehicle is moving now 
	 */
	public float getSpeed() {	
		return currentSpeed;
	}
		
	/**
	 * Setting the speed
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.currentSpeed = speed;
	}
	
	/**
	 * Setting the position
	 * @param x : the position of the vehicle (as an object)
	 * @param y : the position of the vehicle
	 */
	public void setPosition(float x, float y) {
		this.xPosition = x;
		this.yPosition = y;
		
	}
	
	/**
	 * If the vehicles are colliding
	 * @param v
	 * @return if the vehicles are colliding
	 */
	public Boolean equals(Vehicle v){
		if (this.xPosition == v.xPosition) {
			if (this.yPosition == v.yPosition) {
				if (this.heading == v.heading) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Printing the coordinates of the vehicles, called and printed in Player.java
	 */
	public String toString(){
		String statistics = 
				"The car is facing: " + heading +
				" with a speed of: " + currentSpeed +
				" with the x coordinate: " + xPosition +
				" and the y coordinate: " + yPosition;
		return statistics;
	}
}
