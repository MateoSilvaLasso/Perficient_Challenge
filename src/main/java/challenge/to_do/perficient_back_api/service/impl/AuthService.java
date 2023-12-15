package challenge.to_do.perficient_back_api.service.impl;

import challenge.to_do.perficient_back_api.JWT.AuthResponse;
import challenge.to_do.perficient_back_api.JWT.JwtService;
import challenge.to_do.perficient_back_api.JWT.LoginRequest;
import challenge.to_do.perficient_back_api.JWT.RegisterRequest;
import challenge.to_do.perficient_back_api.repository.model.User;
import challenge.to_do.perficient_back_api.repository.persistence.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getName()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }
    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode( request.getPassword()))
                .firtsName(request.getFirtsName())
                .LastName(request.getLastName())
                .country(request.getCountry())
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user)).build();
    }

}
