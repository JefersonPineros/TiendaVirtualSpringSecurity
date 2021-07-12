package co.com.tienda.virtual.back.api.auth;

import co.com.tienda.virtual.back.api.entity.models.Usuarios;
import co.com.tienda.virtual.back.api.services.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    private Logger log = LoggerFactory.getLogger(InfoAdicionalToken.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Usuarios user = usuarioService.findByEmailCompleto(authentication.getName());

        Map<String, Object> info = new HashMap<>();
        info.put("nombres", user.getNombre());
        info.put("apellidos", user.getApellido());
        info.put("email", user.getEmail());
        info.put("date_login", user.getFechaLogueo());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        log.info(authentication.getName().concat(": nombre usuario InfoAdicional"));
        return accessToken;
    }
}
