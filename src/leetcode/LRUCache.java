package leetcode;

import java.util.HashMap;

/**
 * @author 86152
 */
public class LRUCache {

    static class DeLinkedList {
        int size;
        Node head;
        Node tail;

        DeLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void addLast(Node node) {
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node d = head.next;
            remove(d);
            return d;
        }
    }

    static class Node {
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }


    HashMap<Integer, Node> map;

    DeLinkedList deLinkedList;

    int capacity;


    public LRUCache(int capacity) {
        map = new HashMap<>();
        deLinkedList = new DeLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        var node = map.get(key);
        deLinkedList.remove(node);
        deLinkedList.addLast(node);
        return node.val;
    }

    public void put(int key, int value) {
        var newNode = new Node(key, value);
        if (!map.containsKey(key)) {
            if (deLinkedList.size == capacity) {
                var node = deLinkedList.removeFirst();
                map.remove(node.key);
                map.put(key, newNode);
                deLinkedList.addLast(newNode);
            } else {
                map.put(key, newNode);
                deLinkedList.addLast(newNode);
            }
        } else {
            var old = map.get(key);
            map.put(key, newNode);
            deLinkedList.remove(old);
            deLinkedList.addLast(newNode);
        }
    }
}
