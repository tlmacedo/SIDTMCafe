package br.com.sidtmcafe.model.dao;

import br.com.sidtmcafe.componentes.AlertMensagem;
import br.com.sidtmcafe.componentes.Variaveis;
import br.com.sidtmcafe.interfaces.Constants;
import br.com.sidtmcafe.model.vo.WsCepPostmonVO;
import org.json.JSONObject;


public class WsCepPostmonDAO extends BuscaWebService implements Constants {

    JSONObject jsonObject;

    WsCepPostmonVO wsCepPostmonVO;

    public WsCepPostmonVO getCepPostmonVO(String cep) {
        jsonObject = getJsonObjectWebService(WS_POSTMON_URL + cep);

        if (jsonObject == null)
            return wsCepPostmonVO = null;

        try {
            wsCepPostmonVO = new WsCepPostmonVO();

            wsCepPostmonVO.setBairro(jsonObject.getString("bairro"));
            wsCepPostmonVO.setBairro(jsonObject.getString("bairro"));
            wsCepPostmonVO.setCidade(jsonObject.getString("cidade"));
            wsCepPostmonVO.setCep(jsonObject.getString("cep"));
            wsCepPostmonVO.setLogradouro(jsonObject.getString("logradouro"));
            wsCepPostmonVO.setEstado_area(jsonObject.getJSONObject("estado_info").getString("area_km2"));
            wsCepPostmonVO.setEstado_codigo_ibge(jsonObject.getJSONObject("estado_info").getString("codigo_ibge"));
            wsCepPostmonVO.setEstado_nome(jsonObject.getJSONObject("estado_info").getString("nome"));

            wsCepPostmonVO.setCidade_area(jsonObject.getJSONObject("cidade_info").getString("area_km2"));
            wsCepPostmonVO.setCidade_codigo_ibge(jsonObject.getJSONObject("cidade_info").getString("codigo_ibge"));
            wsCepPostmonVO.setEstado_sigla(jsonObject.getString("estado"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return wsCepPostmonVO;
    }
}
