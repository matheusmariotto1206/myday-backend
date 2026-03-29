package br.com.fiap.myday.controller;

import br.com.fiap.myday.security.JwtUtil;
import br.com.fiap.myday.entity.User;
import br.com.fiap.myday.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

   @PostMapping("/register")
public ResponseEntity<String> register(@RequestBody User user) {
    if (user.getEmail() == null || user.getPassword() == null
            || user.getEmail().isBlank() || user.getPassword().isBlank()) {
        return ResponseEntity.badRequest().body("email e senha são obrigatórios");
    }

    if (repo.findByEmail(user.getEmail()).isPresent()) {
        return ResponseEntity.status(409).body("email já cadastrado");
    }

    user.setPassword(encoder.encode(user.getPassword()));
    repo.save(user);
    return ResponseEntity.ok("usuário criado com sucesso");
}

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> optional = repo.findByEmail(user.getEmail());

        if (optional.isEmpty()) {
            return ResponseEntity.status(401).body("Email não encontrado");
        }

        User db = optional.get();

        if (!encoder.matches(user.getPassword(), db.getPassword())) {
            return ResponseEntity.status(401).body("Senha incorreta");
        }

        return ResponseEntity.ok(jwt.generateToken(user.getEmail()));
    }
}
