package org.nd4j.linalg.api.ops.aggregates;

import org.nd4j.linalg.api.ops.Accumulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for batches creation. Should be merged into Executioner, to be consistent .
 *
 * @author raver119@gmail.com
 */
public class ScalarBatchHelper {
    private static final ThreadLocal<List<Aggregate>> aggregates = new ThreadLocal<>();



    public static <T extends Accumulation> void enqueue(T op) {
        if (aggregates.get() == null)
            aggregates.set(new ArrayList<Aggregate>());

        // TODO: check if previously enqueued ops are of the same OpNum/OpType BEFORE enqueue

        aggregates.get().add(new ReduceScalarAggregate<T>(op));
    }


    public static void commit() {
        // basically we want to push stuff, and store results somewhere
    }
}
