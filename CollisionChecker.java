package Main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX= entity.worldX+entity.solidArea.x;//x upper left corner+offset
        int entityRightWorldX= entity.worldX+entity.solidArea.x+entity.solidArea.width;//x upper left corner+offset+width of the solid area
        int entityTopWorldY=entity.worldY+entity.solidArea.y;//y upper left corner + offset
        int entityBottomWorldY=entity.worldY+entity.solidArea.y+entity.solidArea.height;// y upper left corner+offset+height of solid area

        //scaling to our measure of unit aka tileSize
        int entityLeftCol=entityLeftWorldX/gp.tileSize;//left x
        int entityRightCol=entityRightWorldX/gp.tileSize;//right x
        int entityTopRow=entityTopWorldY/gp.tileSize;//top y
        int entityBottomRow=entityBottomWorldY/gp.tileSize;//bottom y

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                //calculating the coordinates of the top line of solid area
                entityTopRow=(entityTopWorldY-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNumber[entityTopRow][entityLeftCol];
                tileNum2=gp.tileM.mapTileNumber[entityTopRow][entityRightCol];
                //player is hitting a solid tile
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "down":
                entityBottomRow=(entityBottomWorldY+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNumber[entityBottomRow][entityLeftCol];
                tileNum2=gp.tileM.mapTileNumber[entityBottomRow][entityRightCol];
                //player is hitting a solid tile
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "left":
                entityLeftCol=(entityLeftWorldX-entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNumber[entityTopRow][entityLeftCol];
                tileNum2=gp.tileM.mapTileNumber[entityBottomRow][entityLeftCol];
                //player is hitting a solid tile
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
            case "right":
                entityRightCol=(entityRightWorldX+entity.speed)/gp.tileSize;
                tileNum1=gp.tileM.mapTileNumber[entityTopRow][entityRightCol];
                tileNum2=gp.tileM.mapTileNumber[entityBottomRow][entityRightCol];
                //player is hitting a solid tile
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn=true;
                }
                break;
        }
    }
}
