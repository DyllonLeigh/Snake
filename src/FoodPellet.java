import java.awt.*;
import java.util.Random;

public class FoodPellet extends GameObject {

    private Handler handler;
    private boolean beenEaten = false;
    private Random r = new Random();

    public FoodPellet(int x, int y, ID id, boolean beenEaten, Handler handler) {
        
        super(x, y, id, beenEaten);
        this.handler = handler;
    }

    @Override
    public void tick() {    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, Game.foodSize, Game.foodSize);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, Game.foodSize, Game.foodSize);
    }
}
