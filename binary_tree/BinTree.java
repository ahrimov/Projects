import java.util.Scanner;

public class BinTree {

    private Node[] arr;
    private int sizeArray = 0;
    private int root;

    private int read_k;
    private int read_index;

    private int sum;

    BinTree(){
        arr = new Node[0];
    }

    private void resize(int newSize) {
        Node[] newArr = new Node[newSize];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        sizeArray = newSize;
        arr = newArr;
        arr[newSize - 1] = new Node();
    }

    public void read() {
        read_index = 0;
        read_k = 0;
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        char  ch = str.charAt(read_k);
        if (ch == '(')
            root = readBinTree(ch, str);
    }



    private int readBinTree(char prev, String str) {
        if (prev != '(')
            return -1;
        char buf;
        resize(sizeArray + 1);
        int currentIndex = read_index;
        arr[currentIndex].data = str.charAt(++read_k);
        buf = str.charAt(++read_k);
        if (buf == '(') {
            read_index ++;
            arr[currentIndex].LSub = readBinTree(buf, str);
            buf = str.charAt(++read_k);
            if (buf == '(') {
                read_index++;
                arr[currentIndex].RSub = readBinTree(buf, str);
            }
        }
        else if (buf == '^') {
            buf = str.charAt(++read_k);
            if (buf == '(') {
                read_index++;
                arr[currentIndex].RSub = readBinTree(buf, str);
            }
        }
        if (buf == ')')
            return currentIndex;
        buf = str.charAt(++read_k);
        return currentIndex;
    }

    public void write() {
        if (sizeArray == 0) {
            System.out.println("()");
            return;
        }
        writeBinTree(root);
        System.out.print("\n");
    }

    private void writeBinTree(int i) {
        System.out.print("(" + arr[i].data);
        if (arr[i].LSub != 0){
            writeBinTree(arr[i].LSub);
            if (arr[i].RSub != 0)
                writeBinTree(arr[i].RSub);
        }
        else if (arr[i].RSub != 0) {
            System.out.print("^");
            writeBinTree(arr[i].RSub);
        }
        System.out.print(")");
    }

    public void writeLists() {
        int i = root;
        writeLists(i, 1);
    }

    private void writeLists(int i, int lvl) {
        if (arr[i].LSub != 0)
            writeLists(arr[i].LSub, lvl + 1);
        if (arr[i].RSub != 0)
            writeLists(arr[i].RSub, lvl + 1);
        if (arr[i].LSub == 0 && arr[i].RSub == 0)
            System.out.println("level = " + lvl + " leaf data = " + arr[i].data);
    }

    public void numberNodes(final int n) {
        sum = 0;
        numberNodes(n, root, 1);
        System.out.println( "Number of nodes at level " + n + " = " + sum);
    }

    private void numberNodes(final int n, int i, int currentLvl) {
        System.out.println("Level = " + currentLvl + " data = " + arr[i].data + " sum = " + sum);
        if (currentLvl == n) {
            sum++;
            return;
        }
        if (arr[i].LSub != 0)
            numberNodes(n, arr[i].LSub, currentLvl + 1);
        if (arr[i].RSub != 0)
            numberNodes(n, arr[i].RSub, currentLvl + 1);
    }
}
