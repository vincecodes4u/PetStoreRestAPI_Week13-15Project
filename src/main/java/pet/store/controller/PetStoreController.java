package pet.store.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@Slf4j
public class PetStoreController {
@Autowired
private PetStoreService petStoreService;

@PostMapping("/pet_store")
@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
	log.info("Creating pet store {}", petStoreData);
	
	return petStoreService.savePetStore(petStoreData);
}

@PutMapping("/pet_store/{petStoreId}")
public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
	petStoreData.setPetStoreId(petStoreId);
	log.info("Updating pet store {}", petStoreData);
	return petStoreService.savePetStore(petStoreData);
	}


@PostMapping("/pet_store/{petStoreId}/employee")
@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreEmployee insertPetStoreEmployee(@PathVariable Long petStoreId,
		@RequestBody PetStoreEmployee petStoreEmployee) {
	log.info("Creating employee () for petstore with ID = ()", petStoreEmployee.getEmployeeId(), petStoreId);
	return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
}
@PutMapping("/pet_store/{petStoreId}/employee")
public PetStoreEmployee updatePetStoreEmployee(@PathVariable Long petStoreId,
		@RequestBody PetStoreEmployee petStoreEmployee) {
	log.info("Updating employee () for petstore with ID=()", petStoreEmployee.getEmployeeId(), petStoreId);
	return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
}
@PostMapping("/pet_store/{petStoreId}/customer")
@ResponseStatus(code = HttpStatus.CREATED)
public PetStoreCustomer insertPetStoreCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
	log.info("Creating customer {} for pet store with ID={}", petStoreCustomer.getCustomerId(), petStoreId);
	return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
}
@PutMapping("/pet_store/{petStoreId}/customer")
public PetStoreCustomer updatePetStoreCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
	log.info("Updating customer {} for pet store with ID=()", petStoreCustomer.getCustomerId(), petStoreId);
	return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
}
@GetMapping("/pet_store")
public List<PetStoreData> ListAllPetStores(){
	log.info("Listing all pet stores");
	return petStoreService.retrieveAllPetStores();
}
@GetMapping("/pet_store/{petStoreId}")
public PetStoreData getPetStoreById(@PathVariable Long petStoreId) {
	log.info("Retrieving pet store with ID=()", petStoreId);
	return petStoreService.retrievePetStoreById(petStoreId);
}
@DeleteMapping("/pet_store/{petStoreId}")
public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
	log.info("Received request to delete pet store with ID: " + petStoreId + ".");
	petStoreService.deletePetStoreById(petStoreId);
	
	return Map.of("Message", "Pet Store with ID=" + petStoreId +"was deleted successfully");
	}
}

