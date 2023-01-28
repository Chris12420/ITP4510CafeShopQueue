public class ListQueue extends LinkedList {

	public ListQueue() {
		super();
	}

	public boolean empty() {
		return isEmpty();
	}

	public void enqueue(Object item) {
		addToTail(item);
	}

	public Object dequeue() {
		return removeFromHead();
	}

	public Object front() {
		Object item = removeFromHead();
		addToHead(item);
		return item;
	}



} // class ListQueue