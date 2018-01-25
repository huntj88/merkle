import java.io.File

fun main(cows: Array<String>) {


    val jsonFile1 = File("src/main/resources/data.json")
    val text1 = jsonFile1.readText()
    val tree1 = MerkleTree(text1)

    val jsonFile2 = File("src/main/resources/dataChanged.json")
    val text2 = jsonFile2.readText()
    val tree2 = MerkleTree(text2)

    tree1.compareTrees(tree2)

    println()
    println()
    println()
    tree2.compareTrees(tree1)
}