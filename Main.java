package Main;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Thw window properly closes when the user clicks the close button
        window.setResizable(false);//The window cannot be resized.
        window.setTitle("");

        GamePanel gamePanel=new GamePanel();
        window.add(gamePanel);

        window.pack();//causes this window to be sized to fit the preferred size and layouts of its subcomponents

        window.setLocationRelativeTo(null);//window in the center of the screen
        window.setVisible(true);//window is visible

        gamePanel.startGameThread();

    }
}