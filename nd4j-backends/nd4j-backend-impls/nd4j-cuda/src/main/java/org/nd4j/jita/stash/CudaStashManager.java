package org.nd4j.jita.stash;

import org.nd4j.linalg.memory.stash.BasicStashManager;
import org.nd4j.linalg.memory.stash.Stash;

/**
 * @author raver119@gmail.com
 */
public class CudaStashManager extends BasicStashManager {
    @Override
    protected <T> Stash<T> createStash(T stashId) {
        return new CudaStash<T>(stashId);
    }
}
