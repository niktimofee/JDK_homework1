package seminar1;

import javax.swing.*;
import java.awt.*;

public class TestGrid2 extends JFrame {
    public TestGrid2() {
        super("GridBagStart");
        // Выход при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Устанавливаем расположение компонентов
        setLayout(new GridBagLayout());
        // Добавляем две кнопки, ячейки по умолчанию
        add(new JButton("Hello"));
        add(new JButton("Cancel"));
        // Настройка ячейки для текстового поля
        GridBagConstraints textFieldConstraints =
                new GridBagConstraints();
        // Заполняем ячейки по горизонтали
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        // Просим занять все оставшиеся ячейки
        textFieldConstraints.gridwidth =
                GridBagConstraints.REMAINDER;
        textFieldConstraints.weightx = 5.0f;
        add(new JTextField(50), textFieldConstraints);
        // Выводим окно на экран
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        new TestGrid2();
                    }
                });
    }
}