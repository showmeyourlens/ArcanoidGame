/**
 * Created by Cross on 15.08.2018.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

public class Game extends JPanel{ // doczytac o JP

    private Dimension gamefield = new Dimension(400,300);
    private double defgamefieldwidth = 0;
    private double defgamefieldheight = 0;
    public Player player;
    public Game(Frame container){
        container.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.moveOnYAxis(10);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) player.moveOnYAxis(-10);
                repaint();

            }
        });
        player = new Player(this, (gamefield.width - Player.standardPlayerWidth)/2, (gamefield.height - Player.standardPlayerHeight)/2, Player.standardPlayerWidth, Player.standardPlayerHeight);

    }


    public void setSize(Dimension size){
    super.setSize(size);
    gamefield = new Dimension(size.width-200, size.height-100);
    player.setY(gamefield.height - Player.standardPlayerHeight);
    }

    public void paint(Graphics g){
     super.paint(g);
     g.translate((getWidth()-gamefield.width)/2, (getHeight()-gamefield.height)/2);
     player.render(g);
     g.setColor(new Color(0,0,0));
     g.drawRect(0,0,gamefield.width, gamefield.height);
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

}
