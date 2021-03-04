import java.util.ArrayList;

public class NotationQueue<E> implements QueueInterface<E> {
	
	private E[] queue;
	private int lastElement;

	public NotationQueue(int x) {
		queue = (E[]) new Object[x];
		lastElement = -1;
	}

	public NotationQueue() {
		this(2);
	}

	public boolean isEmpty() {
		return lastElement == -1;
	}

	public boolean isFull() {
		return lastElement == queue.length - 1;
	}

	public E dequeue() throws QueueUnderflowException {
		
		if (isEmpty())
			throw new QueueUnderflowException();
		
		E first = queue[0];
		for (int i = 0; i < lastElement; i++)
			queue[i] = queue[i + 1];
		
		lastElement--;
		return first;
		
	}

	public int size() {
		return lastElement + 1;
	}

	public boolean enqueue(E e) throws QueueOverflowException {
		
		if (isFull())
			throw new QueueOverflowException();
		
		queue[++lastElement] = e;
		return true;
		
	}
	
	public String toString() {
		return toString("");
	}

	public String toString(String delimiter) {
		
		String toRet = queue[0] + "";
		for (int i = 1; i <= lastElement; i++)
			toRet += delimiter + queue[i];
		
		return toRet;
	}

	public void fill(ArrayList<E> list) {
		for (E next : list)
			enqueue(next);
	}

}
