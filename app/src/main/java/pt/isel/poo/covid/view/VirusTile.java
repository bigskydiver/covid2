package pt.isel.poo.covid.view;

import android.content.Context;
import android.graphics.Canvas;

import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.model.Wall;
import pt.isel.poo.covid.tile.Img;
import pt.isel.poo.covid.tile.Tile;

public class VirusTile implements Tile {
    private Virus virus;
    private Img virusimg;
    public VirusTile(Context context, Virus virus){

        this.virus=virus;

    }



    @Override
    public void draw(Canvas canvas, int side) {

    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
