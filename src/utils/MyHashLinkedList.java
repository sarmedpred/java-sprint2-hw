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

        Node(Node prev, T task, Node next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    Map<Integer, Node> listOfNodes = new HashMap<>();

    Node get(int id) {
        return listOfNodes.get(id);
    }

    /**
     * Удалить Node
     */
    private void removeNode(Node node) {
        if (node == null) return;
        listOfNodes.remove(node.task.getId());
        Node prev = node.prev;
        Node next = node.next;
        if (node.prev != null) prev.next = next;
        if (node.next != null) next.prev = prev;
        if (head == node) head = node.next;
        if (tail == node) tail = node.prev;
    }

    public void removeTask (T task) {
        Node node = get(task.getId());
        removeNode(node);
    }

    /**
     * Добавить Node
     */

    public void linkLast(T task) {
        if (task == null) return;
        removeTask(task);
        Node node = new Node(null, task, null);
        listOfNodes.put(task.getId(), node);
        Node last = tail;
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
        Node node = head;
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
        Node node = head;
        while (node != null) {
            list.add(node.task);
            node = node.next;
        }
        return list;
    }

}
