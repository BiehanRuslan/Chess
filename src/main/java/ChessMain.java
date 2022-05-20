import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChessMain {
    public static void main(String[] args) {

        ChessMain main = new ChessMain();

        ArrayList<Player> players = main.createPlayers();
        for (Player player : players) {
            System.out.println(player);
        }

        HashMap<String, Piece> pieceHashMap = main.createPieces();
        System.out.println(pieceHashMap);

        main.play(players, pieceHashMap);
        System.out.println(pieceHashMap);

        /*System.out.println(pieceHashMap.get("black_knight1"));
        main.play(players, pieceHashMap);
        System.out.println(pieceHashMap.get("black_knight1"));
        System.out.println(pieceHashMap);*/

        /* create players
        Player whitePlayer = new Player("First Player", "first.player@gmail.com", true, 2000, 20);
        Player blackPlayer = new Player("Second Player", "second.player@gmail.com", false, 2500, 35);*/

        /* getter
        System.out.println("First player name: " + whitePlayer.getName());
        System.out.println("First player email: " + whitePlayer.getEmail());
        System.out.println("First player whiteColor: " + whitePlayer.isWhite());
        System.out.println("First player rank: " + whitePlayer.getRank());
        System.out.println("First player age: " + whitePlayer.getAge());

        System.out.println("Second player name: " + blackPlayer.getName());
        System.out.println("Second player email: " + blackPlayer.getEmail());
        System.out.println("Second player whiteColor: " + blackPlayer.isWhite());
        System.out.println("Second player rank: " + blackPlayer.getRank());
        System.out.println("Second player age: " + blackPlayer.getAge());*/

        /* print players
        System.out.println(whitePlayer);
        System.out.println(blackPlayer);*/

        /* try - catch example with setter
        try {
            whitePlayer.setRank(10000);
        } catch (IllegalArgumentException err) {
            System.out.println("please update rank to valid");
        }
        System.out.println("First player rank: " + whitePlayer.getRank());*/
    }

    public ArrayList<Player> createPlayers() {

        /*///* create players
        Scanner inputPlayers = new Scanner(System.in);

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            System.out.println("Input name: ");
            String name = inputPlayers.nextLine();
            System.out.println("Input email: ");
            String email = inputPlayers.nextLine();
            System.out.println("White color?: ");
            boolean white = inputPlayers.nextBoolean();
            System.out.println("Input rank: ");
            int rank = inputPlayers.nextInt();
            System.out.println("Input age: ");
            int age = inputPlayers.nextInt();

            inputPlayers.nextLine();

            players.add(new Player(name, email, white, rank, age));
        }
        //*/

        ///* create players
        Player whitePlayer = new Player("First Player", "first.player@gmail.com", true, 2000, 20);
        Player blackPlayer = new Player("Second Player", "second.player@gmail.com", false, 2500, 35);

        ArrayList<Player> players = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);
        //*/

        return players;
    }

    public HashMap<String, Piece> createPieces() {
        King whiteKing = new King(new Spot("h", 7), "white_king", true);
        King blackKing = new King(new Spot("d", 8), "black_king", false);
        Rook whiteRook1 = new Rook(new Spot("a", 7), "white_rook1", true);
        Rook whiteRook2 = new Rook(new Spot("g", 7), "white_rook2", true);
        Knight blackKnight1 = new Knight(new Spot("d", 6), "black_knight1", false);

        HashMap<String, Piece> pieceHashMap = new HashMap<>();
        pieceHashMap.put(whiteKing.getId(), whiteKing);
        pieceHashMap.put(blackKing.getId(), blackKing);
        pieceHashMap.put(whiteRook1.getId(), whiteRook1);
        pieceHashMap.put(whiteRook2.getId(), whiteRook2);
        pieceHashMap.put(blackKnight1.getId(), blackKnight1);

        return pieceHashMap;
    }

    public void play(ArrayList<Player> players, HashMap<String, Piece> hashMap) {
        /*//move 1
        try {
            //players.get(0).movePieces(hashMap.get("white_rook1"), new Spot("a", 8));
            players.get(0).movePieces(hashMap.get("black_king"), new Spot("a", 8));
        } catch (IllegalArgumentException error) {
            System.out.println("The move is incorrect, please try again!");
            // retry functionality
        }
        //move 2
        players.get(1).movePieces(hashMap.get("black_knight1"), new Spot("c", 8));*/

        //players.get(1).movePieces(hashMap.get("black_knight1"), new Spot("g", 7), hashMap);

        Scanner inputMove = new Scanner(System.in);
        int i = 1;
        while (true) {
            if (i % 2 == 0) {
                System.out.println("Player 2 moves");
            } else {
                System.out.println("Player 1 moves");
            }

            System.out.println("Input piece: ");
            String idPiece = inputMove.nextLine();
            System.out.println("Input X: ");
            String xPiece = inputMove.nextLine();
            System.out.println("Input Y: ");
            int yPiece = inputMove.nextInt();
            inputMove.nextLine();

            if (i % 2 == 0) {
                players.get(1).movePieces(hashMap.get(idPiece), new Spot(xPiece, yPiece), hashMap);
            } else {
                players.get(0).movePieces(hashMap.get(idPiece), new Spot(xPiece, yPiece), hashMap);
            }

            if (hashMap.get("white_king").isKilled()) {
                System.out.println("Black is win");
                break;
            } else if (hashMap.get("black_king").isKilled()) {
                System.out.println("White is win");
                break;
            }
            i++;
        }
    }
}
