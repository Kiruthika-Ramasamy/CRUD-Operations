package com.example.load.controller;

import com.example.load.model.LoadDetails;
import com.example.load.repository.LoadRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value ="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoadController {

    @Autowired
    private LoadRepository loadRepository;
    @Autowired
    private Gson gson;

    @GetMapping("/load/all")
    public List<LoadDetails> getAllLoad() {
        return loadRepository.findAll();
    }

    @PostMapping("/load")
    public String saveLoadDetails(@Valid @RequestBody LoadDetails load) {
        LoadDetails loadDetails = loadRepository.save(load);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "loads details added successfully");
        return gson.toJson(jsonObject);
    }

    @GetMapping("/load")
    public List<LoadDetails> getLoadByShipperId(@RequestParam String shipperId) {
        return loadRepository.findByShipperId(shipperId);
    }

    @GetMapping("/load/{loadId}")
    public LoadDetails getLoadByLoadId(@PathVariable Long loadId) {
        return loadRepository.findById(loadId).get();
    }

    @PutMapping("/load/{loadId}")
    public LoadDetails updateLoadByLoadId(@PathVariable Long loadId, @Valid @RequestBody LoadDetails load) throws Exception {
        LoadDetails loadDetails = loadRepository.findById(loadId).get();
        if (loadDetails == null)
            throw new Exception("LoadDetails not found for this id :: " + loadId);

        loadDetails.setLoadingPoint(load.getLoadingPoint());
        loadDetails.setUnloadingPoint(load.getUnloadingPoint());
        loadDetails.setProductType(load.getProductType());
        loadDetails.setTruckType(load.getTruckType());
        loadDetails.setNoOfTrucks(load.getNoOfTrucks());
        loadDetails.setWeight(load.getWeight());
        loadDetails.setComment(load.getComment());
        loadDetails.setDate(load.getDate());

        return loadRepository.save(loadDetails);
    }

    @DeleteMapping("/load/{loadId}")
    public Map<String, Boolean> deleteLoadDetails(@PathVariable(value = "loadId") Long loadId) throws Exception {
        Optional<LoadDetails> loadDetails = loadRepository.findById(loadId);
        if (!loadDetails.isPresent())
            throw new Exception("LoadDetails not found for this id :: " + loadId);
        loadRepository.delete(loadDetails.get());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
