package br.pucpr.simuladorpkce.auth;

import br.pucpr.simuladorpkce.auth.dto.AccessTokenResponseDTO;
import br.pucpr.simuladorpkce.auth.dto.AuthCodeResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public AuthCodeResponseDTO authorize(@RequestParam String clientId, @RequestParam String challenge){
        return new AuthCodeResponseDTO(service.generateAuthorizationCode(clientId, challenge), "1800");
    }

    @GetMapping("/exchange")
    public AccessTokenResponseDTO exchange(@RequestParam String code, @RequestParam String verifier) throws NoSuchAlgorithmException {
        return new AccessTokenResponseDTO(service.generateAccessToken(code, verifier));
    }
}
