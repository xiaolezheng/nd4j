package org.nd4j.linalg.api.ops.aggregates;

import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.Accumulation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author raver119@gmail.com
 */
public class ReduceScalarAggregate<T extends Accumulation> implements Aggregate {
    private T op;

    public ReduceScalarAggregate(T op) {
        this.op = op;
    }

    @Override
    public String name() {
        return op.name();
    }

    @Override
    public int opNum() {
        return op.opNum();
    }

    @Override
    public void setFinalResult(Number result) {
        op.setFinalResult(result);
    }

    @Override
    public Number getFinalResult() {
        return op.getFinalResult();
    }

    @Override
    public List<INDArray> getArguments() {
        return Collections.singletonList(op.x());
    }

    @Override
    public List<DataBuffer> getShapes() {
        return Arrays.asList(new DataBuffer[] {op.x().shapeInfoDataBuffer(), op.extraArgsDataBuff()});
    }

    @Override
    public List<Integer> getIndexingArguments() {
        return Collections.emptyList();
    }

    @Override
    public List<Number> getRealArguments() {
        return Collections.emptyList();
    }

    @Override
    public List<int[]> getIntArrayArguments() {
        return Collections.emptyList();
    }

    /**
     * This method returns maximum number of shapes being passed per Aggregate
     *
     * @return
     */
    @Override
    public int maxArguments() {
        return 1;
    }

    /**
     * This method returns maximum number of shapes being passed per Aggregate
     *
     * @return
     */
    @Override
    public int maxShapes() {
        return 2;
    }

    /**
     * This method returns maximum number of IntArrays being passed per Aggregate
     *
     * @return
     */
    @Override
    public int maxIntArrays() {
        return 0;
    }

    /**
     * This method returns maximum length for IntArrays, if any
     *
     * @return
     */
    @Override
    public int maxIntArraySize() {
        return 0;
    }

    /**
     * This method returns maximum number of IndexArguments per Aggregate
     *
     * @return
     */
    @Override
    public int maxIndexArguments() {
        return 0;
    }

    /**
     * This method returns maximum number of real (float/double) per Aggregate
     *
     * @return
     */
    @Override
    public int maxRealArguments() {
        return 0;
    }

    /**
     * This method returns amount of memory required for batch creation for this specific Aggregate
     *
     * @return
     */
    @Override
    public long getRequiredBatchMemorySize() {
        return 0;
    }

    /**
     * This method returns amount of shared memory required for this specific Aggregate.
     * PLEASE NOTE: this method is especially important for CUDA backend. On CPU backend it might be ignored, depending on Aggregate.
     *
     * @return
     */
    @Override
    public int getSharedMemorySize() {
        return 0;
    }

    /**
     * This method returns desired number of threads per Aggregate instance
     * PLEASE NOTE: this method is especially important for CUDA backend. On CPU backend it might be ignored, depending on Aggregate.
     *
     * @return
     */
    @Override
    public int getThreadsPerInstance() {
        return 0;
    }
}
