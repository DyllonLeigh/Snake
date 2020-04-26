import java.awt.*;
import java.util.Random;

public class Tail extends GameObject {
    private int snakeLength;
    private Handler handler;

    public Tail(int x, int y, ID id, boolean alive, int snakeLength, Handler handler ) {
        super(x, y, id, alive);
        this.snakeLength = snakeLength;
        this.handler = handler;
    }

    @Override
    public void tick() {
        snakeLength--;
        if (snakeLength <= 0) handler.removeObject(this);
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
