package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

//stores the variables that will be used for the res.res.player, monster and NPC classes
public class Entity {
    //position information and update
    public int worldX,worldY;
    public int speed;

    //images used for the player
    public BufferedImage up, down, left, right, up_left, up_right, down_left, down_right;
    public String direction;

    //marking the border of the player
    public Rectangle solidArea;
    public boolean collisionOn=false;

}
