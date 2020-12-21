package z11_9;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.List;

public class MainFrame extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static final String msg = "Шешко Алексей Николаевич, 11 группа, 2 курс, ФПМИ, БГУ\nкоманды:\n" +
            "File/Load from file для загрузки файла\n" +
            "File/Save для сохраниния файла\n" +
    		"File/Exit для выхода из приложения\n"+
    		"View/Font для выбора шрифта\n" +
            "View/Look and Feel для выбора внешнего вида\n" +
    		"Action/Add для добавления клиента\n"+
    		"Action/Delete для удаления по ключу\n" +
    		"Action/Clear all  для удаления всех клиентов\n"+
    		"Action/Search для поиска по ключу\n" +
    		"Action/Sort  для сортировки по ключу\n"+
    		"Action/Reverse sort  для выбора обратной сортировки для Sort\n" ;
            
    
    private final static String status = " Status: ";
    public static DefaultListModel<CarPark> model = new DefaultListModel<CarPark>();
    public static JList<CarPark> list = new JList<CarPark>(model);
    public static final JLabel statusLabel = new JLabel(status);
    private static List<CarPark> tempList;
    private static JRadioButtonMenuItem reverseSort;
    private static Container contentPane;
   private static Font font;


    MainFrame() {
        super("Car Parks");
        createGUI();
    }

    private void createGUI() {
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image image = Toolkit.getDefaultToolkit().getImage("src//car.jpg");
        
        setIconImage(image);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });
        createBody();
        createMenu();
        setSize(800, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createBody() {
        contentPane = getContentPane();
        JScrollPane scroll = new JScrollPane(list);
        contentPane.add(new JLabel("List of Car Parks:"), BorderLayout.NORTH);
        contentPane.add(scroll, BorderLayout.CENTER);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
    }

    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem loadItem = new JMenuItem("Load from file");
        fileMenu.add(loadItem);
        loadItem.addActionListener(this);

        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        saveItem.addActionListener(this);

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(this);

        JMenu viewMenu = new JMenu("View");
        menuBar.add(viewMenu);
        JMenuItem fontItem = new JMenuItem("Font");
        viewMenu.add(fontItem);

        // Handle button clicks
        final FontChooser chooser = new FontChooser(MainFrame.this);
        fontItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText(status + "changing font...");
                // Pop up the dialog
                chooser.setVisible(true);
                // Get the user's selection
                 font = chooser.getSelectedFont();
                if(font != null) {
                setUIFont(new FontUIResource(font));
                SwingUtilities.updateComponentTreeUI(MainFrame.this);
                }
                if(FontChooser.flag) {
                statusLabel.setText(status + "changed");
                }
                else {
                	statusLabel.setText(status + "canceled");
                }

            }
        });
        JMenu lookAndFeelItem = new JMenu("Look and Feel");
        viewMenu.add(lookAndFeelItem);


        // Create the menu


        // Create an object used for radio button mutual exclusion
        ButtonGroup radiogroup = new ButtonGroup();

        // Look up the available look and feels
        UIManager.LookAndFeelInfo[] plafs =
                UIManager.getInstalledLookAndFeels();


        // Loop through the plafs, and add a menu item for each one
        for(int i = 0; i < plafs.length; i++) {
            String plafName = plafs[i].getName();
            final String plafClassName = plafs[i].getClassName();

            // Create the menu item
            JMenuItem item = lookAndFeelItem.add(new JRadioButtonMenuItem(plafName));

            // Tell the menu item what to do when it is selected
            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Set the new look and feel
                        UIManager.setLookAndFeel(plafClassName);
                        // Tell each component to change its look-and-feel
                        SwingUtilities.updateComponentTreeUI(MainFrame.this);
                        // Tell the frame to resize itself to the its
                        // children's new desired sizes
                        //frame.pack();
                    }
                    catch(Exception ex) { System.err.println(ex); }
                }

            });

            // Only allow one menu item to be selected at once
            radiogroup.add(item);
        }


            JMenu actionMenu = new JMenu("Actions");
            menuBar.add(actionMenu);
            JMenuItem addItem = new JMenuItem("Add");
            actionMenu.add(addItem);
            addItem.addActionListener(this);
            JMenuItem delete = new JMenuItem("Delete");
            actionMenu.add(delete);
            delete.addActionListener(this);


            JMenuItem clearItem = new JMenuItem("Clear all");
            actionMenu.add(clearItem);
            clearItem.addActionListener(this);

            JMenuItem search = new JMenuItem("Search");
            actionMenu.add(search);

            search.addActionListener(this);
            JMenu sort = new JMenu("Sort");
            actionMenu.add(sort);


            JMenuItem byName = new JMenuItem("By name");
            JMenuItem byTime = new JMenuItem("By time");
            JMenuItem byNumber = new JMenuItem("By number");
            sort.add(byName);
            sort.add(byTime);
            sort.add(byNumber);

            byName.addActionListener(this);
            byNumber.addActionListener(this);
            byTime.addActionListener(this);

            reverseSort = new JRadioButtonMenuItem("Reverse sort", false);
            actionMenu.add(reverseSort);
            reverseSort.addActionListener(this);

            JMenu helpMenu = new JMenu("Help");
            menuBar.add(helpMenu);
            JMenuItem aboutItem = new JMenuItem("About");
            helpMenu.add(aboutItem);
            aboutItem.addActionListener(this);

        }


    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        switch (actionCommand) {
            case "Exit":
                statusLabel.setText(status + " quit...");
                int response =
                        JOptionPane.showConfirmDialog(MainFrame.this, "Really Quit?");
                if (response == JOptionPane.YES_OPTION) System.exit(0);
                break;

            case "Load from file":
                statusLabel.setText(status + " loading file...");
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setSelectedFile(new File("carparks.dat"));
                int result = chooser.showOpenDialog(MainFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getPath();
                    File f1 = new File(filename);
                    try {
                        FileInputStream is = new FileInputStream(f1);
                        DataInputStream ids = new DataInputStream(is);
                        ObjectInputStream istream = new ObjectInputStream(is);
                        int n = ids.readInt();
                        CarPark p = new CarPark();
                        model.clear();


                        for (int i = 1; i <= n; i++) {
                            p = (CarPark) istream.readObject();
                            model.addElement(p);
                        }


                        istream.close();
                        statusLabel.setText(status + " loaded");
                    } catch (Exception e) {
                        System.out.println("Error with file!");
                        statusLabel.setText(status + " Error with file!");
                    }
                }
                break;


            case "Save":
                statusLabel.setText(status + " saving...");
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("."));
                chooser.setSelectedFile(new File("carparks.dat"));
                result = chooser.showSaveDialog(MainFrame.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = chooser.getSelectedFile().getPath();
                    File f1 = new File(filename);
                    try {
                        FileOutputStream os = new FileOutputStream(f1);
                        DataOutputStream ods = new DataOutputStream(os);
                        ObjectOutputStream ostream = new ObjectOutputStream(os);
                        int n = model.size();
                        ods.writeInt(n);
                        ArrayList<CarPark> newList = new ArrayList<CarPark>();
                        for (int i = 0; i < n; i++) {
                            newList.add(model.getElementAt(i));
                        }
                        Iterator<CarPark> itr = newList.iterator();//itr СѓРєР°Р·С‹РІР°РµС‚ РЅР° РЅР°С‡Р°Р»Рѕ СЃРїРёСЃРєР° РїРµСЂРµРґ РїРµСЂРІС‹Рј СЌР»РµРјРµРЅС‚РѕРј
                        while (itr.hasNext()) {
                            CarPark temp = (CarPark) itr.next();
                            ostream.writeObject(temp);
                        }
                        ostream.close();
                        statusLabel.setText(status + " saved");
                    } catch (Exception e) {

                        statusLabel.setText(status + " error");
                    }
                }
                break;

            case "Clear all":
                model.clear();
                statusLabel.setText(status + " cleaned");
                break;

            case "About":
                statusLabel.setText(status + " about");
                JOptionPane.showMessageDialog(this, msg, "About", JOptionPane.INFORMATION_MESSAGE);
                statusLabel.setText(status);
                break;
            case "Add":
                statusLabel.setText(status + " adding...");

                AddFrame dialog = new AddFrame(MainFrame.this);
                dialog.setVisible(true);
                if (dialog.flag) {
                    model.addElement(dialog.carPark);
                    statusLabel.setText(status + "added");
                }
                else {
                    statusLabel.setText(status + " canceled");
                }
                break;


            case "Search":
                statusLabel.setText(status + " searching...");
                SearchFrame searchDialog = new SearchFrame(MainFrame.this);
                searchDialog.setVisible(true);
                if (searchDialog.isFound && !searchDialog.isCanceled) {
                    statusLabel.setText(status + "founded");

                }
                else{
                    statusLabel.setText(status + " canceled");
                }
                break;

            case "By name":
                tempList = new ArrayList<>();
                for (int i = 0; i < model.size(); i++)
                    tempList.add(model.get(i));
                model.clear();
                tempList.sort(new Comparator<CarPark>() {
                    @Override
                    public int compare(CarPark o1, CarPark o2) {
                        if (reverseSort.isSelected()) {
                            return (o2.getName()).compareTo(o1.getName());
                        }
                        return (o1.getName()).compareTo(o2.getName());
                    }
                });
                for (CarPark emp : tempList)
                    model.addElement(emp);

                statusLabel.setText(status + "sorted by name");
                break;
            case "By time":
                tempList = new ArrayList<>();
                for (int i = 0; i < model.size(); i++)
                    tempList.add(model.get(i));
                model.clear();
                tempList.sort(new Comparator<CarPark>() {
                    @Override
                    public int compare(CarPark o1, CarPark o2) {
                        if (reverseSort.isSelected()) {
                            return (CarPark.parseDate(o2.getStart_time())).compareTo(CarPark.parseDate(o1.getStart_time()));
                        }
                        return (CarPark.parseDate(o1.getStart_time())).compareTo(CarPark.parseDate(o2.getStart_time()));
                    }
                });
                for (CarPark emp : tempList)
                    model.addElement(emp);

                statusLabel.setText(status + "sorted by time");

                break;
            case "By number":
                tempList = new ArrayList<>();
                for (int i = 0; i < model.size(); i++)
                    tempList.add(model.get(i));
                model.clear();
                tempList.sort(new Comparator<CarPark>() {
                    @Override
                    public int compare(CarPark o1, CarPark o2) {
                        if (reverseSort.isSelected()) {
                            return (o2.getNumber()).compareTo(o1.getNumber());
                        }
                        return (o1.getNumber()).compareTo(o2.getNumber());
                    }
                });
                for (CarPark emp : tempList)
                    model.addElement(emp);

                statusLabel.setText(status + "sorted by number");
                break;

            case "Delete":
                statusLabel.setText(status + " deleting...");
                DeleteDialog deleteDialog = new DeleteDialog(MainFrame.this);
                deleteDialog.setVisible(true);
                if (deleteDialog.isDeleted) {
                    statusLabel.setText(status + "deleted");
                } else statusLabel.setText(status + " canceled");
                break;

            case "Reverse sort":
                statusLabel.setText(status + " Reverse sort");
                break;
        }


    }


    public static void setUIFont(FontUIResource f) {
        Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                FontUIResource orig = (FontUIResource) value;
                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
                UIManager.put(key, new FontUIResource(font));
            }
        }
    }

}



