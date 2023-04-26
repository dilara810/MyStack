/* Name    : Dilara
 Surname   : TOSUN
Student ID : 20050111041*/
class Node {
    private int data;
    public Node next;
    public Node(int dd) {
        data = dd;
        next =null;
    }

    public int getData(){return data;}
    public Node getNext(){return next;}
    public void setNext(Node node){next = node;}
    public void setData(int d){data = d;}

}

class MyStack {

    private Node first;
    private int len=0;

    public int getLen(){return this.len;}

    public MyStack() {
        first = null;
    }

    public void push(int dd) {
        Node temp = new Node(dd);
        if (first != null) {
            temp.next = first;
        }
        first = temp;
        len++;

    }

    public int peek() {
        Node temp = first;

        if (temp == null) {
            throw new IllegalStateException("Stack is empty");
        }

        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.getData();
    }

    public int pop() {
        if (first == null) {
            throw new IllegalStateException("Stack is empty");
        } else {
            int data = first.getData();
            first = first.next;
            return data;
        }
    }

    public boolean isNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String evaluate(String command) {
        StringBuilder sb = new StringBuilder(" ");
        String[] commands = command.split(" ");
        int len = commands.length, i = 0;
        while (i < len) {
            if (isNumber(commands[i])) {
                int num = Integer.parseInt(commands[i]);
                push(num);
            } else if (commands[i].equals("+")) {
                if (getLen() >= 2) {
                    int num1 = pop();
                    int num2 = pop();
                    push(num1 + num2);
                } else {
                    sb.append("Not enough integers in the stack\n");
                }
            }else if (commands[i].equals("-")) {
                if (getLen() >= 2) {
                    int num1 = pop();
                    int num2 = pop();
                    push(num2 - num1);
                } else {
                    sb.append("Not enough integers in the stack\n");
                }
            }else if (commands[i].equals("/")) {
                if (getLen() >= 2) {
                    int num1 = pop();
                    int num2 = pop();
                    if (num1 == 0) {
                        sb.append("error\n");
                        push(num2);
                        push(num1);
                    } else {
                        push(num2 / num1);
                    }
                } else {
                    sb.append("Not enough integers in the stack\n");
                }
            } else if (commands[i].equals("*")) {
                if (getLen() >= 2) {
                    int num1 = pop();
                    int num2 = pop();
                    push(num1 * num2);
                } else {
                    sb.append("Not enough integers in the stack");
                }
            } else if (commands[i].equals("pop")) {
                int num = pop();
                sb.append(num).append("\n");
            } else if (commands[i].equals("exit")) {
                sb.append("$\n");
            } else if (commands[i].equals("print")) {
                sb.append("[");
                Node temp = first;
                while (temp != null) {
                    sb.append(temp.getData());
                    if (temp.next != null) {
                        sb.append(", ");
                    }
                    temp = temp.next;
                }
                sb.append("]\n");
            } else if(commands[i].equals("peek")){
                int num = peek();
                sb.append(num).append("\n");
            } else if (commands[i].equals("%")) {
                if (getLen() >= 2) {
                    int num1 = pop();
                    int num2 = pop();
                    if (num1 == 0) {
                        sb.append("error\n");
                        push(num2);
                        push(num1);
                    } else {
                        push(num2 % num1);
                    }
                } else {
                    sb.append("Not enough integers in the stack\n");
                }
            } else {
                sb.append("Invalid operator\n");
            }
            i++;
        }
        return sb.toString();
    }
}