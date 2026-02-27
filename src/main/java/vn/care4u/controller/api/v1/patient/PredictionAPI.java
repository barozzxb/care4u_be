package vn.care4u.controller.api.v1.patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.entity.Account;
import vn.care4u.model.AccountDetail;
import vn.care4u.model.dto.PredictionDTO;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.service.PredictionService;

@RestController
@RequestMapping("/api/v1/patient/predictions")
@Tag(name = "Patient Prediction API", description = "API for patient health predictions")
public class PredictionAPI {

	@Autowired
	PredictionService predServ;
	
	@Operation(summary = "Get all predictions for a patient", description = "Retrieve all health predictions associated with a specific patient by their ID.")
	@GetMapping("/get-all")
	public ApiResponse<Page<PredictionDTO>> getAllPredictions(Pageable pageable, Authentication auth) {
		AccountDetail principal = (AccountDetail) auth.getPrincipal();
	    String email = principal.getAccount().getEmail();
		Page<PredictionDTO> predictions = predServ.getPredictionByEmail(email, pageable);
		return ApiResponse.<Page<PredictionDTO>>builder()
				.status(200)
				.message("Lấy danh sách dự đoán thành công")
				.body(predictions)
				.build();
	}
	
	@PostMapping("/create")
	public ApiResponse<PredictionDTO> createPrediction(@RequestBody PredictionDTO predictionDTO, Authentication auth) {
		AccountDetail principal = (AccountDetail) auth.getPrincipal();
	    String email = principal.getAccount().getEmail();
		predServ.savePrediction(email, predictionDTO);
		return ApiResponse.<PredictionDTO>builder()
				.status(201)
				.message("Tạo dự đoán thành công")
				.build();
	}
	
	@DeleteMapping("/delete/{id}")
	public ApiResponse<PredictionDTO> deletePrediction(@PathVariable Long id) {
		predServ.deletePrediction(id);
		return ApiResponse.<PredictionDTO>builder()
				.status(200)
				.message("Xóa dự đoán thành công")
				.build();
	}
}
