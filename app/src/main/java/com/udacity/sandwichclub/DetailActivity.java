package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    TextView alsoKnownAsTV;
    TextView ingredientsTV;
    TextView originTV;
    TextView descriptionTV;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        alsoKnownAsTV = findViewById(R.id.also_known_tv);
        ingredientsTV =  findViewById(R.id.ingredients_tv);
        originTV =  findViewById(R.id.origin_tv);
        descriptionTV =  findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
       if (sandwich.getAlsoKnownAs()!=null){

            StringBuilder sb = new StringBuilder();

                for (String str: sandwich.getAlsoKnownAs())
                {
                    sb.append(str.toString());
                    sb.append(",");
                }

            alsoKnownAsTV.setText(" " + sb);


        }

        if (sandwich.getIngredients()!=null){

            StringBuilder sb = new StringBuilder();

                for (String str: sandwich.getIngredients())
                {
                    sb.append(str.toString());
                    sb.append(",");
                }

            ingredientsTV.setText(" " + sb);

        }

        originTV.setText(" " + sandwich.getPlaceOfOrigin());
        descriptionTV.setText(" " + sandwich.getDescription());

    }
}
