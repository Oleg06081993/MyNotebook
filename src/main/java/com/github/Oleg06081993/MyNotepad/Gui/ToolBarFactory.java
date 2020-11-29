package com.github.Oleg06081993.MyNotepad.Gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class ToolBarFactory {

    private TabManagerService tabManagerService;

    public ToolBarFactory(TabManagerService tabManagerService) {
        this.tabManagerService = tabManagerService;
    }

    public JToolBar createGetAndOpenToolbar() {
        JToolBar getAndOpenToolbar = createNotFloatableToolbar();

        addButtonTo(getAndOpenToolbar, "Get the code");
        addButtonTo(getAndOpenToolbar, "Open the page");
        getAndOpenToolbar.add(new JLabel("Link:"));
        getAndOpenToolbar.add(new JTextField(10));

        return getAndOpenToolbar;
    }

    public JToolBar createFontAndTabsToolbar() {
        JToolBar fontAndTabsToolbar = createNotFloatableToolbar();

        fontAndTabsToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));

        fontAndTabsToolbar.add(tabManagerService.getAddTabButton());

        addButtonTo(fontAndTabsToolbar, "Обычный");
        addButtonTo(fontAndTabsToolbar, "Курсив");
        addButtonTo(fontAndTabsToolbar, "Жирный");

        return fontAndTabsToolbar;
    }

    private JToolBar createNotFloatableToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.addSeparator();
        return toolBar;
    }

    private void addButtonTo(JToolBar toolBar, String buttonName) {
        toolBar.add(new JButton(buttonName));
    }

}
