package earlp.com.matrixcalcuator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

public class Calculator extends AppCompatActivity {

    public int numRows;
    public int numCols;
    public int numRowsTwo;
    public int numColsTwo;
    private MathMatrix values;
    private MathMatrix valuesTwo;

    private int filledRows;
    private int filledRowsTwo;

    boolean twoMatrices = false;

    //UI elements
    private TextView matrixText, matrixTwoText;
    private EditText rowInput, colInput, rowAdd, scaleInput, rowInputTwo, colInputTwo;
    private CheckBox twoMatrixCheck;
    private Button initializeBtnTwo, initializeBtnOne;

    private LinearLayout oneCreation, twoCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //initialize UI Elements

        //two text views showing matrices
        matrixText = findViewById(R.id.matrixText);
        matrixTwoText = findViewById(R.id.matrix2Text);

        //input to initialize the rows and columns
        rowInput = findViewById(R.id.rowEnter);
        colInput = findViewById(R.id.colEnter);
        rowInputTwo = findViewById(R.id.rowEnter2);
        colInputTwo = findViewById(R.id.colEnter2);


        //text edit to initialize the rows
        rowAdd = findViewById(R.id.rowInput);

        //Button to initialize second matrix
        initializeBtnTwo = findViewById(R.id.Initialize2);
        initializeBtnOne = findViewById(R.id.Initialize1);

        scaleInput = findViewById(R.id.scaleFactorTxt);
        twoMatrixCheck = findViewById(R.id.twoMatricesCheck);

        oneCreation = findViewById(R.id.matrixOneCreation);
        twoCreation = findViewById(R.id.matrixTwoCreation);
        twoCreation.setVisibility(View.GONE);


        //make checkbox set visibility
        twoMatrixCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    twoMatrices = true;
                    matrixTwoText.setVisibility(View.VISIBLE);
                    twoCreation.setVisibility(View.VISIBLE);
                }else{
                    twoMatrices = false;
                    matrixTwoText.setVisibility(View.GONE);
                    twoCreation.setVisibility(View.GONE);
                }
            }
        });

        //make the row add button click once the user presses the enter key
        rowInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            addRow(v);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


    }

    private void fillMatrix(int value, int[][] matrix){
        for (int r = 0; r < numRows; r++){
            for(int c = 0; c < numCols; c++){
                matrix[r][c] = value;
            }
        }
    }

    //sets the number of rows and clumns for the first matrix
    public void submit1(View v){
        if(!rowInput.getText().toString().equals("") && !colInput.getText().toString().equals("")) {
            //takes in the user input for rows and columns
            numRows = Integer.parseInt(rowInput.getText().toString());
            numCols = Integer.parseInt(colInput.getText().toString());

            //makes new matrix with the new rows and columns
            values = new MathMatrix(numRows, numCols, 0);
            filledRows = 0;

            updateOne();

            oneCreation.setVisibility(View.GONE);
            ;
        }else{
            Toast.makeText(this, "Row/Column missing", Toast.LENGTH_SHORT).show();
        }
            
    }

    //sets the number of rows and clumns for the second matrix
    public void submit2(View v){
        if(!rowInputTwo.getText().toString().equals("") && !colInputTwo.getText().toString().equals("")) {
            //takes in the user input for rows and columns
            numRowsTwo = Integer.parseInt(rowInputTwo.getText().toString());
            numColsTwo = Integer.parseInt(colInputTwo.getText().toString());

            //makes new matrix with the new rows and columns
            valuesTwo = new MathMatrix(numRowsTwo, numColsTwo, 0);
            updateTwo();


            //cear a matrix two initiaizations
            twoCreation.setVisibility(View.GONE);

        }else{
            Toast.makeText(this, "Row/Column missing", Toast.LENGTH_SHORT).show();
        }

    }

     public void addRow(View v){
        //if not all the first rows are filled
        if(filledRows < numRows) {
            String row = rowAdd.getText().toString();
            Scanner scan = new Scanner(row);
            for (int i = 0; i < numCols; i++) {
                values.changeElement(filledRows, i, scan.nextInt());
            }
            filledRows++;
            matrixText.setText(values.toString());
            rowAdd.setText("");
            scan.close();
        }
        //if the second matrix has not been filled
        else if (twoMatrices && filledRowsTwo <numRowsTwo){
            String row = rowAdd.getText().toString();
            Scanner scan = new Scanner(row);
            for (int i = 0; i < numColsTwo; i++) {
                valuesTwo.changeElement(filledRowsTwo, i, scan.nextInt());
            }
            filledRowsTwo++;
            matrixTwoText.setText(valuesTwo.toString());
            rowAdd.setText("");
            scan.close();
        }else{
            Toast.makeText(this, "Matrix Filled", Toast.LENGTH_SHORT).show();
        }
        if(!twoMatrices && filledRows == numRows){

        }
     }

     public void multiply(View v) {
        String input = scaleInput.getText().toString();
         if (!input.equals("")){
             values.scale(Integer.parseInt(input));
            matrixText.setText(values.toString());
        }
        else
            Toast.makeText(this, "Please enter scale factor", Toast.LENGTH_SHORT).show();
     }

     private void updateOne    (){
         matrixText.setText(values.toString());
     }

    private void updateTwo    (){
        matrixTwoText.setText(valuesTwo.toString());
    }

}
