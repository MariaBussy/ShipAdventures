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

    //where the player is drawn on the screen
    public final int screenX;
    public final int screenY;

    final int originalPlayerWidth=55;
    final int originalPlayerHeight=100;
    final int scale=3;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH=keyH;

        screenX=gp.screenWidth/2-gp.tileSize/2;
        screenY=gp.screenHeight/2-gp.tileSize/2;

        solidArea=new Rectangle(14, 29, 40, 80);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        //coordinates for starting position
        worldX=gp.tileSize*32;
        worldY=gp.tileSize*200;
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
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed){
                direction="up";
            }
            else if(keyH.downPressed){
                direction="down";
            }
            else if(keyH.leftPressed){
                direction="left";
            }
            else if(keyH.rightPressed){
                direction="right";
            }
        }

        //check tile collision
        collisionOn=false;
        gp.cChecker.checkTile(this);

        //if there is no collision
        if(!collisionOn){
            switch(direction){
                case "up":
                    worldY-=speed;
                    break;
                case "down":
                    worldY+=speed;
                    break;
                case "left":
                    worldX-=speed;
                    break;
                case "right":
                    worldX+=speed;
                    break;
            }
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
            g2.drawImage(image, screenX, screenY, originalPlayerWidth, originalPlayerHeight, null);
        else
            g2.drawImage(image, screenX, screenY, originalPlayerHeight, originalPlayerWidth, null);
    }
}
