package tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNumber;

    public TileManager(GamePanel gp){
        this.gp=gp;
        tile=new Tile[20];
        mapTileNumber = new int [gp.maxWorldRow][gp.maxWorldCol];
        for(int i=0; i<gp.maxWorldRow; i++)
        {
            Arrays.fill(mapTileNumber[i], 0, gp.maxWorldCol-1, 19);
        }
        getTileImage();
        loadMap("/maps/map.txt");
        int x=3;
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0water.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/1earth.png"));
            tile[1].collision=true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/2shore_left.png"));
            tile[2].collision=true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/3shore_right.png"));
            tile[3].collision=true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/4inner_corner_up_left.png"));
            tile[4].collision=true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/5inner_corner_up_right.png"));
            tile[5].collision=true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/6corner_down_right.png"));
            tile[6].collision=true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/7corner_down_left.png"));
            tile[7].collision=true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tiles/8corner_up_right.png"));
            tile[8].collision=true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tiles/9corner_up_left.png"));
            tile[9].collision=true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/10inner_corner_down_left.png"));
            tile[10].collision=true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/11inner_corner_down_right.png"));
            tile[11].collision=true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/12down_shore1.png"));
            tile[12].collision=true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/13up_shore1.png"));
            tile[13].collision=true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/14down_shore2.png"));
            tile[14].collision=true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/tiles/15up_shore2.png"));
            tile[15].collision=true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/tiles/0water.png"));
            tile[19].collision=true;


        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br =new BufferedReader(new InputStreamReader(is));

            /*int col=0;
            int row=0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line =br.readLine();
                while(col<gp.maxWorldCol){
                    String[] numbers=line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[row][col] = num;
                    col++;
                }
                if(col== gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }*/
            int row=0;
            for(row=0; row<gp.maxWorldRow; row++){
                int col=0;
                String line=br.readLine();
                String[] numbers=line.split(" ");
                for(col=0; col<gp.maxWorldCol; col++)
                {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNumber[row][col] = num;
                }
            }

            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
            int tileNum = mapTileNumber[worldRow][worldCol];

            int worldX=worldCol*gp.tileSize;
            int worldY=worldRow*gp.tileSize;

            int screenX=worldX-gp.player.worldX + gp.player.screenX;
            int screenY=worldY-gp.player.worldY + gp.player.screenY;

            //loading tiles as long as they are in the player screen
            if(worldX + gp.tileSize>gp.player.worldX-gp.player.screenX &&
                    worldX - gp.tileSize<gp.player.worldX+gp.player.screenX &&
                    worldY + gp.tileSize>gp.player.worldY-gp.player.screenY &&
                    worldY - gp.tileSize<gp.player.worldY+gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
