class MerkleTree(text: String) {

    private val rootNode: Node

    init {
        val leafList = text.lines().map { Leaf(it) }
        rootNode = buildTree(leafList)
    }

    private fun buildTree(leafList: List<Leaf>): Node {
        val treeDepth = getTreeDepth(leafList.size)

        val splitList = leafList.splitList()
        return Computed(splitList.first, splitList.second)
    }

    fun compareTrees(merkleTree: MerkleTree) {
        if(this.rootNode.getHash() == merkleTree.rootNode.getHash()) {
            print("same")
        } else {
            this.rootNode.compareChildren(merkleTree.rootNode)
        }
    }

    private fun getTreeDepth(numberOfLines: Int): Int {
        val depthIncreaseLeafIncrements = mutableListOf(1)

        if (numberOfLines > 1)
            depthIncreaseLeafIncrements.add(1)

        while (numberOfLines > depthIncreaseLeafIncrements.reduce { x, y -> x + y }) {
            depthIncreaseLeafIncrements.add(depthIncreaseLeafIncrements.last() * 2)
        }

        return depthIncreaseLeafIncrements.size
    }

}