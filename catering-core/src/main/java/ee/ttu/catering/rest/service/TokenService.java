package ee.ttu.catering.rest.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import ee.ttu.catering.rest.exception.TokenNotFoundException;
import ee.ttu.catering.rest.model.Token;
import ee.ttu.catering.rest.model.User;
import ee.ttu.catering.rest.repository.TokenRepository;
import ee.ttu.catering.rest.repository.UserRepository;

@Service
@Transactional
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.token.timeout}")
    private Integer timeout;

    public Token getToken(String value) throws TokenNotFoundException {
        Assert.notNull(value, "Token value must not be null");
        Token token = tokenRepository.findByValue(value);
        Date now = new Date();
        if (token == null || (now.getTime() - token.getLastAccess().getTime()) / 1000 > timeout) {
            throw new TokenNotFoundException(
                    String.format("token={%s}", value)
            );
        }
        token.setLastAccess(now);
        return token;
    }

    public Token generateToken(User user) {
        Assert.notNull(user, "User must not be null");
        Token token = new Token();
        token.setUser(user);
        token.generateRandomValue();
        token.setLastAccess(new Date());
        tokenRepository.save(token);
        return token;
    }

    public Token generateToken(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "Username must not be null");
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("user=(%s)", username)
            );
        }
        return generateToken(user);
    }

    public void deleteOlderThan(Integer seconds) {
        Assert.notNull(seconds);
        Assert.isTrue(seconds > 0, "seconds must be > 0");
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR, -1 * seconds);
        tokenRepository.deleteOlderThan(calendar.getTime());
    }

    public void deleteToken(Token token) {
        Assert.notNull(token, "Token must not be null");
        tokenRepository.delete(token);
    }

}
