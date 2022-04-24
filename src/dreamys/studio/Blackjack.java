package dreamys.studio;

import dreamys.studio.object.Deck;
import dreamys.studio.object.Player;
import dreamys.studio.object.Table;

import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to 21!");
        System.out.println("This is a game of blackjack.");
        System.out.println("How many players will be playing? Enter a number between 1 and 6.");

        int numPlayers = sc.nextInt();
        if (numPlayers < 1 || numPlayers > 6) {
            System.out.println("Invalid number of players. Exiting.");
            return;
        }

        //ask each player for their name
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter player " + (i + 1) + "'s name.");
            players[i] = new Player(sc.next());
        }

        Deck deck = new Deck();
        Table table = new Table(deck, players);
        table.start();
    }
}
