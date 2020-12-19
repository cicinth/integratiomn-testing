package steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import definition.Process;

import support.RestSupport;

public class CrudProcessSteps {
    @Dado("^usuario gostaria de salvar um processo$")
    public void userGostariaDeSalvarUmProcesso() throws Throwable {
        Process.clearFields();
    }
    @E("^usuario informa (.*) com valor igual \"([^\"]*)\"$")
    public void useroInformaCampoComValorIgual(String field, String value) {
        Process.addFields(field, value);
    }
    @Quando("^usuario clica no botão salvar$")
    public void userClicaBotaoSalvar() throws Throwable {
        RestSupport.executePost(Process.getEndpoint(), Process.getFields());
        Process.setLastProcess(RestSupport.key("id").toString());
        Process.clearFields();
    }
    @E("^usuario deseja ver as informacoes")
    public void userDesejaVerAsInformacoes() throws Throwable {
        RestSupport.executeGet(Process.getEndpoint() + Process.getLastProcess() + ".json");
    }
    @E("^usuario clica outra vez no botao salvar")
    public void userClicaNoBotaoSalvarOutraVez() throws Throwable {
        RestSupport.executePut(Process.getEndpoint() + Process.getLastProcess() + ".json", Process.getFields());
    }
    @E("^usuario clica em deletar$")
    public void userClicaNoBotaoDeletar() throws Throwable {
        RestSupport.executeDelete(Process.getEndpoint() + Process.getLastProcess() + ".json");
    }
    @Dado("^usuario deseja ver as informacoes do processo especifico \"([^\"]*)\"$")
    public void userDesejaVerAsInformacoesDoProcessoEspecifico(String idProcesso) throws Throwable {
        Process.setIdProcess(idProcesso);
    }

    @Quando("^usuario clicar no botao  exibir")
    public void UserClicarNoBoatoExibir() {
        RestSupport.executeGet(Process.getEndpoint() + Process.getIdProcess() );
    }
    @Quando("^usuario deseja editar$")
    public void UserDesejaEditar() {
        RestSupport.executeGet(Process.getEndpoint() + Process.getIdProcess() );
    }
    @Quando("^usuario deseja salvar em branco - scenario outline$")
    public void userDesejaSalvarEmBranco() {
        RestSupport.executePost(Process.getEndpoint(), Process.getFields());
    }
}