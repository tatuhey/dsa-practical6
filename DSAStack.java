public class DSAStack {

    // private Object[] stack;
    // private int count;
    // private int DEFAULT_CAPACITY = 100;

    private DSALinkedList listStack;

    // default constructor
    public DSAStack() {
        // stack = new Object[DEFAULT_CAPACITY];
        // count = 0;

        listStack = new DSALinkedList();
    }

    // // alternate constructor
    // public DSAStack(int maxCapacity) {
    //     stack = new Object[maxCapacity];
    //     count = 0;
    // }

    // accessors
    // public int getCount() {
    //     return count;
    // }

    public boolean isEmpty() {
        return listStack.isEmpty();
    }

    // public boolean isFull() {
    //     return count == stack.length;

    // }

    // mutators
    public void push(Object value) {
        // if (isFull()) 
        //     throw new IllegalStateException("Stack is full"); //https://stackoverflow.com/questions/18141596/which-exception-to-throw-when-user-tries-to-add-to-a-full-container
        // else {
        //     stack[count] = value;
        //     count++;
        // }

        listStack.insertFirst(value);
    }

    public Object pop() {
        // Object topValue = top();
        // count--;
        // return topValue;

        Object topValue = listStack.peekFirst();
        listStack.removeFirst();
        return topValue;
    }

    public Object top() {
        // if (isEmpty())
        //     throw new IllegalStateException("Stack is empty");
        // else
        //     return stack[count - 1];

        return listStack.peekFirst();
    }
}