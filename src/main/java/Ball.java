import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by Cross on 15.08.2018.
 */
public class Ball {

    public static int STANDARD_BALL_RADIUS = 20;
    public static int MAX_XDIFF = Player.standardPlayerWidth/2 + STANDARD_BALL_RADIUS;
    private Point2D.Double position = new Point2D.Double(0.0, 0.0);
    private int radius;
    private Game instance;
    private Vector vector = new Vector(0,0);
    public Ball(Game instance, double x, double y, int radius){
        this.instance = instance;
        position = new Point2D.Double(x,y);
        this.radius = radius;

    }
    public void setPosition (double x, double y){
        position = new Point2D.Double(x, y);
    }

    public void tick(){   // poprawic kolizje!!!!
        if (position.x - radius <= 0 && vector.xlength<0) vector.xlength = -vector.xlength;
        if (position.x + radius >= instance.getGameDimention().width && vector.xlength>0) vector.xlength = -vector.xlength;
        if (position.y <= 0 && vector.ylength<0) vector.ylength = -vector.ylength;
        if (position.y + radius >= instance.getGameDimention().height  && vector.ylength>0) instance.loseBall();



        if (instance.getPlayer() != null){                
            if (instance.getPlayer().collidesWith(new Rectangle((int)position.x - radius/2, (int)position.y, radius, radius*2))
                    && vector.ylength > 0) vector.setDeflectionVector(instance.getPlayer().getHitboxX());

        }
        position.setLocation(position.x + vector.xlength, position.y + vector.ylength);
    }

    public void setVector(double speed){
        vector.setVector(speed);
    }

    public void render(Graphics g){                        // Fuszerka!!!!!!!

        g.setColor(new Color(0,0,0));
        g.fillOval((int)(position.x - radius), (int)position.y, radius*2, radius*2);
            }
    public class Vector {

        double xlength;
        double ylength;
        double length;

        public Vector(double x, double y){
            xlength = x;
            ylength = y;
        }

        public void setVector (double length){

         Random generator = new Random();
         double sigma = generator.nextFloat();
         if (Math.pow(length, 2) - Math.pow(xlength, 2) > 0){
             xlength = length*sigma-1;
             ylength = Math.sqrt(Math.pow(length, 2) - Math.pow(xlength, 2));}
         else {
             xlength = 1;
             ylength = 1;
             System.out.println("Error");}

         this.length = (int)length;
        }

        public void setDeflectionVector(int hitbox_x){
            int xdiff = (int)position.x - hitbox_x - Player.standardPlayerWidth/2;
            System.out.println(xdiff);
            xlength = vector.length * xdiff/MAX_XDIFF;
            ylength = Math.sqrt(Math.pow(length, 2) - Math.pow(xlength, 2))*(-1);
        }

    }

}

