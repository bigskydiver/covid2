package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.Position;

public class TrashCan extends element {
    public TrashCan(Position pos) {
        super(pos);
    }

    boolean kills (){
        return true;
    }
}
