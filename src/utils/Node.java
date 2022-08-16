package utils;

import model.Task;

/**
 * Node.
 */

class Node <T extends Task>{
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


