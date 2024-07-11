import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import org.junit.jupiter.api.Test;

class RectangleTest {
    @Test
    void testEdges() {
        Point p = new Point(20, 20);
        Rectangle rectangle = new Rectangle(p, 200, 100);
        Line[] edges = rectangle.getEdges();
        for (Line line: edges) {
            System.out.print(line.start().getX() + " ");
            System.out.print(line.start().getY() + " ");
            System.out.print(line.end().getX() + " ");
            System.out.print(line.end().getY() + " ");
            System.out.println();
        }
    }

}