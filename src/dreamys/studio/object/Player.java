package dreamys.studio.object;

import dreamys.studio.card.Card;
import dreamys.studio.card.CardValue;
import dreamys.studio.util.DynamicCardArray;
import dreamys.studio.util.StandReason;

import java.util.Scanner;

public class Player {
    private String name;
    private DynamicCardArray hand;
    private boolean finished;
    private StandReason reason;

    public Player(String name) {
        this.name = name;
        hand = new DynamicCardArray();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public DynamicCardArray getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        int value = 0;
        for (int i = 0; i < hand.size(); i++) {
            value += hand.get(i).getValue().getValue();
        }
        return value;
    }

    //handles value displayed with both ace values
    public String getDisplayValue(){
        int valueA1 = 0;
        int valueA11 = 0;
        for (int i = 0; i < hand.size(); i++) {
            valueA1 += hand.get(i).getValue().getValue();
            valueA11 += hand.get(i).getValue().getValue();

            if (hand.get(i).getValue() == CardValue.A) {
                valueA1 = valueA1 - 111 + 1;
            }

            if (hand.get(i).getValue() == CardValue.A) {
                valueA11 = valueA11 - 111 + 11;
            }
        }

        //if it doesn't contain an ace, we simply return the normal result
        if (!hand.containsAce()) {
            return String.valueOf(valueA1);
        }

        return valueA1 + "/" + valueA11;
    }

    public void chooseAce(int index) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Player " + name + " (" + getDisplayValue() + ") , please choose an ace value (1 or 11): ");
        while (true) {
            int aceValue = sc.nextInt();

            //validate input
            if (aceValue != 1 && aceValue != 11) {
                System.out.println("Invalid input. Please enter 1 or 11: ");
                continue;
            }

            if (aceValue == 1) {
                hand.set(index, new Card(hand.get(index).getSuit(), CardValue.A1));
                break;
            }

            hand.set(index, new Card(hand.get(index).getSuit(), CardValue.A11));
            break;
        }
    }

    public void finish(StandReason reason) {
        finished = true;
        this.reason = reason;
    }

    public boolean isFinished() {
        return finished;
    }

    public StandReason getReason() {
        return reason;
    }
}
