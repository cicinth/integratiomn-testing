package steps;
import cucumber.api.java.pt.Então;
import groovy.json.internal.LazyMap;
import org.junit.Assert;
import support.RestSupport;

public class GeneralProcessSteps {
    @Então("^usuario podera ver (.*) com valor \"([^\"]*)\"$")
    public void usuarioDesejaVerConteudoComValor(String field, String value) throws Throwable {
        Assert.assertEquals(value, RestSupport.key(field));
    }
    @Então("^usuario devera ver a mensagem de \"([^\"]*)\"$")
    public void usuarioDeveraVerAMensagem(String type) throws Throwable {
        LazyMap messageJson = new LazyMap();
        messageJson.put("Salvo com sucesso", 201);
        messageJson.put("Sucesso", 200);
        messageJson.put("Deletado com sucesso", 204);
        messageJson.put("Não encontrado", 404);
        messageJson.put("Não autorizado", 401);
        messageJson.put("Não processado", 422);

        Assert.assertEquals(messageJson.get(type),RestSupport.getResponseCode());
    }
}
