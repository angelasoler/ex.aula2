package acc.br.projetodois;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class WebController {
    @Autowired
    public ScoreRepository scoreRepo;

    @ResponseBody
    @GetMapping("/score")
    public Score getScore() {
        Score score;
        try {
            score = scoreRepo.findById(new Integer(1)).get();
        }
        catch(Exception e) {
            score = new Score(0,0,0);
            scoreRepo.save(score);
        }
        return score;
    }

    @GetMapping("/teste")
    public String teste(@RequestParam(name="escolha") String aEscolha, Model model) {
        String saida;
        Score score = this.getScore();

        switch(aEscolha.toLowerCase()) {
            case "papel":
                saida = "ganhou";
                score.setVitorias(score.getVitorias() + 1);
                break;
            case "tesoura":
                saida = "perdeu";
                score.setDerrotas(score.getDerrotas() + 1);
                break;
            default:
                saida = "empate";
                score.setEmpates(score.getEmpates() + 1);
                break;
        }

        scoreRepo.save(score);

        model.addAttribute("saida", saida);
        model.addAttribute("aEscolha", aEscolha);
        model.addAttribute("score", score);
        return "resultado";
    }
}