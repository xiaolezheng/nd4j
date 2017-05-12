package org.nd4j.linalg.memory.stash;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * This interface describe short-living storage, with pre-defined life time.
 *
 * @author raver119@gmail.com
 */
public interface Stash<T extends Object> {

    /**
     * This method returns ID of this stash
     *
     * @return
     */
    String getId();

    /**
     * This method checks, if value with a given key exists
     *
     * @param key
     * @return
     */
    boolean checkIfExists(T key);

    /**
     * This method stores copy of given INDArray into this stash.
     *
     * PLEASE NOTE: This method leaves original INDArray intact
     *
     * @param key
     * @param object
     */
    void put(T key, INDArray object);

    /**
     * This method returns INDArray previously stored with a given key
     *
     * PLEASE NOTE: It will be new Java object, with the same content inside
     *
     * @param key
     * @return
     */
    INDArray get(T key);

    /**
     * This method resets this stash, deleting all content inside
     *
     * PLEASE NOTE: This method will NOT destroy any memory attached to it. See {@link #destroyStash()} for that
     */
    void purge();

    /**
     * This method purges storage, AND releases all memory used internally
     */
    void destroyStash();
}
