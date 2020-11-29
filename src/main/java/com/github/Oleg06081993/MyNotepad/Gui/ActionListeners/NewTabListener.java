package com.github.Oleg06081993.MyNotepad.Gui.ActionListeners;

import com.github.Oleg06081993.MyNotepad.Gui.TabManagerService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class NewTabListener implements ActionListener {

    private final TabManagerService tabManagerService;

    public NewTabListener(TabManagerService tabManagerService) {
        this.tabManagerService = tabManagerService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createNewTab();
    }

    private void createNewTab() {
        JScrollPane pane = new JScrollPane(new JEditorPane());

        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tabManagerService.addTab(pane);
    }
}
