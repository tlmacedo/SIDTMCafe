package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.WsCnpjReceitaWsVO;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WsCnpjReceitaWsDAO extends BuscaWebService implements Constants {

    JSONObject jsonObject;
    JSONArray jsonArray;

    WsCnpjReceitaWsVO wsCnpjReceitaWsVO;

    public WsCnpjReceitaWsVO getWsCnpjReceitaWsVO(String cnpj) {
        jsonObject = getJsonObjectHttpUrlConnection(WS_RECEITAWS_URL + cnpj, WS_RECEITAWS_TOKEN, "/days/0");
        if (jsonObject == null)
            return wsCnpjReceitaWsVO = null;
        try {
            wsCnpjReceitaWsVO = new WsCnpjReceitaWsVO();

            wsCnpjReceitaWsVO.setStatus(jsonObject.getString("status"));
            if (wsCnpjReceitaWsVO.getStatus().equals("ERROR"))
                wsCnpjReceitaWsVO.setMessage(jsonObject.getString("message"));
            wsCnpjReceitaWsVO.setCnpj(jsonObject.getString("cnpj"));
            wsCnpjReceitaWsVO.setTipo(jsonObject.getString("tipo"));
            wsCnpjReceitaWsVO.setAbertura(jsonObject.getString("abertura"));
            wsCnpjReceitaWsVO.setNome(jsonObject.getString("nome"));
            wsCnpjReceitaWsVO.setFantasia(jsonObject.getString("fantasia"));

            jsonArray = jsonObject.getJSONArray("atividade_principal");
            List<Pair<String, String>> listAtividadePrincipal = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                listAtividadePrincipal.add(new Pair<String, String>(jsonArray.getJSONObject(i).getString("code"),
                        jsonArray.getJSONObject(i).getString("text")));
            }
            wsCnpjReceitaWsVO.setAtividadePrincipal(listAtividadePrincipal);

            jsonArray = jsonObject.getJSONArray("atividades_secundarias");
            List<Pair<String, String>> listAtividadesSecundarias = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                listAtividadesSecundarias.add(new Pair<String, String>(jsonArray.getJSONObject(i).getString("code"),
                        jsonArray.getJSONObject(i).getString("text")));
            }
            wsCnpjReceitaWsVO.setAtividadesSecundarias(listAtividadesSecundarias);

            jsonArray = jsonObject.getJSONArray("qsa");
            List<Pair<String, String>> listQsa = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                listQsa.add(new Pair<String, String>(jsonArray.getJSONObject(i).getString("qual"),
                        jsonArray.getJSONObject(i).getString("nome")));
            }
            wsCnpjReceitaWsVO.setQsa(listQsa);

            wsCnpjReceitaWsVO.setNaturezaJuridica(jsonObject.getString("natureza_juridica"));
            wsCnpjReceitaWsVO.setLogradouro(jsonObject.getString("logradouro"));
            wsCnpjReceitaWsVO.setNumero(jsonObject.getString("numero"));
            wsCnpjReceitaWsVO.setComplemento(jsonObject.getString("complemento"));
            wsCnpjReceitaWsVO.setCep(jsonObject.getString("cep"));
            wsCnpjReceitaWsVO.setBairro(jsonObject.getString("bairro"));
            wsCnpjReceitaWsVO.setMunicipio(jsonObject.getString("municipio"));
            wsCnpjReceitaWsVO.setUf(jsonObject.getString("uf"));
            wsCnpjReceitaWsVO.setEmail(jsonObject.getString("email"));
            wsCnpjReceitaWsVO.setTelefone(jsonObject.getString("telefone"));
            wsCnpjReceitaWsVO.setEfr(jsonObject.getString("efr"));
            wsCnpjReceitaWsVO.setSituacao(jsonObject.getString("situacao"));
            wsCnpjReceitaWsVO.setDataSituacao(jsonObject.getString("data_situacao"));
            wsCnpjReceitaWsVO.setMotivoSituacao(jsonObject.getString("motivo_situacao"));
            wsCnpjReceitaWsVO.setSituacaoEspecial(jsonObject.getString("situacao_especial"));
            wsCnpjReceitaWsVO.setDataSituacaoEspecial(jsonObject.getString("data_situacao_especial"));
            wsCnpjReceitaWsVO.setCapitalSocial(jsonObject.getString("capital_social"));
            wsCnpjReceitaWsVO.setExtra("");//jsonObject.getString("extra"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return wsCnpjReceitaWsVO;
    }


}
