package pt.isel.poo.covid.model;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.tile.TilePanel;

import java.util.Scanner;
public class Level {
    public final int  levelNumber,height,width;
    private element[][] model ;
    public Level  (int levelNumber, int height, int width){
        this.levelNumber = levelNumber;
        this.height=height;
        this.width=width;
        model = new element[width][height];
    }

    public void put( int l,int  c, char type){
        Position pos = new Position( l,c);
        switch ( type ){
            case '.':
                model[c][l] = new Space(pos);
                break;
            case '@':
                model[c][l] = new Hero(pos);
                break;
            case 'V':
                model[c][l] = new TrashCan(pos);
                break;
            case 'X':
                model[c][l] = new Wall(pos);
                break;
            case '*':
                model[c][l] = new Virus(pos);
                break;
        }
    }

    public element getElement (Position pos){

        return model[pos.x][pos.y];
    }

    public element[][] getModel() {
        return model;
    }

    public void loadslvl (Scanner in) throws Loader.LevelFormatException {

        Loader loader = new Loader (in);

        Level level = loader.load(levelNumber);

    }

    public void reset (){
        model = new element[width][height];
    }

    public int getNumber() {
        return levelNumber;
    }
}
