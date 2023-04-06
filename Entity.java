package entity;

import java.awt.image.BufferedImage;

//stores the variables that will be used for the res.res.player, monster and NPC classes
public class Entity {
    public int x,y;
    public int speed;

    public BufferedImage up, down, left, right, up_left, up_right, down_left, down_right;
    public String direction;

}
