package nl.rwslinkman.presentable;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import nl.rwslinkman.presentable.util.TestStringPresenter;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Rick Slinkman
 */
public class PresentableAdapterTest
{
    private PresentableAdapter<String> testAdapter;

    @Before
    public void setUp()
    {
        testAdapter = buildTestAdapter(true);
    }

    @Test
    public void test_shouldReturnViewHolder()
    {

        ViewGroup inputGroup = new RelativeLayout(null);

        RecyclerView.ViewHolder output = testAdapter.onCreateViewHolder(inputGroup, 0);
        assertNotNull(output);
        assertNotNull(output.itemView);
    }

    @Test(expected = IllegalStateException.class)
    public void test_shouldThrowExceptionWhenNoViewReturned()
    {
        PresentableAdapter<String> adapter = buildTestAdapter(false);
        ViewGroup inputGroup = new RelativeLayout(null);

        adapter.onCreateViewHolder(inputGroup, 0);
    }

    @Test
    public void test_shouldReturnItemCount()
    {
        assertEquals(2, testAdapter.getItemCount());
    }

    @Test
    public void test_shouldReturnEmptyDataList_withNullData()
    {
        PresentableAdapter<String> adapter = buildTestAdapter(true, null);
        assertNotNull(adapter.getData());
        assertEquals(0, adapter.getData().size());
    }

    private PresentableAdapter<String> buildTestAdapter(boolean shouldCreateView)
    {
        List<String> data = Arrays.asList("Hello", "World");
        return buildTestAdapter(shouldCreateView, data);
    }

    private PresentableAdapter<String> buildTestAdapter(boolean shouldCreateView, List<String> data)
    {
        TestStringPresenter presenter = new TestStringPresenter(shouldCreateView);
        return new PresentableAdapter<>(presenter, data);
    }
}