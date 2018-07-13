package worldcupapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expresssion
{
    public static class Node
    {
        String operation = "";
        double value = 0;
        Node leftNode = null, rightNode = null;

        public Node(String operation, double value, Node leftNode, Node rightNode)
        {
            this.operation = operation;
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        public String getOperation()
        {
            return operation;
        }


        public Node getLeftNode()
        {
            return leftNode;
        }

        public Node getRightNode()
        {
            return rightNode;
        }
    }

    public static final String[] ops = {"+", "/", "*"};


    public static int evaluate(String string)
    {
        int total = 0;

        List<Node> ops = new ArrayList<>();
        String nums[] = null;
        String cop = "";
        if (string.contains("+"))
        {
            cop = "+";
            nums = string.split("\\" + cop);
        }

        Node num1 = new Node("", Double.parseDouble(nums[0]), null, null), num2 =
                new Node("", Double.parseDouble(nums[1]), null, null);
        Node op = new Node(cop, 0, num1, num2);
        total += evaluate(op);
        return total;
    }

    private static String getOp(String string)
    {
        if (string.contains("+")) return "+";
        return null;
    }

    static boolean containsOp(String string)
    {
        if (!(Arrays.stream(ops).parallel().anyMatch(string::contains))) return true;
        return false;
    }

    private static int evaluate(Node node)
    {

        String op = node.operation;
        if ("+".equals(node.operation))
        {
            return (int) (node.leftNode.value + node.rightNode.value);
        }
        else if ("-".equals(node.operation))
        {
            return evaluate(node.leftNode) - evaluate(node.rightNode);
        }
        else if ("*".equals(node.operation))
        {
            return (int) (node.leftNode.value * node.rightNode.value);
        }
        else if ("/".equals(node.operation))
        {
            return evaluate(node.leftNode) / evaluate(node.rightNode);
        }
        else if ("^".equals(node.operation))
            return (int) Math.pow(node.leftNode.value, node.rightNode.value);
        else
        {
            throw new IllegalArgumentException("Unkown Operation!");
        }
    }
}