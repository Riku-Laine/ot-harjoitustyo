/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import yatzy.dao.Database;
import yatzy.dao.RecordDao;

/**
 * Class for application logic.
 *
 * @author Riku_L
 */
public class YatzyService {

    private final ArrayList<Player> playerList;
    private final DiceCollection dices;
    private int throwsUsed;
    private final int maxNumberOfThrows;
    private final RecordDao recordDao;
    private final Database recordDB;

    /**
     * Default constructor for a conventional Yatzy game of with 5 6-sided dies
     * and 3 throws.
     *
     * @throws ClassNotFoundException
     */
    public YatzyService() throws ClassNotFoundException {
        this.playerList = new ArrayList<>();
        this.dices = new DiceCollection();
        this.throwsUsed = 0;
        this.maxNumberOfThrows = 3;
        recordDB = new Database("jdbc:sqlite:records.db");
        recordDao = new RecordDao(recordDB);
    }

    /**
     * Constructor for a customized game of Yatzy.
     *
     * @param nDies Number of dies.
     * @param biggestEye Number of sides in a dice.
     * @param maxNumberOfThrows Number of throws in a turn.
     * @throws ClassNotFoundException
     */
    public YatzyService(int nDies, int biggestEye, int maxNumberOfThrows) throws ClassNotFoundException {
        this.playerList = new ArrayList<>();
        this.dices = new DiceCollection(nDies, biggestEye);
        this.throwsUsed = 0;
        this.maxNumberOfThrows = maxNumberOfThrows;
        recordDB = new Database("jdbc:sqlite:records.db");
        recordDao = new RecordDao(recordDB);
    }

    /**
     * Get players as an ArrayList.
     *
     * @return ArrayList of players in the game.
     */
    public ArrayList<Player> getPlayers() {
        return this.playerList;
    }

    /**
     * Add player to player list. If playerlist is empty, added player will be
     * set to start the game.
     *
     * @param name Name of the player.
     */
    public void addPlayer(String name) {
        Player p = new Player(name);
        if (this.playerList.isEmpty()) {
            p.setTurn(true);
        }
        this.playerList.add(p);
    }

    /**
     * Add player to player list. If playerlist is empty, added player will be
     * set to start the game.
     *
     * @param name Name of the player.
     * @param scorecardType Type of the scorecard, e.g. "American" or
     * "Scandinavian".
     */
    public void addPlayer(String name, String scorecardType) {
        Player p = new Player(name, scorecardType);
        if (this.playerList.isEmpty()) {
            p.setTurn(true);
        }
        this.playerList.add(p);
    }

    /**
     * Get player with turn.
     *
     * @return Player with turn. If there are no players, null is returned.
     */
    public Player getPlayerWithTurn() {
        // only one player has turn
        for (int i = 0; i < this.playerList.size(); i++) {
            if (this.playerList.get(i).getTurn()) {
                return this.playerList.get(i);
            }
        }
        return null;
    }

    /**
     * Get nuumber of throws used.
     *
     * @return Throws used.
     */
    public int getThrowsUsed() {
        return this.throwsUsed;
    }

    /**
     * If there are throws left, throw selected dies.
     *
     * @param selected Array of boolean values to indicate which dies to throw.
     */
    public void throwSelectedDies(boolean[] selected) {
        if (selected.length != this.dices.getDies().length) {
            throw new IllegalArgumentException("Length of given array is wrong!");
        }
        if (this.throwsUsed < this.maxNumberOfThrows) {
            this.dices.rollDies(selected);
        }

        this.throwsUsed++;
    }

    /**
     * If there are throws left, throw all dies.
     */
    public void throwAllDies() {
        if (this.throwsUsed < this.maxNumberOfThrows) {
            this.dices.rollAllDies();
        }

        this.throwsUsed++;
    }

    /**
     * Change player in turn.
     */
    private void changeTurn() {
        Player p = getPlayerWithTurn();
        p.setTurn(false);
        int index = this.playerList.indexOf(p) + 1;
        if (index >= this.playerList.size()) {
            index = 0;
        }
        this.playerList.get(index).setTurn(true);
        // Set dies to zero
        this.dices.setDies(new int[this.dices.getDies().length]);
        this.throwsUsed = 0;
    }

    /**
     * Writes the scoreboard.
     *
     * @return The scoreboard to write in the UI.
     */
    public String getScoreboard() {

        if (this.playerList.isEmpty()) {
            return "No players!";
        }

        String scoreBoard = "Combinations    | " + this.playerList.stream()
                .map(p -> (p.getName())).collect(Collectors.joining(" | ")) + " |\n\n";

        ArrayList<String> combinations = getPlayerWithTurn().getScorecard().getCombinations();

        scoreBoard = combinations.stream().map((combination) -> {
            String row = padRight(combination, 16) + "| ";
            for (Player p : this.playerList) {
                int score = p.getScorecard().getPointsFor(combination);
                if (score == -1) {
                    row += padRight("-", p.getName().length()) + " | ";
                } else {
                    row += padRight(String.valueOf(score), p.getName().length()) + " | ";
                }
            }
            return row;
        }).map((row) -> row + "\n").reduce(scoreBoard, String::concat);
        return scoreBoard;
    }

