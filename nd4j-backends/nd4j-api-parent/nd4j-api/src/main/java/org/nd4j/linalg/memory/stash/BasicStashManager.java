package org.nd4j.linalg.memory.stash;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author raver119@gmail.com
 */
public abstract class BasicStashManager implements StashManager {
    protected Map<String, Stash> holder = new ConcurrentHashMap<>();


    @Override
    public <T> boolean checkIfStashExists(String stashId) {
        return holder.containsKey(stashId);
    }

    @Override
    public <T> Stash<T> getStash(String stashId) {
        return holder.get(stashId);
    }

    @Override
    public <T> Stash<T> createStashIfNotExists(String stashId) {
        if (checkIfStashExists(stashId)) {
            return getStash(stashId);
        } else {
            Stash<T> stash = createStash(stashId);
            holder.put(stashId, stash);
            return stash;
        }
    }

    protected abstract <T> Stash<T>  createStash(String stashId);
}
