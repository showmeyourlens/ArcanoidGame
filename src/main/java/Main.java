import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

/**
 * Created by Cross on 15.08.2018.
 */


public class Main {
    public static JFrame frame;
    public static Game game;
    public static int frameStartWidth = 600;
    public static int frameStartHeight = 600;
    public static void main(String[] args){
        frame = new JFrame("Arcanoid game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(frameStartWidth,frameStartHeight);
        frame.setLocationRelativeTo(null);


        game = new Game(frame);
        game.setSize(frame.getSize());
        frame.add(game);


        frame.setVisible(true);

        /*frame.getContentPane().addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                game.setSize(frame.getSize());
            }
        });*/

    }


}
