interface Node {

    fun getHash(): String
    fun compareChildren(nodeFromOtherTree: Node)
}

class Leaf(private val lineOfText: String) : Node {
    override fun getHash(): String {
        return lineOfText.hash()
    }

    override fun compareChildren(nodeFromOtherTree: Node) {
        if(nodeFromOtherTree is Leaf) {
            if(this.lineOfText != nodeFromOtherTree.lineOfText)
                println("line of text different: ${nodeFromOtherTree.lineOfText} vs ${this.lineOfText}")

        } else {
            println("other node is not a leaf")
            println(nodeFromOtherTree)
        }
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

    override fun compareChildren(nodeFromOtherTree: Node) {

        if(nodeFromOtherTree is Computed) {
            if (this.leftChildNode.getHash() != nodeFromOtherTree.leftChildNode.getHash()) {
                println("left node different")
                this.leftChildNode.compareChildren(nodeFromOtherTree.leftChildNode)
            }

            if (this.rightChildNode.getHash() != nodeFromOtherTree.rightChildNode.getHash()) {
                println("right node different")
                this.rightChildNode.compareChildren(nodeFromOtherTree.rightChildNode)
            }

        } else {
            println("other node is a leaf")
            println(nodeFromOtherTree)
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