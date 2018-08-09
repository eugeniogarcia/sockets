package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class Controlador {

	@MessageMapping("/destinoA")
	@SendTo("/canalA/topico1")
	public Respuesta destinoA(Mensaje message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Respuesta(HtmlUtils.htmlEscape(message.getTexto()) + "!","destinoA");
	}

	@MessageMapping("/destinoAA")
	@SendTo("/canalA/topico1")
	public Respuesta destinoAA(Mensaje message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Respuesta(HtmlUtils.htmlEscape(message.getTexto()) + "!","destinoAA");
	}

	@MessageMapping("/destinoAAA")
	@SendTo("/canalA/topico2")
	public Respuesta destinoAAA(Mensaje message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Respuesta(HtmlUtils.htmlEscape(message.getTexto()) + "!","destinoAAA");
	}

	@MessageMapping("/destinoB")
	@SendTo("/canalB/topico1")
	public Respuesta destinoB(Mensaje message) throws Exception {
		Thread.sleep(1000); // simulated delay
		return new Respuesta(HtmlUtils.htmlEscape(message.getTexto()) + "!","destinoB");
	}
}
