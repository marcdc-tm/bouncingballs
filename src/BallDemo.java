import java.awt.Color;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random randomGenerator;
    private HashMap<Integer, Color> colors;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 800, 500);
        randomGenerator = new Random();
        colors = new HashMap<>();
        colors.put(0, Color.BLUE);
        colors.put(1, Color.RED);
        colors.put(2, Color.GREEN);
        colors.put(3, Color.BLACK);
        colors.put(4, Color.YELLOW);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 750, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }

    public void bounce(int numberOfBalls) {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 750, ground);

        // create and show the balls
        HashSet<BouncingBall> balls = new HashSet<BouncingBall>();
        for (int i = 0; i < numberOfBalls; i++) {
            int yPos = 30 + randomGenerator.nextInt(100);
            int diameter = 10 + randomGenerator.nextInt(20);
            int kleur = randomGenerator.nextInt(5);
            int speed = randomGenerator.nextInt(31);
            BouncingBall ball = new BouncingBall(50 + 32 * i, yPos, diameter, colors.get(kleur), ground, myCanvas, speed);
            balls.add(ball);
            ball.draw();
        }

        // make them bounce
        boolean finished = false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for (BouncingBall ball : balls) {
                ball.move();
                // stoppen als de bal een zekere afstand langs x heeft afgelegd.     
                if (ball.getXPosition() >= 550 + 32 * numberOfBalls) {
                    finished = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        BallDemo bd = new BallDemo();
        bd.bounce(5);
    }
}
