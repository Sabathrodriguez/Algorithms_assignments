package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here

    }

    private class Heap {
        private int size = 0;
        private int capacity = 10;

        private int[] items = new int[capacity];

        private int getLeftChildIndex(int parentIndex) { return 2 * parentIndex + 1; }
        private int getRightChildIndex(int parentIndex) {return 2 * parentIndex + 2; }
        private int getParentIndex(int childIndex) {return (childIndex-1)/2; }

        private boolean hasLeftChild(int index) { return size < 2 * index + 1; }
        private boolean hasRightChild(int index) { return size < 2 * index + 2; }
        private boolean hasParent(int index) { return size < (index-1)/2; }

        private int getLeftChild(int parentIndex) {return items[2 * parentIndex + 1]; }
        private int getRightChild(int parentIndex) {return items[2 * parentIndex + 2]; }
        private int getParent(int childIndex) {return items[(childIndex-1)/2]; }

        private void swap(int indexOne, int indexTwo) {
            int temp = items[indexOne];
            items[indexOne] = items[indexTwo];
            items[indexTwo] = temp;
        }
        private void ensureExtraCapacity() {
            if (size == capacity) {
                items = Arrays.copyOf(items, capacity * 2);
                capacity *= 2;
            }
        }

        public int peek() {
            if (size == 0) { throw new IllegalStateException(); }
            return items[0];
        }

        public int poll() {
            if (size == 0) { throw new IllegalStateException(); }
            int item = items[0];
            items[0] = items[size-1];
            size--;
            heapifyDown();
            return item;
        }

        public void add(int item) {
            ensureExtraCapacity();
            items[size] = item;
            size++;
            heapifyUp();
        }

        public void heapifyUp() {
            int index = size - 1;
            while (hasParent(index) && getParent(index) > items[index]) {
                swap(getParentIndex(index), index);
                index = (index-1)/2;
            }
        }

        public void heapifyDown() {
            int index = 0;
            while (hasLeftChild(index)) {
                int smallerChildIndex = getLeftChildIndex(index);
                if (hasRightChild(index) && getRightChild(index) < getLeftChild(index)) {
                    smallerChildIndex = getRightChildIndex(index);
                }
                if (items[index] < items[smallerChildIndex]) { break; }

                else {
                    swap(index, smallerChildIndex);
                }
                index = smallerChildIndex;
            }
        }
    }
}
