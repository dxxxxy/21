package studio.dreamys;

import studio.dreamys.object.Deck;
import studio.dreamys.object.Player;
import studio.dreamys.object.Table;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Blackjack {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File file = new File("dummy");

        //first launch
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("Seeing as this is your first time launching the game, you will have to read the rules.");
            System.out.print("Press ENTER to continue reading.");
            sc.nextLine();
            System.out.print("Welcome to 21!");
            sc.nextLine();
            System.out.print("This is a game of blackjack.");
            sc.nextLine();
            System.out.print("IMPORTANT: You're playing against the dealer, not against each other.");
            sc.nextLine();
            System.out.print("ALSO: You will be asked to choose the value of an ACE card.");
            sc.nextLine();
            System.out.print("TIP: Press ENTER when it is the dealer's turn.");
            sc.nextLine();
        }

        try {
            //start taking user input
            System.out.print("How many players will be playing? Enter a number between 1 and 6: ");
            int numPlayers = sc.nextInt();
            if (numPlayers < 1 || numPlayers > 6) {
                System.out.println("Invalid number of players. Exiting...");
                return;
            }

            //ask each player for their name
            Player[] players = new Player[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Enter player " + (i + 1) + "'s name: ");
                players[i] = new Player(sc.next());
            }

            //start the game
            new Table(new Deck(), players).start();

        //in case people enter strings instead of ints etc...
        } catch (InputMismatchException e) {
            System.out.println("Whoops, you probably entered something we didn't want you to :(");
            e.printStackTrace();
        }
    }
}
