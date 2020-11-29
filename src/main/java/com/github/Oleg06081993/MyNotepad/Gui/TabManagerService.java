package com.github.Oleg06081993.MyNotepad.Gui;

import com.github.Oleg06081993.MyNotepad.Gui.ActionListeners.NewTabListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class TabManagerService {

    private final JTabbedPane tabbedPane;
    private final NewTabListener newTabListener;
    private int tabNumber = 0;

    public TabManagerService(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        this.newTabListener = new NewTabListener(this);
    }

    public void addTab(JScrollPane pane) {
        tabNumber++;
        tabbedPane.addTab("Вкладка " + tabNumber, pane);
    }

    public JButton getAddTabButton() {
        Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\addTabb.png");
        JButton buttonAddTabb = new JButton(icon);
        buttonAddTabb.addActionListener(newTabListener);
        return buttonAddTabb;
    }
}
