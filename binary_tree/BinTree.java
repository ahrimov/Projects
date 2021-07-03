import java.util.Scanner;

public class BinTree {

    private Node root;

    private int read_k;

    private int sum;

    BinTree(){
        root = new Node();
    }
    
    public void read() {
        read_k = 0;
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        char  ch = str.charAt(read_k);
        if (ch == '(') {
            root.data = str.charAt(++read_k);
            readBinTree(root, str);
        }
    }



    private void readBinTree(Node currentNode, String str) {
        char buf;
        buf = str.charAt(++read_k);
        if (buf == '(') {
            Node newNodeL = new Node(str.charAt(++read_k));
            currentNode.LSub = newNodeL;
            readBinTree(newNodeL, str);
            buf = str.charAt(++read_k);
            if (buf == '(') {
                Node newNodeR = new Node(str.charAt(++read_k));
                currentNode.RSub = newNodeR;
                readBinTree(newNodeR, str);
            }
        }
        else if (buf == '^') {
            buf = str.charAt(++read_k);
            if (buf == '(') {
                Node newNodeR = new Node(str.charAt(++read_k));
                currentNode.RSub = newNodeR;
                readBinTree(newNodeR, str);
            }
        }
        if (buf == ')')
            return;
        buf = str.charAt(++read_k);
    }

    public void write() {
        if (root == null) {
            System.out.println("()");
            return;
        }
        writeBinTree(root);
        System.out.print("\n");
    }

    private void writeBinTree(Node currentNode) {
        System.out.print("(" + currentNode.data);
        if (currentNode.LSub != null){
            writeBinTree(currentNode.LSub);
            if (currentNode.RSub != null)
                writeBinTree(currentNode.RSub);
        }
        else if (currentNode.RSub != null) {
            System.out.print("^");
            writeBinTree(currentNode.RSub);
        }
        System.out.print(")");
    }

    public void writeLists() {
        writeLists(root, 1);
    }

    private void writeLists(Node currentNode, int lvl) {
        if (currentNode.LSub != null)
            writeLists(currentNode.LSub, lvl + 1);
        if (currentNode.RSub != null)
            writeLists(currentNode.RSub, lvl + 1);
        if (currentNode.LSub == null && currentNode.RSub == null)
            System.out.println("level = " + lvl + " leaf data = " + currentNode.data);
    }

    public void numberNodes(final int n) {
        sum = 0;
        numberNodes(n, root, 1);
        System.out.println("Number of nodes at level " + n + " = " + sum);
    }

    private void numberNodes(final int n, Node currentNode, int currentLvl) {
        System.out.println("Level = " + currentLvl + " data = " + currentNode.data + " sum = " + sum);
        if (currentLvl == n) {
            sum++;
            return;
        }
        if (currentNode.LSub != null)
            numberNodes(n, currentNode.LSub, currentLvl + 1);
        if (currentNode.RSub != null)
            numberNodes(n, currentNode.RSub, currentLvl + 1);
    }
}
