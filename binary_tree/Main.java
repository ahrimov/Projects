
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        BinTree binT = new BinTree();
        System.out.println("Enter a data:");
        binT.read();
        binT.write();
        System.out.println("Leafs data:");
        binT.writeLists();
        System.out.println("Enter a level");
        int n;
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        binT.numberNodes(n);
    }
}
