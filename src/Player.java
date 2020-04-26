import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    private Handler handler;
    private Random r = new Random();
    private int snakeLength;

    public Player(int x, int y, int snakeLength, ID id, boolean arg, Handler handler){
        super(x, y, id, arg);
        this.snakeLength = snakeLength;
        this.handler = handler;
    }

    @Override
    public void tick() {

        if (x < 0) {
            x = (Game.maxResX - Game.snakeSize);
        } else if (x > (Game.maxResX - Game.snakeSize)) {
            x = 0;
        }
        if (y < 0) {
            y = (Game.maxResY - Game.snakeSize);
        } else if (y > (Game.maxResY - Game.snakeSize)) {
            y = 0;
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.FoodPellet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    snakeLength++;

                    // Randomising Pellets next location.
                    int a = r.nextInt(Game.maxResX - Game.foodSize);
                    int b = r.nextInt(Game.maxResY - Game.foodSize);

                    // Create a new Food Pellet.
                    handler.removeObject(tempObject);
                    handler.addObject(new FoodPellet(a, b, ID.FoodPellet, false, handler));
                }
            }
        }

        x += velX;
        y += velY;

        handler.addObject(new Tail(x, y, snakeLength, ID.Tail, false, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, Game.snakeSize, Game.snakeSize);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, Game.snakeSize, Game.snakeSize);
    }
}