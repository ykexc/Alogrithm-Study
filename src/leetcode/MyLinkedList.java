package leetcode;

/**
 * @author 86152
 */
public class MyLinkedList {
    Node head;
    Node tail;
    int size;

    public MyLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        if (index < size / 2) {
            var temp = head;
            for (int i = 0; i <= index; i++) {
                temp = temp.next;
            }
            return temp.val;
        } else {
            var temp = tail;
            for (int i = 0; i < size - index; i++) {
                temp = temp.pre;
            }
            return temp.val;
        }
    }

    public void addAtHead(int val) {
        size++;
        var newNode = new Node(val);
        newNode.next = head.next;
        head.next.pre = newNode;
        newNode.pre = head;
        head.next = newNode;
    }

    public void addAtTail(int val) {
        size++;
        var newNode = new Node(val);
        newNode.pre = tail.pre;
        tail.pre.next = newNode;
        newNode.next = tail;
        tail.pre = newNode;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index < 0) {
            addAtHead(val);
            return;
        }
        var newNode = new Node(val);
        Node temp;
        if (index < size / 2) {
            temp = head;
            for (int i = 0; i <= index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = 0; i < size - index; i++) {
                temp = temp.pre;
            }
        }
        newNode.pre = temp.pre;
        temp.pre.next = newNode;
        newNode.next = temp;
        temp.pre = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index < size / 2) {
            var temp = head;
            for (int i = 0; i <= index; i++) {
                temp = temp.next;
            }
            var t = temp.pre;
            t.next = temp.next;
            temp.next.pre = t;
        } else {
            var temp = tail;
            for (int i = 0; i < size - index; i++) {
                temp = temp.pre;
            }
            var t = temp.pre;
            t.next = temp.next;
            temp.next.pre = t;
        }
        size--;
    }


    static class Node {
        Node pre;
        Node next;
        int val;
        Node(int val) {
            this.val = val;
        }
    }

}
