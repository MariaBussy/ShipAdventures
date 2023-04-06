package entity;

import Main.GamePanel;
import Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    final int originalPlayerWidth=55;
    final int originalPlayerHeight=100;
    final int scale=3;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x=220;
        y=500;
        speed=4;
        direction="up";
    }

    public void getPlayerImage(){
        try{
            up= ImageIO.read(getClass().getResourceAsStream("/player/ship_up.png"));
            down=ImageIO.read(getClass().getResourceAsStream("/player/ship_down.png"));
            left=ImageIO.read(getClass().getResourceAsStream("/player/ship_left.png"));
            right=ImageIO.read(getClass().getResourceAsStream("/player/ship_right.png"));
            up_left=ImageIO.read(getClass().getResourceAsStream("/player/ship_up_left.png"));
            up_right=ImageIO.read(getClass().getResourceAsStream("/player/ship_up_right.png"));
            down_left=ImageIO.read(getClass().getResourceAsStream("/player/ship_down_left.png"));
            down_right=ImageIO.read(getClass().getResourceAsStream("/player/ship_down_right.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed){
            direction="up";
            y-=speed;
        }
        else if(keyH.downPressed){
            direction="down";
            y+=speed;
        }
        else if(keyH.leftPressed){
            direction="left";
            x-=speed;
        }
        else if(keyH.rightPressed){
            direction="right";
            x+=speed;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image=null;
        switch (direction){
            case "up":
                image=this.up;
                break;
            case "down":
                image=this.down;
                break;
            case "left":
                image=this.left;
                break;
            case "right":
                image=this.right;
                break;
        }
        if(direction.equals("up") || direction.equals("down"))
            g2.drawImage(image, x, y, originalPlayerWidth, originalPlayerHeight, null);
        else
            g2.drawImage(image, x, y, originalPlayerHeight, originalPlayerWidth, null);
    }
}
