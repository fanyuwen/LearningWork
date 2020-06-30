package com.learning.algorithm.graph;

import java.util.Iterator;

/**
 * 参照书《算法》
 * 背包数据结构(只能进行添加,不能进行删除和修改)
 *
 * @author fanyuwen
 * @date 2020/1/25 20:52
 */
public class Bag<Item> implements Iterable<Item> {
    //链表的首结点
    private Node first;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {
        private Item item;
        private Node next;
    }

    public void add(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
