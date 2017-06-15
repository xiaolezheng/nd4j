package org.nd4j.linalg.dataset.api.preprocessor;

import org.nd4j.linalg.dataset.api.MultiDataSet;
import org.nd4j.linalg.dataset.api.MultiDataSetPreProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Will preprocess data in the order of the preprocessors given in the constructor.
 * Fitting the preprocessor etc has to be handled outside of this class.
 * This simply applies the preprocess function in the requisite order.
 */
public class ChainedMultiDataSetPreProcessor implements MultiDataSetPreProcessor {

    private List<MultiDataSetPreProcessor> preProcessorList;

    public ChainedMultiDataSetPreProcessor(MultiDataSetPreProcessor... preProcessors) {
        this.preProcessorList = Arrays.asList(preProcessors);
    }

    public List<MultiDataSetPreProcessor> getPreProcessorList() {
        return preProcessorList;
    }

    public MultiDataSetPreProcessor getPreProcessorAt(int index) {
        return preProcessorList.get(index);
    }

    @Override
    public void preProcess(MultiDataSet multiDataSet) {

        for (MultiDataSetPreProcessor p : preProcessorList) {
            p.preProcess(multiDataSet);
        }

    }
}
