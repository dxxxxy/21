package studio.dreamys.card;

public class Card {
    private CardSuit suit;
    private CardValue value;
    private boolean hidden;

    public Card(CardSuit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        if (hidden) {
            return "HIDDEN";
        }

        //return enum name instead of actual value
        if (value == CardValue.A || value == CardValue.J || value == CardValue.Q || value == CardValue.K) {
            return value + suit.getUnicode();
        }

        return value.getValue() + suit.getUnicode();
    }
}
