import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Cross on 15.08.2018.
 */
public class Player {
    public static int standardPlayerWidth = 80;
    public static int standardPlayerHeight = 10;
    private Rectangle hitbox;
    private Game instance;
    private MovementState movementState;
    public Player(final Game inst, int x, int y, int width, int height){
        instance = inst;
        hitbox = new Rectangle(x, y, width, height);
        movementState = new MovementState();
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hitbox.x += movementState.xDirection;
                if (hitbox.x <= 0) hitbox.x = 0;
                if (hitbox.x > instance.getGameDimention().width - Player.standardPlayerWidth) hitbox.x = instance.getGameDimention().width - Player.standardPlayerWidth;
                instance.repaint();
            }
        });
        timer.start();
    }

    public void render(Graphics g){
        g.setColor(new Color(200,200,200));
        g.fillRect(hitbox.x, hitbox.y, hitbox.width,hitbox.height);
    }

    public void moveOnYAxis(int speed){
        hitbox.x += speed;

    }

    public boolean collidesWith(Rectangle object){
        return hitbox.intersects(object);
    }

    private abstract class AbstractDirectionAction extends AbstractAction{
        private final MovementState movementState;
        private final int value;

        public AbstractDirectionAction (MovementState movementState, int value){
            this.movementState = movementState;
            this.value=value;
        }


        public MovementState getMovementState(){
            return movementState;
        }

        public int getValue(){
            return value;
        }

    }

    public class xDirectionAction extends AbstractDirectionAction{

        public xDirectionAction (MovementState movementState, int value){
            super (movementState, value);
        }
        @Override
        public void actionPerformed(ActionEvent e){
            getMovementState().xDirection = getValue();
        }
    }

    public class MovementState {
        public int xDirection;
    }

    public MovementState getMovementState(){
        return movementState;
    }



    public void setY(int y){
        hitbox.y = y;
    }

    public void setX(int x){
        hitbox.x = x;
    }
}
