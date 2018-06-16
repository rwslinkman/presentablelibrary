package nl.rwslinkman.presentablelibrary;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nl.rwslinkman.presentable.PresentableAdapter;
import nl.rwslinkman.presentable.interaction.PresentableItemInteractionListener;

/**
 * Example to display the workings of the Presentable library
 * @author Rick Slinkman
 */
public class ExampleActivity extends Activity implements PresentableItemInteractionListener<String>, View.OnClickListener, View.OnKeyListener
{
    private static final String TAG = "ExampleActivity";
    private RecyclerView mRecyclerView;
    private static final int NUMBER_OF_NAMES = 100;
    private PresentableAdapter<String> mAdapter;
    private FloatingActionButton mAddNameButton;
    private TextView mAddNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setReverseLayout(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.example_recycler);
        mRecyclerView.setLayoutManager(llm);

        mAddNameButton = (FloatingActionButton) findViewById(R.id.example_add_name_btn);
        mAddNameField = (TextView) findViewById(R.id.example_add_name_field);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Create data (load from your server, load from device, etc..)
        List<String> data = Utils.createRandomNamesList(getResources(), NUMBER_OF_NAMES);

        // Create adapter
        mAdapter = new PresentableAdapter<>(new ExampleStringPresenter(), data);
        mAdapter.setItemInteractionListener(this);
//        mAdapter.setItemClickListener(this);
//        mAdapter.setItemPressedListener(this);
        mRecyclerView.setAdapter(mAdapter);

        // Interaction with other elements
        mAddNameButton.setOnClickListener(this);
        mAddNameField.setOnKeyListener(this);
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
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch (keyCode)
            {
                case KeyEvent.KEYCODE_DPAD_CENTER:
                case KeyEvent.KEYCODE_ENTER:
                    // Simulate button click
                    onClick(mAddNameButton);
                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.example_add_name_btn) {
            // Collect input
            String input = mAddNameField.getText().toString();

            // Add a new item and refresh list afterwards.
            mAdapter.addItem(input);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);

            Utils.closeKeyboard(this);

            String logMsg = String.format("Item '%s' added", input);
            Log.d(TAG, logMsg);
            Toast.makeText(this, logMsg, Toast.LENGTH_SHORT).show();
        }
    }
}
