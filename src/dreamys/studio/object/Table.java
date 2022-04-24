package dreamys.studio.object;

import dreamys.studio.card.Card;
import dreamys.studio.card.CardValue;
import dreamys.studio.util.StandReason;

import java.util.Scanner;

public class Table {
    private Deck deck;
    private Player[] players;
    private Player dealer;

    public Table(Deck deck, Player[] players) {
        this.deck = deck;
        this.players = players;
        dealer = new Player("Dealer");
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        deal(); //start (initial deal)
        print(); //print table
        checkCards(); //check cards (conditionals)
        print(); //print table again with updated cards

        //main player loop
        while (!dealersTurn()) {
            for (Player player : players) {
                if (!player.isFinished()) {
                    System.out.println(player.getName() + ", would you like to hit or stand? (h or s)");

                    while (true) {
                        String input = sc.nextLine();
                        if (!input.equals("h") && !input.equals("s")) {
                            System.out.println("Invalid input. Please enter h or s.");
                            continue;
                        }

                        if (input.equals("h")) {
                            hit(player);
                            break;
                        }

                        player.finish(StandReason.STAND);
                        break;
                    }
                }
            }

            print();
            checkCards();
            print();
        }

        //reveal dealer's second card
        dealer.getHand().get(1).setHidden(false);

        //main dealer loop
        while (!dealer.isFinished()) {
            hit(dealer);
            print();
            checkCards();
            print();
        }

        print();
        checkCards();
        print();

        handleGameOver();
    }

    public void hit(Player player) {
        player.addCard(deck.draw());
    }

    public void print() {
        System.out.println("\n\nDealer: " + dealer.getHand());
        for (Player player : players) {
            System.out.println(player.getName() + " (" + player.getDisplayValue() + ") : " + player.getHand() + (player.getReason() == null ? "" : " (" + player.getReason() + ")"));
        }
    }

    public void checkCards() {
        //check for players
        for (Player player : players) {
            //check for ace
            if (player.getHand().containsAce()) {
                for (int i = 0; i < player.getHand().size(); i++) {
                    if (player.getHand().get(i).getValue() == CardValue.A) {
                        player.chooseAce(i);
                    }
                }
            }

            //check for blackjack
            if (player.getValue() == 21) {
                player.finish(StandReason.BLACKJACK);
            }

            //check for bust
            if (player.getValue() > 21) {
                player.finish(StandReason.BUST);
            }
        }

        //check dealer for special ace case
        if (dealer.getHand().containsAce()) {
            for (int i = 0; i < dealer.getHand().size(); i++) {
                if (dealer.getHand().get(i).getValue() == CardValue.A) {
                    if (dealer.getValue() - 111 + 10 >= 17) {
                        dealer.getHand().set(i, new Card(dealer.getHand().get(i).getSuit(), CardValue.A11));
                        dealer.finish(StandReason.STAND);
                    } else {
                        dealer.getHand().set(i, new Card(dealer.getHand().get(i).getSuit(), CardValue.A1));
                    }
                }
            }
        }

        //check dealer for bust
        if (dealer.getValue() > 21) {
            dealer.finish(StandReason.BUST);
        }

        //check dealer for blackjack
        else if (dealer.getValue() == 21) {
            dealer.finish(StandReason.BLACKJACK);
        }

        //check for dealer stand
        else if (dealer.getValue() >= 17) {
            dealer.finish(StandReason.STAND);
        }
    }

    public boolean dealersTurn() {
        //check if everyone is standing, if so, it's the dealers turn
        int standing = 0;
        for (Player player : players) {
            if (player.isFinished()) {
                standing++;
            }
        }

        return standing == players.length;
    }

    public void handleGameOver() {
        System.out.println("\n\nGame over!");
        String winners = "Winners: \n";

        //if dealer busts, everyone who hasn't busted wins
        if (dealer.getReason() == StandReason.BUST) {
            System.out.println("Dealer busted!");
            for (Player player : players) {
                if (player.getReason() != StandReason.BUST) {
                    winners += player.getName() + " (" + player.getValue() + ")\n";
                }
            }
        }

        //if dealer stands, check who won
        if (dealer.getReason() == StandReason.STAND) {
            System.out.println("Dealer stood at " + dealer.getValue());
            for (Player player : players) {
                if (player.getValue() > dealer.getValue()) {
                    winners += player.getName() + " (" + player.getValue() + ")\n";
                }
            }
        }

        //if dealer has blackjack, everyone who also has blackjack wins
        if (dealer.getReason() == StandReason.BLACKJACK) {
            System.out.println("Dealer hit blackjack!");
            for (Player player : players) {
                if (player.getReason() == StandReason.BLACKJACK) {
                    winners += player.getName() + " (" + player.getValue() + ")\n";
                }
            }
        }

        System.out.println(winners);
    }

    public void deal() {
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.draw());
            }
            dealer.addCard(deck.draw());
        }
        dealer.getHand().get(1).setHidden(true);

        System.out.println("\nDealing...");
    }
}
