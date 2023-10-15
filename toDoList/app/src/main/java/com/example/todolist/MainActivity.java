package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        private ArrayList<String> items;
        private ArrayAdapter<String> itemsAdapter;
        private ListView lvItems;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            lvItems = findViewById(R.id.lvItems);
            items = new ArrayList<>();
            itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
            lvItems.setAdapter(itemsAdapter);
            setupListViewListener();
            readItems();
        }

        public void onAddItem(View v) {
            EditText etNewItem = findViewById(R.id.etNewItem);
            String itemText = etNewItem.getText().toString().trim();
            if (!itemText.isEmpty()) {
                itemsAdapter.add(itemText);
                etNewItem.setText("");
                writeItems();
            }
        }

        private void setupListViewListener() {
            lvItems.setOnItemLongClickListener((adapter, item, pos, id) -> {
                // Handle long click item removal
                if (removeItem(pos)) {
                    itemsAdapter.notifyDataSetChanged();
                    writeItems();
                }
                return true;
            });
        }

        private boolean removeItem(int pos) {
            try {
                items.remove(pos);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private void readItems() {
            File todoFile = new File(getFilesDir(), "todo.txt");
            try {
                items.clear();
                items.addAll(FileUtils.readLines(todoFile));
            } catch (IOException e) {
                items = new ArrayList<>();
            }
        }

        private void writeItems() {
            File todoFile = new File(getFilesDir(), "todo.txt");
            try {
                FileUtils.writeLines(todoFile, items);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
}