package ian.choe.faketwitch

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping


@Controller
class StreamPageController {
    @GetMapping("/")
    fun index(): String {
        return "index" // src/main/resources/templates/index.html (Thymeleaf)
    }
}
