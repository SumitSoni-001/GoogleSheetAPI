package com.example.krishworksassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.SheetProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button ResultBtn;
    TextView showData, showResult;
    Retrofit retrofit;  // creating Retrofit Object
    ApiInterface apiInterface;

    String dataBuffer = "", ResultBuffer = "";    // Stores Response
    int length = -1; // Stores Length of response list
    int[] mark; // Stores student's marks in Integer form

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showData = findViewById(R.id.Data);
        showResult = findViewById(R.id.Result);
        ResultBtn = findViewById(R.id.ResultBtn);

        retrofit = new APIControl().getAPI();
        apiInterface = retrofit.create(ApiInterface.class);

        fetchData();

        ResultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ResultBuffer = "";
                calculation();
            }
        });

    }

    private void fetchData() {
        dataBuffer = "";
        ResultBuffer = "";
        showData.setText("");
        showResult.setText("");

        apiInterface.getAPI().enqueue(new Callback<SheetResponse>() {
            @Override
            public void onResponse(Call<SheetResponse> call, Response<SheetResponse> response) {
                if (response.isSuccessful()) {

                    length = response.body().getValues().size();
                    mark = new int[length - 1];

                    for (int i = 0; i < length; i++) {

                        dataBuffer += response.body().getValues().get(i).get(0) + " ";
                        dataBuffer += response.body().getValues().get(i).get(1) + " ";
                        dataBuffer += response.body().getValues().get(i).get(2) + " ";

                        if (!response.body().getValues().get(i).get(2).equals("MARKS")) {
                            mark[i-1] = Integer.parseInt(response.body().getValues().get(i).get(2));
                        }

                        dataBuffer += "\n";
                    }
                    showData.setText(dataBuffer);
                } else {
                    if (response.errorBody() != null) {
                        showData.setText("Unsuccessful" + response.errorBody());
                    }
                }
            }

            @Override
            public void onFailure(Call<SheetResponse> call, Throwable t) {
                showData.setText(t.getMessage());
            }
        });
    }

    private void calculation() {     // Declaring the student PASS or FAIL
        if (dataBuffer != "") {
            ResultBuffer += "\n";
            for (int i = 0; i < length - 1; i++) {
                if (mark[i] >= 40) {
                    ResultBuffer += "Pass\n";
                } else {
                    ResultBuffer += "Fail\n";
                }
            }
            showResult.setText(ResultBuffer);
        } else {
            showResult.setText("No Data");
        }
    }

}