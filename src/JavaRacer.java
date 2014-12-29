import java.awt.Point;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * A basic racing game.
 * 
 * @author kk2575@nyu.edu
 * 
 *         multiple key check taken from
 *         http://wiki.processing.org/index.php?title=Keep_track_of_multiple_key_presses:
 * @author Yonas Sandbæk http://seltar.wliia.org (modified by jeffg)
 * 
 * @version 0.01 Initial assignment
 */

@SuppressWarnings("serial")
public class JavaRacer extends PApplet {
    static final int BGCOLOR = 0; // black background
    static final String TRACK = "racetrack.png";
    static int CAR_SPACING = 10;
    static int CAR_LENGTH = 10;
    static int CAR_WIDTH = 5;
    static int COLLISION_THRESHOLD = 500;

    // Globals
    Player player1;
    Player player2;
    PImage raceTrack;

    // Used to keep track of multiple key presses
    boolean[] keys = new boolean[526]; // number of possible key codes in processing

    // Providing a main allows the project to be exported as an application
    static public void main(String args[]) {
        PApplet.main("JavaRacer");
    }

    // This runs just once when the program starts to initialize the canvas
    public void setup() {
        // Set up the track
        raceTrack = loadImage(TRACK);
        loadPixels();
        size(raceTrack.width, raceTrack.height);

        // Set staring location based on the raceTrack image
        int startingXPosition = 60;
        int startingYPosition = 125;

        // Create cars and set orientation at starting line
        player1 = new Player("Player1", startingXPosition, startingYPosition);
        player1.turnRight(90);
        player2 = new Player("Player2", startingXPosition, startingYPosition + CAR_SPACING);
        player2.turnRight(90);

    }

    // this is recalled continuously while the program is running
    public void draw() {
        background(BGCOLOR);
        image(raceTrack, 0, 0);
        drawObjects();
        drawText();
        processInput();
        moveObjects();

    }

    /**
     * Draw any text necessary to the canvas
     */
    private void drawText() {
        fill(255,204,0);
        textSize(20);
        text("Player1 controls: W, S, A, D", 10, 20);
        text("Player2 controls: UP, DOWN, LEFT, RIGHT", 10, 40);
    }
    
    /**
     * Draw any objects necessary to the canvas
     */
    private void drawObjects() {
        fill(255, 0, 0);
        drawPlayer(player1);
        fill(0, 255, 0);
        drawPlayer(player2);
    }

    /**
     * Draw a player's object to the canvas
     */
    private void drawPlayer(Player player) {
        // Move to player position and orient to current heading
        translate(player.getX(), player.getY());
        rotate((float) Math.toRadians(player.getHeading()));

        // Draw the player's car
        rect(0, 0, CAR_LENGTH, CAR_WIDTH);

        // Undo positional changes (so that these changes don't affect any other objects)
        rotate((float) -Math.toRadians(player.getHeading()));
        translate(-player.getX(), -player.getY());
    }

    /**
     * Move any objects on the screen that might need moving
     */
    private void moveObjects() {
        player1.move();
        player2.move();
        checkCollision(player1);
        checkCollision(player2);
    }

    /**
     * Check if player collided with the wall. If they collided make the necessary adjustments to
     * the player.
     * 
     * @param player to check for collisions
     */
    private void checkCollision(Player player) {
        if (isCollision(player)) {
            // In the event of a collision:
            // - Set player to last position
            // - Reduce player speed by 25%
            player.setPosition(player.getLastX(), player.getLastY());
            player.setSpeed((float) (player.getSpeed() * .25));
        }
    }

    private boolean isCollision(Player player) {
        // TODO: There is a bug here because the calculated corners do not take into account any
        // rotation of that car that may have occurred due to turns.
        
        // 4 corners of the player's vehicle
        Point[] corners = new Point[4];
        corners[0] = new Point(player.getX(), player.getY());
        corners[1] = new Point(player.getX() + (2*CAR_LENGTH), player.getY());
        corners[2] = new Point(player.getX(), player.getY() + CAR_WIDTH);
        corners[3] = new Point(player.getX() + (2*CAR_LENGTH), player.getY() + CAR_WIDTH);
        // Check if any corner of the car collided with a wall
        
        
        for (Point corner : corners) {
            int color = raceTrack.get(corner.x, corner.y);
            float r = red(color);
            float g = green(color);
            float b = blue(color);
            if ((r + g + b) > COLLISION_THRESHOLD) {
                return true;
            }
        }
        return false;
    }

    /**
     * Process keyboard input from the user
     */
    void processInput() {
        // Player1 inputs
        if (checkKey('W'))
            player1.decelerate();
        if (checkKey('S'))
            player1.accelerate();
        if (checkKey('A'))
            player1.turnLeft();
        if (checkKey('D'))
            player1.turnRight();

        // Player2 inputs
        if (checkKey(UP))
            player2.decelerate();
        if (checkKey(DOWN))
            player2.accelerate();
        if (checkKey(LEFT))
            player2.turnLeft();
        if (checkKey(RIGHT))
            player2.turnRight();
    }

    /**
     * Determine if a key <code>k</code> is currently pressed or not.
     * 
     * @param k key to check
     * @return true if <code>k</code> is pressed, otherwise false
     */
    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k];
        }
        return false;
    }

    /**
     * Update key status on key press
     */
    public void keyPressed() {
        keys[keyCode] = true;
    }

    /**
     * Update key status on key release
     */
    public void keyReleased() {
        keys[keyCode] = false;
    }
}
