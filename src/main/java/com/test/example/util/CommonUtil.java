package com.test.example.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {

	public CustomResponse getReponse(HttpStatus status,String description,Object data)
	{
		CustomResponse response =new CustomResponse();
		response.setStatus(status.toString());
		response.setDescription(description);
		response.setData(data);
		return response;
		
	}
}
