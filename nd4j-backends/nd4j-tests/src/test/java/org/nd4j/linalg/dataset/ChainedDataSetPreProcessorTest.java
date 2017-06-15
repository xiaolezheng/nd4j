package org.nd4j.linalg.dataset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nd4j.linalg.BaseNd4jTest;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.preprocessor.ChainedDataSetPreProcessor;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerMinMaxScaler;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.factory.Nd4jBackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by susaneraly on 6/15/17.
 */
@RunWith(Parameterized.class)
public class ChainedDataSetPreProcessorTest extends BaseNd4jTest{

    public ChainedDataSetPreProcessorTest(Nd4jBackend backend) {
        super(backend);
    }

    @Test
    public void minMaxThenCustomProcessor() {
        INDArray features = Nd4j.linspace(1,100,100).reshape(50,2);
        INDArray labels = Nd4j.zeros(50,2);

        DataSet dataSet = new DataSet(features,labels);
        NormalizerMinMaxScaler minMaxScaler = new NormalizerMinMaxScaler();
        //should give min = 1,2 and max = 99,100
        // col1 and col2 after preprocess should be the same and be between 0 and 1
        minMaxScaler.fit(dataSet);

        ChainedDataSetPreProcessor chainedDataSetPreProcessor = new ChainedDataSetPreProcessor(minMaxScaler,new willAdd5CustomPreprocessor());
        chainedDataSetPreProcessor.preProcess(dataSet);

        assertEquals(dataSet.getFeatures().getColumn(0),dataSet.getFeatures().getColumn(1));
        assertEquals(Nd4j.linspace(0,1,50).reshape(50,1).addi(5), dataSet.getFeatures().getColumn(0));


        assertTrue(chainedDataSetPreProcessor.getPreProcessorList().size() == 2);
        assertTrue(chainedDataSetPreProcessor.getPreProcessorAt(0) instanceof NormalizerMinMaxScaler);
        assertTrue(chainedDataSetPreProcessor.getPreProcessorAt(1) instanceof willAdd5CustomPreprocessor);

        /*
        NormalizerStandardize standardize = new NormalizerStandardize();
        standardize.fit(dataSet);
        */

    }

    public static final class willAdd5CustomPreprocessor implements DataSetPreProcessor {

        @Override
        public void preProcess(org.nd4j.linalg.dataset.api.DataSet toPreProcess) {
            toPreProcess.getFeatures().addi(5);
        }
    }


    @Override
    public char ordering() {
        return 'c';
    }
}
