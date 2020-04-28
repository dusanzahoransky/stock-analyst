import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun main() {
    println(LocalDate.now().minusYears(2).atStartOfDay(ZoneId.of("UTC")).toEpochSecond())
    println(LocalDate.now().atStartOfDay(ZoneId.of("UTC")).toEpochSecond())

    println(Instant.ofEpochSecond(1524663000))
    println(Instant.ofEpochSecond(1587735000))

    println(ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().minusDays(2)))
    println(ChronoUnit.DAYS.between(LocalDate.now().minusDays(2), LocalDate.now()))
}