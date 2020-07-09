package pt.isel.poo.covid.view;

import android.content.Context;
import android.graphics.Canvas;

import pt.isel.poo.covid.model.TrashCan;
import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.tile.Img;
import pt.isel.poo.covid.tile.Tile;

public class TrashTile implements Tile {
        private TrashCan trash;
        private Img trashimg;
        public TrashTile(Context context, TrashCan trash){

            this.trash=trash;

        }

        @Override
    public void draw(Canvas canvas, int side) {

    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
