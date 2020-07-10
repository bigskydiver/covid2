package pt.isel.poo.covid.model;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.tile.TilePanel;

import java.util.Scanner;
import java.util.ArrayList;
public class Level {
    public final int  levelNumber,height,width;
    private element[][] model ;
    private Hero hero;
    private Virus virus;
    private Wall wall;
    private Space space;
    private TrashCan trashCan;


    private ArrayList<Virus> virusList = new ArrayList<Virus>();
    public Level  (int levelNumber, int height, int width){
        this.levelNumber = levelNumber;
        this.height=height;
        this.width=width;
        model = new element[width][height];


    }
    private void initHero(Position pos){
         hero = new Hero(pos);

    }

    private void initVirus(Position pos){
        virus = new Virus(pos);

    }
    private void initWall(Position pos){
        wall = new Wall(pos);

    }
    private void initTrashCan(Position pos){
        trashCan = new TrashCan(pos);

    }
    private void initSpace(Position pos){
        space = new Space(pos);

    }


    public void put( int l,int  c, char type){
        Position pos = new Position( l,c);
        switch ( type ){
            case '.':
                initSpace(pos);
                model[c][l] = space;
                break;
            case '@':
                initHero(pos);
                model[c][l] = hero;
                break;
            case 'V':
                initTrashCan(pos);
                model[c][l] = trashCan;
                break;
            case 'X':
                initWall(pos);
                model[c][l] = wall;
                break;
            case '*':
                initVirus(pos);
                virusList.add(virus);
                model[c][l] = virus;
                break;
        }
    }

    public element getElement (Position pos){

       return model[pos.x][pos.y];
   }

    public element[][] getModel() {
        return model;
    }

    public Level loadslvl (Scanner in) throws Loader.LevelFormatException {
        Loader loader = new Loader (in);

     return loader.load(1);

    }

    public boolean moveElem(Direction dir , element elem){
        Position location = null;
        boolean moved = false;
        location = elem.getPos();
        if ( elem instanceof Hero){
            checkIfCollided(location,dir);
        }

        if(model[location.x  - dir.x ][location.y - dir.y].getElement() instanceof Space){
            model[location.x - dir.x ][location.y - dir.y ]= model[location.x][location.y];
            model[location.x][location.y] = new Space(new Position(location.x,location.y));
            model[location.x- dir.x ][location.y - dir.y].updatePos(new Position(location.x - dir.x,location.y -dir.y));
            moved= true;
        }

        return moved;
    }


    public void checkIfCollided(Position location,Direction dir){

        if(model[location.x  - dir.x ][location.y - dir.y].getElement() instanceof Virus){

            moveElem(dir,(model[location.x  - dir.x ][location.y - dir.y].getElement()));
            moveElem(dir,model[location.x][location.y].getElement());
        }

    }
    public element getHero(){
        return hero;
    }
    public  int getVirusLength(){return virusList.size();}
    public Virus getVirus ( int i){

        return virusList.get(i);

    }


    public void reset (){
        model = new element[width][height];
    }

    public int getNumber() {
        return levelNumber;
    }
}
