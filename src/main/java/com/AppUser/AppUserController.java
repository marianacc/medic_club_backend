package com.AppUser;

import com.ObjectResponse.ObjectResponse;
import com.Security.SecurityConstants;
import com.Security.SecurityGoogle;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "app-user")
public class AppUserController {

    private AppUserService appUserService;
    private SecurityGoogle securityGoogle;

    @Autowired
    public void AppUserController(AppUserService appUserService, SecurityGoogle securityGoogle){
        this.appUserService = appUserService;
        this.securityGoogle = securityGoogle;
    }

    @ApiOperation(value = "login", notes = "Login with the given credentials.")
    @ApiResponses({@ApiResponse(code = 200, message = "")})
    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    void login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        throw new IllegalStateException("Add Spring Security to handle authentication");
    }

    @RequestMapping(
            value = "continue_with_google",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectResponse> continueWithGoogle(@RequestBody AppUserGoogleModel appUserGoogleModel){
        HttpHeaders response = new HttpHeaders();
        ObjectResponse objectResponse = new ObjectResponse();
        String token = securityGoogle.authenticate(appUserGoogleModel.getEmail());

        try{
            if (token!=null){
                response.set(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
                objectResponse.setSuccess(true);
            } else {
                appUserService.saveGoogleAppUser(appUserGoogleModel);
                String token2 = securityGoogle.authenticate(appUserGoogleModel.getEmail());
                response.set(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token2);
                objectResponse.setSuccess(true);
                objectResponse.setStatusMessage("Paciente registrado con Google");
            }
        }catch(Exception e){
            objectResponse.setSuccess(false);
            objectResponse.setStatusMessage(e.getMessage());
        }

        return ResponseEntity.ok()
                .headers(response)
                .body(objectResponse);
    }
}
