package nl.rwslinkman.presentable;

import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;

import nl.rwslinkman.presentable.util.TestStringPresenter;

/**
 * @author Rick Slinkman
 */
public class PresentableAdapterTest
{
    private PresentableAdapter<String> adapter;

    @Before
    public void setUp() throws Exception
    {
        this.adapter = new PresentableAdapter<>(new TestStringPresenter(), new ArrayList<String>());
    }

    @After
    public void tearDown() throws Exception {
        this.adapter = null;
    }

}