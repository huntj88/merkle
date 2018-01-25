class MerkleTree(text: String) {

    init {
        val leafList = text.lines().map { Leaf(it) }
        buildTree(leafList)
    }

    private fun buildTree(leafList: List<Leaf>) {
        val treeDepth = getTreeDepth(leafList.size)

        val splitList = leafList.splitList()

        val rootNode = Computed(splitList.first, splitList.second)

        println(rootNode.getHash())
        println(rootNode)
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