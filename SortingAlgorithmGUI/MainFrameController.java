// Author       :   Alex Kourkoumelis
// Date         :   5/10/2019
// Title        :   Sorting Algorithm GUI
// Description  :   A GUI to choose different sorting algorithms to sort data.
//              :   Pick the size, sorting algorithm, whether the data is
//              :   sorted or unsorted. Sorts the data and times it using
//              :   StopwatchCPU and stores the sorted data into a file.

import edu.princeton.cs.algs4.StopwatchCPU;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class MainFrameController {

    // all of our gui objects
    // components/buttons etc.
    private MainFrame mainFrame;
    private JComboBox comboBox1;
    private JButton startBtn;
    private JTextArea welcomeTA;
    private JTextField textField1;
    private JButton unsortedBtn;
    private JButton sortedBtn;
    private JLabel sizeLabel;
    private JLabel algLabel;
    private JLabel sortLabel;
    private JLabel timeLabel;

    private int size;
    private boolean sorted;
    private int alg;
    private double startTime;
    private double endTime;

    // these flags will help determine the flow of the gui
    // and guide the user to enter all the required information
    private boolean algFlag = false;
    private boolean sortFlag = false;
    private boolean sizeFlag = false;

    // just initialize all the components and listeners
    public MainFrameController() {
        initComponents();
        initListeners();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }

    // initialize all the components
    public void initComponents() {
        mainFrame = new MainFrame();

        startBtn = mainFrame.getWelcomeBtn();
        welcomeTA = mainFrame.getWelcomeTA();
        comboBox1 = mainFrame.getComboBox1();
        textField1 = mainFrame.getTextField1();
        unsortedBtn = mainFrame.getUnsortedBtn();
        sortedBtn = mainFrame.getSortedBtn();
        sortLabel = mainFrame.getSortLabel();
        sizeLabel = mainFrame.getSizeLabel();
        algLabel = mainFrame.getAlgLabel();
        timeLabel = mainFrame.getTimeLabel();

        // adding options to the drop down menu, or "comboBox"
        comboBox1.addItem("Selection Sort");
        comboBox1.addItem("Insertion Sort");
        comboBox1.addItem("Shell Sort");
        comboBox1.addItem("Bubble Sort");
        comboBox1.addItem("Merge Sort");
        comboBox1.addItem("Quick Sort");
        comboBox1.addItem("Heap Sort");
    }

    // if we have all of the required information we can run the program
    public void runSort() {
        Comparable[] a = new Comparable[size];
        StopwatchCPU stopwatchCPU = new StopwatchCPU();

        // to construct the filename
        String filename;
        int element;
        if(sorted) {
            filename = "sorted";
        } else {
            filename = "unsorted";
        }
        filename += size + ".txt";

        // creating the elements and adding them to the array
        if (sorted) {
            for (int i = 0; i < size; i++) {
                a[i] = i+1;
            }
        } else {
            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                element = rand.nextInt(size * 10) + 1;
                a[i] = element;
            }
        }

        // sorting the data and timing it
        switch (alg) {
            case 1:
                startTime = stopwatchCPU.elapsedTime();
                Sort.selectionSort(a);
                endTime = stopwatchCPU.elapsedTime();
                break;
            case 2:
                startTime = stopwatchCPU.elapsedTime();
                Sort.insertionSort(a);
                endTime = stopwatchCPU.elapsedTime();
                break;
            case 3:
                startTime = stopwatchCPU.elapsedTime();
                Sort.shellSort(a);
                endTime = stopwatchCPU.elapsedTime();
                break;
            case 4:
                startTime = stopwatchCPU.elapsedTime();
                Sort.bubbleSort(a);
                endTime = stopwatchCPU.elapsedTime();
                break;
            case 5:
                startTime = stopwatchCPU.elapsedTime();
                Sort.mergeSort(a, 0, a.length-1);
                endTime = stopwatchCPU.elapsedTime();
                break;
            case 6:
                startTime = stopwatchCPU.elapsedTime();
                Sort.quickSort(a, 0, a.length-1);
                endTime = stopwatchCPU.elapsedTime();
                break;
            case 7:
                startTime = stopwatchCPU.elapsedTime();
                Sort.heapSort(a);
                endTime = stopwatchCPU.elapsedTime();
                break;
            default:
                System.out.println("Error: Did not understand that answer.");
                break;
        }

        // determining elapsed time for sorting
        endTime -= startTime;
        timeLabel.setText("Time: " + endTime);
        welcomeTA.setText("It took " + endTime + " seconds to sort your data.");

        // generating the file with the sorted data
        File file = new File (filename);
        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            for (Comparable i:a) {
                writer.println(i);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

        // printing the sorted data to the console (not in gui)
        // this can be commented out if not desired
        System.out.println("\nSorted array: ");
        for(int i = 0; i < a.length; i++) {
            System.out.println("i: " + i + "\telement: " + a[i]);
        }

    }

    // prompt the user to select an algorithm from the combo box
    public void promptAlg() {
        welcomeTA.setText("n = " + size + "\nPlease choose which sorting algorithm you'd like to use on the data" +
                "from the dropdown box above.\n");
    }

    // prompt the user to enter a size in the text box
    public void promptSize() {
        welcomeTA.setText("What size of data would you like?\nPlease enter a number" +
                " between 0 and 500000 below\nin the text box and then press Enter.\n");
    }

    // prompt the user to select whether the data should be sorted or random
    public void promptSort() {
        welcomeTA.setText("Would you like the data to be sorted?\n" +
                "Please choose from the buttons above.\n");
    }

    // initializing all of our listeners
    private void initListeners() {
        startBtn.addActionListener(new StartBtnListener());
        comboBox1.addActionListener(new ComboBoxListener());
        textField1.addActionListener(new TextFieldListener());
        unsortedBtn.addActionListener(new UnsortedBtnListener());
        sortedBtn.addActionListener(new SortedBtnListener());
    }

    // sorted button
    private class SortedBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sorted = true;
            sortLabel.setText("Sorted/Unsorted: Sorted");
            sortFlag = true;
        }
    }

    // unsorted button
    private class UnsortedBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sorted = false;
            sortLabel.setText("Sorted/Unsorted: Unsorted");
            sortFlag = true;
        }
    }

    // text box for entering the size of the data set
    private class TextFieldListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String s = textField1.getText();
            welcomeTA.append("n = " + s + "\n");
            try {
                size = Integer.parseInt(s);
                if(size > 500000) {
                    size = 500000;
                    s = "500000";
                    System.out.println("got here");
                }
                sizeLabel.setText("Size = " + s);
                sizeFlag = true;
            } catch (NumberFormatException ex) {
                welcomeTA.append("I didn't understand that number. Please try again.\n");
                sizeFlag = false;
            }
        }
    }

    // the run or start button checks to see if all the data is acquired.
    // if the data isn't acquired, directs the user to the appropriate prompt.
    private class StartBtnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!algFlag) {
                promptAlg();
            } else if (!sizeFlag) {
                promptSize();
            } else if (!sortFlag) {
                promptSort();
            } else {
                welcomeTA.setText("Running...");
                runSort();
            }
        }
    }

    // setting the algorithm from the combo box
    private class ComboBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            alg = comboBox1.getSelectedIndex() + 1;
            switch (alg) {
                case 1:
                    System.out.println("You chose Selection Sort");
                    welcomeTA.append("You chose Selection Sort\n");
                    algLabel.setText("Algorithm: Selection Sort");
                    alg = 1;
                    break;
                case 2:
                    System.out.println("You chose Insertion Sort");
                    welcomeTA.append("You chose Insertion Sort\n");
                    algLabel.setText("Algorithm: Insertion Sort");
                    alg = 2;
                    break;
                case 3:
                    alg = 3;
                    System.out.println("You chose Shell Sort");
                    welcomeTA.append("You chose Shell Sort\n");
                    algLabel.setText("Algorithm: Shell Sort");
                    break;
                case 4:
                    System.out.println("You chose Bubble Sort");
                    welcomeTA.append("You chose Bubble Sort\n");
                    algLabel.setText("Algorithm: Bubble Sort");
                    alg = 4;
                    break;
                case 5:
                    System.out.println("You chose Merge Sort");
                    welcomeTA.append("You chose Merge Sort\n");
                    algLabel.setText("Algorithm: Merge Sort");
                    alg = 5;
                    break;
                case 6:
                    System.out.println("You chose Quick Sort");
                    welcomeTA.append("You chose Quick Sort\n");
                    algLabel.setText("Algorithm: Quick Sort");
                    alg = 6;
                    break;
                case 7:
                    System.out.println("You chose Heap Sort");
                    welcomeTA.append("You chose Heap Sort\n");
                    algLabel.setText("Algorithm: Heap Sort");
                    alg = 7;
                    break;
                default:
                    System.out.println("Error: Did not understand that answer.\n");
                    break;
            }
            algFlag = true;
        }
    }
}
