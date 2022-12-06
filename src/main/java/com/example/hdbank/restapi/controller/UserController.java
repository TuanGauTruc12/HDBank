package com.example.hdbank.restapi.controller;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.hdbank.restapi.object.Models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

@RestController
public class UserController {
	
	//Xong getKey
	public String getKey() {
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/get_key";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Gson g = new Gson();

		headers.set("accept", "application/json");
		headers.set("access-token",
				"eyJraWQiOiJXcDRGMndiQVpMa1d2WWgyNDhnYjNtUHBLRzZTdDRNcG85Tmc3U2diZ2E0PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmFwLXNvdXRoZWFzdC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoZWFzdC0xX1FiMVE4VFBzVSIsImNvZ25pdG86dXNlcm5hbWUiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJvcmlnaW5fanRpIjoiYjUzNGE2ZGItMzliNC00ZjAwLWJiNjItNmQyZmIzMDExMmJmIiwiYXVkIjoic2lrY25laTR0MmgzbnRrcWo1ZDQ5bHR2ciIsImV2ZW50X2lkIjoiMDNhOTMzNjUtYzA3Yy00NWRlLWFjZGUtM2QxMTQ1MGY2YTNiIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2Njk5NzA5NTQsIm5hbWUiOiJQaGHMo20gQW5oIFR1w6LMgW4iLCJleHAiOjE2NzAzMzMyMzAsImlhdCI6MTY3MDI0NjgzMCwianRpIjoiMWM4MGQ5YjQtMGQzNC00MDBjLThiMzktNGIzMDBhNmI4NjJjIiwiZW1haWwiOiJwaGFtdHVhbjEyMDIyMDAxQGdtYWlsLmNvbSJ9.RIQ5MUSoUS4dKCm8PsnzBx3siOxWbNUcka8jPptuEDHpiN2BnH0v_OfFMBlLseQBwXYN1tDPuOKfKruMU-1zXNOS9uxjBtDeBktnjuXv0KlEg4LwuyrTjTtj4D22Zu5PFxymqx76_lnHsK_XtLiVDsaTw_J8yOn5ZYe4-Fb-_qfw3lRRc9py_84TVhIs68cmMBrAMeEPQR7erRzzRNfZLbtyUyS1mig1YYeSrgRI5z2FxzqNne5oMZMhxc3ztpX1fe5RDu5PmEHxcVshyyNNv7Wd8-wJR9h3iUchGVD9PdtOnHyr9ERfWmc0Ci40zXtkorTxV9v35Q9DMQ-STmxqyg");
		headers.set("x-api-key", "hutech_hackathon@123456");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<String>() {
				});

