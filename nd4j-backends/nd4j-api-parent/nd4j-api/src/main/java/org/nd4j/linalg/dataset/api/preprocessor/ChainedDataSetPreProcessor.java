package org.nd4j.linalg.dataset.api.preprocessor;

import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Will preprocess data in the order of the preprocessors given in the constructor.
 * Fitting the preprocessor etc has to be handled outside of this class.
 * This simply applies the preprocess function in the requisite order.
 */
public class ChainedDataSetPreProcessor implements DataSetPreProcessor {

    private List<DataSetPreProcessor> preProcessorList;

    public ChainedDataSetPreProcessor(DataSetPreProcessor... preProcessors) {
        this.preProcessorList = Arrays.asList(preProcessors);
    }


    public List<DataSetPreProcessor> getPreProcessorList() {
        return preProcessorList;
    }

    public DataSetPreProcessor getPreProcessorAt(int index) {
        return preProcessorList.get(index);
    }

    @Override
    public void preProcess(DataSet toPreProcess) {

        for (DataSetPreProcessor p : preProcessorList) {
            p.preProcess(toPreProcess);
        }

    }
}
