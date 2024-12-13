import java.util.*

fun main() {
    // Metro lines
    val line1 = listOf(
        "Helwan", "Ain Helwan", "Helwan University", "Wadi Hof", "Hadayek Helwan", "El-Masra", "Tura El-Esmant", "Kozzika",
        "Tura El-Balad", "Sakanat El-Maadi", "Maadi", "Hadayek El-Maadi", "Dar El-Salam", "El-Zahraa", "Mar Girgis",
        "El-Malek El-Saleh", "El-Sayeda Zeinab", "Saad Zaghloul", "Sadat (Tahrir)", "Gamal Abdel Nasser", "Ahmad Orabi",
        "El-Shohadaa (Ramses)", "Ghamra", "Demerdash", "Manshiet El-Sadr", "Kobri El-Qobba", "Hammamat El-Qobba",
        "Saray El-Qobba", "Hadayek El-Zaiton", "Helmeyet El-Zaiton", "El-Matareyya", "Ain Shams", "Ezbet El-Nakhl",
        "El-Marg", "El-Marg El-Gadidah"
    )
    val line2 = listOf(
        "El-Mounib", "Sakiat Mekky", "Um El-Masryeen", "Giza", "Faisal", "Cairo University", "Al-Bohouth", "Dokki",
        "Opera", "Sadat (Tahrir)", "Mohammad Naguib", "Attaba", "El-Shohadaa (Ramses)", "Masarra", "Rod El-Farag",
        "St. Teresa", "El-Khalfawy", "Mezallat", "Koliat El-Zeraa", "Shubra El-Khaima"
    )
    val line3 = listOf(
        "Adly Mansour", "Haykestep", "Omar Ibn El Khattab", "Qubaa", "Hesham Barakat", "El Nozha", "El Shams Club",
        "Alf Masken", "Heliopolis", "Haroun", "Al-Ahram", "Koleyet El-Banat", "Stadium", "Abbassiya", "Fair Zone",
        "Abdou Pasha", "El-Geish", "Bab El Shaariya", "Attaba", "Gamal Abdel Nasser", "Maspero", "Safaa Hijazy",
        "Kit-Kat", "Sudan", "Imbaba", "El-Bohy", "El-Qawmia", "Ring Road", "Rod El Farag"
    )
    val line3Intersection = listOf(
        "Kit-Kat", "Tawfikia", "Wadi El Nile", "Gamet El Dowal", "Boulak El Dakrour", "Cairo University"
    )

    // Create graph
    val metroGraph = createMetroGraph(
        mapOf(
            "Line 1" to line1,
            "Line 2" to line2,
            "Line 3" to line3,
            "Line 3 Intersection" to line3Intersection
        )
    )

    // User input
    println("Enter the start station:")
    val startStation = readLine()?.trim()
    println("Enter the end station:")
    val endStation = readLine()?.trim()

    if (startStation != null && endStation != null) {
        val path = bfsShortestPath(metroGraph, startStation, endStation)
        if (path != null) {
            println("Shortest path:")
            path.forEachIndexed { index, station ->
                if (index < path.size - 1) print("$station -> ") else print(station)
            }
            println("\nNumber of stops: ${path.size - 1}")
        } else {
            println("No path found between $startStation and $endStation.")
        }
    } else {
        println("Invalid input. Please restart the program.")
    }
}

// Create the metro graph as an adjacency list
fun createMetroGraph(lines: Map<String, List<String>>): MutableMap<String, MutableList<String>> {
    val graph = mutableMapOf<String, MutableList<String>>()

    for ((_, lineStations) in lines) {
        for (i in lineStations.indices) {
            graph.putIfAbsent(lineStations[i], mutableListOf())
            if (i > 0) graph[lineStations[i]]?.add(lineStations[i - 1]) // Connect to previous station
            if (i < lineStations.size - 1) graph[lineStations[i]]?.add(lineStations[i + 1]) // Connect to next station
        }
    }

    return graph
}

// BFS algorithm
fun bfsShortestPath(graph: Map<String, List<String>>, start: String, end: String): List<String>? {
    val visited = mutableSetOf<String>()
    val queue: Queue<List<String>> = LinkedList()
    queue.add(listOf(start))

    while (queue.isNotEmpty()) {
        val path = queue.poll()   //Current List
        val station = path.last() //Current Station from the current list

        if (station == end) return path
        if (!visited.contains(station)) {
            visited.add(station)
            //get the neighbor nodes of the current station
            graph[station]?.forEach { neighbor ->
                val newPath = path.toMutableList()
                newPath.add(neighbor)
                queue.add(newPath)
            }
        }
    }

    return null
}