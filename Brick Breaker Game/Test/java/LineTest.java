import Geometry.Line;
import Geometry.Point;

class LineTest {
    public static void main (String[] args) {
    Point p1 = new Point(2, 2);
    Point p2 = new Point(6, 6);
    Line line = new Line(p1, p2);
    Line other = new Line(8, 8, 24, 24);
        System.out.println(line.equals(other));
        System.out.println(line.length());
        System.out.println(line.start().equals(p1));
        System.out.println(line.end().equals(p2));
        System.out.println(p2.equals(other.end()));
        System.out.println(line.determinant(other));
        System.out.println(line.isIntersecting(other));
        System.out.println(line.intersectionWith(other).getX());
    }
}