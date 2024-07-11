// 345184295 Yonatan Benchabbat
package Geometry;

import java.util.List;

/**
 * @author Yonatan Benchababt
 * @version 2.0
 * @since 2023 -05-04
 */
public class Line {
    private final double THRESHOLD = 0.00001;
    final int CHANGE_SIGN = -1;
    final int MIN_VALUE = 0;
    final int MAX_VALUE = 1;
    final int HALF = 2;
    final int FIRST = 0;
    private Point start;
    private Point end;

    // constructor of Geometry.Line with points
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    // constructor using coordinates
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // returns the length of the line
    public double length() {
        return this.start.distance(this.end);
    }

    // returns middle of line
    public Point middle() {
        return new Point(((this.start.getX() + this.end.getX()) / HALF),
                ((this.start.getY() + this.end.getY()) / HALF));

    }

    // returns the starting point of the line
    public Point start() {
        return this.start;
    }

    // returns the end point of the line
    public Point end() {
        return this.end;
    }

    // returns true if start or end points of both lines are equal
    public boolean equals(Line compare) {
        return ((this.start.equals(compare.start))
                && (this.end.equals(compare.end)))
                || ((this.start.equals(compare.end))
                && (this.end.equals(compare.start)));
    }

    // returns change in Y
    public double deltaY() {
        return this.end.getY() - this.start.getY();
    }

    // returns change in X
    public double deltaX() {
        return this.end.getX() - this.start.getX();
    }

    // returns slope of line
    public double slope() {
        return this.deltaY() / this.deltaX();
    }

    // returns Y-Intercept
    public double yIntercept() {
        return this.start.getY() - this.slope() * this.start.getX();
    }

    // returns intersection of lines if it exists otherwise returns null
    public Point intersectionWith(Line other) {
        double det = this.determinant(other);

        // if the determinant is zero there is no unique solution
        if (Math.abs(det) <= THRESHOLD) {
            return null;
        }

        // set code up in matrix with variables a,b,c,d for readability
        double a = this.end.getX() - this.start.getX();
        double b = -(other.end.getX() - other.start.getX());
        double c = this.end.getY() - this.start.getY();
        double d = -(other.end.getY() - other.start.getY());

        // solutions to the equation
        double solution1 = other.start.getX() - this.start.getX();
        double solution2 = other.start.getY() - this.start.getY();

        // variables of x in Ax=b;
        double x1 = ((d * solution1) - (b * solution2)) / det;
        double x2 = ((a * solution2) - (c * solution1)) / det;

        // if the intersection does not lie on the lines
        if ((x1 > MAX_VALUE || x1 < MIN_VALUE)
                || (x2 > MAX_VALUE) || x2 < MIN_VALUE) {
            return null;
        }

        // b1 and b2 are coordinates of intersection
        double b1 = this.start.getX() + x1 * a;
        double b2 = this.start.getY() + x1 * c;
        return new Point(b1, b2);
    }

    // returns determinant for system of linear equation with other
    public double determinant(Line other) {

        // create values for matrix to find determinant
        double a = this.end.getX() - this.start.getX();
        double b = -(other.end.getX() - other.start.getX());
        double c = (this.end.getY() - this.start.getY());
        double d = -(other.end.getY() - other.start.getY());

        // formula for determinant of 2x2 matrix
        return a * d - b * c;
    }

    public boolean isIntersecting(Line other) {

        // get determinant
        double det = this.determinant(other);

        if (Math.abs(det) > THRESHOLD && this.intersectionWith(other) != null) {
            return true;
        }

        //  exists a solution but not within the lines
        if ((Math.abs(det) > THRESHOLD) && this.intersectionWith(other) == null) {
            return false;
        }

        // lines are parallel therefore never intersect
        if ((Math.abs(this.slope() - other.slope()) <= THRESHOLD)
                && (Math.abs(this.yIntercept() - other.yIntercept())
                > THRESHOLD)) {
            return false;
        }
        if (this.length() > other.length()) {
            return checkForInfiniteSolutions(this, other);
        } else {
            return checkForInfiniteSolutions(other, this);
        }
    }

    /**
     * In order to check if lines are overlapping (i.e. infinite solutions)
     * we will take the start point of one and check if it lies within the
     * other incrementing by the second lines slope
     * @param l1 this line
     * @param l2 the line being checked for intersection
     * @return returns true or false
     */
    public boolean checkForInfiniteSolutions(Line l1, Line l2) {
        Point test = l1.start;

        // edge case of opposite slopes within the same line
        if (l1.slope() - CHANGE_SIGN * l2.slope() <= THRESHOLD) {
            test = l1.end;
        }
        double distance = l1.length();

        while (distance > MIN_VALUE) {
            if (l2.isWithinLine(test)) {
                return true;
            }
         Point nextTest = new Point(test.getX() + l2.deltaX(),
                 test.getY() + l2.deltaY());
         distance -= test.distance(nextTest);
         test = nextTest;
        }

        return false;
    }
    public boolean isWithinLine(Point point) {
        return (this.start.getX()  <= point.getX())
                && (point.getX() <= this.end.getX())
                && (this.start.getY() <= point.getY())
                && (point.getY() <= this.end.getY());
    }
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        List<Point> intersections = rectangle.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        int intersectionCounter = FIRST;
        Point closestIntersection = null;
        while (closestIntersection == null) {
            if (intersectionCounter == intersections.size()) {
                return null;
            }
            closestIntersection = intersections.get(intersectionCounter);
            intersectionCounter++;
        }

        // i is a counter for the index in list we are in
        int i = 0;
        Line[] edges = rectangle.getEdges();
        for (Point p: intersections) {
            if (p == null) {
                if (this.start.distance(edges[i].start)
                        < this.start.distance(edges[i].end)) {
                    p = edges[i].start;
                } else {
                    p = edges[i].end;
                }
            }
            if (this.start.distance(p) < this.start.distance(closestIntersection)) {
                closestIntersection = p;
            }
            i++;
        }
        return closestIntersection;
    }
}
