package pt.isel.poo.covid.model;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.tile.Tile;

public abstract class element {
    public Position pos ;
    public char character;
    public boolean hasmoved;
    public element ( Position pos){
        this.pos = pos;
    }

    Position getPos (){
        return pos;
    }

    element getElement (){return this;}
    //char getChar(){ return null;}
    void updatePos (Position newpos){
        hasmoved=true;
        pos = newpos;
    }

    boolean kills (){
        return false;
    }

    public boolean Hasmoved(){
        return hasmoved;
    }
    public char getChar() {
        return 0;
    }
}
