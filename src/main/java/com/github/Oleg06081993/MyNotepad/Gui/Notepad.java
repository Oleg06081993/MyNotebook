package com.github.Oleg06081993.MyNotepad.Gui;

import static java.awt.BorderLayout.NORTH;
import static javax.swing.BoxLayout.Y_AXIS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JPanel implements ActionListener {

    private static final JFrame frame = new JFrame("TextEditorPRO V1.0");
    private JMenuItem create, save, saveAs, open, print, exit;
    private final JEditorPane editor = new JEditorPane();
    private File file;

    public void startGui() {
        frame.setSize(1500, 1000);
        frame.setIconImage(
            Toolkit.getDefaultToolkit().getImage("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\mainImage.jpg"));

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        // item (Файл)
        JMenu file = new JMenu("Файл");
        create = new JMenuItem("Create...");
        create.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\create.png"));

        open = new JMenuItem("Открыть...");
        open.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\open.jpg"));
        open.addActionListener(this);

        save = new JMenuItem("Сохранить");
        save.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\saveIcon.png"));
        save.addActionListener(this);
        save.setEnabled(false);

        saveAs = new JMenuItem("Сохранить как...");
        saveAs.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\saveAs.png"));
        saveAs.addActionListener(this);

        print = new JMenuItem("Печать");
        print.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\print.jpg"));

        exit = new JMenuItem("Выход");
        exit.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\exit.png"));

        menuBar.add(file);
        file.add(create);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(new JSeparator());
        file.add(print);
        file.add(new JSeparator());
        file.add(exit);

        // item (Редактирование)
        JMenu edit = new JMenu("Редактирование");
        JMenuItem cut = new JMenuItem("Вырезать");
        JMenuItem copy = new JMenuItem("Копировать");
        JMenuItem paste = new JMenuItem("Вставить");
        menuBar.add(edit);
        cut.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\scissors.png"));
        edit.add(cut);
        copy.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\copy.png"));
        edit.add(copy);
        paste.setIcon(new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\dobavit.png"));
        edit.add(paste);

        // item (Вид)
        JMenu view = new JMenu("Вид");
        JCheckBoxMenuItem line = new JCheckBoxMenuItem("Линейка");
        JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Сетка");
        JCheckBoxMenuItem navigation = new JCheckBoxMenuItem("Навигация");
        JRadioButton onePage = new JRadioButton("Одна страница");
        JRadioButton twoPage = new JRadioButton("Две страницы");
        menuBar.add(view);
        view.add(line);
        view.add(grid);
        view.add(navigation);
        view.add(new JSeparator());
        view.add(onePage);
        view.add(twoPage);

        // item (Текст)
        JMenu text = new JMenu("Текст");
        JMenuItem fontSize = new JMenuItem("Размер");
        JMenu fontStyle = new JMenu("Стиль");
        menuBar.add(text);
        text.add(fontSize);
        text.add(fontStyle);

        //add fields to Шрифт
        JMenuItem bold = new JMenuItem("Жирный");
        JMenuItem italic = new JMenuItem("Курсив");
        JMenu font = new JMenu("Шрифт");
        JMenuItem arial = new JMenuItem("Arial");
        JMenuItem times = new JMenuItem("Times");
        JMenuItem calibri = new JMenuItem("Calibri");
        fontStyle.add(bold);
        fontStyle.add(italic);
        view.add(new JSeparator());
        fontStyle.add(font);
        font.add(arial);
        font.add(times);
        font.add(calibri);

        JTabbedPane tabbedPane = new JTabbedPane();

        TabManagerService tabManagerService = new TabManagerService(tabbedPane);
        ToolBarFactory toolBarFactory = new ToolBarFactory(tabManagerService);

        JScrollPane pane = new JScrollPane(editor);
        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        tabManagerService.addTab(pane);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel toolbarsPanel = createToolbarsPanel(toolBarFactory);
        mainPanel.add(toolbarsPanel, NORTH);

        JPanel tabbedEditorPanel = new JPanel();
        tabbedEditorPanel.setLayout(new BoxLayout(tabbedEditorPanel, Y_AXIS));
        tabbedEditorPanel.setBackground(Color.lightGray);
        tabbedEditorPanel.add(tabbedPane);
        mainPanel.add(tabbedEditorPanel);

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel createToolbarsPanel(ToolBarFactory toolBarFactory) {
        JPanel toolbarsPanel = new JPanel();
        toolbarsPanel.setLayout(new BoxLayout(toolbarsPanel, Y_AXIS));
        toolbarsPanel.add(toolBarFactory.createGetAndOpenToolbar());
        toolbarsPanel.add(toolBarFactory.createFontAndTabsToolbar());
        return toolbarsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String editorText;
        if (event.getSource() == saveAs) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showSaveDialog(Notepad.this);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file = fileChooser.getSelectedFile();
            try {
                if (file != null && result == JFileChooser.APPROVE_OPTION) {
                    FileWriter fileWriter = new FileWriter(file);
                    editorText = editor.getText();
                    fileWriter.write(editorText);
                    fileWriter.close();
                    JOptionPane.showMessageDialog(fileChooser.getParent(),
                        "Ваш файл успешно сохранен!" + "\n" + "\n"
                            + "Путь: " + fileChooser.getSelectedFile() + "\n" + "Имя файла: "
                            + file.getName());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (event.getSource() == open) {
            JFileChooser fileChooser = new JFileChooser();
            int currentVal = fileChooser.showOpenDialog(null);
            if (currentVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Текстовые файлы", "txt");
                fileChooser.setFileFilter(filter);
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    editor.read(bufferedReader, null);
                    bufferedReader.close();
                    save.setEnabled(true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (event.getSource() == save) {
            Icon icon = new ImageIcon("C:\\Users\\Olegik\\IdeaProjects\\11 GUI\\src\\GUI\\save.png");
            String message1 = "Вы уверены, что хотите перезаписать файл?" + "\n" + "Имя файла: "
                + file.getName();
            int result = JOptionPane.showOptionDialog(null,
                new Object[]{message1}, "TextEditorPRO", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, icon, null, null);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    if (file != null) {
                        FileWriter fileWriter = new FileWriter(file);
                        editorText = editor.getText();
                        fileWriter.write(editorText);
                        fileWriter.close();
                        JOptionPane.showMessageDialog(this,
                            new String[]{"Ваш файл успешно перезаписан!", "\n", "Имя файла: "
                                + file.getName()},
                            "TextEditorPRO", JOptionPane.INFORMATION_MESSAGE, icon);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
