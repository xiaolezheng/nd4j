package org.nd4j.linalg.memory.stash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author raver119@gmail.com
 */
public abstract class BasicStash<T extends Object> implements Stash<T> {
    // obviously we won't have INDArrays stored here.
    protected Map<T, INDArray> stash = new ConcurrentHashMap<>();
    @Getter protected T id;

    /*
        This workspace will be used
     */
    protected MemoryWorkspace workspace;

    protected BasicStash(T id) {

    }

    @Override
    public boolean checkIfExists(T key) {
        /*
            Just checkin'
         */
        return stash.containsKey(key);
    }

    @Override
    public void put(T key, INDArray object) {
        /*
            Basically we want to get DataBuffer content here, and store it here together with shape
            Special case here is GPU: we want to synchronize HOST memory, and store only HOST memory.
            So, original INDArray stays intact, and copy gets used here.
         */
    }

    @Override
    public INDArray get(T key) {
        /*
            We want to restore INDArray here, In case of GPU backend - we want to ensure data is replicated to device.
         */
        return null;
    }

    @Override
    public void purge() {
        /*
            We want to purge all stored stuff here.
         */
        stash.clear();

        // TODO: we'll reset workspace memory here probably?
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    protected static class BuffersPair {
        /**
         * DataBuffer that refers to some underlying workspace memory chunk. Should be used only for memcpy
         */
        protected DataBuffer dataBuffer;

        // TODO: decide, if we really want shape in jvm[] here
        protected int[] shape;
    }
}
