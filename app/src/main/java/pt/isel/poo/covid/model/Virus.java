package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.Position;

public class Virus extends element {
    private Level level;
    private boolean dead;
    public Virus(Position pos) {
        super(pos);
    }

    public boolean isdead (Direction dir){
        Position location = pos;
        return level.getModel()[location.x - dir.x][location.y - dir.y]instanceof TrashCan;
    }
    public char  getChar(){
        character = '*';
        return character;
    }

}
