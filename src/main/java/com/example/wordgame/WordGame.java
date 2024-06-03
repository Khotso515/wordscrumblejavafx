package com.example.wordgame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.prefs.Preferences;


import java.util.*;

public class WordGame extends Application {

    private static final Map<String, List<String>> LEVELS_AND_WORDS = new LinkedHashMap<>();
    static {
        LEVELS_AND_WORDS.put("M,S,A,E,T,R", Arrays.asList("STREAM", "MIST", "MARES", "RATES", "EATS", "TEA", "TEAM", "MEAT", "SAM", "ART", "ARE", "ASK", "SET", "TAR", "MARE", "STARE", "STER", "AMES", "MASTE", "RAME", "STEMA", "MASTER", "STAIRS", "STREAMS", "STEAM", "MART", "TAME", "SEAM", "REST", "ARMS", "EAST", "MATS", "SEAT", "STAR", "TAMES", "RATE", "SAT", "ARM", "TAM", "RAM", "MAS", "ERA", "MET", "MAT", "TAS", "MAR", "RES", "ERA", "REM", "AS", "ME", "AT", "AM", "SAME", "ASTER"));

        LEVELS_AND_WORDS.put("R,L,Y,A,P,E,S", Arrays.asList("RELAY", "PLAY", "PLEA", "PAY", "REAL", "SLAY", "REAP", "LAP", "PLAYERS", "PLAYER", "LAY", "LAYERS", "YEAR", "YEARS", "REAL", "YES", "SAY", "AS", "PAL", "SAYER", "PEARL", "PEAR", "RAY", "RALE", "PALE", "LEAP", "PREY", "SPARE", "RAPES", "PLAYS", "ALPS", "PALS", "EARLY", "LAYER", "RAYS", "PARSE", "REPS", "SALE", "ALES", "SPA", "ERA", "SEA", "ALE", "APE", "EAR", "REP", "RAP", "ASP", "PER", "PAR", "ARE", "LAP", "ARP", "LAR", "SER", "SAY", "PAS", "RASP", "SEAR", "SEAL", "AR", "PES", "PAL", "SAL", "PES", "LA", "PA", "LARS", "RA"));

        LEVELS_AND_WORDS.put("N,H,A,L,E,C,L,G,E", Arrays.asList("CHALLENGE", "CHANGE", "ANGEL", "ALL", "LAG", "LEACH", "CAGE", "CELL", "ECH", "GLEAN", "HALE", "HANG", "HANGEL", "HEN", "LANE", "LANG", "LEAN", "LENCH", "LENG", "CALL", "HEEL", "HELL", "GEL", "GAL", "ACE", "AGE", "ALE", "ANCH", "ANCHL", "ACHE", "HENCE", "HANG", "HALL", "CLEAN", "LECH", "CLAN", "NEAL", "NELL", "GLEN", "LANCE", "NAH", "LACE", "HAG", "ALE", "HE", "HEN", "HER", "EH", "EL", "EN", "LA", "LEG", "AN", "AGE", "CAN", "ANE", "CHA", "ENG", "LEAN", "CLANG", "ACNE", "GALE", "LEAH", "ANGLE", "GLANCE", "HEAL", "CHEL", "CHANG"));

        LEVELS_AND_WORDS.put("R,T,S,E,S,O,F", Arrays.asList("FORESTS", "FOREST", "STORES", "ROSE", "SOFT", "TORE", "FORTS", "TOES", "FORT", "FORE", "FESTER", "SETS", "SORES", "AFTER", "OFFER", "TEST", "SERF", "FESTER", "FETES", "RATE", "FARE", "FORTES", "FLOAT", "SESTERT", "REST", "SET", "SORT", "ROTE", "STORE", "TOSS", "FORE", "FEST", "FOES", "FOE", "ORE", "ORES", "FRET", "SOFT", "EFT", "ROT", "RET", "ROTS", "FRO", "OF", "FOR", "TO", "OR", "OS", "SO", "RES", "SORE", "SEFT", "TER", "REFS", "REF", "REST", "TOS", "ER", "ES", "TOS", "ER", "STO", "ORT", "ROF", "FOR"));

        LEVELS_AND_WORDS.put("T,E,W,A,R,S,D,E,H", Arrays.asList("WATERSHED", "WATERS", "WATER", "WADERS", "HEARD", "STARE", "WARD", "SHED", "SWAT", "RAW", "RATE", "SHARE", "DEAR", "WEAR", "HARE", "EARTH", "WHERE", "DRAW", "WAFT", "ETHER", "SHREW", "THREW", "DAWES", "WISHED", "TEARD", "WART", "HE", "WE", "THE", "HEAD", "TEAR", "READ", "WEED", "REW", "SET", "SEA", "WET", "WHAT", "HAT", "WAD", "SHE", "SAD", "DEW", "HER", "DASH", "HEAT", "HASTE", "TEASE", "TEASER", "WARD", "WET", "TWEED", "WEATHER", "HEART", "HAT", "HEATED", "WEDS", "WEARS", "WORTH", "HAS", "EAT", "AS", "HARD", "WARE", "SET", "RED", "ET", "EATS", "ETC", "DETER", "DEATH", "WEARS", "WARTS"));

        LEVELS_AND_WORDS.put("E,I,R,F,S,O,W,K,R", Arrays.asList("FIRE", "FOR", "FEW", "FOE", "FORE", "FORK", "HERO", "HIKE", "IRE", "RAW", "SAFE", "SIR", "WIFE", "WORK", "WOKE", "ROW", "FIREW", "FIROW", "FORES", "FORKED", "FRESH", "FROZE", "GROW", "HIRED", "KIWI", "RIFE", "SORE", "WIRES", "WORSE", "WRIER", "KIR", "FIR", "KIEF", "ROSE", "SIRE", "HIRE", "ROWS", "WEIR", "SOW", "SOW", "WE", "FEW", "WOE", "WISE", "WISER", "FRIES", "ROES", "RISE", "ORES", "FOE", "REW", "WISER", "ROKE", "WOK", "WIRE", "FEW", "FIR", "WORS", "RIF", "IF", "OF", "WOF", "KIF", "KIR"));

        LEVELS_AND_WORDS.put("D,S,I,B,G,E,R", Arrays.asList("BIG", "BIRD", "DIE", "DIG", "RISE", "RIDE", "BRIDGE", "GIBES", "SERI", "SERES", "SIRE", "BRIG", "BRIEF", "DIRGE", "ESRIG", "GERIS", "IBEDS", "RIBS", "BED", "RED", "RID", "SIR", "BEG", "IDS", "BIDS", "BRED", "BE", "ID", "BID", "IRE", "IR", "RIB", "RIG", "RES", "REG", "DIGS", "DRIBS", "ER", "RE", "ED", "DR", "DIR", "BID", "BI", "BRI", "GIS", "SI", "IR"));

        LEVELS_AND_WORDS.put("E,R,A,S,C,R,K,P,S,K", Arrays.asList("SKYSCRAPER", "ASK", "ARE", "CAR", "CRAP", "ERA", "ERR", "PAY", "PERK", "RAPE", "RAYS", "SCAR", "SEEK", "SCARE", "SCARP", "SCRAP", "SKY", "SPEAR", "SPRAY", "YARE", "SEE", "SEA", "SAP", "EAR", "SKIP", "SEAR", "RAKE", "RAS", "RASP", "RAPS", "PEAS", "SKA", "KEP", "CRAKE", "APE", "RAKES", "SPAKE", "SEARS", "EAR", "ACE", "RA", "ESKAR", "SPARK", "PARK", "SPAKE", "PEAK", "EARS", "ASK", "ESK", "SKER", "SARK", "ARKS", "ARC", "SKAR", "SARE", "ARK", "AER", "PEAS", "RA", "KAS", "ARE", "RES", "SARS", "SER"));

        LEVELS_AND_WORDS.put("U,O,M,N,T,A,S,N,I", Arrays.asList("MOUNTAINS", "AIN'T", "ANT", "MAN", "MANY", "MATIN", "MINT", "MONA", "MOUTHS", "MOWN", "STONY", "MOUNT", "TAIN", "MAST", "MIAOU", "MUNI", "SCAN", "SIT", "SON", "TEAM", "UNIT", "AUNT", "IN", "IS", "IT", "MUSIN", "MUTE", "SNAM", "ASTI", "STUN", "SNOT", "STUN", "SAINT", "AINT", "SAT", "SUM", "SATIN", "ANTI", "STUNT", "MIST", "AST", "SOT", "NUT", "TUNA", "SAT", "OMIT", "TAM", "MAT", "MAN", "NAT", "NUTS", "TAM", "TIN", "SUN", "MIT", "MOT", "NIS", "NO", "AINT", "AIS", "MIN", "IT", "IA", "TO", "OMIT", "TAN", "AM", "US", "IN", "AT", "UN"));

        LEVELS_AND_WORDS.put("S,E,S,P,I,N,A,H,P", Arrays.asList("AH", "AS", "HAS", "HEN", "HI", "HIS", "IN", "INN", "ISA", "PAN", "PEN", "PINE", "PINES", "PINEY", "PIQUE", "SAP", "SEAN", "SHIP", "SHINE", "SIN", "SIP", "SPIKE", "THAN", "HAP", "HENS", "HIKE", "PIAN", "SHINE", "SHIPS", "HAPPY", "HAPPENS", "HAPPINESS", "HAPPIES", "PINS", "SPIN", "APES", "SHAPE", "SPINE", "SHE", "PEAS", "SEAS", "IS", "SHE", "SIN", "SPAN", "SHAPES", "AN", "ASP", "HIP", "PISS", "SPANS", "NAPS", "PEPS", "PEIN", "PINE", "PIAN", "APSE", "SHINE", "SHIPS", "HEN", "HIS", "AS", "HE", "HI", "I", "NA", "PIS", "PS", "SHA", "HIP", "NIS", "N", "HE", "SHE"));
    }


