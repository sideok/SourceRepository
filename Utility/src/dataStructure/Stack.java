package dataStructure;

/**
 * Stack sample 
 * @author SIDeok (2019.10.14)
 * @param <E>
 */
public class Stack<E> {
	
	private Object[] stack = null;
	private int top = 0;
	/**
	 * Create a stack
	 * @param len : size of stack
	 * @throws Exception 
	 */
	public Stack(int len) {
		try {
			if(len <= 0) {
				throw new Exception("size is greater than 0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.stack = new Object[len];
		this.top = 0;
	}

	/**
	 * get a top index
	 * @return top index
	 */
	public int getTop() {
		return this.top;
	}
	
	/**
	 * push a element
	 * @param element
	 * @return process result
	 */
	public boolean push(E element) {
		
		try {
			if(this.top >= this.stack.length) 
				throw new Exception("can`t be top length greater than this Stack size : " + this.stack.length);
			
			this.stack[this.top] = (Object)element;
			this.top += 1;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * pop a element
	 * @return process result
	 */
	@SuppressWarnings("unchecked")
	public E pop() {
		E rtnE = null;
		try {
			if(this.top <= 0) 
				throw new Exception("already this stack is empty.");
			
			this.top -= 1;
			rtnE = (E)this.stack[this.top];
			this.stack[this.top] = null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return rtnE;
	}
}
