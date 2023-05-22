package jp.co.scsk.miniapp.sample.api.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(name = "Candidates")
@Data
@Accessors(chain = true)
public class CandidatesVO {
    @Schema(name = "candidates", description = "candidates list")
    List<String> candidates;
}
