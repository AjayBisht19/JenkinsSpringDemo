package com.medicare.controller;

import org.apache.commons.io.IOUtils; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.util.StringUtils;
import org.springframework.core.io.ClassPathResource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medicare.dto.proReq;
import com.medicare.entity.Product;
import com.medicare.repository.productRepository;

@RestController
@CrossOrigin("*")
public class proController {

	@Autowired
	private productRepository proRepo;
	private MultipartFile file;
	public final String storageDirectoryPath = "C://Medicare";

	@PostMapping("/upload")
	public ResponseEntity<?> uplaodImage(@RequestParam("image") MultipartFile Recfile) throws IOException {

		System.out.println("Original Image Byte Size - " + Recfile.getBytes().length);
		System.out.println("recfile stream "+ Recfile.getInputStream());
		this.file = Recfile;
		System.out.println(this.file);
		System.out.println("11111");
//		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//				compressBytes(file.getBytes()));
//		imageRepository.save(img);
//		Product product=new Product();
//		product.setImage(compressBytes(file.getBytes()));
//		product.setCategory(proreq.getCategory());
//		product.setName(proreq.getName());
//		product.setPrice(proreq.getPrice());
//		product.setQuantity(proreq.getQuantity());

//		System.out.println(product);
//		
//		proRepo.save(product);

		return ResponseEntity.ok("done");
	}
	
	@GetMapping("/message")
	public String message() {
		return "Yeah you know what the message is...";
	}
	
	@GetMapping("/pros")
	public List<Product> products(){
		List<Product> findAll = this.proRepo.findAll();
		return findAll;
	}

	@PostMapping("/uploadData")
	public ResponseEntity<?> uploadData(@RequestBody proReq proreq) throws IOException {

		System.out.println("222222222");

		String fileName = StringUtils.cleanPath(this.file.getOriginalFilename());
		Path storageDirectory = Paths.get(storageDirectoryPath);
		System.out.println("storage path " + storageDirectory);
		
		if (!Files.exists(storageDirectory)) { // if the folder does not exist
			try {
				Files.createDirectories(storageDirectory); // we create the directory in the given storage directory path
			} catch (Exception e) {
				System.out.println("askdfkajsdfahsd");
				e.printStackTrace();// print the exception
			}
		}
//		File saveFile=new ClassPathResource("static/img").getFile();
//		Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
//		System.out.println("input stream "+file.getInputStream());
//		System.out.println("path: "+path);
//		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		System.out.println("file name "+fileName);
		Path destination = Paths.get(storageDirectory + "\\" + fileName);
		System.out.println("Destination " + destination);

//		File saveFile=new ClassPathResource("static/img").getFile();
//		Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
		try {
			System.out.println("input stream error");
			System.out.println("Input Stream " + this.file.getInputStream());
			Files.copy(this.file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("jiopuk");
			e.printStackTrace();
			System.out.println("error is here");
		}

		Product product = new Product();
		product.setImageName(this.file.getOriginalFilename());
		product.setCategory(proreq.getCategory());
		product.setName(proreq.getName());
		product.setPrice(proreq.getPrice());
		product.setQuantity(proreq.getQuantity());
		proRepo.save(product);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/products")
	public List<Product> getImage() throws IOException {
		System.out.println("lolo");
//		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
//		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//				decompressBytes(retrievedImage.get().getPicByte()));
		List<Product> products = proRepo.findAll();

		for (Product product : products) {
//			File getFile=new ClassPathResource("static/img").getFile();
//			Path path = Paths.get(getFile.getAbsolutePath()+File.separator+product.getImageName());
			System.out.println(product.getImageName());
			Path destination = Paths.get(storageDirectoryPath + "\\" + product.getImageName());// retrieve the image by
																								// its name
			product.setImage(IOUtils.toByteArray(destination.toUri()));
		}

		return products;
	}



}
