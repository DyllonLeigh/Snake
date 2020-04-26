import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int maxResX = 640, maxResY = 480, snakeSize = 20, foodSize = 10;

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

        this.addKeyListener(new KeyInput(handler));

        new Window(maxResX + 5, maxResY + 29, gameName, this);

        handler.addObject(new Player(playerPosX, playerPosY, 1, ID.Player, false, handler));

        handler.addObject(new FoodPellet(foodPosX, foodPosY, ID.FoodPellet, false, handler));

    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double speed = 10;                           // The number of times per second your actions are processed
        double ns = 1000000000 / speed;
        double speedSync = 0;

        while(running){
            long now = System.nanoTime();
            speedSync += (now - lastTime) / ns;         // Amount of Times to loop before Rendering
            lastTime = now;
            while (speedSync >= 1){                     // This loop regulates the game speed
                tick();
                speedSync--;
            }
            render();
        }
        stop();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join();
            running = false;
            System.exit(1);
        } catch(Exception e) {
            e.printStackTrace();
        }
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
