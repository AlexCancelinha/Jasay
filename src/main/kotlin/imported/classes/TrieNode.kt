package imported.classes

class TrieNode() {

    data class Node(var word: String? = null, val childNodes: MutableMap<Char, Node> = mutableMapOf())

    private val root = Node()

    constructor(list: List<String>) : this() {
        for (str in list)
            insert(str)
    }

    constructor(vararg list: String) : this() {
        for (str in list)
            insert(str)
    }

    fun insert(word: String) {
        var currentNode = root
        for (char in word) {
            currentNode.childNodes[char] ?: run { currentNode.childNodes[char] = Node() }
            currentNode = currentNode.childNodes[char]!!
        }
        currentNode.word = word
    }

    fun search(word: String): Boolean = getNode(word).word != null

    fun startsWith(word: String): Boolean {
        val currentNode = getNode(word)
        return currentNode != root && currentNode.word == null
    }

    fun getpossibilities(prefix: String): List<String>{
        val currentNode = getNode(prefix)
        val result = mutableListOf<String>()
        if(currentNode != root) {
            currentNode.word?.let { result += it }
            applyNode(currentNode) { result += it }
        }
        return result
    }

    // PRIVATE METHODS

    private fun getNode(word: String): Node {
        var currentNode = root
        for (char in word) {
            currentNode.childNodes[char] ?: return root
            currentNode = currentNode.childNodes[char]!!
        }
        return currentNode
    }

    private fun applyNode(node: Node, func: (String) -> Unit){
        for (child in node.childNodes){
            child.value.word?.let { func(it) }
            applyNode(child.value, func)
        }
    }

    fun printWords(): String {
        var result = ""
        applyNode(root) { result += "$it\n" }
        return result
    }

    fun printTree(): String {
        var result = ""
        var deep = 0

        fun aux(node: Node) : String{
            deep++
            for (child in node.childNodes){
                result += if(child.value.word == null)
                    "\t".repeat(deep) + child.key + "\n"
                else "\t".repeat(deep) + child.key + "-" + child.value.word + "\n"
                aux(child.value)
            }
            deep--
            return result
        }
        return aux(root)
    }
}