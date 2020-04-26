import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int maxResX = 640, maxResY = 480, snakeSize = 20, foodSize = 12;

    private Random r = new Random();
    private Handler handler = new Handler();

    private String gameName = "Snake";

// start Player in the centre of the screen:
    private int playerPosX = (maxResX / 2) - snakeSize;
    private int playerPosY = (maxResY / 2) - snakeSize;

// Randomly Generate the Food Pellets initial position:
    private int foodPosX = r.nextInt(maxResX - foodSize);
    private int foodPosY = r.nextInt(maxResY - foodSize);

    private Thread thread;
    private boolean running = false;

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
// Listen for Key Presses:
        this.addKeyListener(new KeyInput(handler));
// Set Properties for new Window:
        new Window(maxResX + 5, maxResY + 29, gameName, this);
// Create the Player:
        handler.addObject(new Player(playerPosX, playerPosY, ID.Player, true, 1, handler));
// Create the HUD:
        handler.addObject(new HUD(2,2,ID.HUD,true));
// Create the Food Pellet:
        handler.addObject(new FoodPellet(foodPosX, foodPosY, ID.FoodPellet, true));
    }

// Runs the game continuously... tick()ing and render()ing at regular intervals
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double speed = 10;
        double ns = 1000000000 / speed;
        double speedSync = 0;

        while(running){
            long now = System.nanoTime();
            speedSync += (now - lastTime) / ns;
            lastTime = now;
            while (speedSync >= 1) {
                tick();
                speedSync--;
            }
            render();
            running = handler.object.get(0).alive;
        }
        System.exit(1);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public void tick(){
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0, maxResX, maxResY);

        handler.render(g);

        g.dispose();
        bs.show();
    }
}
