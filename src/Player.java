import java.awt.Point;

/**
 * Player is a representation for a player in a game that drives some kind of vehicle in a 2D plane.
 * 
 * @author kk2575@nyu.edu
 * 
 */
public class Player {
    private static float SLOW_MOVING_VEHICLE = 0.1f;
    String name;
    Vehicle vehicle;    // The vehicle this player is driving
    int wins, losses;   // Number of wins and loses this player has had
    float lastX, lastY; // In case the player needs to be reverted to it's previous position
    int lap;            // Which lap the player is currently on

    /**
     * No argument constructor creates a default Player object.
     */
    Player() {
        name = "Player";
        vehicle = new Vehicle();
    }

    /**
     * Creates a new Player with the corresponding values
     * 
     * @param name - name of the player
     * @param xPos - x position of the player's paddle
     * @param yPos - y position of the player's paddle
     */
    public Player(String name, int xPos, int yPos) {
        this.name = name;
        vehicle = new Vehicle(xPos, yPos, 90);
    }

    /**
     * Copy constructor
     * 
     * @param player to make a copy of
     */
    public Player(Player player) {
        this.name = player.name;
        this.lastX = player.lastX;
        this.lastY = player.lastY;
        this.wins = player.wins;
        this.losses = player.losses;
        this.vehicle = new Vehicle(player.vehicle);
    }

    /**
     * @return a String representation of this player
     */
    public String toString() {
        return "Player: " + name + "\n  " + vehicle;
    }

    /**
     * Determines if this player is equivalent to another player
     * 
     * @param player to compare this player to
     * @return true if this player and <code>player</code> have all equivalent data, otherwise false
     */
    public boolean equals(Player player) {
        if ((name.equals(player.name)) && (wins == player.wins) && (losses == player.losses)
                && (vehicle.equals(player.vehicle))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the name of this player
     */
    public String getName() {
        return name;
    }

    /**
     * @return the number of wins for this player
     */
    public int getWins() {
        return wins;
    }

    /**
     * @return the number of losses for this player
     */
    public int getLosses() {
        return losses;
    }

    /**
     * @return the x coordinate for this player
     */
    public int getX() {
        return (int) vehicle.getX();
    }

    /**
     * @return the y coordinate for this player
     */
    public int getY() {
        return (int) vehicle.getY();
    }

    /**
     * @return the location (x,y coordinate) of this player
     */
    public Point getPosition() {
        return new Point((int) vehicle.getX(), (int) vehicle.getY());
    }

    /**
     * Called when this player is pushing the gas pedal
     */
    public void accelerate() {
        vehicle.accelerate();
    }

    /**
     * Called when this player is pushing the brake pedal
     */
    public void decelerate() {
        vehicle.decelerate();
    }

    /**
     * Called when the player is turning the wheel left
     */
    public void turnLeft() {
        vehicle.turnLeft();
    }

    /**
     * Called when the player is turning the wheel right
     */
    public void turnRight() {
        vehicle.turnRight();
    }

    /**
     * @return the direction this player is facing (in degrees)
     */
    public int getHeading() {
        return vehicle.getHeading();
    }

    /**
     * Updates the player's position if appropriate
     */
    public void move() {
        lastX = vehicle.getX();
        lastY = vehicle.getY();
        if (Math.abs(vehicle.getSpeed()) > SLOW_MOVING_VEHICLE) {
            System.out.println(this);
            vehicle.move();
        }
    }

    /**
     * Updates the players heading by turning a specific amount clockwise
     * 
     * @param degrees to turn the player
     */
    public void turnRight(int degrees) {
        vehicle.setHeading(vehicle.getHeading() + degrees);
    }

    /**
     * Updates the players heading by turning a specific amount counter-clockwise
     * 
     * @param degrees to turn the player
     */
    public void turnLeft(int degrees) {
        vehicle.setHeading(vehicle.getHeading() - degrees);
    }

    /**
     * @return player's current speed
     */
    public float getSpeed() {
        return vehicle.getSpeed();
    }

    /**
     * Sets the player's speed.
     * 
     * @param speed to set the player to
     */
    public void setSpeed(float speed) {
        vehicle.setSpeed(speed);
    }

    /**
     * @return the last x coordinate for this player
     */
    public float getLastX() {
        return lastX;
    }

    /**
     * @return the last y coordinate for this player
     */
    public float getLastY() {
        return lastY;
    }

    /**
     * Set the location for this player.
     * 
     * @param x coordinate for the player
     * @param y coordinate for the player
     */
    public void setPosition(float x, float y) {
        lastX = vehicle.getX();
        lastY = vehicle.getY();
        vehicle.setPosition(x, y);
    }

    /**
     * @return which lap the player is on
     */
    public int getLap() {
        return lap;
    }

    /**
     * Set this players lap counter.
     * 
     * @param lap to set this player's lap to
     */
    public void setLap(int lap) {
        this.lap = lap;
    }
}