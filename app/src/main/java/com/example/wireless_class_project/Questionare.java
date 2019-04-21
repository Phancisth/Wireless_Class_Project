/*Project By
5988023	Purit		Phanudom
5988053	Naruedon	Wattanakul
5988098	Tattiya		Sakulniwat
 */
package com.example.wireless_class_project;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Questionare extends AppCompatActivity {

    //Namespace
    RadioGroup radioGroup1,radioGroup2, radioGroup3, radioGroup4, radioGroup5;
    RadioButton checkedRadioButtonG1, checkedRadioButtonG2, checkedRadioButtonG3, checkedRadioButtonG4, checkedRadioButtonG5;
    Button nextButton;
    TextView Q1,Q2,Q3,Q4,Q5;
    private int[] Score = new int[8];//0=CN,1=CS,2=DB,3=EB,4=HT,5=MM,6=MS,7=SE
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);
        //Initialize
        Q1 = findViewById(R.id.Questionare_Q1);
        Q2 = findViewById(R.id.Questionare_Q2);
        Q3 = findViewById(R.id.Questionare_Q3);
        Q4 = findViewById(R.id.Questionare_Q4);
        Q5 = findViewById(R.id.Questionare_Q5);

        nextButton = findViewById(R.id.next);
        Button prevButton = findViewById(R.id.prev);
        Button logOutButton = findViewById(R.id.log_out);

        //Listener for 'Next' button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next(v);
            }
        });

        //Listener for 'Previous' button
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prev(v);
            }
        });

        //Listener for 'Log out' button
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout(v);
            }
        });

            page = getIntent().getIntExtra("Page", 1);
        if(page != 1) {
            Score = getIntent().getIntArrayExtra("Score");
        }
        switch(page)
        {//Set the questions right here
            case 1:
                Q1.setText("Code");
                Q2.setText("Math");
                Q3.setText("Memorise");
                Q4.setText("Research");
                Q5.setText("Open-ended Question");
                break;
            case 2:
                Q1.setText("Reading");
                Q2.setText("Hardware");
                Q3.setText("Animation Drawing");
                Q4.setText("Software Quality Checking");
                Q5.setText("Broad Area of Knowledge");
                break;
            case 3:
                Q1.setText("Managing Data");
                Q2.setText("Networking");
                Q3.setText("Like Management");
                Q4.setText("Like Business/Start Up");
                Q5.setText("Health Science");
                nextButton.setText("Submit");
                break;
            case 4:
                finishAndRemoveTask();
                Intent resultisago = new Intent(this, QuestionareResult.class);
                resultisago.putExtra("Score",Score);
                startActivity(resultisago);
                finishAndRemoveTask();
                break;
        }

        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        radioGroup3 = findViewById(R.id.radioGroup3);
        radioGroup4 = findViewById(R.id.radioGroup4);
        radioGroup5 = findViewById(R.id.radioGroup5);

    }
    //Logout of the applicaton
    public void Logout(View view)
    {
        finishAndRemoveTask();
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //Go to the next Question Page
    public void Next(View view) {
    int passable = 1;
        //Get some score here
        switch(page)
        {
            case 1://This is Page 1
            switch(checkButtonG1(view))// Q1: Like Code?
            {
                case "0":
                    passable = 0;
                    break;
                case "1":
                //According to the paper that we wrote
                    //index of Score[]: 0=CN,1=CS,2=DB,3=EB,4=HT,5=MM,6=MS,7=SE
                    Score[6] += 1;
                    break;

                case "2":
                    Score[4] += 1;
                    break;

                case "3":
                    Score[3] += 1;
                    Score[7] += 1;
                    break;

                case "4":
                    Score[0] += 1;
                    Score[2] += 1;
                    break;

                case "5":
                    Score[1] += 1;
                    Score[5] += 1;
                    break;
            }
            switch(checkButtonG2(view))//Q2: Like math?
            {
                case "0":
                    passable = 0;
                    break;
                case "1":
                    Score[6] += 1;
                    break;

                case "2":
                    Score[2] += 1;
                    Score[4] += 1;
                    break;

                case "3":
                    Score[0] += 1;
                    Score[3] += 1;
                    break;

                case "4":
                    Score[5] += 1;
                    Score[7] += 1;
                    break;

                case "5":
                    Score[1] += 1;
                    break;
            }
            switch(checkButtonG3(view))//Q3: Memorize?
            {
                case "0":
                    passable = 0;
                    break;
                case "1":
                    Score[3] += 1;
                    Score[1] += 1;
                    Score[6] += 1;
                    break;

                case "2":
                    Score[2] += 1;
                    break;

                case "3":
                    Score[5] += 1;
                    Score[7] += 1;
                    break;

                case "4":
                    Score[6] += 1;
                    break;

                case "5":
                    Score[4] += 1;
                    break;
            }
            switch(checkButtonG4(view))//Q4: Research?
            {
                case "0":
                    passable = 0;
                    break;
                case "1":
                    Score[3] += 1;
                    break;

                case "2":
                    Score[5] += 1;
                    Score[6] += 1;
                    break;

                case "3":
                    Score[0] += 1;
                    Score[2] += 1;
                    break;

                case "4":
                    Score[4] += 1;
                    Score[7] += 1;
                    break;

                case "5":
                    Score[1] += 1;
                    break;
            }
            switch(checkButtonG5(view))// Q5: Open-ended?
            {
                case "0":
                    passable = 0;
                    break;
                case "1":
                    Score[0] += 1;
                    Score[1] += 1;
                    break;

                case "2":
                    Score[2] += 1;
                    break;

                case "3":
                    Score[4] += 1;
                    Score[7] += 1;
                    break;

                case "4":
                    Score[5] += 1;
                    break;

                case "5":
                    Score[3] += 1;
                    Score[6] += 1;
                    break;
            }

                break;
            case 2: //Questionnaire Page 2
                switch(checkButtonG1(view))// Q6: Reading?
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[5] += 1;
                        break;

                    case "2":
                        Score[0] += 1;
                        break;

                    case "3":
                        Score[7] += 1;
                        break;

                    case "4":
                        Score[1] += 1;
                        Score[2] += 1;
                        break;

                    case "5":
                        Score[3] += 1;
                        Score[4] += 1;
                        Score[6] += 1;
                        break;
                }
                switch(checkButtonG2(view))//Q7: Hardware?
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[2] += 1;
                        Score[7] += 1;
                        break;

                    case "2":
                        Score[3] += 1;
                        Score[5] += 1;
                        break;

                    case "3":
                        Score[6] += 1;
                        break;

                    case "4":
                        Score[4] += 1;
                        break;

                    case "5":
                        Score[0] += 1;
                        Score[1] += 1;
                        break;
                }
                switch(checkButtonG3(view))// Q8:MM
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[5] -= 3;
                        break;

                    case "2":
                        Score[5] -= 1;
                        break;

                    case "4":
                        Score[5] += 1;
                        break;

                    case "5":
                        Score[5] += 3;
                        break;
                }
                switch(checkButtonG4(view))//Q9:SE
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[7] -= 3;
                        break;

                    case "2":
                        Score[7] -= 1;
                        break;

                    case "4":
                        Score[7] += 1;
                        break;

                    case "5":
                        Score[7] += 3;
                        break;
                }
                switch(checkButtonG5(view))//Q10:CS
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[1] -= 3;
                        break;

                    case "2":
                        Score[1] -= 1;
                        break;

                    case "4":
                        Score[1] += 1;
                        break;

                    case "5":
                        Score[1] += 3;
                        break;
                }
                break;
            case 3: //Questionnaire Page 3

                switch(checkButtonG1(view))//Q11: DB
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[2] -= 3;
                        break;

                    case "2":
                        Score[2] -= 1;
                        break;

                    case "4":
                        Score[2] += 1;
                        break;

                    case "5":
                        Score[2] += 3;
                        break;
                }
                switch(checkButtonG2(view))//Q12:CN
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[0] -= 3;
                        break;

                    case "2":
                        Score[0] -= 1;
                        break;

                    case "4":
                        Score[0] += 1;
                        break;

                    case "5":
                        Score[0] += 3;
                        break;
                }
                switch(checkButtonG3(view))//Q13: MS
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[6] -= 3;
                        break;

                    case "2":
                        Score[6] -= 1;
                        break;

                    case "4":
                        Score[6] += 1;
                        break;

                    case "5":
                        Score[6] += 3;
                        break;
                }
                switch(checkButtonG4(view))//Q14: EB
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[3] -= 3;
                        break;

                    case "2":
                        Score[3] -= 1;
                        break;

                    case "4":
                        Score[3] += 1;
                        break;

                    case "5":
                        Score[3] += 3;
                        break;
                }
                switch(checkButtonG5(view))//Q15: HT
                {
                    case "0":
                        passable = 0;
                        break;
                    case "1":
                        Score[4] -= 3;
                        break;

                    case "2":
                        Score[4] -= 1;
                        break;

                    case "4":
                        Score[4] += 1;
                        break;

                    case "5":
                        Score[4] += 3;
                        break;
                }

                break;
        }
        //Go the to the next page in the questionnaire
        if(passable == 1) {
            Intent nextpage = new Intent(this, Questionare.class);
            nextpage.putExtra("Score", Score);
            nextpage.putExtra("Page", page + 1);
            //System.out.println(page + " " + "/n" + "Score " + Score[0] + " " + Score[1] + " " + Score[2] + " " + Score[3] + " " + Score[4] + " " + Score[5] + " " + Score[6] + " " + Score[7] + " /n");
            startActivity(nextpage);
        }
        else
        {
            //Nothing The page is still
            Toast.makeText(Questionare.this,"Please Answer All Questions", Toast.LENGTH_SHORT).show();
            for(int i=0;i<8;i++)
            {
                Score[i] = 0;
            }
        }

    }
    //Resets the questions page
    public void Prev(View view) {
        finishAndRemoveTask();
        Intent restart = new Intent(this,Questionare.class);
        startActivity(restart);
    }
    //Check button pressed
    public String checkButtonG1 (View view) {

        int radioID = radioGroup1.getCheckedRadioButtonId();
        checkedRadioButtonG1 = findViewById(radioID);
        if(checkedRadioButtonG1 == null)
        {


        }
        else {
            CharSequence radioButtonText = checkedRadioButtonG1.getText();

            return radioButtonText.toString();
        }
        return "0";
    }
    //Check button pressed
    public String checkButtonG2 (View view) {

        int radioID = radioGroup2.getCheckedRadioButtonId();
        checkedRadioButtonG2 = findViewById(radioID);
        if(checkedRadioButtonG2 == null)
        {


        }
        else {
            CharSequence radioButtonText = checkedRadioButtonG2.getText();

            return radioButtonText.toString();
        }
        return "0";

    }
    //Check button pressed
    public String checkButtonG3 (View view) {

        int radioID = radioGroup3.getCheckedRadioButtonId();
        checkedRadioButtonG3 = findViewById(radioID);
        if(checkedRadioButtonG3 == null)
        {


        }
        else {
            CharSequence radioButtonText = checkedRadioButtonG3.getText();

            return radioButtonText.toString();
        }
        return "0";

    }
    //Check button pressed
    public String checkButtonG4 (View view) {

        int radioID = radioGroup4.getCheckedRadioButtonId();
        checkedRadioButtonG4 = findViewById(radioID);
        if(checkedRadioButtonG4 == null)
        {


        }
        else {
            CharSequence radioButtonText = checkedRadioButtonG4.getText();

            return radioButtonText.toString();
        }
        return "0";

    }
    //Check button pressed
    public String checkButtonG5 (View view) {

        int radioID = radioGroup5.getCheckedRadioButtonId();
        checkedRadioButtonG5 = findViewById(radioID);
        if(checkedRadioButtonG5 == null)
        {


        }
        else {
            CharSequence radioButtonText = checkedRadioButtonG5.getText();

            return radioButtonText.toString();
        }
        return "0";

    }

}
