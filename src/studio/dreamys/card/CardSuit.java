package studio.dreamys.card;

public enum CardSuit {
    SPADE("\u2660"), //♠
    CLUB("\u2663"), //♣
    HEART("\u2665"), //♥
    DIAMOND("\u2666"); //♦

    private String unicode;

    CardSuit(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }
}
