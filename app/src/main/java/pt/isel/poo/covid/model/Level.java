package pt.isel.poo.covid.model;

import android.util.Log;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.tile.TilePanel;
import pt.isel.poo.covid.view.HeroTile;
import pt.isel.poo.covid.view.TrashTile;
import pt.isel.poo.covid.view.VirusTile;
import pt.isel.poo.covid.view.WallTile;

import java.io.PrintStream;
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
                model[l][c] = space;
                break;
            case '@':
                initHero(pos);
                model[l][c] = hero;
                break;
            case 'V':
                initTrashCan(pos);
                model[l][c] = trashCan;
                break;
            case 'X':
                initWall(pos);
                model[l][c] = wall;
                break;
            case '*':
                initVirus(pos);
                virusList.add(virus);
                model[l][c] = virus;
                break;
        }
    }

    public element getElement (Position pos){

       return model[pos.y][pos.x];
   }

    public element[][] getModel() {
        return model;
    }

    public Level loadslvl (Scanner in, int lvlnbr) throws Loader.LevelFormatException {
        Loader loader = new Loader (in);

     return loader.load(lvlnbr);


    }
    public void Levelprint(){
        for (int x = 0 ; x< height;++x){
            for(int y = 0 ; y< width ; ++y){

                System.out.print( toString (getElement( new Position(y,x))));
            }
            System.out.println();
        }
    }
    public String toString ( element elem){
        String out = "broken";
        if ( elem instanceof  Hero) out = "@";
        else if ( elem instanceof Wall) out = "X" ;
        else if ( elem instanceof Virus)out = "*";
        else if ( elem instanceof TrashCan) out = "V";
        else if ( elem instanceof Space) out = ".";
        return out;
    }


    public boolean moveVirus(Direction dir,int i) {
        boolean moved = false;
        virus = getVirus(i);
        Position location = virus.getPos();


        if ((location.x -dir.x < height && location.y - dir.y < width && location.x -dir.x >= 0 && location.y - dir.y >= 0)&&  model[location.x - dir.x][location.y - dir.y].getElement() instanceof TrashCan){
            model[location.x][location.y] = new Space(new Position(location.x, location.y));
            model[location.x - dir.x][location.y - dir.y].updatePos(new Position(location.x - dir.x, location.y - dir.y));
            deleteVirus(i);
            return true;
        }
        if(location.x -dir.x < height && location.y - dir.y < width && location.x -dir.x >= 0 && location.y - dir.y >= 0) {
            if (!checkIfCollided(location, dir)) {
                model[location.x - dir.x][location.y - dir.y] = model[location.x][location.y];
                model[location.x][location.y] = new Space(new Position(location.x, location.y));
                model[location.x - dir.x][location.y - dir.y].updatePos(new Position(location.x - dir.x, location.y - dir.y));
                moved = true;
            }
        }
    return moved;
    }
    public void moveHero (Direction dir) {
        hero = (Hero) getHero();
        Position location = hero.getPos();
        if (hero.isdead())return;
        if(location.x -dir.x < height && location.y - dir.y < width && location.x -dir.x >= 0 && location.y - dir.y >= 0) {
            if (!checkIfCollided(location, dir)) {
                model[location.x - dir.x][location.y - dir.y] = model[location.x][location.y];
                model[location.x][location.y] = new Space(new Position(location.x, location.y));
                model[location.x - dir.x][location.y - dir.y].updatePos(new Position(location.x - dir.x, location.y - dir.y));

            } else if (model[location.x - dir.x][location.y - dir.y].getElement() instanceof Virus) {
                if (moveVirus(dir, virusList.indexOf((Virus) model[location.x - dir.x][location.y - dir.y].getElement()))) {
                    moveHero(dir);
                }

            }

        }
    }
    public boolean CheckifDead(Position location , Direction dir){
        if(model[location.x  - dir.x ][location.y - dir.y].getElement() instanceof TrashCan){

            hero.state = true;
            return true;
        }
        return false;
    }


    public boolean checkIfCollided(Position location,Direction dir){

        return!(model[location.x  - dir.x ][location.y - dir.y].getElement() instanceof Space);

    }

    public element getHero(){
        return hero;
    }
    public  int getVirusLength(){return virusList.size();}

    public Virus getVirus ( int i){

        return virusList.get(i);

    }
    public void deleteVirus ( int i){

        virusList.remove(i);

    }


    public void reset (){
        model = new element[width][height];
    }

    public int getNumber() {
        return levelNumber;
    }

    public void save(PrintStream output, int currentLvl) {
        output.printf("#%d %d x %d %n" ,currentLvl,height,width );

        for (int c= 0; c < height; c++) {

            for (int l = 0 ; l < width; l++) {
                Position posi = new Position(l,c);
                output.print(getElement(posi).getChar());
                if(l%(width-1) ==0 && l != 0 ){
                    output.println();

                }

            }

        }




    }
}
