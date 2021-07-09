package org.timerrubikscube.aatimer;

import androidx.appcompat.app.AppCompatActivity;

import org.timerrubikscube.R;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class ScramblePictureActivity extends AppCompatActivity {
    ArrayList<Integer> cubeFace;
    String theScramble;
    ImageButton backArrow;

    ArrayList<ImageView> i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scramble_picture);
        initVariables();
        if (getIntent() != null && getIntent().getStringExtra("scramble") != null)
            theScramble = getIntent().getStringExtra("scramble");
        execution();
        setColor();
    }

    private void setColor() {
        for (int j = 1; j <= 48; j++) {
            if (cubeFace.get(j) == 1) {
                i.get(j).setImageResource(R.drawable.white);
            } else if (cubeFace.get(j) == 2) {
                i.get(j).setImageResource(R.drawable.red);
            } else if (cubeFace.get(j) == 3) {
                i.get(j).setImageResource(R.drawable.blue);
            } else if (cubeFace.get(j) == 4) {
                i.get(j).setImageResource(R.drawable.orange);
            } else if (cubeFace.get(j) == 5) {
                i.get(j).setImageResource(R.drawable.green);
            } else if (cubeFace.get(j) == 6) {
                i.get(j).setImageResource(R.drawable.yellow);
            }
        }
    }

    private void initVariables() {
        backArrow = findViewById(R.id.scramble_backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        i = new ArrayList<>();
        i.add(findViewById(R.id.c1));
        i.add(findViewById(R.id.c1));
        i.add(findViewById(R.id.c2));
        i.add(findViewById(R.id.c3));
        i.add(findViewById(R.id.c4));
        i.add(findViewById(R.id.c5));
        i.add(findViewById(R.id.c6));
        i.add(findViewById(R.id.c7));
        i.add(findViewById(R.id.c8));
        i.add(findViewById(R.id.c9));
        i.add(findViewById(R.id.c10));
        i.add(findViewById(R.id.c11));
        i.add(findViewById(R.id.c12));
        i.add(findViewById(R.id.c13));
        i.add(findViewById(R.id.c14));
        i.add(findViewById(R.id.c15));
        i.add(findViewById(R.id.c16));
        i.add(findViewById(R.id.c17));
        i.add(findViewById(R.id.c18));
        i.add(findViewById(R.id.c19));
        i.add(findViewById(R.id.c20));
        i.add(findViewById(R.id.c21));
        i.add(findViewById(R.id.c22));
        i.add(findViewById(R.id.c23));
        i.add(findViewById(R.id.c24));
        i.add(findViewById(R.id.c25));
        i.add(findViewById(R.id.c26));
        i.add(findViewById(R.id.c27));
        i.add(findViewById(R.id.c28));
        i.add(findViewById(R.id.c29));
        i.add(findViewById(R.id.c30));
        i.add(findViewById(R.id.c31));
        i.add(findViewById(R.id.c32));
        i.add(findViewById(R.id.c33));
        i.add(findViewById(R.id.c34));
        i.add(findViewById(R.id.c35));
        i.add(findViewById(R.id.c36));
        i.add(findViewById(R.id.c37));
        i.add(findViewById(R.id.c38));
        i.add(findViewById(R.id.c39));
        i.add(findViewById(R.id.c40));
        i.add(findViewById(R.id.c41));
        i.add(findViewById(R.id.c42));
        i.add(findViewById(R.id.c43));
        i.add(findViewById(R.id.c44));
        i.add(findViewById(R.id.c45));
        i.add(findViewById(R.id.c46));
        i.add(findViewById(R.id.c47));
        i.add(findViewById(R.id.c48));



        /*
        i1 = findViewById(R.id.c1);
        i2 = findViewById(R.id.c2);
        i3 = findViewById(R.id.c3);
        i4 = findViewById(R.id.c4);
        i5 = findViewById(R.id.c5);
        i6 = findViewById(R.id.c6);
        i7 = findViewById(R.id.c7);
        i8 = findViewById(R.id.c8);
        i9 = findViewById(R.id.c9);
        i10 = findViewById(R.id.c10);
        i11 = findViewById(R.id.c11);
        i12 = findViewById(R.id.c12);
        i13 = findViewById(R.id.c13);
        i14 = findViewById(R.id.c14);
        i15 = findViewById(R.id.c15);
        i16 = findViewById(R.id.c16);
        i17 = findViewById(R.id.c17);
        i18 = findViewById(R.id.c18);
        i19 = findViewById(R.id.c19);
        i20 = findViewById(R.id.c20);
        i21 = findViewById(R.id.c21);
        i22 = findViewById(R.id.c22);
        i23 = findViewById(R.id.c23);
        i24 = findViewById(R.id.c24);
        i25 = findViewById(R.id.c25);
        i26 = findViewById(R.id.c26);
        i27 = findViewById(R.id.c27);
        i28 = findViewById(R.id.c28);
        i29 = findViewById(R.id.c29);
        i30 = findViewById(R.id.c30);
        i31 = findViewById(R.id.c31);
        i32 = findViewById(R.id.c32);
        i33 = findViewById(R.id.c33);
        i34 = findViewById(R.id.c34);
        i35 = findViewById(R.id.c35);
        i36 = findViewById(R.id.c36);
        i37 = findViewById(R.id.c37);
        i38 = findViewById(R.id.c38);
        i39 = findViewById(R.id.c39);
        i40 = findViewById(R.id.c40);
        i41 = findViewById(R.id.c41);
        i42 = findViewById(R.id.c42);
        i43 = findViewById(R.id.c43);
        i44 = findViewById(R.id.c44);
        i45 = findViewById(R.id.c45);
        i46 = findViewById(R.id.c46);
        i47 = findViewById(R.id.c47);
        i48 = findViewById(R.id.c48);

         */

    }

    private void execution() {
        cubeFace = new ArrayList<>();
        cubeFace.add(-1);
        /* yellow - 6
        green - 5
        orange - 4
        blue - 3
        red - 2
        white -1
         */

        initColors();
        ArrayList<String> scrambleArray = new ArrayList<>();


        String temp = "";
        for (int i = 0; i < theScramble.length(); i++) {
            char ch = theScramble.charAt(i);
            if (ch != ' ') {
                temp += ch;
            } else {
                scrambleArray.add(temp);
                temp = "";
            }
        }


        for (int i = 0; i < scrambleArray.size(); i++) {

            if (scrambleArray.get(i).equals("R")) {
                workForR();
            } else if (scrambleArray.get(i).equals("R2")) {
                workForR();
                workForR();
            } else if (scrambleArray.get(i).equals("R'")) {
                workForR();
                workForR();
                workForR();
            } else if (scrambleArray.get(i).equals("U")) {
                workForU();
            } else if (scrambleArray.get(i).equals("U2")) {
                workForU();
                workForU();
            } else if (scrambleArray.get(i).equals("U'")) {
                workForU();
                workForU();
                workForU();
            } else if (scrambleArray.get(i).equals("D")) {
                workForD();
            } else if (scrambleArray.get(i).equals("D2")) {
                workForD();
                workForD();
            } else if (scrambleArray.get(i).equals("D'")) {
                workForD();
                workForD();
                workForD();
            } else if (scrambleArray.get(i).equals("L")) {
                workForL();
            } else if (scrambleArray.get(i).equals("L2")) {
                workForL();
                workForL();
            } else if (scrambleArray.get(i).equals("L'")) {
                workForL();
                workForL();
                workForL();
            } else if (scrambleArray.get(i).equals("F")) {
                workForF();
            } else if (scrambleArray.get(i).equals("F2")) {
                workForF();
                workForF();
            } else if (scrambleArray.get(i).equals("F'")) {
                workForF();
                workForF();
                workForF();
            } else if (scrambleArray.get(i).equals("B")) {
                workForB();

            } else if (scrambleArray.get(i).equals("B2")) {
                workForB();
                workForB();
            } else if (scrambleArray.get(i).equals("B'")) {
                workForB();
                workForB();
                workForB();
            }
        }
    }

    private void initColors() {
        yellow();
        green();
        orange();
        blue();
        red();
        white();
    }

    private void workForB() {
        int temp25 = cubeFace.get(25);
        int temp26 = cubeFace.get(26);

        cubeFace.set(25, cubeFace.get(31));
        cubeFace.set(26, cubeFace.get(32));

        cubeFace.set(31, cubeFace.get(29));
        cubeFace.set(32, cubeFace.get(30));

        cubeFace.set(29, cubeFace.get(27));
        cubeFace.set(30, cubeFace.get(28));

        cubeFace.set(28, temp26);
        cubeFace.set(27, temp25);


        int temp1 = cubeFace.get(1);
        int temp2 = cubeFace.get(2);
        int temp3 = cubeFace.get(3);

        cubeFace.set(1, cubeFace.get(19));
        cubeFace.set(2, cubeFace.get(20));
        cubeFace.set(3, cubeFace.get(21));

        cubeFace.set(19, cubeFace.get(45));
        cubeFace.set(20, cubeFace.get(46));
        cubeFace.set(21, cubeFace.get(47));

        cubeFace.set(45, cubeFace.get(39));
        cubeFace.set(46, cubeFace.get(40));
        cubeFace.set(47, cubeFace.get(33));

        cubeFace.set(39, temp1);
        cubeFace.set(40, temp2);
        cubeFace.set(33, temp3);
    }

    private void workForF() {
        int temp9 = cubeFace.get(9);
        int temp10 = cubeFace.get(10);

        cubeFace.set(9, cubeFace.get(15));
        cubeFace.set(10, cubeFace.get(16));

        cubeFace.set(15, cubeFace.get(13));
        cubeFace.set(16, cubeFace.get(14));

        cubeFace.set(13, cubeFace.get(11));
        cubeFace.set(14, cubeFace.get(12));

        cubeFace.set(11, temp9);
        cubeFace.set(12, temp10);

        int temp5 = cubeFace.get(5);
        int temp6 = cubeFace.get(6);
        int temp7 = cubeFace.get(7);

        cubeFace.set(5, cubeFace.get(35));
        cubeFace.set(6, cubeFace.get(36));
        cubeFace.set(7, cubeFace.get(37));

        cubeFace.set(35, cubeFace.get(41));
        cubeFace.set(36, cubeFace.get(42));
        cubeFace.set(37, cubeFace.get(43));

        cubeFace.set(41, cubeFace.get(23));
        cubeFace.set(42, cubeFace.get(24));
        cubeFace.set(43, cubeFace.get(17));

        cubeFace.set(17, temp7);
        cubeFace.set(24, temp6);
        cubeFace.set(23, temp5);
    }

    private void workForL() {
        int temp33 = cubeFace.get(33);
        int temp34 = cubeFace.get(34);

        cubeFace.set(33, cubeFace.get(39));
        cubeFace.set(34, cubeFace.get(40));

        cubeFace.set(40, cubeFace.get(38));
        cubeFace.set(39, cubeFace.get(37));

        cubeFace.set(38, cubeFace.get(36));
        cubeFace.set(37, cubeFace.get(35));

        cubeFace.set(35, temp33);
        cubeFace.set(36, temp34);

        int temp1 = cubeFace.get(1);
        int temp7 = cubeFace.get(7);
        int temp8 = cubeFace.get(8);

        cubeFace.set(1, cubeFace.get(29));
        cubeFace.set(7, cubeFace.get(27));
        cubeFace.set(8, cubeFace.get(28));

        cubeFace.set(27, cubeFace.get(47));
        cubeFace.set(28, cubeFace.get(48));
        cubeFace.set(29, cubeFace.get(41));

        cubeFace.set(47, cubeFace.get(15));
        cubeFace.set(48, cubeFace.get(16));
        cubeFace.set(41, cubeFace.get(9));

        cubeFace.set(15, temp7);
        cubeFace.set(16, temp8);
        cubeFace.set(9, temp1);
    }

    private void workForD() {
        int temp41 = cubeFace.get(41);
        int temp42 = cubeFace.get(42);

        cubeFace.set(41, cubeFace.get(47));
        cubeFace.set(42, cubeFace.get(48));

        cubeFace.set(47, cubeFace.get(45));
        cubeFace.set(48, cubeFace.get(46));

        cubeFace.set(45, cubeFace.get(43));
        cubeFace.set(46, cubeFace.get(44));

        cubeFace.set(43, temp41);
        cubeFace.set(44, temp42);

        int temp13 = cubeFace.get(13);
        int temp14 = cubeFace.get(14);
        int temp15 = cubeFace.get(15);

        cubeFace.set(13, cubeFace.get(37));
        cubeFace.set(14, cubeFace.get(38));
        cubeFace.set(15, cubeFace.get(39));

        cubeFace.set(37, cubeFace.get(29));
        cubeFace.set(38, cubeFace.get(30));
        cubeFace.set(39, cubeFace.get(31));

        cubeFace.set(29, cubeFace.get(21));
        cubeFace.set(30, cubeFace.get(22));
        cubeFace.set(31, cubeFace.get(23));

        cubeFace.set(21, temp13);
        cubeFace.set(22, temp14);
        cubeFace.set(23, temp15);
    }

    private void workForU() {
        int temp1 = cubeFace.get(1);
        int temp2 = cubeFace.get(2);

        cubeFace.set(1, cubeFace.get(7));
        cubeFace.set(2, cubeFace.get(8));

        cubeFace.set(8, cubeFace.get(6));
        cubeFace.set(7, cubeFace.get(5));

        cubeFace.set(6, cubeFace.get(4));
        cubeFace.set(5, cubeFace.get(3));

        cubeFace.set(4, temp2);
        cubeFace.set(3, temp1);

        int temp9 = cubeFace.get(9);
        int temp10 = cubeFace.get(10);
        int temp11 = cubeFace.get(11);

        cubeFace.set(9, cubeFace.get(17));
        cubeFace.set(10, cubeFace.get(18));
        cubeFace.set(11, cubeFace.get(19));

        cubeFace.set(17, cubeFace.get(25));
        cubeFace.set(18, cubeFace.get(26));
        cubeFace.set(19, cubeFace.get(27));

        cubeFace.set(25, cubeFace.get(33));
        cubeFace.set(26, cubeFace.get(34));
        cubeFace.set(27, cubeFace.get(35));

        cubeFace.set(33, temp9);
        cubeFace.set(34, temp10);
        cubeFace.set(35, temp11);
    }

    private void workForR() {
        int temp17 = cubeFace.get(17);
        int temp18 = cubeFace.get(18);

        cubeFace.set(17, cubeFace.get(23));
        cubeFace.set(18, cubeFace.get(24));

        cubeFace.set(24, cubeFace.get(22));
        cubeFace.set(23, cubeFace.get(21));


        cubeFace.set(22, cubeFace.get(20));
        cubeFace.set(21, cubeFace.get(19));


        cubeFace.set(20, temp18);
        cubeFace.set(19, temp17);


        int temp3 = cubeFace.get(3);
        int temp4 = cubeFace.get(4);
        int temp5 = cubeFace.get(5);

        cubeFace.set(3, cubeFace.get(11));
        cubeFace.set(4, cubeFace.get(12));
        cubeFace.set(5, cubeFace.get(13));

        cubeFace.set(11, cubeFace.get(43));
        cubeFace.set(12, cubeFace.get(44));
        cubeFace.set(13, cubeFace.get(45));

        cubeFace.set(43, cubeFace.get(31));
        cubeFace.set(44, cubeFace.get(32));
        cubeFace.set(45, cubeFace.get(25));

        cubeFace.set(25, temp5);
        cubeFace.set(32, temp4);
        cubeFace.set(31, temp3);
    }

    private void white() {
        cubeFace.add(1);
        cubeFace.add(1);
        cubeFace.add(1);
        cubeFace.add(1);
        cubeFace.add(1);
        cubeFace.add(1);
        cubeFace.add(1);
        cubeFace.add(1);
    }

    private void red() {
        cubeFace.add(2);
        cubeFace.add(2);
        cubeFace.add(2);
        cubeFace.add(2);
        cubeFace.add(2);
        cubeFace.add(2);
        cubeFace.add(2);
        cubeFace.add(2);
    }

    private void blue() {
        cubeFace.add(3);
        cubeFace.add(3);
        cubeFace.add(3);
        cubeFace.add(3);
        cubeFace.add(3);
        cubeFace.add(3);
        cubeFace.add(3);
        cubeFace.add(3);
    }

    private void orange() {
        cubeFace.add(4);
        cubeFace.add(4);
        cubeFace.add(4);
        cubeFace.add(4);
        cubeFace.add(4);
        cubeFace.add(4);
        cubeFace.add(4);
        cubeFace.add(4);
    }

    private void green() {
        cubeFace.add(5);
        cubeFace.add(5);
        cubeFace.add(5);
        cubeFace.add(5);
        cubeFace.add(5);
        cubeFace.add(5);
        cubeFace.add(5);
        cubeFace.add(5);
    }

    private void yellow() {
        cubeFace.add(6);
        cubeFace.add(6);
        cubeFace.add(6);
        cubeFace.add(6);
        cubeFace.add(6);
        cubeFace.add(6);
        cubeFace.add(6);
        cubeFace.add(6);
    }
}