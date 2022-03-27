/**
 * Генератор ID.
 */

public class GeneratorId {
    static private int Id = 0;

    static public int next() {
        return ++Id;
    }
}
