package pt.isel.poo.covid.model;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.tile.Tile;

public abstract class element {
    private Position pos ;

    public element ( Position pos){
        this.pos = pos;
    }

    Position getPos (){
        return pos;
    }

    element getElement (){return this;}

    void updatePos (Position newpos){pos = newpos;}






}