    private TextField wordInput = new TextField();
    private Label timerLabel = new Label("Time left: ");
    private Label scoreLabel = new Label("Score: 0");
    private Label resultLabel = new Label();
    private int score = 0;
    private int timeLeft = 0;
    private String currentLevelLetters = "";
    private Timeline timer;
    private Set<String> validWords;
    private Stage primaryStage;
    private AudioClip correctSound;
    private AudioClip incorrectSound;
    private AudioClip endGameSound;
    private int hintCounter = 0;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Scene startScene = createStartScene();
        startScene.getStylesheets().add(getClass().getResource("scratch_1.css").toExternalForm());
        primaryStage.setScene(startScene);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Word Game");
        primaryStage.show();

    }

    private Scene createStartScene() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome to Word Game!");
        welcomeLabel.setStyle("-fx-font-size: 20px;");
        Label welcometext = new Label("choose the level to start Game");
        Button easyButton = new Button("Level: Easy ");
        easyButton.setOnAction(e -> startGame(80));
        Button mediumButton = new Button("Level: Medium");
        mediumButton.setOnAction(e -> startGame(60));
        Button hardButton = new Button("Level: Hard");
        hardButton.setOnAction(e -> startGame(45));
        Button expertButton = new Button("Level: Expert");
        expertButton.setOnAction(e -> startGame(30));
        welcomeLabel.getStyleClass().add("label");
        welcometext.getStyleClass().add("label");

        root.getChildren().addAll(welcomeLabel,welcometext, easyButton, mediumButton, hardButton, expertButton);

        return new Scene(root, 400, 300);
    }

    private void startGame(int timeLimit) {
        currentLevelLetters = LEVELS_AND_WORDS.keySet().toArray(new String[0])[(int) (Math.random() * LEVELS_AND_WORDS.size())];
        score = 0;
        timeLeft = timeLimit;
        hintCounter = 0;
        timerLabel.setText("Time left: " + timeLeft);
        scoreLabel.setText("Score: " + score);
        resultLabel.setText("");
        wordInput.requestFocus();

        validWords = new HashSet<>(LEVELS_AND_WORDS.get(currentLevelLetters));

        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (timeLeft > 0) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft);
            } else {
                endGame();
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        primaryStage.setScene(createGameScene());
        wordInput.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                submitWord();
            }
        });
    }

    private Scene createGameScene() {
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root");
        timerLabel.getStyleClass().add("label");
        scoreLabel.getStyleClass().add("label");
        resultLabel.getStyleClass().add("result-label");
        wordInput.getStyleClass().add("text-field");

        HBox lettersBox = new HBox(10);
        lettersBox.setAlignment(Pos.CENTER);
        for (char letter : currentLevelLetters.replaceAll("[^A-Z]", "").toCharArray()) {
            Button letterButton = new Button(String.valueOf(letter));
            letterButton.setOnAction(e -> addLetter(letter));
            lettersBox.getChildren().add(letterButton);
        }

        wordInput.setPrefWidth(200);
        wordInput.setPromptText("Type your word here...");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> submitWord());

        Button hintButton = new Button("Hint");
        hintButton.setOnAction(e -> giveHint());

        root.getChildren().addAll(timerLabel, scoreLabel, lettersBox, wordInput, submitButton, hintButton, resultLabel);

        return new Scene(root, 400, 300);
    }

    private void giveHint() {
        if (hintCounter < validWords.size()) {
            String hintWord = (String) validWords.toArray()[hintCounter++];
            resultLabel.setText("Hint: A valid word is " + hintWord.charAt(0)+ hintWord.charAt(1)+ hintWord.charAt(2) + "...");
        } else {
            resultLabel.setText("No more hints available!");
        }
    }

    private void addLetter(char letter) {
        wordInput.appendText(String.valueOf(letter));
    }

    private Set<String> submittedWords = new HashSet<>();

    private void submitWord() {
        String word = wordInput.getText().toUpperCase();
        if (isValidWord(word)) {
            if (!submittedWords.contains(word)) {
                int wordScore = word.length(); // Score based on word length
                score += wordScore;
                scoreLabel.setText("Score: " + score);
                resultLabel.setText("Correct word: " + word + " (Score: " + wordScore + ")");
                submittedWords.add(word);
                displaySubmittedWords();
            } else {
                resultLabel.setText("You've already submitted the word: " + word);
            }
        } else {
            resultLabel.setText("Incorrect word: " + word);
        }
        wordInput.clear();
        wordInput.requestFocus(); // Keep focus on text field after submission
    }

    private void displaySubmittedWords() {
    }

    private boolean isValidWord(String word) {
        return validWords.contains(word);
    }

    private static final String LEADERBOARD_KEY = "leaderboard";

    private void saveScore(int score) {
        Preferences prefs = Preferences.userNodeForPackage(WordGame.class);
        int highScore = prefs.getInt(LEADERBOARD_KEY, 0);
        if (score > highScore) {
            prefs.putInt(LEADERBOARD_KEY, score);
        }
    }

    private int getHighScore() {
        Preferences prefs = Preferences.userNodeForPackage(WordGame.class);
        return prefs.getInt(LEADERBOARD_KEY, 0);
    }

    private void endGame() {
        timer.stop();

        saveScore(score);

        int totalPossibleScore = 0;
        for (String word : validWords) {
            totalPossibleScore += calculateScore(word);
        }

        int highScore = getHighScore();

        FlowPane wordsPane = new FlowPane();
        wordsPane.setHgap(5);
        wordsPane.setVgap(5);
        wordsPane.setStyle("-fx-background-color: #f0f8ff; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        for (String word : validWords) {
            Label wordLabel = new Label(word);
            wordLabel.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-font-size: 14px;");
            wordsPane.getChildren().add(wordLabel);
        }

        resultLabel.setText("For " + currentLevelLetters + ", all possible words are:");
        resultLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        scoreLabel.setText("Your Score: " + score + "\nTotal Possible Score: " + totalPossibleScore + "\nHigh Score: " + highScore);
        scoreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button nextButton = new Button("Next Game");
        nextButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px; -fx-border-radius: 5px;");
        nextButton.setOnAction(e -> primaryStage.setScene(createStartScene()));

        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-background-color: #dc3545; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px; -fx-border-radius: 5px;");
        quitButton.setOnAction(e -> primaryStage.close());

        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(nextButton, quitButton);

        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(scoreLabel, resultLabel, wordsPane, buttonsBox);

        Scene resultScene = new Scene(root, 400, 300);
        resultScene.getStylesheets().add(getClass().getResource("scratch_1.css").toExternalForm());
        primaryStage.setScene(resultScene);
    }

    private int calculateScore(String word) {
        return word.length();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
