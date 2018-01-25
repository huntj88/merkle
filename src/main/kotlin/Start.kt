import java.io.File

fun main(cows: Array<String>) {


    val jsonFile = File("src/main/resources/data.json")
    val text = jsonFile.readText()

    MerkleTree(text)
}