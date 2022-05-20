import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;
    private String email;
    private boolean white;
    private int rank;
    private int age;

    public Player(String name, String email, boolean white, int rank, int age) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("The name should not be empty");
        } else {
            this.name = name;
        }

        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("The email address should not be null");
        } else if (email.contains("@") == false || email.contains(".") == false) {
            throw new IllegalArgumentException("The email address should contain '@' and '.'");
        } else {
            this.email = email;
        }

        this.white = white;

        if (rank < 100 || rank > 3000) {
            throw new IllegalArgumentException("The rank should be in range of 100 and 3000");
        } else {
            this.rank = rank;
        }

        if (age < 1 || age > 100) {
            throw new IllegalArgumentException("The age should be in range of 1 and 100");
        } else {
            this.age = age;
        }
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public boolean isWhite() {
        return white;
    }
    public int getRank() {
        return rank;
    }
    public int getAge() {
        return age;
    }

    public void setName(String name) {
        if (name == null || name.isBlank() || name.isEmpty()) {
            throw new IllegalArgumentException("The name should not be empty");
        } else {
            this.name = name;
        }
    }
    public void setEmail(String email) {
        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("The email address should not be null");
        } else if (email.contains("@") == false || email.contains(".") == false) {
            throw new IllegalArgumentException("The email address should contain '@' and '.'");
        } else {
            this.email = email;
        }
    }
    public void setWhite(boolean white) {
        this.white = white;
    }
    public void setRank(int rank) {
        if (rank < 100 || rank > 3000) {
            throw new IllegalArgumentException("The rank should be in range of 100 and 3000");
        } else {
            this.rank = rank;
            System.out.println("The rank of " + name + " was changed to " + rank);
        }
    }
    public void setAge(int age) {
        if (age < 1 || age > 100) {
            throw new IllegalArgumentException("The age should be in range of 1 and 100");
        } else {
            this.age = age;
        }
    }

    //public void movePieces(Piece piece, Spot spot) {
    public void movePieces(Piece piece, Spot spot, HashMap<String, Piece> hashMap) {
        if (isWhite() != piece.isWhite()) {
            throw new IllegalArgumentException("Incorrect piece color. Try again");
        } else {
            for (Map.Entry<String, Piece> spotPiece: hashMap.entrySet()) {
                if (!piece.getId().equals(spotPiece.getValue().getId()) &&
                        piece.isWhite() != spotPiece.getValue().isWhite() &&
                        spot.getX().equals(spotPiece.getValue().getSpot().getX()) &&
                        spot.getY() == spotPiece.getValue().getSpot().getY()) {
                    piece.setKilled(false);
                    spotPiece.getValue().setKilled(true);
                    System.out.println(spotPiece.getValue().getId() + " is killed");
                }
                if (!piece.getId().equals(spotPiece.getValue().getId()) &&
                        piece.isWhite() == spotPiece.getValue().isWhite() &&
                        spot.getX().equals(spotPiece.getValue().getSpot().getX()) &&
                        spot.getY() == spotPiece.getValue().getSpot().getY() &&
                        (piece.getName() == "King" && spotPiece.getValue().getName() == "Rook" ||
                        piece.getName() == "Rook" && spotPiece.getValue().getName() == "King")) {
                    System.out.println("Castling");
                    spotPiece.getValue().setSpot(piece.getSpot());
                    System.out.println(spotPiece.getValue().getId() + " is moved " + spotPiece.getValue().getSpot());
                    piece.setSpot(spot);
                    System.out.println(piece.getId() + " is moved " + piece.getSpot());
                }
            }
            piece.setSpot(spot);
            System.out.println("Piece " + piece.getName() + " is moved to " + spot);
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", white=" + white +
                ", rank=" + rank +
                ", age=" + age +
                '}';
    }
}
