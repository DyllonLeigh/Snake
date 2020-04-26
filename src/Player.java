import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    private Random r = new Random();
    private int snakeLength;
    private Handler handler;

    public Player(int x, int y, ID id, boolean alive, int snakeLength, Handler handler){
        super(x, y, id, alive);
        this.snakeLength = snakeLength;
        this.handler = handler;
    }

    @Override
    public void tick() {
// Keeping X and Y within the bounds of the Window:
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

// Check for Collisions:
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.id == ID.FoodPellet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    snakeLength++;

                    int a = r.nextInt(Game.maxResX - Game.foodSize);
                    int b = r.nextInt(Game.maxResY - Game.foodSize);

                    handler.removeObject(tempObject);
                    handler.addObject(new FoodPellet(a, b, ID.FoodPellet, true));
                }
            }
            if (tempObject.id == ID.Tail) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    this.alive = false;
                }
            }

        }
// The Star of the Show!!!
        handler.addObject(new Tail(x, y, ID.Tail, true, snakeLength, handler));

        x += velX;
        y += velY;

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
