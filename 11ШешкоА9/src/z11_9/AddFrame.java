package z11_9;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddFrame extends JDialog {
    private final JTextField number;
    private final JTextField name;
    private final JTextField start_time;
    private final JTextField end_time;
    private final JTextField tariff;
    public CarPark carPark;
    public boolean flag;
    public boolean isMistake = false;
    private static final long serialVersionUID = 1L;
    public static String error = "invalid input";
    private static Color color;
    public boolean isCanceled = false;

    public AddFrame(JFrame owner) {
        super(owner,"Add customer", true);
        setSize(350, 500);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        setResizable(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
        contentPane.add(new JLabel("Number"));
        number = new JTextField("", 25);
        contentPane.add(number);
        contentPane.add(new JLabel("Name"));
        name = new JTextField("", 25);
        contentPane.add(name);
        contentPane.add(new JLabel("Start Time"));
        start_time = new JTextField("", 25);
        contentPane.add(start_time);
        contentPane.add(new JLabel("End Time"));
        end_time = new JTextField("", 25);//размер поля - 25 символов
        contentPane.add(end_time);
        contentPane.add(new JLabel("Tarif"));
        tariff = new JTextField("", 10);
        contentPane.add(tariff);
        flag = false;
        color = number.getBackground();

        JButton ok = new JButton("Ok");
        contentPane.add(ok);
        ok.addActionListener(event -> {
            flag = true;
            carPark = new CarPark();
            if (name.getText().equals("")) {
                checker(name);

            } else {
                carPark.setName(name.getText());
            }
            if (CarPark.validNumber(number.getText())) {
                carPark.setNumber(number.getText());
            } else {
                checker(number);
            }
            if (CarPark.validDate(start_time.getText())) {
                carPark.setStart_time(start_time.getText());
                if (CarPark.validEndTime(carPark.getStart_time(), end_time.getText())) {
                    carPark.setEnd_time(end_time.getText());
                }

            } else {
                checker(start_time);
                checker(end_time);
            }
            if (tariff.getText().equals("")) {
                checker(tariff);
            } else {
                carPark.setTariff(tariff.getText());
            }

            clear(name);
            clear(number);
            clear(tariff);
            clear(start_time);
            clear(end_time);



            setVisible(isMistake);
            isMistake = false;

        });
        JButton cancel = new JButton("Cancel");
        contentPane.add(cancel);
        cancel.addActionListener(event -> {
            isCanceled = true;
            setVisible(false);
        });
    }
    void checker(JTextField jTextField) {
        jTextField.setBackground(Color.RED);
        isMistake = true;
        flag = false;
        jTextField.setText(error);
    }
    void clear(JTextField jTextField){
        jTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                jTextField.setBackground(color);
                jTextField.setText("");
                isMistake = false;
            }
        });
    }


}

