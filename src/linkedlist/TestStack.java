package linkedlist;

import java.util.Stack;

public class TestStack {
public static void main(String[] args) {
	Stack<String> stack = new Stack();	
	//入棧
	stack.add("jack");
	stack.add("tom");
	stack.add("smith");
	//出棧
	while(stack.size()>0) {
		System.out.println(stack.pop());
	}
	
}
}
