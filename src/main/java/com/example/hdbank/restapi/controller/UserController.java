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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.hdbank.restapi.object.Models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
public class UserController {

	//Xong
	//JWT_Token_kết_nối_hệ_thống
	public String getAccessToken() {
		String url = "https://hdbank-hackathon.auth.ap-southeast-1.amazoncognito.com/oauth2/token";
		JsonNode jsonNode = null;
		ObjectMapper objectMapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

		headers.set("accept", "application/json");
		headers.set("Content-Type", "application/x-www-form-urlencoded");

		map.add("client_id", "sikcnei4t2h3ntkqj5d49ltvr");
		map.add("grant_type", "refresh_token");
		map.add("refresh_token","eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiUlNBLU9BRVAifQ.S0acJ1HZRkc5HqqhPanpfief3pyeY32MFT_XaxzWzqwvTGuM0fh2CYVFN3uCkkfWkwvRbEgaZRVX5qK3yApw8uKhrDhReExLmnFcYB2-J5vXTO-wQfDHgiNs7MWCPecnjwgEichgsFfvjtOMRP8ErlxXBZl7tipYryAT-vRvjkU8FRGOsMR3x7fxGfJ9ZSR-lSQPkRv6wmMDbTWx4A98TNeg3iWqaSl0U2kPE5X2gFuxiIHXfsJqYCd-nO7vy64UhkWI3_hp2QAi8E257WXIrvIRZCZeVx4_3XDlbUv70IAZ2JOyyPRric_eDkxiisqEov0FXoR99v1bIh0A-IJgaA.1p9LfUNjYtbSj6BW.1ZJjn9unZDyW8AvLmY4X74JD9_Q_TmRaXp30Z4nP9VUf-Wa30-WRZjpg7qZaQAge5mQdDYxRns8TuCAg49NvDeobd-TYm_loPtXpbcHJwgoea8cXkcqTASwSidNNQJx9cKUx6zclD9g0EMo0bXsa6rjj5v6zXSeCHEk0a8s8_KVn10qAvS68dY-rBnUn-iTBxtB9hu90iSYyFvNU27Pln-q_J47qGv7iHoBwW-p_7vTCnOEG2Q0e1Bbz3dlf5yg536WzaruVY9lIwR0ojVVHRLBl8u1PPVLCC5LgKczx2xPghppbnw3xm4WQXVl5igC61SR8qO9R_IdcXGycyAGCm4QMwMBLNhltYNHeo4bEX1vvk2O8XxzPowKDWIxay4si22iqMH1dNSC3BWksJ5dZaGCipbZ05RfRpQNjkAoEcaRnYxrRnQCmchjQ6NIisCynAZVC-AYMPXLDDCcQShgggmb1fckBMelQ2O_tY8hmEPvyBTcHr5pqtuugkPiMpu3D_C8qkiF2wPGjXH3P71o5mloEyus2TmYK_xAKeT8m5BYwp9ndC2ZgsBtPiLWBGOM76OF2lS_xVasnW8JjIPuAifZmIXUg9YQLKlojjjn8UBwq9dz6B9zu1wd7u5K-4mSkU0bl8mNQn0UIIJZIfbvQzuu6qBFJ-BJN-JMBPgbe-w6e6kVliKAkiM-xa8AbSA6TBgDOb_Bk0fEnieXS64UyDbuv850f-3EJQLgJVxPFUvJLH2a_uHnCJ0C8sflwrc3Iufnx_PxD8oICKxIwpw-eZXIuo67rUgnUhSuAQja8d4355bZ57HwqmTD3d0sopdUTLwzJO0i8R_PihAM5vey60dogNhwbiCK7MnkWBVw32KsnYXqbRsT_HZ3i_5W6BmvxpbzN6I9HPdj-7IuQL2-GPYyPgW-_7eWD-4JyQDIolk7G7pOJXVEpGL0g9bpQd-bWcuZZJZ-dXHQvZ0mx-Crv1JgIyddqMuDTdg_Lgjs_dEJ3Yo0tthdHRFJ6Ewr8dwToMdICYlK2NGld9vNgutD5lfqmqEPlSTaiXMDJxkdU3B3r0L51aC_T3CrG3O0HClUhFiyMVpinjlb5HuXBC0-xqeuYuPmSZH4POKv2fd4nUiaWN_S5fXRuXreaT5VacnWOM80UZvtLnjepSIR79XgtwXu0fXfdV98QIHMrQ_xyXRabhWT7KTzDOy6UScv0UPZRFSKjIPhLqO129EbuI0LoGaGDbrm67QQnPjMQHSv3I2e4EKi6U4r5lh2SSConOzONtmGsjQ8P7G9og_sx9RpoMHIkt1r6HZXoJUzElVOsVTbNxEO4nq7YaHgtrhxcVbjhc1AwnMkM4w.BB0mnuwe8Bte5-X_dYIarw");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
		try {
			jsonNode = objectMapper.readTree(response.getBody());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String access_token = jsonNode.get("id_token").asText();
		return access_token;
	}

	// Xong 
	//getKey
	public String getKey() {
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/get_key";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		Gson g = new Gson();

		headers.set("accept", "application/json");

		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
				new ParameterizedTypeReference<String>() {
				});

		Models getKey = g.fromJson(responseEntity.getBody(), Models.class);
		return getKey.getData().getKey();
	}

