package modelo;

/**
 *
 * @author JMndz
 */
public class Pregunta {
    
    private int idPregunta;
    private String Pregunta;

    public Pregunta() {
    }

    public Pregunta(int idPregunta, String Pregunta) {
        this.idPregunta = idPregunta;
        this.Pregunta = Pregunta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return Pregunta;
    }

    public void setPregunta(String Pregunta) {
        this.Pregunta = Pregunta;
    }   
  
}
