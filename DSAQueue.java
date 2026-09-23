public class DSAQueue {
    
    // cannot be private
    // protected Object[] queue;
    // protected int count;
    // protected int DEFAULT_CAPACITY = 100;

    private DSALinkedList listQueue;

    // default constructor
    public DSAQueue() {
        // queue = new Object[DEFAULT_CAPACITY];
        // count = 0;

        listQueue = new DSALinkedList();
    }

    // alternate constructor
    // public DSAQueue(int maxCapacity) {
    //     queue = new Object[maxCapacity];
    //     count = 0;
    // }

    // accessors
    // public int getCount() {
    //     return count;
    // }

    public boolean isEmpty() {
        // return count == 0;
        
        return listQueue.isEmpty();
    }

    // public boolean isFull() {
    //     return count == queue.length;
    // }

    // they are defined in the subclasses (shuffleQ and circularQ)
    // https://math.hws.edu/eck/cs124/javanotes6/c5/s5.html
    // https://stackoverflow.com/questions/79411745/polymorphism-and-abstract-classes-in-java
    // https://www.w3schools.com/java/java_polymorphism.asp
    // PDI lecture06

    public void enqueue(Object value) {
        listQueue.insertLast(value);
    };

    public Object dequeue() {
        Object frontValue = listQueue.peekFirst();
        listQueue.removeFirst();
        return frontValue;
    };
    public Object peek() {
        return listQueue.peekFirst();
    }
}


// class ShuffleQueue extends DSAQueue {

//     public ShuffleQueue() {
//         super(); // calling constructor in superclass
//     }

//     public ShuffleQueue(int maxCapacity){
//         super(maxCapacity);
//     }

//     // https://stackoverflow.com/questions/18141596/which-exception-to-throw-when-user-tries-to-add-to-a-full-container
//     @Override
//     public void enqueue(Object value) {
//         if (isFull())
//             throw new IllegalStateException("Queue is full");
//         else {
//             queue[count] = value;
//             count++;
//         }
//     }

//     // public void enqueue(Object value) {
//     //     if (isFull())
//     //         throw new IllegalStateException("Queue is full");
//     //     else {
//     //         insertLast(value);
//     //     }
//     // }
 

//     @Override
//     public Object dequeue() {
//         Object frontVal = peek();
//         for(int i = 0; i < count-1 ; i++) {
//             queue[i] = queue[i+1];
//         }
//         count--;
//         return frontVal;
//     }

//     @Override
//     public Object peek() {
//         if (isEmpty())
//             throw new IllegalStateException("Queue is empty");
//         else
//             return queue[0];
//     }
// }

// class CircularQueue extends DSAQueue {

//     // www.geeksforgeeks.org/java/implement-a-circular-queue-in-java/
//     private int front, rear;

//     public CircularQueue() {
//         super();
//         front = 0;
//         rear = 0;
//     }

//     public CircularQueue(int maxCapacity) {
//         super(maxCapacity);
//         front = 0;
//         rear = 0;
//     }

//     @Override
//     public void enqueue(Object value) {
//         if (isFull())
//             throw new IllegalStateException("Queue is full");
//         else {
//             queue[rear] = value;
//             rear = (rear + 1) % queue.length;
//             count++;
//         }

//     }

//     @Override
//     public Object dequeue() {
//         Object frontVal = peek();
//         front = (front + 1) % queue.length;
//         count--;
//         return frontVal;
//     }

//     @Override
//     public Object peek() {
//         if (isEmpty())
//             throw new IllegalStateException("Queue is empty");
//         else
//             return queue[front];
//     }
// }