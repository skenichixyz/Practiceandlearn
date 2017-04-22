package com.xyz.practiceandlearn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_ID_INCOMPLETE_CHOICE;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_ID_INCOMPLETE_QUESTION;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_ANSWER;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_CHOICE_A;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_CHOICE_B;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_CHOICE_C;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_CHOICE_D;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_DES;
import static com.xyz.practiceandlearn.IncompleteDatabase.COLUMN_INCOMPLETE_QUESTION;
import static com.xyz.practiceandlearn.IncompleteDatabase.INCOMPLETE_CHOICE;
import static com.xyz.practiceandlearn.IncompleteDatabase.INCOMPLETE_QUESTION;

public class IncompleteSentenceActivity extends AppCompatActivity {

    private MyDatabase objMYDatabase;
    private String sql = COLUMN_ID_INCOMPLETE_QUESTION + "<=50";
    private String sql2 = COLUMN_ID_INCOMPLETE_CHOICE + "<=50";
    private String[] strQuestion, strAnswer, strChoiceA, strChoiceB, strChoiceC, strChoiceD, strDes;
    private int currentposition;
    private boolean showanswer;
    private MediaPlayer soundtrue,soundwrong;
    private static final int maxrow = 49;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomplete_sentence);

        objMYDatabase = new MyDatabase(this);
        currentposition = 0;
        showanswer = false;

        setupArray();

        showQuestionAndChoice(0);

        back();

        next();

        showAnswer(0);

        clearcheck();
    }

    private void setupArray(){

        strQuestion = listQuestion();
        strAnswer = listAnswer();
        strChoiceA = listChoiceA();
        strChoiceB = listChoiceB();
        strChoiceC = listChoiceC();
        strChoiceD = listChoiceD();
        strDes = listDes();

    }

    private void playTrue(){
        soundtrue = MediaPlayer.create(this,R.raw.soundtrue);
        soundtrue.start();
    }

    private void playWrong(){
        soundwrong = MediaPlayer.create(this,R.raw.soundwrong);
        soundwrong.start();
    }

    private void showQuestionAndChoice(final int p){

        TextView txtQuestion = (TextView) findViewById(R.id.txtQuestion_Incomplete);
        txtQuestion.setText(strQuestion[p]);
        RadioButton btnA = (RadioButton) findViewById(R.id.rdoA_Incomplete);
        btnA.setText(strChoiceA[p]);
        RadioButton btnB = (RadioButton) findViewById(R.id.rdoB_Incomplete);
        btnB.setText(strChoiceB[p]);
        RadioButton btnC = (RadioButton) findViewById(R.id.rdoC_Incomplete);
        btnC.setText(strChoiceC[p]);
        RadioButton btnD = (RadioButton) findViewById(R.id.rdoD_Incomplete);
        btnD.setText(strChoiceD[p]);
        //TextView txtDes = (TextView) findViewById(R.id.txtDes_Incomplete);
        //txtDes.setText(strDes[p]);

    }

    private void back() {

        Button btnBack = (Button) findViewById(R.id.btnBack5);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showanswer = false;

                if (currentposition>0)
                    currentposition--;



                clearcheck();

                TextView txtDes = (TextView) findViewById(R.id.txtDes_Incomplete);
                txtDes.setText("");

                //clearlayout();

                showQuestionAndChoice(currentposition);

                showAnswer(currentposition);
            }
        });

    }

    private void showAnswer(final int p){

        Button btnAns = (Button) findViewById(R.id.btnAns5);
        btnAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!showanswer){

                    TextView txtDes = (TextView) findViewById(R.id.txtDes_Incomplete);
                    txtDes.setText(strDes[p]);
                    RadioButton textChoiceA = (RadioButton) findViewById(R.id.rdoA_Incomplete);
                    RadioButton textChoiceB = (RadioButton) findViewById(R.id.rdoB_Incomplete);
                    RadioButton textChoiceC = (RadioButton) findViewById(R.id.rdoC_Incomplete);
                    RadioButton textChoiceD = (RadioButton) findViewById(R.id.rdoD_Incomplete);

                    Boolean chkA = textChoiceA.isChecked();
                    Boolean chkB = textChoiceB.isChecked();
                    Boolean chkC = textChoiceC.isChecked();
                    Boolean chkD = textChoiceD.isChecked();
                    if (strAnswer[currentposition].equals("A")){

                        if (chkA){
                            //playSound
                            playTrue();
                            Toast.makeText(getBaseContext(), "r", Toast.LENGTH_LONG).show();
                        }
                        else {

                            playWrong();
                            Toast.makeText(getBaseContext(), "c", Toast.LENGTH_LONG).show();
                        }

                    }
                    if (strAnswer[currentposition].equals("B")){

                        if (chkB){
                            //playSound
                            playTrue();
                            Toast.makeText(getBaseContext(), "r", Toast.LENGTH_LONG).show();
                        }
                        else {

                            playWrong();
                            Toast.makeText(getBaseContext(), "c", Toast.LENGTH_LONG).show();
                        }

                    }
                    if (strAnswer[currentposition].equals("C")){

                        if (chkC){
                            //playSound
                            playTrue();
                            Toast.makeText(getBaseContext(), "r", Toast.LENGTH_LONG).show();
                        }
                        else {

                            playWrong();
                            Toast.makeText(getBaseContext(), "c", Toast.LENGTH_LONG).show();
                        }

                    }
                    if (strAnswer[currentposition].equals("D")){

                        if (chkD){
                            //playSound
                            playTrue();
                            Toast.makeText(getBaseContext(), "r", Toast.LENGTH_LONG).show();
                        }
                        else {

                            playWrong();
                            Toast.makeText(getBaseContext(), "c", Toast.LENGTH_LONG).show();
                        }

                    }

                }else {
                    TextView txtDes = (TextView) findViewById(R.id.txtDes_Incomplete);
                    txtDes.setText("");
                }
                showanswer = !showanswer;


            }
        });

    }

    private void next() {

        Button btnNext = (Button) findViewById(R.id.btnNext5);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showanswer = false;

                if (currentposition<maxrow)
                    currentposition++;



                clearcheck();

                TextView txtDes = (TextView) findViewById(R.id.txtDes_Incomplete);
                txtDes.setText("");

                //clearlayout();

                showQuestionAndChoice(currentposition);

                showAnswer(currentposition);
            }
        });

    }

    private void clearcheck() {

        RadioGroup rdogroup2 = (RadioGroup) findViewById(R.id.rdoGroup_Incomplete);
        rdogroup2.clearCheck();
    }

    private String[] listQuestion(){

        String strListQuestion[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_QUESTION, new String[]{COLUMN_INCOMPLETE_QUESTION}, sql, null, null, null, null);
        objCursor.moveToFirst();
        strListQuestion = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListQuestion[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_QUESTION));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListQuestion;

    }

    private String[] listAnswer(){

        String strListAnswer[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_QUESTION, new String[]{COLUMN_INCOMPLETE_ANSWER}, sql, null, null, null, null);
        objCursor.moveToFirst();
        strListAnswer = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListAnswer[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_ANSWER));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListAnswer;

    }

    private String[] listChoiceA() {

        String strListChoiceA[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_CHOICE, new String[]{COLUMN_INCOMPLETE_CHOICE_A}, sql2, null, null, null, null);
        objCursor.moveToFirst();
        strListChoiceA = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListChoiceA[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_CHOICE_A));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListChoiceA;

    }

    private String[] listChoiceB() {

        String strListChoiceB[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_CHOICE, new String[]{COLUMN_INCOMPLETE_CHOICE_B}, sql2, null, null, null, null);
        objCursor.moveToFirst();
        strListChoiceB = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListChoiceB[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_CHOICE_B));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListChoiceB;

    }

    private String[] listChoiceC() {

        String strListChoiceC[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_CHOICE, new String[]{COLUMN_INCOMPLETE_CHOICE_C}, sql2, null, null, null, null);
        objCursor.moveToFirst();
        strListChoiceC = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListChoiceC[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_CHOICE_C));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListChoiceC;

    }

    private String[] listChoiceD() {

        String strListChoiceD[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_CHOICE, new String[]{COLUMN_INCOMPLETE_CHOICE_D}, sql2, null, null, null, null);
        objCursor.moveToFirst();
        strListChoiceD = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListChoiceD[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_CHOICE_D));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListChoiceD;

    }

    private String[] listDes() {

        String strListDes[];
        SQLiteDatabase db = objMYDatabase.getReadableDatabase();
        Cursor objCursor = db.query(INCOMPLETE_CHOICE, new String[]{COLUMN_INCOMPLETE_DES}, sql2, null, null, null, null);
        objCursor.moveToFirst();
        strListDes = new String[objCursor.getCount()];
        for (int i=0; i<objCursor.getCount(); i++){
            strListDes[i] = objCursor.getString(objCursor.getColumnIndex(COLUMN_INCOMPLETE_DES));
            objCursor.moveToNext();
        }
        objCursor.close();

        return strListDes;

    }
}