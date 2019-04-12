package nl.hyper42.kim.offchain.dao;

/**
 * The dao to get the next number in a Sequence .
 *
 * @author Micha Wensveen
 */
public interface SequenceDao {

    /**
     * Generate the next number int asequence.
     *
     * @param sequenceName Name of the Sequeance
     * @return the number
     */
    long generateSequence(String sequenceName);
}
