import java.awt.*;

/**
 * Created by Cross on 15.08.2018.
 */
public class Player {
    public static int standardPlayerWidth = 80;
    public static int standardPlayerHeight = 10;
    private Rectangle hitbox;
    private Game instance;
    public Player(Game inst, int x, int y, int width, int height){
        instance = inst;
        hitbox = new Rectangle(x, y, width, height);
    }

    public void render(Graphics g){
        g.setColor(new Color(200,200,200));
        g.fillRect(hitbox.x, hitbox.y, hitbox.width,hitbox.height);
    }

    public void moveOnYAxis(int speed){
        hitbox.x += speed;
        if (hitbox.x <= 0) hitbox.x = 0;
        if (hitbox.x > instance.getGameDimention().width - Player.standardPlayerWidth) hitbox.x = instance.getGameDimention().width - Player.standardPlayerWidth;
    }

    public void setY(int y){
        hitbox.y = y;
    }
}
