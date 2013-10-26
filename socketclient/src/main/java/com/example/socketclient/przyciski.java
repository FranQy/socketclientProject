package com.example.socketclient;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;

import android.view.MotionEvent;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;


import android.widget.TextView;

import java.io.PrintWriter;

/**
 * Created by franqy on 05.10.13.
 * przyciski v0.3
 */
public class przyciski extends pilot{
  //  public Activity activity;
    boolean klawisze = false;
    ImageView przyciskia, infoImage, hand;


    PrintWriter out;



    public przyciski(){

super();



        blad = (TextView) this.activity.findViewById(R.id.textView1);
blad.setText("asd");
        przyciskia = (ImageView) this.activity.findViewById(R.id.imagePrzyciski);
        infoImage =(ImageView) this.activity.findViewById(R.id.imageInfo);

       // connectingSlidingMenu = (SlidingDrawer) this.activity.findViewById(R.id.slidingDrawer2);
      // PilotButton = (ImageView) this.activity.findViewById(R.id.pilotButton);

        hand = (ImageView)this.activity.findViewById(R.id.handleImag);
//testLay = (LinearLayout) this.activity.findViewById(R.id.linearLayout4);



        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    infoImage.setVisibility(View.INVISIBLE);
                click();

            }
        });

        przyciskia.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float X, Y;
                X = motionEvent.getX();
                Y = motionEvent.getY();

                float[] XY = new float[]{X, Y};


                Matrix invertMatrix = new Matrix();
                //TODO: check Null pointer bla bla
                przyciskia.getImageMatrix().invert(invertMatrix);


                invertMatrix.mapPoints(XY);

                int x = (int) XY[0];
                int y = (int) XY[1];

                Drawable imgDrawable = przyciskia.getDrawable();
                assert imgDrawable != null;
                Bitmap bitmap = ((BitmapDrawable)  imgDrawable).getBitmap();



                  // blad.setText(String.valueOf(x));


                if(x>0 && x<500)
                ktoryPrzycisk(bitmap.getPixel(x, y));



                return false;
            }


        });

    }

void ktoryPrzycisk(int touchedRGB)
{
    if(touchedRGB == -14149877)
    {
        blad.setText("menu masz na dole");
       // click();



    }
    else if(touchedRGB == -32640)
    {
       click();
        infoImage.setVisibility(View.VISIBLE);
    }



    if (klawisze) {
        switch (touchedRGB) {
            case -39424: {
                click("radio");
                break;
            }
            case -256: {
                click("glosniej");
                break;
            }
            case -16733697: {
                click("ciszej");
                break;
            }
            case -5636268: {
                click("wycisz");
                break;
            }
            case -13312: {
                click("p/p");
                break;
            }
            case -11189248: {
                click("perw");
                break;
            }
            case -6528: {
                click("next");
                break;
            }
            case -65536: {
                click("off");
                break;
            }
            case -13142: {
                click("tv");
                break;
            }
            case -32982: {
                click("movie");
                break;
            }
            case -11522794: {
                click("rec");
                break;
            }
            case -2912929: {
                click("stop");
                break;
            }
            case -16744448: {
                click("red");
                break;
            }
            case -65281: {
                click("green");
                break;
            }
            case -11206656: {
                click("yellow");
                break;
            }
            case -2883584: {
                click("blue");
                break;
            }
            case -43691: {
                click("DVR");
                break;
            }

            case -21846: {
                click("info");


                break;
            }
            case -5467245: {
                click("exit");
                break;
            }
            case -58368: {
                click("ch+");
                break;
            }
            case -11206743: {
                click("ch-");
                break;
            }
            case -51200: {
                click("return");
                break;
            }
            case -16711681: {
                click("OK");
                break;
            }
            case -1900772: {
                click("up");
                break;
            }
            case -16777089: {
                click("down");
                break;
            }
            case -16744449: {
                click("right");
                break;
            }
            case -16776961: {
                click("left");
                break;
            }

            default: {
                break;
            }
        }
    }






}

    void givePrintWriter(PrintWriter out)
    {
    this.out = out;
    }

    void click(String command){
        out.println(command);
        blad.setText(command);
        click();
    }
    public void click(){
        Vibrator v = (Vibrator) pilot.activity.getSystemService(Context.VIBRATOR_SERVICE);
        int VIBRATION_TIME = 80;
        v.vibrate(VIBRATION_TIME);

    }













}
