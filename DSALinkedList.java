import java.util.*;

public class DSALinkedList implements Iterable<Object> {
    private DSAListNode head;
    private DSAListNode tail; // double-ended lists

    public DSALinkedList() {
        head = null;
        tail = null;
    }

    // accessor
    public boolean isEmpty() {
        return head == null;
    }

    public Object peekFirst() {
        if (isEmpty())
            throw new IllegalStateException("Node is empty");
        else
            return head.getValue();
    }

    public Object peekLast() {
        if (isEmpty())
            throw new IllegalStateException("Node is empty");
        else 
            return tail.getValue();
    }

    @Override
    public Iterator<Object> iterator() {
        return new DSALinkedListIterator();
    }

    // mutator
    public void insertFirst (Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = head; // head is tail as well as the first
        } else {
            newNd.setNext(head);
            head.setPrev(newNd);
            head = newNd;
        }
    }

    public void insertLast (Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            tail = newNd;
            head = tail;
        } else {
            newNd.setPrev(tail); // setting the backward chain
            tail.setNext(newNd); // after the current tail, use mutator to create new node
            tail = newNd;
        }
    }

    public Object removeFirst() {
        if (isEmpty())
            throw new IllegalStateException("Node is empty");
        else {
            Object nodeValue =  head.getValue();

            if (head == tail) { // for one node list
                head = null;
                tail = null; 
            } else {
                head = head.getNext();
                head.setPrev(null); // set the new first node's backward chain to point at null
            }
            
            return nodeValue;
        }
    }

    public Object removeLast() { // becomes the literal opposite of removeFirst
        if (isEmpty())
            throw new IllegalStateException("Node is empty");
        else {
            Object nodeValue = tail.getValue();

            if(head == tail) { // for one node list
                head = null;
                tail = null;
            } else {
                tail = tail.getPrev();
                tail.setNext(null);
            }

            return nodeValue;
        }
    }

    public Object remove(Object inValue) {
        if(isEmpty())
            return null;
        
        DSAListNode currNd = head;

        while(currNd != null) {
            if(currNd.getValue().equals(inValue)) {
                if (currNd == head)
                    return removeFirst();

                else if (currNd == tail)
                    return removeLast();
                
                else {
                    currNd.getPrev().setNext(currNd.getNext());
                    currNd.getNext().setPrev(currNd.getPrev());
                    return currNd.getValue();
                }
            }
            currNd = currNd.getNext();
        }
        return null; // value not found
    }
    
    public void display() {
        if (isEmpty())
            throw new IllegalStateException("Node is empty");
        else {
            DSAListNode currNd = head;
            while(currNd != null) {
                System.out.print(currNd.getValue());
                currNd = currNd.getNext();
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    // Custom iterator
    // https://www.geeksforgeeks.org/java/java-implementing-iterator-and-iterable-interface/
    // https://www.baeldung.com/java-creating-custom-iterator
    private class DSALinkedListIterator implements Iterator<Object> {
        private DSAListNode currNd;
        public DSALinkedListIterator() {
            currNd = head;
        }
        
        @Override 
        public boolean hasNext() {
            return currNd != null; // returns true if next exists
        }

        @Override 
        public Object next() {
            if(!hasNext()) {
                throw new NoSuchElementException("Next element does not exist");
            } else {
                Object data = currNd.getValue(); // get current value
                currNd = currNd.getNext();  // move pointer to next
                return data;
            }
        }
    }

    // DSAListNode
    private class DSAListNode {
        public Object m_value;
        public DSAListNode m_next;
        public DSAListNode m_prev;

        public DSAListNode(Object inValue) {
            m_value = inValue;
            m_next = null;
            m_prev = null;
        }

        // accessor
        public Object getValue() {
            return m_value;
        }

        public DSAListNode getNext() {
            return m_next;
        }

        public DSAListNode getPrev() {
            return m_prev;
        }

        // mutator
        public void setValue(Object inValue) {
            m_value = inValue;
        }

        public void setNext(DSAListNode newNext) {
            m_next = newNext;
        }

        public void setPrev(DSAListNode newPrev) {
            m_prev = newPrev;
        }
    }
}
