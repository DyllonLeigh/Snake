import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                // key events for player
                if((key == KeyEvent.VK_W) && !(tempObject.getVelY() > 0)) {
                    tempObject.setVelY( - Game.snakeSize);
                    tempObject.setVelX(0);
                }
                if((key == KeyEvent.VK_S) && !(tempObject.getVelY() < 0)) {
                    tempObject.setVelY(Game.snakeSize);
                    tempObject.setVelX(0);
                }
                if((key == KeyEvent.VK_D) && !(tempObject.getVelX() < 0)) {
                    tempObject.setVelY(0);
                    tempObject.setVelX(Game.snakeSize);
                }
                if((key == KeyEvent.VK_A) && !(tempObject.getVelX() > 0)) {
                    tempObject.setVelY(0);
                    tempObject.setVelX( - Game.snakeSize);
                }
            }
        }
    }
}
