package dreamys.studio.util;

import dreamys.studio.card.Card;
import dreamys.studio.card.CardValue;

@SuppressWarnings("ManualArrayCopy")
public class DynamicCardArray {
    private Card[] array;
    private int last;

    public DynamicCardArray() {
        array = new Card[10000];
    }

    public void add(Card card) {
        array[last] = card;
        last++;
    }

    public Card get(int index) {
        //validation
        if (index < 0 || index >= last) {
            return null;
        }

        return array[index];
    }

    public void set(int index, Card card) {
        //validation
        if (index < 0 || index >= last) {
            return;
        }

        array[index] = card;
    }

    public int size() {
        return last;
    }

    public void remove(int index) {
        //validation
        if (index < 0 || index >= last) {
            return;
        }

        for (int i = index; i < last; i++) {
            array[i] = array[i + 1];
        }

        last--;
    }

    public boolean containsAce() {
        for (int i = 0; i < last; i++) {
            if (array[i].getValue() == CardValue.A) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < last; i++) {
            result += array[i] + " ";
        }

        return result;
    }
}
