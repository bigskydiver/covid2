package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.Position;

public class Virus extends element {
    private Level level;
    public Virus(Position pos) {
        super(pos);
    }
/*
    public boolean isdead (Direction dir ){

        return level.getElement(new Position((pos.x - dir.x), pos.y - dir.y)).kills();
    }
    public boolean canMove (Direction dir ){
        return level.getElement(new Position((pos.x - dir.x), pos.y - dir.y))!= null;
    }
*/
}
