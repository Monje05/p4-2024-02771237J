package ule.ed.recursivelist;

import java.util.NoSuchElementException;

public class LinkedEDList<T> implements EDList<T> {

	//	referencia al primer  de la lista
	private Node<T> front;

	

	private class Node<T> {

		Node(T element) {
			this.elem = element;
			this.next = null;
		}

		T elem;

		Node<T> next;
	}

	@Override
	public boolean isEmpty() {
		if(front == null) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		return sizeRecursive(front);
	}

	private int sizeRecursive(Node<T> node) {
		if(node == null) {
			return 0;
		} else {
			return 1 + sizeRecursive(node.next);
		}
	}


	@Override
	public void addLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		front = addLastRecursive(front, elem);
		
	}

	private Node<T> addLastRecursive(Node<T> node, T elem) {
		if(node == null) {
			return new Node<T>(elem);
		} else {
			node.next = addLastRecursive(node.next, elem);
			return node;
		}
	}

	
	@Override
	public void addPos(T elem, int position) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(position <= 0) {
			throw new IllegalArgumentException();
		}
		if(position > size()) {
			addLast(elem);
		} else {
			front = addPosRecursive(front, elem, position);
		}
		
	}

	private Node<T> addPosRecursive(Node<T> node, T elem, int position) {
		if(position == 1) {
			Node<T> newNode = new Node<T>(elem);
			newNode.next = node;
			return newNode;
		} else {
			node.next = addPosRecursive(node.next, elem, position - 1);
			return node;
		}
	}


	@Override
	public T getElemPos(int position) {
		if(position < 1 || position > size()) {
			throw new IllegalArgumentException();
		}
		return getElemPosRecursive(front, position);
	}

	private T getElemPosRecursive(Node<T> node, int position) {
		if(position == 1) {
			return node.elem;
		} else {
			return getElemPosRecursive(node.next, position - 1);
		}
	}



	@Override
	public int getPosFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int position = getPosFirstRecursive(front, elem, 1);
		if(position == -1) {
			throw new NoSuchElementException();
		}
		return position;
	}

	private int getPosFirstRecursive(Node<T> node, T elem, int position) {
		if(node == null) {
			return -1;
		}
		if(node.elem.equals(elem)) {
			return position;
		} 
		return getPosFirstRecursive(node.next, elem, position + 1);
	}



	@Override
	public int getPosLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int position = getPosLastRecursive(front, elem, 1, -1);
		if(position == -1) {
			throw new NoSuchElementException();
		}
		return position;
	}

	private int getPosLastRecursive(Node<T> node, T elem, int current, int lastPosition) {
		if(node == null) {
			return lastPosition;
		}
		if(node.elem.equals(elem)) {
			lastPosition = current;
		}
		return getPosLastRecursive(node.next, elem, current + 1, lastPosition);
	}



	@Override
	public T removelast() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("La lista esta vacía.");
		}
		T removeElement;
		if(front.next == null) {
			removeElement = front.elem;
			front = null;
		} else {
			removeElement = removeLastRecursive(front);
		}
		return removeElement;
	}

	private T removeLastRecursive(Node<T> node) {
		T removeElement;
		if(node.next.next == null) {
			removeElement = node.next.elem;
			node.next = null;
			return removeElement;
		} else {
			 removeElement = removeLastRecursive(node.next);
		}
		return removeElement;
	}


	@Override
	public int removeLastElem(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		int size = size();
		if(size == 1 && front.elem.equals(elem)) {
			front = null;
			return 1;
		} else {
			int position = removeLastElemRecursive(front, elem, size, 1);
			if(position == -1) {
				throw new NoSuchElementException();
			}
			return position;
		}
	}

	private int removeLastElemRecursive(Node<T> node, T elem , int size, int current) {
		if(node == null) {
			return -1;
		}
		if(node.elem.equals(elem)) {
			if(current == size) {
				if(current == 1) {
					front = node.next;
				} else {
					Node<T> prev = getNodeAtPosition(front, current - 1);
					prev.next = node.next;
				}
				return current;
			} 
		}
		int result = removeLastElemRecursive(node.next, elem, size, current + 1);
		if(result == -1)  {
			return -1;
		}
		return result;
	}

	private Node<T> getNodeAtPosition(Node<T> node, int position) {
		if(position < 1 || position > size()) {
			throw new IllegalArgumentException();
		}
		for(int i = 1; i < position; i++) {
			node = node.next;
		}
		return node;
	}


	@Override
	public EDList<T> reverse() {
		EDList<T> reversedList = new LinkedEDList<>();
		reverseRecursive(front, reversedList);
		return reversedList;
	}

	private void reverseRecursive(Node<T> node, EDList<T> reversedList) {
		if(node == null) {
			return;
		}
		reverseRecursive(node.next, reversedList);
		reversedList.addLast(node.elem);
 	}



	@Override
	public int removeOddElements(){
		int count = removeOddElementsRecursive(front, 1);
		return count;
	}

	private int removeOddElementsRecursive(Node<T> node, int position) {
		if(node == null) {
			return 0;
		}
		int count = 0;
		if(position % 2 != 0) {
			removeFirstElem(node.elem);
			count++;
		}
		count += removeOddElementsRecursive(node.next, position + 1);
		return count;
	}

	@Override
	public int removeConsecDuplicates() {
		int count = removeConsecDuplicatesRecursice(front);
		return count;
	}

	private int removeConsecDuplicatesRecursice(Node<T> node) {
		if(node == null || node.next == null) {
			return 0;
		}
		int count = 0;
		if(node.next.elem.equals(node.elem)) {
			node.next = node.next.next;
			count++;
			count += removeConsecDuplicatesRecursice(node);
		} else {
			count += removeConsecDuplicatesRecursice(node.next);
		}
		return count;
	}



	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		if(from <= 0 || until <= 0 || from < until) {
			throw new IllegalArgumentException();
		}
		if(from > size()) {
			from = size();
		}
		return "(" + toSringExceptFromUntilReverseRecursive(front, 1, from, until)+ ")";
	}

	private String toSringExceptFromUntilReverseRecursive(Node<T> node,int current, int from, int until) {
		if (node == null || current >= until) {
			return "";
		}
		String result = "";
		result += toSringExceptFromUntilReverseRecursive(node.next, current + 1, from, until);
	
		if (current < from) {
			result += node.elem + " ";
		}
		return result;
	}



	@Override
	public boolean lengthEqualsTo(int n) {
		if(n < size()) {
			return false;
		}
		return lengthEqualsToRecursive(front, n, 0);
	}

	private boolean lengthEqualsToRecursive(Node<T> node, int target, int current) {
		if(target == current) {
			return true;
		}
		if(node == null) {
			return false;
		}
		return lengthEqualsToRecursive(node.next, target, current + 1);
	}

	@Override
	public int removeEvenElements() {
		int count = removeEvenElementsRecursive(front, 1);
		return count;
	}

	private int removeEvenElementsRecursive(Node<T> node, int position) {
		if(node == null) {
			return 0;
		}
		int count = 0;
		if(position % 2 == 0) {
			removeFirstElem(node.elem);
			count++;
		}
		count += removeEvenElementsRecursive(node.next, position + 1);
		return count;
	}


	@Override
	public int removeFirstElem(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int position = removeFirstElemRecursive(front, elem, 1);
		if(position == -1) {
			throw new NoSuchElementException();
		}
		return position;
	}

	private int removeFirstElemRecursive(Node<T> node, T elem, int current) {
		if(node == null) {
			return -1;
		}
		if(node.elem.equals(elem)) {
			if(current == 1) {
				front = node.next;
				return 1;
			}else {
				Node<T> prev = getNodeAtPosition(front, current - 1);
				prev.next = node.next;
				return current;
			}
		}
		return removeFirstElemRecursive(node.next, elem, current + 1);
	}

	@Override
	public boolean addBefore(T elem, T target) {
		if(elem == null || target == null) {
			throw new NullPointerException();
		}
		return addBeforeRecursive(null, front, elem, target);
	}

	private boolean addBeforeRecursive(Node<T> prev, Node<T> node, T elem, T target) {
		if(node == null) {
			addPos(elem, 1);
			return false;
		}
		if(node.elem.equals(target)) {
			Node<T> nuevo = new Node<T>(elem);
			nuevo.next = node;
			if(prev != null) {
				prev.next = nuevo;
			} else {
				nuevo.next = front;
				front = nuevo;
			}
			return true;
		}
		return addBeforeRecursive(node, node.next, elem, target);
	}

	@Override
	public String toString() {
		if(isEmpty()) {
			return "(" + toStringRecursive(front) + ")";
		}
		return "(" + toStringRecursive(front) + " )";
	}

	private String toStringRecursive(Node<T> node) {
		if(isEmpty()) { 
			return "";
		} else if(node == null) {
			return "";
		} else if (node.next == null) {
			return node.elem.toString();
		} else {
			return node.elem.toString() + " " + toStringRecursive(node.next);
		}
	}

	
	
}