	// Xong đăng ký
	//Bổ sung khi đăng ký thành công insert vào database mysql ()
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String postRegister(@RequestParam(name = "userName", required = true) String username,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "fullName", required = true) String fullName,
			@RequestParam(name = "phone", required = true) String phone)
			throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException,
			NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/register";

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String textPlain = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		String encrypted = Base64.getEncoder().encodeToString(encrypt(textPlain, getKey()));

		String data = "{ \"data\": { \"credential\": \"" + encrypted + "\", \"email\": \"" + email
				+ "\", \"fullName\": \"" + fullName + "\", \"identityNumber\": \"225753433\", \"key\": \"" + getKey()
				+ "\", \"phone\": \"" + phone
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Lấy publickey
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

	// Ma hoa pass và username
	public byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException,
			InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}

	// Xong đăng nhập
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String login(@RequestParam(name = "userName", required = true) String username,
			@RequestParam(name = "password", required = true) String password)
			throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException,
			NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/login";
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String textPlain = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
		String encrypted = Base64.getEncoder().encodeToString(encrypt(textPlain, getKey()));

		String data = "{ \"data\": { \"credential\": \"" + encrypted + "\", \"key\": \"" + getKey()
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Đổi mật khẩu
	@RequestMapping(value = "/change_password", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String changePassword(@RequestParam(name = "userName", required = true) String username,
			@RequestParam(name = "old_password", required = true) String old_password,
			@RequestParam(name = "new_pasword", required = true) String new_pasword)
			throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException,
			NoSuchAlgorithmException, JsonMappingException, JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/change_password";

		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String textPlain = "{\"username\":\"" + username + "\",\"oldPass\":\"" + old_password + "\",\"newPass\":\""
				+ new_pasword + "\"}";
		String encrypted = Base64.getEncoder().encodeToString(encrypt(textPlain, getKey()));

		String data = "{ \"request\": { \"requestId\": \"f29aecf0-3df6-4eb8-82b4-1ecf5b6a216c\", \"requestTime\": \"1667200102200\" }, \"data\": { \"credential\": \""
				+ encrypted + "\", \"key\": \"" + getKey() + "\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Xong
	// Lấy số dư
	@RequestMapping(value = "/balance", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String balanceBank(@RequestParam(name = "acctNo", required = true) String acctNo) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/balance";
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String data = "{ \"data\": { \"acctNo\": \"" + acctNo
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Lấy lịch sử giao dịch
	// Xong
	@RequestMapping(value = "/tranhis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String tranhisBank(@RequestParam(name = "acctNo", required = true) String acctNo,
			@RequestParam(name = "fromDate", required = true) String fromDate,
			@RequestParam(name = "toDate", required = true) String toDate) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/tranhis";
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String data = "{ \"data\": { \"acctNo\": \"" + acctNo + "\", \"fromDate\": \"" + fromDate + "\", \"toDate\": \""
				+ toDate
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Chức năng lấy danh sách học phí chưa đóng
	// Xong
	@RequestMapping(value = "/getPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String listPaymentBank(@RequestParam(name = "sdId", required = true) String sdId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/getpayment";
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String data = "{ \"data\": { \"sdId\": \"" + sdId
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Chức năng chuyển khoản
	// Xong
	@RequestMapping(value = "/transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String transferBank(@RequestParam(name = "fromAcct", required = true) String fromAcct,
			@RequestParam(name = "toAcct", required = true) String toAcct,
			@RequestParam(name = "amount", required = true) String amount,
			@RequestParam(name = "description", required = true) String description) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/transfer";
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String data = "{ \"data\": { \"amount\": \"" + amount + "\", \"description\": \"" + description
				+ "\", \"fromAcct\": \"" + fromAcct + "\", \"toAcct\": \"" + toAcct
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

	// Chức năng thanh toán học phí
	@RequestMapping(value = "/payment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String paymentBank(@RequestParam(name = "amount", required = true) String amount,
			@RequestParam(name = "description", required = true) String description,
			@RequestParam(name = "fromAcct", required = true) String fromAcct,
			@RequestParam(name = "sdId", required = true) String sdId) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://7ucpp7lkyl.execute-api.ap-southeast-1.amazonaws.com/dev/payment";
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("access-token", getAccessToken());
		headers.set("x-api-key", "hutech_hackathon@123456");
		headers.set("Content-Type", "application/json");

		String data = "{ \"data\": { \"amount\": \"" + amount + "\", \"description\": \"" + description
				+ "\", \"fromAcct\": \"" + fromAcct + "\", \"sdId\": \"" + sdId
				+ "\" }, \"request\": { \"requestId\": \"a7ea23df-7468-439d-9b12-26eb4a760901\", \"requestTime\": \"1667200102200\" } }";
		HttpEntity<String> entity = new HttpEntity<String>(data, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

}
