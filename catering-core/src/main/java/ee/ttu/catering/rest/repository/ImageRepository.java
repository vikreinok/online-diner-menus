package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String>{
	
}
