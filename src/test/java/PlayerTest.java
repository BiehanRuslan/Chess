import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class PlayerTest {
    private static ArrayList<Player> players;
    private static HashMap<String, Piece> pieceHashMap;

    // once before all tests
    @BeforeAll
    public static void setUp() {
        ChessMain main = new ChessMain();

        //players = main.createPlayers();
        Player whitePlayer = new Player("First Player", "first.player@gmail.com", true, 2000, 20);
        Player blackPlayer = new Player("Second Player", "second.player@gmail.com", false, 2500, 35);
        players = new ArrayList<>();
        players.add(whitePlayer);
        players.add(blackPlayer);

        pieceHashMap = main.createPieces();
    }

    //each times before each test
    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Running before each test");
    }

    //each times after each test
    @AfterEach
    public void afterEachTest() {
        System.out.println("Running after each test");
    }

    // once after all tests
    @AfterAll
    public static void tearDown() {
        System.out.println("Running after all test");
    }

    @Test
    public void validMoveTest() {
        /*players.get(0).movePieces(pieceHashMap.get("white_king"), new Spot("h", 8));
        Assertions.assertEquals(8, pieceHashMap.get("white_king").getSpot().getY());*/
        players.get(0).movePieces(pieceHashMap.get("white_king"), new Spot("h", 8), pieceHashMap);
        Assertions.assertEquals(8, pieceHashMap.get("white_king").getSpot().getY());
    }

    @Test
    public void invalidMovePieceColorTest() {
        /*Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePieces(pieceHashMap.get("black_king"), new Spot("h", 8));
        });*/
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePieces(pieceHashMap.get("black_king"), new Spot("h", 8), pieceHashMap);
        });
    }

    @Test
    public void invalidMoveBadSpotTest() {
        /*Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePieces(pieceHashMap.get("white_king"), new Spot("h", 9));
        });*/
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            players.get(0).movePieces(pieceHashMap.get("white_king"), new Spot("h", 9), pieceHashMap);
        });
    }

    @Test
    public void createPlayerSuccessTest() {
        Player whitePlayer = new Player("First Player", "first.player@gmail.com", true, 2000, 20);
        Assertions.assertNotNull(whitePlayer);
        Assertions.assertEquals("First Player", whitePlayer.getName());
        Assertions.assertEquals("first.player@gmail.com", whitePlayer.getEmail());

        //Assertions.assertEquals(true, whitePlayer.isWhite());
        Assertions.assertTrue(whitePlayer.isWhite());
        Assertions.assertEquals(2000, whitePlayer.getRank());
        Assertions.assertEquals(20, whitePlayer.getAge());
    }

    @ParameterizedTest
    //@ValueSource(strings = {null, "", "   "})
    @MethodSource ("nameError")
    public void incorrectNamePlayerTest(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player whitePlayer = new Player(name, "first.player@gmail.com", true, 2000, 20);
        });
    }

    @ParameterizedTest
    @MethodSource ("emailError")
    public void incorrectEmailPlayerTest(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player whitePlayer = new Player("First Player", email, true, 2000, 20);
        });
    }

    static Stream<String> nameError() {
        return Stream.of("", "   ", null);
    }

    static Stream<String> emailError() {
        return Stream.of("", "   ", null, "first.playergmail.com", "firstplayergmailcom");
    }
}
