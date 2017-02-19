package msu.ece.xiaozeng.mpf3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import msu.ece.xiaozeng.mpf3.entity.Pill;
import msu.ece.xiaozeng.mpf3.ui.RVAdapter;

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setTitle("Search");
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_return_pills);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        ArrayList<Pill> pills = Pill.initializeData();
        RVAdapter adapter = new RVAdapter(pills,this);
        rv.setAdapter(adapter);
    }


}
