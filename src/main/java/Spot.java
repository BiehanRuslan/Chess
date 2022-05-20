public class Spot {
    String x;
    int y;

    public Spot(String x, int y) {
        if (x.compareTo("h") > 0 || y > 8 || y <= 0) {
            throw new IllegalArgumentException("Incorrect spot error");
        } else {
            this.x = x;
            this.y = y;
        }
    }

    public String getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(String x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "x='" + x + '\'' +
                ", y=" + y +
                '}';
    }
}
