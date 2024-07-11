//345184295 Yonatan Benchabbat
package GameMaintenance;
/**
 * @author Yonatan Benchababt
 * @version 1.0
 * @since 2023 -06- 10
 */
public class Counter {
    private int value;
    /**
     * Constructor for counter class with default value zero.
     */
    public Counter() {
        this.value = 0;
    }
    /**
     * Counter constructor with a given value already initialized.
     * @param currentValue current value of item being counted.
     */
    public Counter(int currentValue) {
        this.value = currentValue;
    }
    /**
     * increases the value of counter by a given number.
     * @param number number being added to value.
     */
    public void increase(int number) {
        this.value += number;
    }
    /**
     * decrease the value of counter by a given number.
     * @param number number being subtracted from value.
     */
    public void decrease(int number) {
        this.value -= number;
    }
    /**
     * returns the value of the counter.
     * @return int containing value of counter.
     */
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
