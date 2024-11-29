private val CAIRO_METRO = mapOf(
    "Line 1" to listOf("Helwan", "Ain Helwan", "Helwan University", "Wadi Hof", "Hadayek Helwan", "El-Masra", "Tura El-Esmant", "Kozzika", "Tura El-Balad", "Sakanat El-Maadi", "Hadayek El-Maadi", "Dar El-Salam", "El-Zahraa", "Mar Girgis", "El-Malek El-Saleh", "El-Sayeda Zeinab", "Saad Zaghloul", "Sadat (Tahrir)", "Gamal Abdel Nasser", "Ahmad Orabi", "El-Shohadaa (Ramses)", "Ghamra", "Demerdash", "Manshiet El-Sadr", "Kobri El-Qobba", "Hammamat El-Qobba", "Saray El-Qobba", "Hadayek El-Zaiton", "Helmeyet El-Zaiton", "El-Matareyya", "Ain Shams", "Ezbet El-Nakhl", "El-Marg", "El-Marg El-Gadidah"),
    "Line 2" to listOf("El-Mounib", "Sakiat Mekky", "Um El-Masryeen", "Giza", "Faisal", "Cairo University", "Al-Bohouth", "Dokki", "Opera", "Sadat (Tahrir)", "Mohammad Naguib", "Attaba", "El-Shohadaa (Ramses)", "Masarra", "Rod El-Farag", "St. Teresa", "El-Khalfawy", "Mezallat", "Koliat El-Zeraa", "Shubra El-Khaima"),
    "Line 3" to listOf("Adly Mansour", "Haykestep", "Omar Ibn El Khattab", "Qubaa", "Hesham Barakat", "El Nozha", "El Shams Club", "Alf Masken", "Heliopolis", "Haroun", "Al-Ahram", "Koleyet El-Banat", "Stadium", "Abbassiya", "Fair Zone", "Abdou Pasha", "El-Geish", "Bab El Shaariya", "Attaba", "Gamal Abdel Nasser", "Maspero", "Safaa Hijazy", "Kit-Kat", "Sudan", "Imbaba", "El-Bohy", "El-Qawmia", "Ring Road", "Rod El Farag", "Tawfikia", "Wadi El Nile", "Gamet El Dowal", "Boulak El Dakrour", "Cairo University")
)
fun main() {

    println("Welcome to Cairo Metro")
    print("Please enter your start station: ")
    val startStation = readLine()?.trim() ?: ""
    print("Please enter your arrival station: ")
    val arrivalStation = readLine()?.trim() ?: ""

    // Find the line for both start and arrival stations
    val startLine = findLine(CAIRO_METRO, startStation)
    val arrivalLine = findLine(CAIRO_METRO, arrivalStation)

    if (startLine == null || arrivalLine == null) {
        println("One or both stations are invalid or not part of the Cairo Metro network.")
        return
    }

    if (startLine != arrivalLine) {
        println("Stations must be on the same metro line.")
        return
    }

    val lineStations = CAIRO_METRO[startLine]!!
    val startIndex = lineStations.indexOf(startStation)
    val arrivalIndex = lineStations.indexOf(arrivalStation)

    if (startIndex == -1 || arrivalIndex == -1) {
        println("Invalid stations. Please try again.")
        return
    }

    // Determine direction
    val direction = if (startIndex < arrivalIndex) {
        "Towards ${lineStations.last()}"
    } else {
        "Towards ${lineStations.first()}"
    }

    // Calculate the number of stations between start and arrival
    val numberOfStations = Math.abs(startIndex - arrivalIndex)

    // Print the route information
    println("\nRoute Information:")
    println("Line: $startLine")
    println("Direction: $direction")
    println("Number of stations: $numberOfStations")
    println("Ticket Price: ${calcTicket(numberOfStations)}")
    println("Stations: ${lineStations.subList(Math.min(startIndex, arrivalIndex), Math.max(startIndex, arrivalIndex) + 1).joinToString(" -> ")}")
}

fun findLine(metro: Map<String, List<String>>, station: String): String? {
    for ((line, stations) in metro) {
        if (stations.contains(station)) {
            return line
        }
    }
    return null
}

fun calcTicket(numberOfStations: Int): Double{
    return when(Math.abs(numberOfStations)){
        in 1..9 -> 8.0
        in 10..16 ->10.0
        in 17..23 -> 15.0
        else -> 20.0
    }
}