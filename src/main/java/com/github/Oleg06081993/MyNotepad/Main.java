package com.github.Oleg06081993.MyNotepad;

import com.github.Oleg06081993.MyNotepad.Gui.UserAuthService;
import com.github.Oleg06081993.MyNotepad.Gui.Notepad;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        Notepad notepad = new Notepad();
        UserAuthService userAuthService = new UserAuthService();
        //userPasswordValidatorService.validate();   -закомментил пока, т.к. задолбался при запуске вводить пароль
        notepad.startGui();
    }
}
