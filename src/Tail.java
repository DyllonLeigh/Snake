import java.awt.*;
import java.util.Random;

public class Tail extends GameObject {
    private Handler handler;
    private int snakeLength;

    public Tail(int x, int y, int snakeLength, ID id, boolean arg, Handler handler ) {
        super(x, y, id, arg);
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
        g.fillRect(x, y, Game.snakeSize, Game.snakeSize);
        g.setColor(Color.green);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, Game.snakeSize, Game.snakeSize);
    }
}
