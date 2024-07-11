// 345184295 Yonatan Benchabbat
package Geometry;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -05- 03
 */

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private final int EDGES = 4;
    private final int TOP = 0;
    private final int LEFT = 1;
    private final int BOTTOM = 2;
    private final int RIGHT = 3;

    // constructor
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public Point getUpperLeft() {
        return upperLeft;
    }
    public Line[] getEdges() {
        Line[] edges = new Line[EDGES];

        // create array of lines containing edges of rectangle
        edges[TOP] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        edges[LEFT] = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        edges[BOTTOM] = new Line(upperLeft.getX(),
                upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
        edges[RIGHT] = new Line(upperLeft.getX() + width,
                upperLeft.getY(),
                upperLeft.getX() + width,
                upperLeft.getY() + height);
        return edges;
    }

    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Line[] edges = this.getEdges();
        for (Line i : edges) {
            if (line.isIntersecting(i)) {
                list.add(line.intersectionWith(i));
            }
        }
        return list;
    }
}