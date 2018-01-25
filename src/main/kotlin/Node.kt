interface Node {

    fun getHash(): String
}

class Leaf(private val lineOfText: String) : Node {
    override fun getHash(): String {
        return lineOfText.hash()
    }

    override fun toString(): String {
        return lineOfText
    }

}

class Computed(leftLeaves: List<Leaf>, rightLeaves: List<Leaf>) : Node {

    private val leftChildNode: Node
    private val rightChildNode: Node

    init {
        leftChildNode = generateChildren(leftLeaves)
        rightChildNode = generateChildren(rightLeaves)
    }

    private fun generateChildren(leaves: List<Leaf>): Node {
        return if (leaves.size != 1) {
            val splitForRightNode = leaves.splitList()
            Computed(splitForRightNode.first, splitForRightNode.second)
        } else {
            leaves.first()
        }
    }

    override fun getHash(): String {
        return (leftChildNode.getHash() + rightChildNode.getHash()).hash()
    }

    override fun toString(): String {
        return "$leftChildNode\n$rightChildNode"
    }
}

fun List<Leaf>.splitList(): Pair<List<Leaf>, List<Leaf>> {
    val leftList = subList(0, size / 2)
    val rightList = subList(size / 2, size)
    return Pair(leftList, rightList)
}

fun String.hash(): String {
    return HashUtils.sha1(this)
}