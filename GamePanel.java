package Main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    //game screen settings
    final int originalTileSize=16; //16x16 tile
    final int scale=3;
    public final int tileSize=originalTileSize*scale; //48x48 tile
    //window parameters
    public final int maxScreenCol=10;
    public final int maxScreenRow=13;
    public final int screenWidth=tileSize*maxScreenCol;
    public final int screenHeight=tileSize*maxScreenRow;
    //World settings
    public final int maxWorldCol=65;
    public final int maxWorldRow=214;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    //map
    TileManager tileM = new TileManager(this);
    Thread gameThread;
    KeyHandler keyH=new KeyHandler();
    public Player player=new Player(this, keyH);

    //FPS-Frames Per Second
    int FPS=60;
    //Collision checker
    public CollisionChecker cChecker=new CollisionChecker(this);
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));//set the size of this panel
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);//All the drawing from this component will be done in an offscreen painting buffer
        //can improve the game's rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();//calls run automatically
    }

    //Game Loop
   /* @Override-SLEEP METHOD
    public void run() {
        double drawInterval=1000000000/FPS;//draw the screen 16 times/second-0.01666 deconds
        double nextDrawTime=System.nanoTime()+drawInterval;

        while(gameThread!=null){

            //Update information
             update();
            //Draw the screen with the updated information
            repaint();

            //remaining time until next draw time

            try {

                double remainingTime=nextDrawTime-System.nanoTime();
                remainingTime=remainingTime/1000000;//nanoseconds to milliseconds

                if(remainingTime<0){
                    remainingTime=0;
                }

                Thread.sleep((long)remainingTime);//pause game loop until sleep time is over

                nextDrawTime+=drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
    @Override//-DELTA METHOD
    public void run() {
        double drawInterval=1000000000/FPS;//draw the screen 16 times/second-0.01666 deconds
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;

        while(gameThread!=null){
            currentTime=System.nanoTime();

            delta+=(currentTime-lastTime)/drawInterval;//how many times the screen could have been redrawn

            lastTime=currentTime;

            if(delta>=1){//if the screen could have been updated at least once
                //Update information
                update();
                //Draw the screen with the updated information
                repaint();

                delta--;
            }

        }
    }
    //upper left corner=> X:0, Y:0
    //X values increases to the right
    //Y values increases as they go down
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();//Disposes of this graphics context and releases any system resources that is using.
    }
}
