// Author       :   Alex Kourkoumelis
// Date         :   5/10/2019
// Title        :   Sorting Algorithm GUI
// Description  :   A GUI to choose different sorting algorithms to sort data.
//              :   Pick the size, sorting algorithm, whether the data is
//              :   sorted or unsorted. Sorts the data and times it using
//              :   StopwatchCPU and stores the sorted data into a file.

public class Main {

    public static void main(String[] args) {
        // hi
        System.out.println("Hello World!");

        MainFrameController mainFrameController = new MainFrameController();
        mainFrameController.showMainFrameWindow();

        // begin the first prompt to guide the user
        mainFrameController.promptSize();
    }
}