package hello;

public class Respuesta {

	private String contenido;
	private String desde;

	public Respuesta() {
	}

	public Respuesta(String contenido,String desde) {
		this.contenido= contenido;
		this.desde= desde;
	}

	public String getContent() {
		return "Desde "+desde+" -> "+contenido;
	}

}
