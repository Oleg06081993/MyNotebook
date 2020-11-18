import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static java.awt.BorderLayout.*;

public class Main extends Component {
    JFrame frame;
    JPanel panel;
    JButton saveButton;
    JButton openButton;
    JTextArea textArea;
    String getter;
    File file;
    JFileChooser fileChooser;
    BufferedReader bufferedReader;
    int currentVal;

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        Main myGUI = new Main();
        myGUI.go();
    }

    void go() {
        frame = new JFrame("Имитация простого блокнота");
        frame.setLocationByPlatform(true);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.GRAY);
        saveButton = new JButton("Сохранить текст");
        openButton = new JButton("Открыть текстовый файл");
        panel.add(openButton);
        panel.add(saveButton);
        saveButton.addActionListener(new SaveListener());
        openButton.addActionListener(new OpenListener());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        //textArea.setText("Write something here and save it into the file");
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JLabel label = new JLabel("Ваш текст");
        panel.add(NORTH, label);
        panel.add(CENTER,scroll);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(Main.this);   // ВЫЗЫВАЕТСЯ ДИАЛОГОВОЕ ОКНО 1 РАЗ
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file = fileChooser.getSelectedFile();
            try {
                if (file != null && result == JFileChooser.APPROVE_OPTION) {
                    FileWriter fileWriter = new FileWriter(file);
                    getter = textArea.getText();
                    fileWriter.write(getter);
                    fileWriter.close();
                    JOptionPane.showMessageDialog(fileChooser.getParent(),
                        "Your file was successfully saved!" + "\n" + "\n"
                            + "Path: " + fileChooser.getSelectedFile() + "\n" + "FileName: "
                            + file.getName());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    class OpenListener implements ActionListener {
        public void actionPerformed(ActionEvent ev2) {
            JFileChooser fileChooser = new JFileChooser();
            currentVal = fileChooser.showOpenDialog(null);
            if (currentVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                try {
                    FileReader fileReader = new FileReader(file);
                    bufferedReader = new BufferedReader(new FileReader(file));
                    textArea.read(bufferedReader,null);
                    bufferedReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

//напоминалка себе: предупредить пользолвателя если он хочет перезаписать файл







