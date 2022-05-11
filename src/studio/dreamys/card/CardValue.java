package studio.dreamys.card;

public enum CardValue {
    A(111),
    _2(2),
    _3(3),
    _4(4),
    _5(5),
    _6(6),
    _7(7),
    _8(8),
    _9(9),
    _10(10),
    J(10),
    Q(10),
    K(10),

    //hidden cards
    A1(1),
    A11(11);

    private int value;

    CardValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
