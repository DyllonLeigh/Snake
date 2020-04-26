import java.awt.*;

public class HUD extends GameObject {
    public HUD(int x, int y, ID id, boolean alive) {
        super(x, y, id, alive);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        /*g.setColor(Color.WHITE);
        g.fillRect(x, y, 100, 30);*/
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
