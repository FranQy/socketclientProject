package com.example.socketclient;


import android.app.Dialog;
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

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by franqy on 05.10.13.
 * przyciski v0.3
 */
public class przyciski extends Pilot {
    //  public Activity activity;
    static Timer longpressTimer;
    boolean klawisze = false;
    ImageView przyciskia, przyciskiFront;
    //ImageView onoff, mute, music, photo, movie, quieter, louder, perv, next, playpause, stop;
//long mDownTime, mOldDownTime;

    boolean volume = false;
    // PrintWriter out;
    // ObjectOutputStream out;
    static public TCP_Data data = new TCP_Data();
    static boolean returnstate = false;
    static boolean longClick = false;
    long time;

    public przyciski() {

        super();

        // out = null;

        blad = (TextView) this.activity.findViewById(R.id.textView1);

        przyciskia = (ImageView) this.activity.findViewById(R.id.przyciskiBack);
        przyciskiFront = (ImageView) this.activity.findViewById(R.id.przyciski);


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

                final int x = (int) XY[0];
                final int y = (int) XY[1];

                Drawable imgDrawable = przyciskia.getDrawable();
                assert imgDrawable != null;
                final Bitmap bitmap = ((BitmapDrawable) imgDrawable).getBitmap();


                // blad.setText(String.valueOf(x));


                //if(x>0 && x<500)


                int actionMask = motionEvent.getActionMasked();


                if (y < bitmap.getHeight() && y >= 0 && klawisze) {
                    switch (actionMask) {
                        case MotionEvent.ACTION_DOWN: {

                            final int touchedRGB = bitmap.getPixel(x, y);


                            time = System.currentTimeMillis();
                            returnstate = true;
                            longpressTimer = new Timer();

                            if (touchedRGB == -7024087 || touchedRGB == -7023945) {
                                ktoryPrzycisk(touchedRGB);
                                volume = true;
                            }

                            longpressTimer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    longClick = true;
                                    if (touchedRGB == -7024087 || touchedRGB == -7023945) {


                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {

                                                while (returnstate) {

                                                    if (System.currentTimeMillis() - time >= 100) {

                                                        time = System.currentTimeMillis();

                                                        ktoryPrzycisk(touchedRGB);

                                                    }

                                                }

                                            }
                                        }).start();
                                    } else {
                                        switch (touchedRGB) {
                                            case -798025: {
                                                click(TCP_Data.pilotButton.FORWARD, true);
                                                break;
                                            }
                                            case -821484: {
                                                click(TCP_Data.pilotButton.REWIND, true);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }, 500);

                            break;

                        }
                        case MotionEvent.ACTION_UP: {
                            if (!longClick && !volume)
                                ktoryPrzycisk(bitmap.getPixel(x, y));
                            longClick = false;
                            volume = false;
                            returnstate = false;
                            longpressTimer.cancel();
                            blad.setText("up");
                            time = 0;
                            break;
                        }
                    }
                }


                return returnstate;
            }


        });


    }

    void ktoryPrzycisk(int touchedRGB) {

        if (klawisze) {

            switch (touchedRGB) {

                case -7024087: {
                    click(TCP_Data.pilotButton.VOLUP, true);
                    break;
                }
                case -7023945: {
                    click(TCP_Data.pilotButton.VOLDOWN, true);
                    break;
                }
                case -15404256: {
                    click(TCP_Data.pilotButton.MUTE, true);

                    break;
                }
                case -789740: {
                    //click("p/p");
                    click(TCP_Data.pilotButton.PLAYPAUSE, true);
                    break;
                }
                case -798025: {
                    click(TCP_Data.pilotButton.PERV, true);
                    break;
                }
                case -821484: {
                    click(TCP_Data.pilotButton.NEXT, true);
                    break;
                }
                case -846828: {

                    // Created a new Dialog
                    final Dialog dialog = new Dialog(activity);
                    dialog.setTitle("wylaczyc komputer?");
                    dialog.setContentView(R.layout.na_pewno);

                    Button napewnoTak = (Button) dialog.findViewById(R.id.dialogNaPewnoTak);
                    Button napewnoNie = (Button) dialog.findViewById(R.id.dialogNaPewnoNie);


                    napewnoNie.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });


                    napewnoTak.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            click(TCP_Data.pilotButton.OFF, true);
                            dialog.cancel();
                        }
                    });


                    dialog.show();


                    break;
                }

                case -15404070: {
                    //click("stop");
                    click(TCP_Data.pilotButton.STOP, true);
                    break;
                }


                case -15443725: {
                    // click("OK");
                    click(TCP_Data.pilotButton.RETTURN, true);
                    break;
                }
                case -16734011: {
                    click(TCP_Data.pilotButton.UP, true);
                    break;
                }
                case -2381627: {
                    click(TCP_Data.pilotButton.DOWN, true);
                    break;
                }
                case -16777019: {
                    click(TCP_Data.pilotButton.RIGHT, true);
                    break;
                }
                case -16777216: {
                    click(TCP_Data.pilotButton.LEFT, true);
                    break;
                }
                case -846617: {
                    click(TCP_Data.pilotButton.MULTIMEDIA, true);
                    break;
                }
                case -846735: {
                    click(TCP_Data.pilotButton.MUSIC, true);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }


    void click(TCP_Data.pilotButton button, boolean vibrate) {
        data.type = TCP_Data.typ.PILOT;
        data.button = button;
        if (klawisze) {
            try {
                MainActivity.oos.writeObject(data);
                MainActivity.oos.reset();
                MainActivity.oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (vibrate)
            click();
    }

    public void click() {
        Vibrator v = (Vibrator) Pilot.activity.getSystemService(Context.VIBRATOR_SERVICE);
        int VIBRATION_TIME = 50;
        v.vibrate(VIBRATION_TIME);

    }


    public void volume(boolean up, boolean vibrate) {
        if (klawisze) {
            if (up) {
                click(TCP_Data.pilotButton.VOLUP, vibrate);
            } else {
                click(TCP_Data.pilotButton.VOLDOWN, vibrate);
            }
        }
    }


}
