import Geometry.Point;

class PointTest {
    public static void main(String[] args) {
        Point point = new Point(3, 5);
        Point other = new Point(3, 5);
        double x = point.getX();
        double y = point.getY();
        System.out.println(x);
        System.out.println(y);
        System.out.println(point.equals(other));
        System.out.println(point.distance(other));
    }
}