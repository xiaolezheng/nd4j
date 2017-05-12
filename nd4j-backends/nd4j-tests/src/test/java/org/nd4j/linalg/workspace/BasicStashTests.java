package org.nd4j.linalg.workspace;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.nd4j.linalg.BaseNd4jTest;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.factory.Nd4jBackend;
import org.nd4j.linalg.memory.stash.Stash;

import static org.junit.Assert.assertEquals;

/**
 * @author raver119@gmail.com
 */
@Slf4j
@RunWith(Parameterized.class)
public class BasicStashTests extends BaseNd4jTest {
    private final static String STASH_ID = "TEST_STASH";

    DataBuffer.Type initialType;

    public BasicStashTests(Nd4jBackend backend) {
        super(backend);
        this.initialType = Nd4j.dataType();
    }

    @Test
    public void testSimplePutGet1() throws Exception {
        INDArray array = Nd4j.linspace(0, 9, 10);
        INDArray exp = Nd4j.linspace(0, 9, 10);

        Stash<String> stash = Nd4j.getStashManager().getStash(STASH_ID);

        stash.put("A", array);

        INDArray restored = stash.get("A");

        assertEquals(exp, restored);

    }

    @Override
    public char ordering() {
        return 'c';
    }
}
