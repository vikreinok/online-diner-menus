package ee.ttu.catering.rest.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ee.ttu.catering.rest.exception.FileSeriveException;
import ee.ttu.catering.rest.exception.ImageFileNotFoundException;
import ee.ttu.catering.rest.model.Image;
import ee.ttu.catering.rest.repository.ImageRepository;

@Service
@Transactional(rollbackFor=ImageFileNotFoundException.class)
public class FileServiceImpl implements FileService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ImageRepository imageRepo;
	
	@Override
	public byte[] get(String fineName) {
		return readFileFromDatabase(fineName);
	}
	
	@Override
	public String create(String name, MultipartFile file, String fileName) {
		
		try {
			writeToDatabase(name, file, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	private byte[] readFileFromDatabase(String fileName) {
		Image image = imageRepo.findOne(fileName);
		if(image == null) {
			return null;
		} else {
			return image.getData();
		}
	}
	
	private String writeToDatabase(String name, MultipartFile file, String fileName) throws IOException {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				
				Image image = new Image(name, bytes, fileName);
				
				image = imageRepo.save(image);
				
				return image.getName();
			} catch (Exception e) {
				logger.error("You failed to upload " + fileName + " => " + e.getMessage());
			}
			throw new FileSeriveException("Image saving exception");
		} else {
			throw new FileSeriveException("Image saving exception");
		}
	}
	
	@SuppressWarnings("unused")
	private byte[] readFileFromFilesystem(String pictureName) {
		String fileName = System.getProperty("catalina.base") + File.separator + "temp" + File.separator + pictureName;
		fileName += ".jpg";
		
		byte[] bytes = null;
		InputStream in;
		try {
			in = new FileInputStream(new File(fileName));
			bytes = IOUtils.toByteArray(in);
		} catch (FileNotFoundException e) {
			logger.warn("The image fail is not found. File name: " + fileName);
		} catch (IOException e) {
			logger.warn("The image fail is not found. File name: " + fileName);
		}
		return bytes;
	}
	
	@SuppressWarnings("unused")
	private String writeToFile(String fileName, MultipartFile file) throws IOException {
		
		  if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	        	    File tempDir = new File(System.getProperty("catalina.base") + File.separator + "temp");

	                File createdFile = new File( tempDir + File.separator + fileName + ".jpg");
	        	    
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(createdFile));
	                
	                stream.write(bytes);
	                stream.close();
	                return "You successfully uploaded " + fileName + " into " + fileName + "-uploaded !";
	            } catch (Exception e) {
	                return "You failed to upload " + fileName + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + fileName + " because the file was empty.";
	        }
	}
	
}
