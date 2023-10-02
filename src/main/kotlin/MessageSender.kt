import java.io.PrintWriter
import java.net.Socket

const val SERVER_IP = "localhost"
const val SERVER_PORT = 8080

fun sendMessage(message:String){
    val socket = Socket(SERVER_IP,SERVER_PORT)
    val out = PrintWriter(socket.getOutputStream())
    out.write(message)
    out.close()
    socket.close()
}

