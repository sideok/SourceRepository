package dataStructure;

import java.lang.reflect.Array;
/**
 * Stack sample 
 * @author SIDeok (2019.10.14)
 * @param <E>
 */
public class Stack<E> {
	
	private E[] stack = null;
	private int top = 0;
	/**
	 * Create a stack
	 * @param len : size of stack
	 * @param cls : type of Element
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public Stack(int len, Class<E> cls) {
		try {
			if(len <= 0) {
				throw new Exception("size is greater than 0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.stack = (E[]) Array.newInstance(cls, len);
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
			
			this.stack[this.top] = element;
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
	public E pop() {
		E rtnE = null;
		try {
			if(this.top <= 0) 
				throw new Exception("already this stack is empty.");
			
			this.top -= 1;
			rtnE = this.stack[this.top];
			this.stack[this.top] = null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return rtnE;
	}
}
