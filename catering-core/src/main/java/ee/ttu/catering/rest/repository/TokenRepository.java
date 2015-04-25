package ee.ttu.catering.rest.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ee.ttu.catering.rest.model.Token;

public interface TokenRepository extends CrudRepository<Token, Long> {

    public Token findByValue(String value);

    @Modifying
    @Query("DELETE FROM Token t WHERE t.lastAccess <= :instant")
    public void deleteOlderThan(@Param("instant") Date instant);

}