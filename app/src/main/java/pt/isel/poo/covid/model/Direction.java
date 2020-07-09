package pt.isel.poo.covid.model;

public class Direction {
    int x;
    int y;
    public Direction (int x ,int y){
        this.x = x;
        this.y = y;
        Direction left = new Direction( 1 ,0);
        Direction right = new Direction( -1 ,0);
        Direction down = new Direction( 0 ,1);
    }
}