    /**
     * Pads given string with spaces to the right side.
     *
     * @param s String to pad.
     * @param n Length of the returned string, String s included.
     * @return Padded string of length n.
     */
    private String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Get dies.
     *
     * @return Dice values in an integer array.
     */
    public int[] getDies() {
        return this.dices.getDies();
    }

    /**
     * Set value of dies. Throws an error if illegal values are used.
     *
     * @param dies
     */
    public void setDies(int[] dies) {
        this.dices.setDies(dies);
    }

    /**
     * Set scored points for a combination and change turn.
     *
     * @param player
     * @param combination
     */
    public void setScoreAndChangeTurn(Player player, String combination) {
        player.setPoints(combination, this.dices);
        changeTurn();
    }

    /**
     * Get scored points for a combination.
     *
     * @param player
     * @param combination
     * @return Points of the specified player for the specified combination.
     */
    public int getScore(Player player, String combination) {
        return player.getScorecard().getPointsFor(combination);
    }

    /**
     * Add all the players in the playerList to high score database.
     */
    public void addAllPlayersInTheGameToRecords() {
        this.playerList.stream().forEach(player -> {
            Record record = new Record(player, player.getScorecard().getType(),
                    getDies().length, this.dices.getBiggestEyeNumber(),
                    this.maxNumberOfThrows, player.getScorecard().getPointsFor("Total"));
            try {
                this.recordDao.saveOrUpdate(record);
            } catch (SQLException ex) {
                System.out.println("Error in updating records!");
            }
        });
    }

    /**
     * Reset game: clear player list, set used throws to zero and set dies to
     * zero.
     */
    public void reset() {
        this.playerList.clear();
        this.throwsUsed = 0;
        setDies(new int[getDies().length]);
    }

    /**
     * Get high scores to write in the UI.
     *
     * @return The high score board as string.
     */
    public String getHighScores() {
        ArrayList<Record> records = new ArrayList<>();
        try {
            records = this.recordDao.findAll();
        } catch (SQLException ex) {
            System.out.println("Could not access records! :(");
        }
        if (records == null || records.isEmpty()) {
            return "No records!";
        }
        Collections.sort(records);
        String board = "Records: (player name, points)\n";
        int standing = 1;
        for (Record record : records) {
            if (matchesTheGame(record)) {
                board += standing + ". " + record.getPlayer().getName() + ", " + record.getPoints() + "\n";
                standing++;
            }
        }

        return board;
    }

    /**
     * Remove record by the given name from the database. Accessible by the
     * administrator.
     *
     * @param record Object of class rcord of which record to remove.
     */
    public void removeRecord(Record record) {
        try {
            this.recordDao.delete(record);
        } catch (SQLException ex) {
            System.out.println("Couldn't remove record! :(");
        }
    }

    /**
     * Remove all records from the database. Accessible by the administrator.
     */
    public void removeAllRecords() {
        try {
            ArrayList<Record> records = this.recordDao.findAll();
            for (Record record : records) {
                this.recordDao.delete(record);
            }
        } catch (SQLException ex) {
            System.out.println("Could not remove all records!");
        }
    }

    public int getMaxThrows() {
        return this.maxNumberOfThrows;
    }

    public int getMaxNumber() {
        return this.dices.getBiggestEyeNumber();
    }

    /**
     * Set ith player to begin game.
     *
     * @param i
     */
    public void setToBegin(int i) {
        for (int index = 0; index < this.playerList.size(); index++) {
            this.playerList.get(index).setTurn(index == i);
        }
    }

    /**
     * Check whether game has ended, e.g. all combinations have been played and
     * scorecard is full.
     *
     * @return Boolean indicating whether a game has been played out.
     */
    public boolean hasEnded() {
        for (Player p : this.playerList) {
            if (!p.isScorecardFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ger records as Arraylist.
     *
     * @return Records as ArrayList.
     */
    public ArrayList<Record> getRecords() {
        try {
            return this.recordDao.findAll();
        } catch (SQLException ex) {
            System.out.println("Error in retrieving records!");
        }
        return null;
    }

    /**
     * Boolean indicating whether the specified record matches the game, i.e. if
     * its settings are the sama as in the game now in progree.
     *
     * @param record Record to check against.
     * @return true if there is a match, false otherwise.
     */
    private boolean matchesTheGame(Record record) {
        if (getPlayerWithTurn() == null) {
            return false;
        }
        return record.getDiceAmount() == getDies().length
                & record.getMaxEyeNumber() == dices.getBiggestEyeNumber()
                & record.getThrowAmount() == maxNumberOfThrows
                & record.getScorecardType().equals(getPlayerWithTurn().getScorecard().getType());
    }

    /**
     * Check if all elements of a boolean array are false.
     *
     * @param array An array containing boolean elements.
     * @return 
     */
    public boolean areAllFalse(boolean[] array) {
        for (boolean b : array) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Close database connection.
     */
    public void closeDatabaseConnection() {
        this.recordDao.closeConnection();
    }
}
