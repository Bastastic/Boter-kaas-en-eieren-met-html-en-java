package bas.nl.boterkaaseneiren;

public enum Stuk {
    XPIECE("X", 1, true),
    OPIECE("O", 2);

    private String p = "";

    Stuk(String p, int i) {
        this.p = p;
    }

    Stuk(String p, int i, boolean b) {
        this.p = p;
    }

    public String getStuk() {
        return p;
    }

    public String toString() {
        return p;
    }
}
