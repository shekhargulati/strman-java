package CS342Final.strman;

/**
 * A class designed to hold our different options for formatting numbers
 * @author Frank Lubek
 * @author Jake Arcivar
 */
public class NumberFormatOptions {



    /**
     * Options for formatting numbers.
     * <p>precision is the number of digits after the decimal point.
     * thousandsSeparator is what is inserted to divide groupings of numbers based
     * on certain powers of 10.
     * deciamlPoint is what is inserted betweeen integer and non-integer parts of a real number.
     */
    private int precision;
    private String thousandsSeparator;
    private String decimalPoint;


    /**
     * Constructor for our NumberFormatOptions class.
     * <p>Takes the percision we want or how many decimal places,
     * the thousands seperator we want ("," or "."), and the decimal point we want.
     *
     *
     * @param prec  An int that specifies amount of decimal points
     * @param dPoint  A Sting that denotes what we use as our decimal point
     * @param tSep  A String that denotes whether we use commas or periods as seperators
     */
    public NumberFormatOptions(int prec, String dPoint, String tSep) {
        this.precision = prec;
        this.thousandsSeparator = tSep;
        this.decimalPoint = dPoint;
    }

    public NumberFormatOptions(int prec) {
        this.precision = prec;
        this.thousandsSeparator = null;
        this.decimalPoint = null;
    }

    /**
     * Gets the precision the number we are formatting will use.
     *
     * @return int Our precission being used
     */
    public int getPrecision() {
        return this.precision;
    }

    /**
     * Gets the thousands seperator the number we are formatting will use
     *
     * @return String Our thousands seperator being used
     */
    public String getSeparator() {
        return this.thousandsSeparator;
    }

    /**
     * Gets the decimal point the number we are formatting will use
     *
     * @return String Our decimal point being used
     */
    public String getDecimalPoint() {
        return this.decimalPoint;
    }
}
