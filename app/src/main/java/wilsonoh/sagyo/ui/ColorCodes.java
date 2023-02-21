package wilsonoh.sagyo.ui;

/**
 * ANSI colorcodes taken from https://stackoverflow.com/a/5762502
 */
public enum ColorCodes {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    private final String ansiCode;

    ColorCodes(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    /**
     * Returns the ANSI code associated with the color
     *
     * @return the ANSI code
     */
    public String getAnsiCode() {
        return this.ansiCode;
    }
}
