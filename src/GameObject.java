import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected boolean alive;
    protected int velX, velY;

    public GameObject(int x, int y, ID id, boolean alive) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.alive = alive;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

/*    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }*/
}
