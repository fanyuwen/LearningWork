package com.learning;

import java.util.Arrays;

public class Main<T> {

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(2, new Object());// N = 0
        heap.insert(4, new Object());
        heap.insert(3, new Object());
        heap.insert(5, new Object());
        heap.insert(0, new Object());// N = 1
        heap.insert(7, new Object());
        heap.insert(1, new Object());// N = 2
        heap.insert(9, new Object());
        heap.insert(8, new Object());
        heap.insert(6, new Object());
        //[3,0,1,2]
        //[1,2,3,0]

        int[] pq = new int[4], qp = new int[4];
        Arrays.setAll(pq, i -> 4 - i - 1);
        for (int i = 0; i < 4; i++) {
            qp[pq[i]] = i;
        }

        for(int i = 0;i<4;i++){
            if(pq[qp[i]] != qp[pq[i]]){
                System.out.println("bu fu he " + i);
            }
        }

        System.out.println(Arrays.toString(pq));
        System.out.println(Arrays.toString(qp));
    }
}

class Heap {
    private int N;
    int[] pq;
    int[] qp;
    Object[] keys;

    public Heap(int N) {
        this.N = 0;
        pq = new int[N];
        qp = new int[N];
        keys = new Object[N];
        for (int i = 0; i < N; i++) qp[i] = -1;
    }

    public void exch(int origin, int dest) {
        int swap = pq[origin];
        pq[origin] = pq[dest];
        pq[dest] = swap;
    }

    private boolean greater(int left, int right) {
        return pq[left] >= pq[right];
    }

    public void insert(int key, Object ele) {
        N++;
        qp[key] = N - 1;
        pq[N - 1] = key;
        keys[key] = ele;
        swim(N - 1);
    }

    public Object min() {
        return keys[pq[0]];
    }

    public int delMin() {
        int result = pq[0];
        exch(0, --N);
        sink(0);
        keys[pq[N]] = null;
        qp[pq[N]] = -1;
        return result;
    }

    void swim(int index) {
        while (index > 0 && greater((index - 1) / 2, index)) {
            exch(index, index = (index - 1) / 2);
        }
    }

    void sink(int index) {
        while (index * 2 < N && index >= 0) {
            int left = index * 2 + 1, i = left;
            if (left < N - 1 && greater(left, left + 1))
                i++;
            if (!greater(index, i)) {
                break;
            }
            exch(index, index = i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("索引数组打印:").append(Arrays.toString(pq))
                .append("\n")
                .append("逆序数组打印:")
                .append(Arrays.toString(qp)).toString();
    }
}