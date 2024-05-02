package com.calibri.feign;

import com.calibri.config.FeignConfig;
import com.calibri.dto.ChatGptReq;
import com.calibri.dto.ChatGptRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "GPT",
        url = "${feign.client.gpt.listOfServers:http://192.168.2.68:3040}",
        configuration = FeignConfig.class
)
public interface GPTClient {

//    @Operation(
//            operationId = "getAttachmentsList",
//            tags = { "Attachments" },
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  AttachmentInfoPageDto.class))),
//                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDtoRs.class))),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDtoRs.class))),
//                    @ApiResponse(responseCode = "403", description = "Forbidden error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDtoRs.class))),
//                    @ApiResponse(responseCode = "500", description = "Server error", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  ErrorDtoRs.class)))
//            },
//            security = {
//                    @SecurityRequirement(name = "Authorization")
//            }
//    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/v1/chat/completions",
            produces = "application/json"
    )
    ChatGptRes sendMessage(@RequestBody ChatGptReq message);
}
