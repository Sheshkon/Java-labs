package z11_9;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {
    private JTextField key;
    public CarPark carPark;
    public boolean isDeleted;
    private static final long serialVersionUID = 1L;

    public DeleteDialog(JFrame owner) {
        super(owner, "Delete", true);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 2));
        Container contentPane = getContentPane();

        JRadioButton nameButton = new JRadioButton("name", true);
        JRadioButton numberButton = new JRadioButton("number", false);
        JRadioButton timeButton = new JRadioButton("time", false);

        ButtonGroup group = new ButtonGroup();
        group.add(nameButton);
        group.add(timeButton);
        group.add(numberButton);
        contentPane.add(timeButton);
        contentPane.add(nameButton);
        contentPane.add(numberButton);


        contentPane.add(new JLabel("key"));
        key = new JTextField("", 25);
        contentPane.add(key);

        setResizable(false);

        JButton ok = new JButton("Ok");
        contentPane.add(ok);
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
            	isDeleted = false;
                if (nameButton.isSelected()) {
                    for (int i = 0; i < MainFrame.model.size(); i++) {
                        carPark = MainFrame.model.getElementAt(i);
                        if (carPark.getName().equals(key.getText())) {
                            MainFrame.model.removeElementAt(i);
                            isDeleted = true;
                            i--;
                        }
                    }

                }
                if (timeButton.isSelected()) {
                    for (int i = 0; i < MainFrame.model.size(); i++) {
                        carPark = MainFrame.model.getElementAt(i);
                        if (carPark.getStart_time().equals(key.getText())) {
                            MainFrame.model.remove(i);
                            i--;
                            isDeleted  = true;
                        }
                    }

                }
                if (numberButton.isSelected()) {
                    for (int i = 0; i < MainFrame.model.size(); i++) {
                        carPark = MainFrame.model.getElementAt(i);
                        if (carPark.getNumber().equals(key.getText())) {
                            MainFrame.model.remove(i);
                            i--;
                            isDeleted  = true;
                        }
                    }

                }
                if(isDeleted) {
                	setVisible(false);
                }
                
                if(!isDeleted){
                    JOptionPane.showMessageDialog(new JFrame(), "Nothing to delete", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
        JButton cancel = new JButton("Cancel");
        contentPane.add(cancel);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });
    }
}

