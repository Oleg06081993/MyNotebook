package com.github.Oleg06081993.MyNotepad.Gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserAuthService {

    private static final String MESSAGE = "Пожалуйста, введите ваш логин и пароль";

    private final Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\Enter.png");
    private final JTextField userField = new JTextField();
    private final JPasswordField passField = new JPasswordField();

    public void validate() {
        //int counterTry = 0; My thoughts about the blocking of user
        boolean isPasswordCorrect = false;
        do {
            int result = JOptionPane.showOptionDialog(null,
                new Object[]{MESSAGE, userField, passField},
                "TextEditorPRO", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, icon, null, null);
            char[] passEnter = passField.getPassword();

            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                System.exit(0);
            }
            if (result == JOptionPane.OK_OPTION) {
                if (checkPassword(passEnter)) {
                    Icon icon = new ImageIcon(
                        "C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\successEnter.png");  //Пароль верный
                    JOptionPane.showMessageDialog(null,
                        new String[]{"Добро пожаловать в TextEditorPRO!" + "\n" +
                            "Логин: " + userField.getText()},
                        "TextEditorPRO", JOptionPane.INFORMATION_MESSAGE, icon);
                    isPasswordCorrect = true;
                } else {                                                           //Пароль НЕверный
                    Icon icon = new ImageIcon(
                        "C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\wrongEnter.png");  //Пароль НЕверный
                    JOptionPane.showMessageDialog(null,
                        new String[]{"Вы ввели некорректный пароль!" + "\n" +
                            "Логин: " + userField.getText()},
                        "TextEditorPRO", JOptionPane.ERROR_MESSAGE, icon);
                    passField.setText("");
                    passField.requestFocus();
                }
            }
        } while (!isPasswordCorrect);
    }

    private boolean checkPassword(char[] passEnter) {
        char[] correctPassword = new char[]{'q', 'w', 'e'};

        if (correctPassword.length != passEnter.length) {
            return false;
        }

        for (int i = 0; i < correctPassword.length; i++) {
            if (correctPassword[i] != passEnter[i]) {
                return false;
            }
        }

        return true;
    }
}













