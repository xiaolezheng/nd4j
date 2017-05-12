package org.nd4j.linalg.cpu.nativecpu.stash;

import org.nd4j.linalg.memory.stash.BasicStashManager;
import org.nd4j.linalg.memory.stash.Stash;

/**
 * @author raver119@gmail.com
 */
public class CpuStashManager extends BasicStashManager {
    @Override
    protected <T> Stash<T> createStash(T stashId) {
        return new CpuStash<T>(stashId);
    }
}
