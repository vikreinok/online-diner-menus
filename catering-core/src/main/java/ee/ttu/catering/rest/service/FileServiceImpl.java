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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ee.ttu.catering.rest.exception.ImageFileNotFoundException;

@Service
@Transactional(rollbackFor=ImageFileNotFoundException.class)
public class FileServiceImpl implements FileService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public byte[] get(String pictureName) {
		
		String fileName = System.getProperty("catalina.base") + File.separator + "temp" + File.separator + pictureName;
		fileName += ".jpg";
		
	    byte[] bytes = null;
	    InputStream in;
		try {
			in = new FileInputStream(new File(fileName));
			bytes = IOUtils.toByteArray(in);
		} catch (FileNotFoundException e) {
			logger.warn("The image fail is not found. File name: " + fileName);
//			throw new ImageFileNotFoundException("The image fail is not found. File name: " + fileName);
		} catch (IOException e) {
//			e.printStackTrace();
			logger.warn("The image fail is not found. File name: " + fileName);

		}
		
		return bytes;
	}
	
	@Override
	public String create(String fileName, MultipartFile file) {
		
		try {
			writeToFile(fileName, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "always good";
	}
	
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
		
		
//	    File tempDir = new File(System.getProperty("catalina.base") + File.separator + "temp");
//	    File tempFile = File.createTempFile(fileName, ".jpg", tempDir);
//	    
//	    BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
//	    
//	    BufferedReader br =  new BufferedReader(new InputStreamReader(file.getInputStream()));
//
//        int c= br.read();
//        while(c!=-1)
//        {
//        	bw.write(c);
//        }
//	    
//	    bw.close();
//	    br.close();
	}
	
}
