/**
 * Created by Cross on 15.08.2018.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

public class Game extends JPanel{ // doczytac o JP

    private Dimension gamefield = new Dimension(400,300);
    public Player player;
    public Ball ball;
    private boolean isPaused = false;
    private boolean isRunning = false;

    public Game(Frame container){
        player = new Player(this, (gamefield.width - Player.standardPlayerWidth)/2, (gamefield.height - Player.standardPlayerHeight)/2,
                Player.standardPlayerWidth, Player.standardPlayerHeight);
        ball = new Ball(this, gamefield.width/2, gamefield.height/2, Ball.STANDARD_BALL_RADIUS);

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "right-stop");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "left-stop");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0 ), "space");
        
        am.put("right", player.new XDirectionAction(player.getMovementState(), 10));
        am.put("left", player.new XDirectionAction(player.getMovementState(), -10));
        am.put("left-stop", player.new XDirectionAction(player.getMovementState(), 0));
        am.put("right-stop", player.new XDirectionAction(player.getMovementState(), 0));
        am.put("space", new SpaceAction());

    }


    public void setSize(Dimension size){
    super.setSize(size);
    if (!isRunning) {
        gamefield = new Dimension(size.width - 200, size.height - 100);
        ball.setPosition(gamefield.width/2, gamefield.height/2);
        player.setY(gamefield.height - Player.standardPlayerHeight);
        player.setX((gamefield.width  - Player.standardPlayerWidth)/2);
        }
    }

    public void paint(Graphics g){
     super.paint(g);
     g.translate((getWidth()-gamefield.width)/2, (getHeight()-gamefield.height)/2);
     ball.render(g);
     player.render(g);
     g.setColor(new Color(0,0,0));
     g.drawRect(0,0,gamefield.width, gamefield.height);

    }

    public void start(){

        isPaused = false;
        if (!isRunning) gameThread.start();
    }

    public void pause(){
        isPaused = true;
    }

    public void stop(){
        isRunning = false;
    }


    private Thread gameThread = new Thread(new Runnable() {
        public void run() {
            isRunning = true;
            ball.setVector(2, 2);  // speed need to be variabe
            while (isRunning) {
                if (!isPaused) {
                ball.tick();
                repaint();
                }
                    try {
                        Thread.sleep(10);
                        System.out.println("werk");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }

        }
    });

    public void loseBall(){
        pause();
        ball.setPosition(gamefield.width/2, gamefield.height/2);
        player.setX(gamefield.width / 2);
        repaint();

    }

    public void setPlayer(Player player){
        this.player = player;
    }
    public Player getPlayer(){
        return player;
    }
    public Dimension getGameDimention(){
        return gamefield;
    }

    
    public class SpaceAction extends AbstractAction{
        
        public void actionPerformed(ActionEvent e){
            if (!isRunning || isPaused) start();
            else pause();
        }  
    }
    
    
}
