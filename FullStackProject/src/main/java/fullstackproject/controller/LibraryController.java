package fullstackproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fullstackproject.exception.ResourceNotFoundException;
import fullstackproject.model.Library;
import fullstackproject.repository.LibraryRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController//@ResponseBody and @Controller
@RequestMapping("/RestAPIproject") //Here the RequestMapping is used in the Class level-->So it'll act as a base URL to the controller.
public class LibraryController {

	@Autowired
	private LibraryRepository libraryRepository;

	//This GetMapping annotation is used to fetch records from database using findall method that present in JPA Repository
	@GetMapping("/libraryRecords")
	public List<Library> getRecordsFromDb(){
		return libraryRepository.findAll();
	}		

	//This PostMapping annotation is used to save records into the database using save method that present in JPA Repository
	@PostMapping("/saveLibraryRecords")
	public Library createLibraryRecords(@RequestBody Library library) {
		return libraryRepository.save(library);
	}

	/* This GetMapping annotation is used to fetch one specific record from database using findById 
	 * method that present in JPA Repository.At first it'll check any record is there 
	 * with the respected BookId if it is then it'll do the following opertion 
	 * otherwise it'll throw exception-->here the exception is user defined-->So we can throw the object only.
     */
    
	@GetMapping("/libraryRecords/{BookId}")
	public ResponseEntity<Library> searchById(@PathVariable int BookId) {
		Library library = libraryRepository.findById(BookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + BookId));
		return ResponseEntity.ok(library);
	}

	/* This PutMapping annotation is used to update a specific record in the database using findById method that present in JPA Repository.
     * At first it'll check any record is there with the respected BookId if it is then it'll do the following opertion 
	 * otherwise it'll throw exception-->here the exception is user defined-->So we can throw the object only.
     */
    @PutMapping("/updateRecords/{BookId}")
	public ResponseEntity<Library> updateBookDetails(@PathVariable int BookId, @RequestBody Library libraryRecordDetails){
		Library library = libraryRepository.findById(BookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + BookId));

		library.setNoOfCopies(libraryRecordDetails.getNoOfCopies());

		Library updatedRecords = libraryRepository.save(library);
		return ResponseEntity.ok(updatedRecords);
	}

    /* This DeleteMapping annotation is used to delete a specific record from database using findById method that present in JPA Repository.
     * At first it'll check any record is there with the respected BookId if it is then it'll do the following opertion 
	 * otherwise it'll throw exception-->here the exception is user defined-->So we can throw the object only.
     */
    @DeleteMapping("/deleteRecords/{BookId}")
	public ResponseEntity<Map<String, Boolean>> deleteRecord(@PathVariable int BookId){
		Library library = libraryRepository.findById(BookId)
				.orElseThrow(() -> new ResourceNotFoundException("Book not exist with id :" + BookId));

		libraryRepository.delete(library);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}


}