		Models getKey = g.fromJson(responseEntity.getBody(), Models.class);
		return getKey.getData().getKey();
	}

	//Xong đăng ký
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String postRegister(@RequestParam(name = "userName", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "fullName", required = true) String fullName,
			@RequestParam(name = "phone", required = true) String phone) 
			throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/register";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token",
				"eyJraWQiOiJXcDRGMndiQVpMa1d2WWgyNDhnYjNtUHBLRzZTdDRNcG85Tmc3U2diZ2E0PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmFwLXNvdXRoZWFzdC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoZWFzdC0xX1FiMVE4VFBzVSIsImNvZ25pdG86dXNlcm5hbWUiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJvcmlnaW5fanRpIjoiYjUzNGE2ZGItMzliNC00ZjAwLWJiNjItNmQyZmIzMDExMmJmIiwiYXVkIjoic2lrY25laTR0MmgzbnRrcWo1ZDQ5bHR2ciIsImV2ZW50X2lkIjoiMDNhOTMzNjUtYzA3Yy00NWRlLWFjZGUtM2QxMTQ1MGY2YTNiIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2Njk5NzA5NTQsIm5hbWUiOiJQaGHMo20gQW5oIFR1w6LMgW4iLCJleHAiOjE2NzAzMzMyMzAsImlhdCI6MTY3MDI0NjgzMCwianRpIjoiMWM4MGQ5YjQtMGQzNC00MDBjLThiMzktNGIzMDBhNmI4NjJjIiwiZW1haWwiOiJwaGFtdHVhbjEyMDIyMDAxQGdtYWlsLmNvbSJ9.RIQ5MUSoUS4dKCm8PsnzBx3siOxWbNUcka8jPptuEDHpiN2BnH0v_OfFMBlLseQBwXYN1tDPuOKfKruMU-1zXNOS9uxjBtDeBktnjuXv0KlEg4LwuyrTjTtj4D22Zu5PFxymqx76_lnHsK_XtLiVDsaTw_J8yOn5ZYe4-Fb-_qfw3lRRc9py_84TVhIs68cmMBrAMeEPQR7erRzzRNfZLbtyUyS1mig1YYeSrgRI5z2FxzqNne5oMZMhxc3ztpX1fe5RDu5PmEHxcVshyyNNv7Wd8-wJR9h3iUchGVD9PdtOnHyr9ERfWmc0Ci40zXtkorTxV9v35Q9DMQ-STmxqyg");
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");
		
		String textPlain = "{\"username\":\""+ username +"\",\"password\":\"" + password + "\"}";        	
		String encrypted = Base64.getEncoder().encodeToString(encrypt(textPlain, getKey()));
		
		String data = "{ \"data\": { \"credential\": \""+encrypted+"\", \"email\": \"" + email + "\", \"fullName\": \"" + fullName + "\", \"identityNumber\": \"225753433\", \"key\": \""+ getKey() +"\", \"phone\": \""+phone+"\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		ResponseEntity<String> response = restTemplate
				  .exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();	
	}

	//Lấy publickey
	public PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	//Ma hoa pass và username
	public byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException,
			InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}

	//Xong đăng nhập
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String login(@RequestParam(name = "userName", required = true) String username,
			@RequestParam(name = "password", required = true) String password) 
			throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/login";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token",
				"eyJraWQiOiJXcDRGMndiQVpMa1d2WWgyNDhnYjNtUHBLRzZTdDRNcG85Tmc3U2diZ2E0PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmFwLXNvdXRoZWFzdC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoZWFzdC0xX1FiMVE4VFBzVSIsImNvZ25pdG86dXNlcm5hbWUiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJvcmlnaW5fanRpIjoiYjUzNGE2ZGItMzliNC00ZjAwLWJiNjItNmQyZmIzMDExMmJmIiwiYXVkIjoic2lrY25laTR0MmgzbnRrcWo1ZDQ5bHR2ciIsImV2ZW50X2lkIjoiMDNhOTMzNjUtYzA3Yy00NWRlLWFjZGUtM2QxMTQ1MGY2YTNiIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2Njk5NzA5NTQsIm5hbWUiOiJQaGHMo20gQW5oIFR1w6LMgW4iLCJleHAiOjE2NzAzMzMyMzAsImlhdCI6MTY3MDI0NjgzMCwianRpIjoiMWM4MGQ5YjQtMGQzNC00MDBjLThiMzktNGIzMDBhNmI4NjJjIiwiZW1haWwiOiJwaGFtdHVhbjEyMDIyMDAxQGdtYWlsLmNvbSJ9.RIQ5MUSoUS4dKCm8PsnzBx3siOxWbNUcka8jPptuEDHpiN2BnH0v_OfFMBlLseQBwXYN1tDPuOKfKruMU-1zXNOS9uxjBtDeBktnjuXv0KlEg4LwuyrTjTtj4D22Zu5PFxymqx76_lnHsK_XtLiVDsaTw_J8yOn5ZYe4-Fb-_qfw3lRRc9py_84TVhIs68cmMBrAMeEPQR7erRzzRNfZLbtyUyS1mig1YYeSrgRI5z2FxzqNne5oMZMhxc3ztpX1fe5RDu5PmEHxcVshyyNNv7Wd8-wJR9h3iUchGVD9PdtOnHyr9ERfWmc0Ci40zXtkorTxV9v35Q9DMQ-STmxqyg");
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");
		
		String textPlain = "{\"username\":\""+ username +"\",\"password\":\"" + password + "\"}";        	
		String encrypted = Base64.getEncoder().encodeToString(encrypt(textPlain, getKey()));
		
		String data = "{ \"data\": { \"credential\": \"" + encrypted + "\", \"key\": \"" + getKey() + "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		ResponseEntity<String> response = restTemplate
				  .exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();	
	}

	//Đổi mật khẩu
	@RequestMapping(value = "/change_password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String changePassword(@RequestParam(name = "userName", required = true) String username,
			@RequestParam(name = "old_password", required = true) String old_password,
			@RequestParam(name = "new_pasword", required = true) String new_pasword)
			throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/change_password";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token",
				"eyJraWQiOiJXcDRGMndiQVpMa1d2WWgyNDhnYjNtUHBLRzZTdDRNcG85Tmc3U2diZ2E0PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLmFwLXNvdXRoZWFzdC0xLmFtYXpvbmF3cy5jb21cL2FwLXNvdXRoZWFzdC0xX1FiMVE4VFBzVSIsImNvZ25pdG86dXNlcm5hbWUiOiI3NGM3MGFiOC1lMTQ3LTRkYTktYTdkNC1hNTEzZTMyMzFhMzEiLCJvcmlnaW5fanRpIjoiYjUzNGE2ZGItMzliNC00ZjAwLWJiNjItNmQyZmIzMDExMmJmIiwiYXVkIjoic2lrY25laTR0MmgzbnRrcWo1ZDQ5bHR2ciIsImV2ZW50X2lkIjoiMDNhOTMzNjUtYzA3Yy00NWRlLWFjZGUtM2QxMTQ1MGY2YTNiIiwidG9rZW5fdXNlIjoiaWQiLCJhdXRoX3RpbWUiOjE2Njk5NzA5NTQsIm5hbWUiOiJQaGHMo20gQW5oIFR1w6LMgW4iLCJleHAiOjE2NzAzMzMyMzAsImlhdCI6MTY3MDI0NjgzMCwianRpIjoiMWM4MGQ5YjQtMGQzNC00MDBjLThiMzktNGIzMDBhNmI4NjJjIiwiZW1haWwiOiJwaGFtdHVhbjEyMDIyMDAxQGdtYWlsLmNvbSJ9.RIQ5MUSoUS4dKCm8PsnzBx3siOxWbNUcka8jPptuEDHpiN2BnH0v_OfFMBlLseQBwXYN1tDPuOKfKruMU-1zXNOS9uxjBtDeBktnjuXv0KlEg4LwuyrTjTtj4D22Zu5PFxymqx76_lnHsK_XtLiVDsaTw_J8yOn5ZYe4-Fb-_qfw3lRRc9py_84TVhIs68cmMBrAMeEPQR7erRzzRNfZLbtyUyS1mig1YYeSrgRI5z2FxzqNne5oMZMhxc3ztpX1fe5RDu5PmEHxcVshyyNNv7Wd8-wJR9h3iUchGVD9PdtOnHyr9ERfWmc0Ci40zXtkorTxV9v35Q9DMQ-STmxqyg");
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");
		
		String textPlain = "{\"username\":\""+ username +"\",\"oldPass\":\"" + old_password + "\",\"newPass\":\"" + new_pasword + "\"}";        	
		String encrypted = Base64.getEncoder().encodeToString(encrypt(textPlain, getKey()));		
		
		String data = "{ \"request\": { \"requestId\": \"f29aecf0-3df6-4eb8-82b4-1ecf5b6a216c\", \"requestTime\": \"1667200102200\" }, \"data\": { \"credential\": \""+ encrypted +"\", \"key\": \"" + getKey() + "\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);
		ResponseEntity<String> response = restTemplate
				  .exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();	
	}

	
}
