import java.io.File
import java.sql.DriverManager

fun main() {
    val folder = "src/main/resources/devices"
    val configurations = File(folder).list()

    val user = System.getProperty("db.user")
    val password = System.getProperty("db.password")
    val connection = DriverManager.getConnection("jdbc:postgresql://localhost/device?user=$user&password=$password")

    val existingConfigurations = mutableSetOf<String>()
    connection.createStatement().use { statement ->
        statement.executeQuery("SELECT name FROM configuration").use { rs ->
            while (rs.next()) {
                existingConfigurations += rs.getString(1)
            }
        }
    }

    val newConfigurations = configurations.orEmpty().toList().minus(existingConfigurations.map { "$it.sql" })
    val sqlOfConfigurations = newConfigurations.map { File("$folder/$it") }

    connection.createStatement().use { statement ->
        sqlOfConfigurations.forEach {
            statement.executeUpdate(it.readText())
        }
        println("Written ${newConfigurations.size} new configurations into the database!")
    }
}