package ule.ed.recursivelist;


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

	public T getElemPosRecursive(Node<T> node, int position) {
		if(position == 1) {
			return node.elem;
		} else {
			return getElemPosRecursive(node.next, position - 1);
		}
	}



	@Override
	public int getPosFirst(T elem) {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public int getPosLast(T elem) {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public T removelast() throws EmptyCollectionException {
		// TODO RECURSIVAMENTE
		return null;
	}



	

	@Override
	public int removeLastElem(T elem) {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public EDList<T> reverse() {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public int removeOddElements(){
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public int removeConsecDuplicates() {
		// TODO RECURSIVAMENTE
		return 0;
	}



	@Override
	public String toSringExceptFromUntilReverse(int from, int until) {
		// TODO RECURSIVAMENTE
		return null;
	}



	@Override
	public boolean lengthEqualsTo(int n) {
		// TODO RECURSIVAMENTE
		return false;
	}

	@Override
	public int removeEvenElements() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int removeFirstElem(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "(" + toStringRecursive(front) + " )";
	}

	private String toStringRecursive(Node<T> node) {
		if(isEmpty()) { 
			return " ";
		} else if(node == null) {
			return "";
		} else if (node.next == null) {
			return node.elem.toString();
		} else {
			return node.elem.toString() + " " + toStringRecursive(node.next);
		}
	}

	
	
}
