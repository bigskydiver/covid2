package pt.isel.poo.covid.view;

import android.content.Context;
import android.graphics.Canvas;

import pt.isel.poo.covid.model.Hero;
import pt.isel.poo.covid.model.Wall;
import pt.isel.poo.covid.tile.Img;
import pt.isel.poo.covid.tile.Tile;

public class WallTile implements Tile {

    private Wall wall;
    private Img wallimg;
    public WallTile(Context context, Wall wall){

        this.wall=wall;

    }

    @Override
    public void draw(Canvas canvas, int side) {

    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
