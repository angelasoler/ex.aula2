package acc.br.projetodois.model;
import org.springframework.data.annotation.Id;

public class Score {

    @Id
    private Integer id;

    private int vitorias, derrotas, empates;

    public Score(int vitorias, int derrotas, int empates) {
        super();
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    public Integer getId() {
        return id;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public void resetScore() {
        this.vitorias = 0;
        this.derrotas = 0;
        this.empates = 0;
    }
}
