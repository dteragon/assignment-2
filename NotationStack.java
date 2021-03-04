import java.util.ArrayList;

public class NotationStack<E> implements StackInterface<E> {

	private E[] stack;
	private int lastElem;
	
	public NotationStack(int x) {
		
		stack = (E[]) new Object[x];
		lastElem = -1;
	}
	
	public NotationStack() {
		this(2);
	}
	
	public boolean isEmpty() {
		return lastElem == -1;
	}

	public boolean isFull() {
		return lastElem == stack.length - 1;
	}

	public E pop() throws StackUnderflowException {
		
		if (isEmpty())
			throw new StackUnderflowException();
		return stack[lastElem--];
		
	}

	public E top() throws StackUnderflowException {
		
		if (isEmpty())
			throw new StackUnderflowException();
		return stack[lastElem];
		
	}

	public int size() {
		return lastElem + 1;
	}

	public boolean push(E e) throws StackOverflowException {
		
		if(isFull())
			throw new StackOverflowException();
		
		stack[++lastElem] = e;
		return true;
		
	}
	
	public String toString() {
		return toString("");
	}

	public String toString(String delimiter) {
		
		String toRet = stack[0] + "";
		for (int i = 1; i <= lastElem; i++)
			toRet += delimiter + stack[i];
		
		return toRet;
	}

	public void fill(ArrayList<E> list) {
		for (E next : list)
			push(next);
	}

}
