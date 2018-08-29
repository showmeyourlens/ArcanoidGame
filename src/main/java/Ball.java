import java.awt.*;

/**
 * Created by Cross on 15.08.2018.
 */
public class Ball {

    public static int STANDARD_BALL_RADIUS = 10;
    private Point position = new Point(0,0);
    private int radius;
    private Game instance;
    private Dimension vector = new Dimension(0,0);
    public Ball(Game instance, int x, int y, int radius){
        this.instance = instance;
        position = new Point(x,y);
        this.radius = radius;

    }
    public void setPosition (int x, int y){
        position = new Point(x, y);
    }

    public void tick(){
        if (position.x - radius <= 0 && vector.width<0) vector.width = -vector.width;
        if (position.x + radius >= instance.getGameDimention().width && vector.width>0) vector.width = -vector.width;
        if (position.y  <= 0 && vector.height<0) vector.height = -vector.height;
        if (position.y  >= instance.getGameDimention().height && vector.height>0) instance.loseBall();

        if (instance.getPlayer() != null){
            if (instance.getPlayer().collidesWith(new Rectangle(position.x - radius, position.y, radius*2, radius*2))) {
                vector.height = -vector.height;
            }
        }

        /* check exact position*/

        position.move(position.x + vector.width, position.y + vector.height);
    }

    public void setVector(int xMovement, int yMovement){
        vector = new Dimension(xMovement, yMovement);
    }

    public Point getPosition(){
        return position;
    }

    public void render(Graphics g){

        g.setColor(new Color(0,0,0));
        g.fillOval(position.x - radius, position.y, radius*2, radius*2);
            }
}
