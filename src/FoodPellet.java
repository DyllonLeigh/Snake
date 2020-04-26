import java.awt.*;
import java.util.Random;

public class FoodPellet extends GameObject {

    public FoodPellet(int x, int y, ID id, boolean alive) {
        super(x, y, id, alive);
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
