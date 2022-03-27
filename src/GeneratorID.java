/**
 * Генератор ID.
 */

public class GeneratorID {
    static private int ID = 0;

    static public int next() {
        return ++ID;
    }
}
