package seminar1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Первое задание – добавить на экран компоновщик-сетку с одним столбцом и добавить над существующей
 * кнопкой следующие компоненты в заданном порядке: JLabel с заголовком «Выберите режим игры»,
 * сгруппированные в ButtonGroup
 * переключаемые JRadioButton с указанием режимов «Человек против компьютера» и «Человек против человека»,
 * JLabel с заголовком «Выберите размеры
 * поля», JLabel с заголовком «Установленный размер поля:», JSlider со значениями 3..10,
 * JLabel с заголовком «Выберите длину для победы», JLabel с заголовком «Установленная длина:»,
 * JSlider со значениями 3..10.
 */

/*
 * Добавить компонентам интерактивности,
 * а именно, при перемещении ползунка слайдера
 * в соответствующих лейблах должны появляться текущие значения слайдеров.
 * Для этого необходимо добавить
 * к слайдеру слушателя изменений (как это было сделано для действия кнопки).
 */

/*
 * В методе обработчика нажатия кнопки необходимо заменить константы в аргументе
 * вызова метода старта игры на текущие показания компонентов (какая радио-кнопка
 * активна, значение слайдера размеров поля, значение слайдера выигрышной длины).
*/

public class SettingsWindow extends JFrame {

    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    JButton btnStart = new JButton("Start new game");
    JLabel gameMode = new JLabel("Select game mode");
    JLabel gameField = new JLabel("Set field size:");
    JLabel winCount = new JLabel("Set win line:");
    JSlider fieldSlider = new JSlider(3,10);
    JSlider winLengthSlider = new JSlider(3,10);
    JRadioButton humanVsPc = new JRadioButton("Human vs PC");
    JRadioButton humanVsHuman = new JRadioButton("Human vs human");
    ButtonGroup gameModeButtonsGroup = new ButtonGroup();
    JPanel panBottom;

    SettingsWindow(GameWindow gameWindow) {
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        panBottom = new JPanel(new GridLayout(8, 1));
        gameModeButtonsGroup.add(humanVsPc);
        gameModeButtonsGroup.add(humanVsHuman);
        panBottom.add(gameMode);
        panBottom.add(humanVsPc);
        panBottom.add(humanVsHuman);
        panBottom.add(gameField);
        panBottom.add(fieldSlider);
        panBottom.add(winCount);
        panBottom.add(winLengthSlider);
        panBottom.add(btnStart);
        humanVsPc.setSelected(true);
        fieldSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameField.setText("Field size: " + fieldSlider.getValue());
            }
        });
        winLengthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                winCount.setText("Win line: " + winLengthSlider.getValue());
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameWindow.startNewGame(humanVsPc.isSelected()?0:1,
                        fieldSlider.getValue(),
                        fieldSlider.getValue(),
                        winLengthSlider.getValue());
                setVisible(false);
            }
        });
        add(panBottom);
    }
}