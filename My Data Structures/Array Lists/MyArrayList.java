/**
 *
 * @author Shashwat Koranne
 * Andrew ID: skoranne.
 *
 */

public class MyArrayList {
    /**
     * Initializing String array called MyArrayList.
     */
    private String[] MyArrayList;
    /**
     * Defining capacity to manipulate array.
     */
    private int capacity;
    /**
     * Initializing the size of array to start with.
     */
    private int sizeOfArray = 0;
    /**
     * Default constructor for MyArrayList.
     */
    public MyArrayList() {
        /**
         * Initializing the array with a default capacity of 10.
         */
        this.MyArrayList = new String[10];
        this.capacity = 1;
    }
    /**
     * @param initialCapacity the capacity with which array needs to be initialized
     * Constructor with specifically mentioned initial capacity.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.capacity = initialCapacity;
            this.MyArrayList = new String[initialCapacity];
        } else {
            throw new IllegalArgumentException("Array length can't be weird!");
        }
    }
    /**
     * @param text to be added in the array
     * Method to add the text in the array.
     */
    public void add(String text) {
        if ((text != null) && (text.matches("[a-zA-Z]+"))) {
            if (capacity <= 0) {
                capacity = 1;
                MyArrayList = new String[capacity];
                MyArrayList[sizeOfArray] = text;
                sizeOfArray++;
                //throw new IllegalArgumentException("Negative capacity not allowed");
            } else if (this.sizeOfArray >= capacity) {
                capacity = (capacity) * 2;
                String[] newMyArrayList = new String[capacity];

                for (int i = 0; i < this.sizeOfArray; i++) {
                    newMyArrayList[i] = MyArrayList[i];
                }

                newMyArrayList[this.sizeOfArray] = text;
                this.sizeOfArray = this.sizeOfArray + 1;
                this.MyArrayList = newMyArrayList;

            } else if (this.sizeOfArray < capacity) {
                this.MyArrayList[this.sizeOfArray] = text;
                this.sizeOfArray = this.sizeOfArray + 1;
            }
        }
    }
    /**
     * Method to search for a key in the array.
     * @param key the key to search
     * @return boolean decision
     */
    public boolean search(String key) {
        for (int i = 0; i < this.sizeOfArray; i++) {
            if (this.MyArrayList[i].equals(key)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
    /**
     * Method to get the size of the array.
     * @return size
     */
    public int size() {
        return this.sizeOfArray;
    }
    /**
     * Method to get the updated capacity of the array.
     * @return capacity
     */
    public int getCapacity() {
        if (capacity == 0) {
            return 0;
        }

        return capacity;
    }
    /**
     * Method to print the contents of the array.
     */
    public void display() {
        for (int i = 0; i < sizeOfArray - 1; i++) {
            String s = MyArrayList[i] + " ";
            System.out.print(s);
            //System.out.println(i);
            //System.out.println(list.get(i));
        }
        System.out.println(MyArrayList[sizeOfArray]);
    }
    /**
     * Method to remove duplicates from the array.
     */
    public void removeDups() {
        int count = 0;
        for (int i = 0; i < this.sizeOfArray - 1; i++) {
            for (int j = i + 1; j < this.sizeOfArray; j++) {
                if ((MyArrayList[i].equals(MyArrayList[j])) && (i != j)) {
                    //System.out.println(MyArrayList[i]);
                    //MyArrayList[j] = MyArrayList[j+1];
                    //list.add(MyArrayList[j]);
                    for (int k = j + 1; k < sizeOfArray; k++) {
                        MyArrayList[k - 1] = MyArrayList[k];
                    }
                    //MyArrayList[j++] = MyArrayList[j++ +1];
                    //MyArrayList[j+1] = null;
                    count = count + 1;
                    this.sizeOfArray = this.sizeOfArray - 1;
                } else if (!MyArrayList[i].equals(MyArrayList[j])) {
                    continue;
                }
                j--;
            }
        }
    }
}
