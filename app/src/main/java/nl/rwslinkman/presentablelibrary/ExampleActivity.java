package nl.rwslinkman.presentablelibrary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import nl.rwslinkman.presentable.PresentableAdapter;
import nl.rwslinkman.presentable.PresentableItemClickListener;

public class ExampleActivity extends AppCompatActivity implements PresentableItemClickListener<String>
{
    private static final String TAG = "ExampleActivity";
    private RecyclerView mRecyclerView;
    private static final int NUMBER_OF_NAMES = 100;
    private PresentableAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.example_recycler);
        mRecyclerView.setLayoutManager(llm);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Create data (load from your server, load from device, etc..)
        List<String> data = createRandomNamesList(NUMBER_OF_NAMES);

        // Create adapter
        mAdapter = new PresentableAdapter<>(new ExampleStringPresenter(), data);
        mAdapter.setItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        // You can update the dataSet as follows
        // mAdapter.setData();
        // mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemPressed(final String item) {
        String msg = "Removed from list: " + item;
        Log.d(TAG, msg);

        Snackbar.make(mRecyclerView, msg, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.example_remove_undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        mAdapter.getData().add(item);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .show();

        // Update adapter and signal redraw
        mAdapter.getData().remove(item);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(String item) {
        String msg = "onItemClicked: " + item;
        Log.d(TAG, msg);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onItemClicked: Clicked on one of " + Integer.toString(mAdapter.getData().size()) + " items");
    }

    private List<Integer> getExampleNamesResources()
    {
        List<Integer> result = new ArrayList<>();
        // Find all strings
        Field[] fields = R.string.class.getFields();
        for(final Field field : fields)
        {
            String name = field.getName(); //name of string
            if(name.startsWith("example_data_item_"))
            {
                try
                {
                    // Add string to list of options
                    int id = field.getInt(R.string.class); //id of string
                    result.add(id);
                }
                catch (Exception ex) {
                    // Error grabbing string
                    Log.e(TAG, "Could not get string: " + ex.getMessage());
                }
            }
        }
        return result;
    }

    @NonNull
    private List<String> createRandomNamesList(int numberOfNames)
    {
        List<Integer> namesResources = getExampleNamesResources();
        if(numberOfNames >= namesResources.size()) {
            // Cannot use more names than available
            numberOfNames = namesResources.size();
        }

        List<String> randomNames = new ArrayList<>();
        for(int i = 0; i < numberOfNames; i++)
        {
            String name = getString(namesResources.get(i));
            randomNames.add(name);
        }
        return randomNames;
    }
}
