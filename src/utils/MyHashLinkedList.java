package utils;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHashLinkedList<T extends Task> {

    /**
     * Node.
     */

    class Node {
        T task;
        Node next;
        Node prev;

        Node(T task) {
            this.task = task;
        }

        Node(MyHashLinkedList<T>.Node prev, T task, MyHashLinkedList<T>.Node next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    Map<Integer, MyHashLinkedList<T>.Node> listOfNodes = new HashMap<>();

    MyHashLinkedList<T>.Node get(int id) {
        return listOfNodes.get(id);
    }

    /**
     * Удалить Node
     */
    private void removeNode(Node node) {
        if (node == null) return;
        listOfNodes.remove(node.task.getId());
        MyHashLinkedList<T>.Node prev = node.prev;
        MyHashLinkedList<T>.Node next = node.next;
        if (node.prev != null) prev.next = next;
        if (node.next != null) next.prev = prev;
        if (head == node) head = node.next;
        if (tail == node) tail = node.prev;
    }

    public void removeTask (T task) {
        MyHashLinkedList<T>.Node node = get(task.getId());
        removeNode(node);
    }

    /**
     * Добавить Node
     */

    public void linkLast(T task) {
        if (task == null) return;
        removeTask(task);
        MyHashLinkedList<T>.Node node = new MyHashLinkedList<T>.Node(null, task, null);
        listOfNodes.put(task.getId(), node);
        MyHashLinkedList<T>.Node last = tail;
        if (tail != null) {
            node.prev = tail;
            tail.next = node;
            tail = node;
        } else {
            tail = node;
            head = node;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        MyHashLinkedList<T>.Node node = head;
        while (node != null) {
            sb.append(node.task).append(", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Конвертировать в list
     */
    public List<T> getTasks() {
        List<T> list = new ArrayList<>();
        MyHashLinkedList<T>.Node node = head;
        while (node != null) {
            list.add(node.task);
            node = node.next;
        }
        return list;
    }

}
