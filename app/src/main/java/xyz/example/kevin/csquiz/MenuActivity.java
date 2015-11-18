package xyz.example.kevin.csquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.example.kevin.csquiz.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewGroup table = (ViewGroup)findViewById(R.id.layout);
        View child;

        // Sets the onClickListener for each category button which belongs to a row of the table.
        for(int i = 0; i < table.getChildCount(); i++)
        {
            child = table.getChildAt(i);

            if(child instanceof TableRow)
            {
                child = ((TableRow) child).getChildAt(0);

                if(child instanceof Button)
                {
                    ((Button) child).setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            // Store the chosen category, questions built in following activity
                            Intent start = new Intent(v.getContext(), MainActivity.class);
                            Button b = (Button)v;
                            start.putExtra("category", b.getText().toString());

                            finish();
                            startActivity(start);
                        }
                    });
                }
            }
        }
    }
}
