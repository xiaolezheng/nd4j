package org.nd4j.linalg.memory.stash;

/**
 * @author raver119@gmail.com
 */
public abstract class BasicStashManager implements StashManager {

    @Override
    public <T> boolean checkIfStashExists(T stashId) {
        return false;
    }

    @Override
    public <T> Stash<T> getStash(T stashId) {
        return null;
    }

    @Override
    public <T> Stash<T> createStashIfNotExists(T stashId) {
        return checkIfStashExists(stashId) ? getStash(stashId) : createStash(stashId);
    }

    protected abstract <T> Stash<T>  createStash(T stashId);
}
