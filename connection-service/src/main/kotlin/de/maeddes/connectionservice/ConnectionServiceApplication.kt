package de.maeddes.connectionservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.persistence.Entity
import javax.persistence.Id

@SpringBootApplication
@EnableMongoAuditing
@RestController
class ConnectionServiceApplication(val directFlightConnectionRepository : DirectFlightConnectionRepository){

    @RequestMapping("init")
    fun init(){
        directFlightConnectionRepository.save(
                DirectFlightConnection(
                        "Lufthansa",
                        "STR",
                        "FRA",
                        Instant.now(),
                        Instant.now().plus(1, ChronoUnit.HOURS)
                )
        )
        directFlightConnectionRepository.save(
                DirectFlightConnection(
                        "Lufthansa",
                        "HAM",
                        "STR",
                        Instant.now(),
                        Instant.now().plus(2, ChronoUnit.HOURS)
                )
        )
   }

    @RequestMapping("clear")
    fun clear() {
        directFlightConnectionRepository.deleteAll()
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(ConnectionServiceApplication::class.java, *args)
}

@Entity
class DirectFlightConnection(
        var airline: String,
        var departureAirport: String,
        var destinationAirport: String,
        var departureTime: Instant,
        var arrivalTime: Instant,
        @Id
        val id : String? = null,
        @CreatedDate
        var createdDate: Instant? = null
        )

@RepositoryRestResource(collectionResourceRel = "directFlightConnections", path = "direct-flight-connections")
interface DirectFlightConnectionRepository : MongoRepository<DirectFlightConnection, String>

