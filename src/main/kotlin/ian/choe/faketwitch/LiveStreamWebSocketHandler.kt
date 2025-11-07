import org.springframework.stereotype.Component
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.BinaryWebSocketHandler
import java.util.*

@Component
class LiveStreamWebSocketHandler : BinaryWebSocketHandler() {
    private val sessions: MutableList<WebSocketSession> = Collections.synchronizedList(ArrayList())

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }

    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        synchronized(sessions) {
            for (s in sessions) {
                if (s.isOpen && s !== session) {
                    try {
                        s.sendMessage(BinaryMessage(message.payload))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}