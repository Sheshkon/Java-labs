package z11_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SearchFrame extends JDialog {
	private JTextField key;
	public CarPark carPark;
	public boolean isFound;
	public boolean isCanceled = false;
	private static final long serialVersionUID = 1L;
	public static DefaultListModel<CarPark> searchModel = new DefaultListModel<CarPark>();
	public static JList<CarPark> searchList = new JList<CarPark>(searchModel);
	public static boolean isValidate = true;

	public SearchFrame(JFrame owner) {
		super(owner, "Search", true);

		setSize(400, 450);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		Container contentPane = getContentPane();

		JScrollPane scroll = new JScrollPane(searchList);

		contentPane.add(scroll, BorderLayout.CENTER);

		SwingUtilities.updateComponentTreeUI(SearchFrame.this);
		  addWindowListener(new WindowAdapter() {
	            @Override
	            public void windowClosing(WindowEvent e) {
	                super.windowClosing(e);
	                searchModel.clear();
	                isCanceled = true;
	            }
	        });

		JRadioButton nameButton = new JRadioButton("name", true);
		JRadioButton numberButton = new JRadioButton("number", false);
		JRadioButton timeButton = new JRadioButton("time", false);

		JRadioButton less = new JRadioButton("<", false);
		JRadioButton equal = new JRadioButton("=", true);
		JRadioButton bigger = new JRadioButton(">", false);

		ButtonGroup group = new ButtonGroup();
		group.add(nameButton);
		group.add(timeButton);
		group.add(numberButton);
		contentPane.add(timeButton);
		contentPane.add(nameButton);
		contentPane.add(numberButton);

		ButtonGroup group2 = new ButtonGroup();
		group2.add(less);
		group2.add(equal);
		group2.add(bigger);
		contentPane.add(less);
		contentPane.add(equal);
		contentPane.add(bigger);

		contentPane.add(scroll, BorderLayout.CENTER);
		key = new JTextField("key", 25);
		contentPane.add(key);
		setResizable(false);

		JButton ok = new JButton("Ok");
		contentPane.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchModel.clear();
				isFound = false;
				isValidate = true;
				if (nameButton.isSelected()) {
					if (equal.isSelected()) {
						for (int i = 0; i < MainFrame.model.size(); i++) {
							carPark = MainFrame.model.getElementAt(i);
							if (carPark.getName().equals(key.getText())) {
								searchModel.addElement(carPark);
								isFound = true;
							}
						}
					} else if (less.isSelected()) {
						for (int i = 0; i < MainFrame.model.size(); i++) {
							carPark = MainFrame.model.getElementAt(i);
							if (carPark.getName().compareToIgnoreCase(key.getText()) < 0) {
								searchModel.addElement(carPark);
								isFound = true;
							}
						}
					} else if (bigger.isSelected()) {
						for (int i = 0; i < MainFrame.model.size(); i++) {
							carPark = MainFrame.model.getElementAt(i);
							if (carPark.getName().compareToIgnoreCase(key.getText()) > 0) {
								searchModel.addElement(carPark);
								isFound = true;
							}
						}
					}
				}
				if (timeButton.isSelected()) {
					if (CarPark.validDate(key.getText())) {
						if (equal.isSelected()) {
							for (int i = 0; i < MainFrame.model.size(); i++) {
								carPark = MainFrame.model.getElementAt(i);
								if (carPark.getStart_time().equals(key.getText())) {
									searchModel.addElement(carPark);
									isFound = true;
								}
							}
						} else if (less.isSelected()) {
							for (int i = 0; i < MainFrame.model.size(); i++) {
								carPark = MainFrame.model.getElementAt(i);
								if (CarPark.parseDate(carPark.getStart_time())
										.compareTo(CarPark.parseDate(key.getText())) < 0) {
									searchModel.addElement(carPark);
									isFound = true;
								}
							}
						} else if (bigger.isSelected()) {
							for (int i = 0; i < MainFrame.model.size(); i++) {
								carPark = MainFrame.model.getElementAt(i);
								if (CarPark.parseDate(carPark.getStart_time())
										.compareTo(CarPark.parseDate(key.getText())) > 0) {

									searchModel.addElement(carPark);
									isFound = true;
								}
							}
						}
					} else {
						isValidate = false;
					}
				}
				if (numberButton.isSelected()) {
					if (CarPark.validNumber(key.getText())) {
						if (equal.isSelected()) {
							for (int i = 0; i < MainFrame.model.size(); i++) {
								carPark = MainFrame.model.getElementAt(i);
								if (carPark.getNumber().equals(key.getText())) {
									searchModel.addElement(carPark);
									isFound = true;
								}
							}
						} else if (less.isSelected()) {
							for (int i = 0; i < MainFrame.model.size(); i++) {
								carPark = MainFrame.model.getElementAt(i);
								if (carPark.getNumber().compareToIgnoreCase(key.getText()) < 0) {
									searchModel.addElement(carPark);
									isFound = true;
								}
							}
						} else if (bigger.isSelected()) {
							for (int i = 0; i < MainFrame.model.size(); i++) {
								carPark = MainFrame.model.getElementAt(i);
								if (carPark.getNumber().compareToIgnoreCase(key.getText()) > 0) {
									searchModel.addElement(carPark);
									isFound = true;
								}
							}
						}
					} else {
						isValidate = false;
					}
				}
				if (!isValidate) {
					JOptionPane.showMessageDialog(new JFrame(), "Key is not valid", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				if (!isFound) {
					JOptionPane.showMessageDialog(new JFrame(), "Is not found", "Dialog", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		JButton cancel = new JButton("Cancel");
		contentPane.add(cancel);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setVisible(false);
				searchModel.clear();
				isCanceled = true;
			}
		});
		JButton clear = new JButton("Clear");
		contentPane.add(clear);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchModel.clear();
			}
		});
	}
}
