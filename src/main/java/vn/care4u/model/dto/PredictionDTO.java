package vn.care4u.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PredictionDTO {

	private String symptoms;
	private String prediction;
	private Timestamp datetime;
}
