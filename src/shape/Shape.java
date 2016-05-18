package shape;

import config.Config;
import ui.Grid;

/**
 * Created by yanni on 02.05.2016
 * model for all shapes
 */

public abstract class Shape {

    protected static final int GRIDSIZE = Config.GRIDSIZE;

    private Rectangle[] rectangles = new Rectangle[4];
    private int rotation;

    protected Shape(int startX, int startY) {
        this.rotation = 0;
        initialize(startX, startY);
        for (Rectangle rectangle : rectangles) {
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
    }

    //***** abstract methods

    public abstract void rotate();

    protected abstract void initialize(int startX, int startY);

    protected abstract boolean canMoveDown(Grid grid);

    //***** API

    /**
     * this method creates a new shape with the given parameter
     *
     * @param random random number
     * @return Shape object
     */
    public static Shape getNewShape(int random, int startX, int startY) {
        switch (random) {
            case 0:
                return new Line(startX, startY);
            case 1:
                return new Line(startX, startY);
            case 2:
                return new Line(startX, startY);
            default:
                return null;
        }
    }

    /**
     * moves the Shape object down
     *
     * @return true if object is at the bottom of the grid
     */
    public boolean moveVertical(Grid grid) {
        if (!canMoveDown(grid)) {
            return true;
        }
        for (Rectangle rectangle : rectangles) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
        }
        move(0, 1);
        for (Rectangle rectangle : rectangles) {
            grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
        return false;
    }

    /**
     * moves the object left or right, depending on the boolean param
     *
     * @param left move the to left = true, move to right = false
     */
    public void moveHorizontal(boolean left, Grid grid) {
        if (left) {
            if(!canMoveLeft(grid)) {
                return;
            }
            move(-1, 0);
        } else {
            if(!canMoveRight(grid)) {
                return;
            }
            move(1, 0);
        }
    }

    //***** getter & setter

    protected Rectangle getRectangle(int index) {
        return rectangles[index];
    }

    public Rectangle[] getAllRectangles() {
        return rectangles;
    }

    protected void setRectangle(int index, Rectangle rectangle) {
        rectangles[index] = rectangle;
    }

    protected int getRotation() {
        return this.rotation;
    }

    protected void setRotation(int rotation) {
        this.rotation = rotation;
    }


    /**
     * helps with simplifying the movement of the shape
     *
     * @param x value for the movement of the x coordinate
     * @param y value for the movement of the y coordinate
     */
    private void move(int x, int y) {
        for (Rectangle rectangle : rectangles) {
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
            rectangle.setRow(rectangle.getRow() + x);
            Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
        }
        for (Rectangle rectangle : rectangles) {
            if (rectangle.getCollumn() + y <= 23) {
                Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 0);
                rectangle.setCollumn(rectangle.getCollumn() + y);
                Grid.grid.setGridValue(rectangle.getRow(), rectangle.getCollumn(), 1);
            }
        }
    }

    public boolean canMoveRight(Grid grid){
        for (Rectangle rectangle : getAllRectangles()){
            if(rectangle.getRow()+1 >= Config.ROWS){
                return false;
            }
            if(grid.isOccupied(rectangle.getRow()+1, rectangle.getCollumn())){
                return false;
            }
        }
        return true;
    }

    public boolean canMoveLeft(Grid grid){
        for (Rectangle rectangle : getAllRectangles()){
            if(rectangle.getRow()-1 < 0){
                return false;
            }
            if(grid.isOccupied(rectangle.getRow()-1, rectangle.getCollumn()) | rectangle.getRow() <= 0){
                return false;
            }
        }
        return true;
    }
}
