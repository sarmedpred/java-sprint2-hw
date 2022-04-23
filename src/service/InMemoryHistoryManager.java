package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Node.
 */

class Node<T extends Task> {
    T task;
    Node<T> next;
    Node<T> prev;

    Node(T task) {
        this.task = task;
    }
}

class MyHashLinkedList<T extends Task> {
   Node<T> head;
   Node<T> tail;
   Map<Integer, Node<T>> map = new HashMap<>();

   Node<T> get(int id) {
       return map.get(id);
   }

    /**
     * Удалить Node
     */
   void remove(int id) {
       Node<T> node = get(id);
       if (node == null) return;
       map.remove(id);
       Node<T> prev = node.prev;
       Node<T> next = node.next;
       if (node.prev != null) prev.next = next;
       if (node.next != null) next.prev = prev;
       if (head == node) head = node.next;
       if (tail == node) tail = node.prev;
   }

    /**
     * Добавить Node
     */

   void add(T task) {
       if (task == null) return;
       remove(task.getId());
       Node<T> node = new Node<>(task);
       map.put(task.getId(), node);
       Node<T> last = tail;
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
        Node<T> node = head;
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
    List<T> toList() {
       List<T> list = new ArrayList<>();
       Node<T> node = head;
        while (node != null) {
            list.add(node.task);
            node = node.next;
        }
        return list;
    }

}

/**
 * История просмотра задач в памяти.
 */
public class InMemoryHistoryManager implements HistoryManager {

    private MyHashLinkedList<Task> history = new MyHashLinkedList<>();

    @Override
    public String toString() {
        return history.toString();
    }

    /**
     * Получить историю просмотров.
     */

    @Override
    public List<Task> getHistory() {
        return history.toList();
    }

    /**
     * Добавить задачу в историю просмотров.
     */

    @Override
    public void addTaskToHistory(Task task) {
        history.add(task);
    }

    /**
     * Удалить задачу из истории просмотра.
     */
    @Override
    public void removeTaskFromHistory(int id) {
        history.remove(id);
    }
}
