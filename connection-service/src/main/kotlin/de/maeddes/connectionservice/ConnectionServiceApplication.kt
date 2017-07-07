package de.maeddes.connectionservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@SpringBootApplication
@RestController
class ConnectionServiceApplication{

}

fun main(args: Array<String>) {
    SpringApplication.run(ConnectionServiceApplication::class.java, *args)
}

@Entity
class DirectFlightConnection(
        @CreatedDate
        var instantOfCreation: Instant,
        var airline: String,
        var 
        departureAirport: Any,
        String,
        arrivalAirport: Any String, departureTime: Any Instant, arrivalTime: Any Instant,
        @Id
        var id: String
)

@RepositoryRestResource(collectionResourceRel = "directFlightConnections", path = "direct-flight-connections")
interface DirectFlightConnectionRepository : MongoRepository<DirectFlightConnection, String> {
})
