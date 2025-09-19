package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayNoteActivity extends AppCompatActivity {

    Button Button;
    EditText titleOfTextNote, contentOfTextNote;
    TextView showNote,showNoteFromAPI;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //showNote=findViewById(R.id.textView3);
        showNoteFromAPI =findViewById(R.id.textView3);
        titleOfTextNote = findViewById(R.id.editTextText3);
        progressBar = findViewById(R.id.progressBar2);
        showNote = findViewById(R.id.textView6);
        Button = findViewById(R.id.button5);

        progressBar.setVisibility(View.GONE);
        showNote.setVisibility(View.GONE);
        Executors.newSingleThreadExecutor().execute(()-> {
            List<NoteEntity> entities = AppDatabase.getInstance(this).noteDao().getAll();
            List<Note> notes = new ArrayList<>();
            for (NoteEntity e : entities) {

            }
            runOnUiThread(() -> {
                StringBuilder sb = new StringBuilder();
                for (Note n : notes) {
                    sb.append(n.display()).append("\n");
                }
                showNote.setText(sb.toString());
            });
        });
        Retrofit retrofit = new Retrofit. Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiSerVice apiService = retrofit.create(ApiSerVice.class);
        Call<List<TextNote>> call = apiService.getTextNote();
        call.enqueue(new Callback<List<TextNote>>() {
            @Override
            public void onResponse(Call < List < TextNote >> call, Response< List < TextNote >> response){
                if (!response.isSuccessful()) {
                    showNoteFromAPI.setText("Error Code: " + response.code());
                    return;
                }
                List<TextNote> notes = response.body();
                StringBuilder builder = new StringBuilder();
                for (TextNote n : notes) {
                    builder.append("Title: ").append(n.getTitle()).append("\n")
                            .append("Body: ").append(n.getTextContent()).append("\n\n");
                }
                showNoteFromAPI.setText(builder.toString());
            }
            @Override
            public void onFailure (Call < List < TextNote >> call, Throwable t){
                showNoteFromAPI.setText("Failed: " + t.getMessage());
            }
        });


        Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(view.VISIBLE);
                    showNote.setVisibility(View.GONE);
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        runOnUiThread(() -> {
                            progressBar.setVisibility(View.GONE);
                            showNote.setVisibility(View.VISIBLE);

                        });
                    }).start();

                }
            });
        }
}
