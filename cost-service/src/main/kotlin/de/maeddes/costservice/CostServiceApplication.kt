package de.maeddes.costservice

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.*
import javax.persistence.*


@SpringBootApplication
@RestController
class CostServiceApplication(val directFlightCostRepository : DirectFlightCostRepository){

    @RequestMapping("/init")
    fun initialize(): String {
        directFlightCostRepository.save(
                Arrays.asList(
                        DirectFlightCost("Lufthansa", "Berlin", "Moskau", 300),
                        DirectFlightCost("Air Berlin", "Berlin", "Prag", 277),
                        DirectFlightCost("Lufthansa", "Frankfurt", "Moskau", 420)
                )
        )
        return "Initialized"
    }

    @RequestMapping("/alt_init")
    fun alt_init(): String {
        directFlightCostRepository.save(
                        DirectFlightCost("Lufthansa", "Berlin", "Moskau", 300)
        )
        return "Initialized"
    }

    @RequestMapping("/clear")
    fun clear(): String {
        directFlightCostRepository.deleteAll()
        return "Cleared"
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(CostServiceApplication::class.java, *args)

}

@Entity
//@EntityListeners(AuditingEntityListener::class)
@Table(name = "direct_flight_costs", uniqueConstraints = arrayOf(UniqueConstraint(columnNames = arrayOf("airline", "departureAirport", "arrivalAirport"))))
data class DirectFlightCost(
        val airline: String? = null,
        val departureAirport: String? = null,
        val arrivalAirport: String? = null,
        val cost: Int? = null, @CreatedDate val instantOfCreation: Instant? = null){

    @Id
    @GeneratedValue
    val id: Long? = null

}

@RepositoryRestResource(collectionResourceRel = "directFlightCosts", path = "direct-flight-costs")
interface DirectFlightCostRepository : JpaRepository<DirectFlightCost, Long>{

    fun findByAirline(@Param("airline") airline: String): Collection<DirectFlightCost>
    fun findByAirlineAndDepartureAirportAndArrivalAirport(@Param("airline") airline: String,
                                                          @Param("departureAirport") departureAirport: String, @Param("arrivalAirport") arrivalAirport: String): DirectFlightCost
}