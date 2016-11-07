package modelo;

/**
 *
 * @author JMndz
 */
public class Respuesta {
    
    private int idUsuario;
    private int idPregunta;
    private String respuesta;

    public Respuesta() {
    }

    public Respuesta(int idUsuario, int idPregunta, String respuesta) {
        this.idUsuario = idUsuario;
        this.idPregunta = idPregunta;
        this.respuesta = respuesta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    
}
