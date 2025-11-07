package ian.choe.faketwitch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FakeTwitchApplication

fun main(args: Array<String>) {
    runApplication<FakeTwitchApplication>(*args)
}
