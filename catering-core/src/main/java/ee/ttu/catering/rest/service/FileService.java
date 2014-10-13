package ee.ttu.catering.rest.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

	public byte[] get(String pictureName);

	public String create(String fileName, MultipartFile file);

}
