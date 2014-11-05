package ee.ttu.catering.rest.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

	public byte[] get(String fineName);

	public String create(String name, MultipartFile file, String fileName);

}
