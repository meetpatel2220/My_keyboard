package com.meet.mdkeyboard;

import android.app.Service;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class MeetKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
private KeyboardView kv;
private Keyboard keyboard;
private boolean iscaps=false;

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);



    }

    @Override
    public View onCreateInputView() {
kv=(KeyboardView)getLayoutInflater().inflate(R.layout.keyboard,null);
keyboard=new Keyboard(this,R.xml.meet);
kv.setKeyboard(keyboard);
kv.setOnKeyboardActionListener(this);
return kv;

    }

    @Override
    public void onPress(int i) {


    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {


        InputConnection ic=getCurrentInputConnection();
        switch (i){

            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(i,0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                iscaps=!iscaps;
                keyboard.setShifted(iscaps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
               ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
               break;
               default:
                   char code=(char) i;
                   if(Character.isLetter(code) && iscaps)
code=Character.toUpperCase(code);
                   ic.commitText(String.valueOf(code),i);



        }
    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
