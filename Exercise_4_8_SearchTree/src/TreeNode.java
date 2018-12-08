/**
 * Knoten eines bin�ren Baums mit einem int-Wert als "Nutzinformation"
 */
public class TreeNode {
    TreeNode left;
    int info;
    TreeNode right;
    
    /** erzeugt Knoten mit angegebenem Wert und linkem sowie rechtem
     *  Teilbaum
     */
    public TreeNode(TreeNode l, int v, TreeNode r) {
        info = v;
        left = l;
        right = r;
    }

    /** erzeugt Blatt mit angegebenem Wert */
    public TreeNode(int v) {
        info = v;
        left = null;
        right = null;
    }
}
