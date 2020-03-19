package com.interview.calculator;

import com.interview.calculator.model.Cars;
import com.interview.calculator.vo.CarsVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import static org.hamcrest.CoreMatchers.containsString;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorApplicationTests {
	@LocalServerPort
	int randomServerPort;
	@Test
	public void testGetListOfCars() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();


		final String baseUrl = "http://localhost:" + randomServerPort + "/cars";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	@Test
	public void testGetDetailCars() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/cars/"+1;
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	@Test
	public void testGetDetailCarsError() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/cars/"+9999;
		URI uri = new URI(baseUrl);
		try {
			ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
			String notFound = "Car not found for this ID";
			Assert.assertThat(e.getResponseBodyAsString(),containsString(notFound));
		}
	}

	@Test
	public void testAddCarsSuccess() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<CarsVO> request = new HttpEntity<>(new CarsVO(1,"mobil x",200000,200,2000));
		final String baseUrl = "http://localhost:" + randomServerPort + "/cars";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());

	}
	@Test
	public void testAddCarsError() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<CarsVO> request = new HttpEntity<>(new CarsVO());
		HttpEntity<CarsVO> requestValidation1 = new HttpEntity<>(new CarsVO(1,"",0,0,0));
		HttpEntity<CarsVO> requestValidation2 = new HttpEntity<>(new CarsVO(1,"Mobil x",0,0,0));
		HttpEntity<CarsVO> requestValidation3 = new HttpEntity<>(new CarsVO(1,"Mobil x",11000,0,0));
		HttpEntity<CarsVO> requestValidation4 = new HttpEntity<>(new CarsVO(1,"Mobil x",11000,1,0));
		HttpEntity<CarsVO> requestValidation5 = new HttpEntity<>(new CarsVO(1,"Mobil x",11000,1,2022));
		final String baseUrl = "http://localhost:" + randomServerPort + "/cars";
		URI uri = new URI(baseUrl);
		try {
			ResponseEntity<String> result = restTemplate.postForEntity(uri, request,String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearEmpty = "car year must not be empty / null";
			String carQtyEmpty = "car qty must not be null or minimun 1 qty";
			String carPriceEmpty = "car price must not be null or minimum 10000";
			String carNameEmpty = "car name must not be empty / null";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearEmpty));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyEmpty));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carPriceEmpty));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carNameEmpty));
		}
		try {
			ResponseEntity<String> result2 = restTemplate.postForEntity(uri, requestValidation1,String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year must be greater than 1900";
			String carQtyValidation = "car qty must be greater than 0";
			String carPriceValidation = "car price must be greater than 10000";
			String carNameEmpty = "car name must not be empty / null";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carPriceValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carNameEmpty));
		}

		try {
			ResponseEntity<String> result3 = restTemplate.postForEntity(uri, requestValidation2,String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year must be greater than 1900";
			String carQtyValidation = "car qty must be greater than 0";
			String carPriceValidation = "car price must be greater than 10000";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carPriceValidation));
		}
		try {
			ResponseEntity<String> result4 = restTemplate.postForEntity(uri, requestValidation3,String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year must be greater than 1900";
			String carQtyValidation = "car qty must be greater than 0";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
		}
		try {
			ResponseEntity<String> result5 = restTemplate.postForEntity(uri, requestValidation4,String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carQtyValidation = "car year must be greater than 1900";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
		}
		try {
			ResponseEntity<String> result6 = restTemplate.postForEntity(uri, requestValidation5,String.class);
		}
		catch (final HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year cannot be greater than 2020";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
		}
	}

	@Test
	public void testEditSuccess() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/cars/"+1;
		HttpEntity<CarsVO> requestEntity = new HttpEntity<CarsVO>(new CarsVO(1,"mobil A di edit",200000,200,2000));
		ResponseEntity<CarsVO> response = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestEntity, CarsVO.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}


	@Test
	public void testEditCarsError() throws URISyntaxException
	{
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<CarsVO> request = new HttpEntity<>(new CarsVO());
		HttpEntity<CarsVO> requestValidation1 = new HttpEntity<>(new CarsVO(1,"",0,0,0));
		HttpEntity<CarsVO> requestValidation2 = new HttpEntity<>(new CarsVO(1,"Mobil x",0,0,0));
		HttpEntity<CarsVO> requestValidation3 = new HttpEntity<>(new CarsVO(1,"Mobil x",11000,0,0));
		HttpEntity<CarsVO> requestValidation4 = new HttpEntity<>(new CarsVO(1,"Mobil x",11000,1,0));
		HttpEntity<CarsVO> requestValidation5 = new HttpEntity<>(new CarsVO(1,"Mobil x",11000,1,2022));
		final String baseUrl = "http://localhost:" + randomServerPort + "/cars/"+1;
		URI uri = new URI(baseUrl);
		try {
			ResponseEntity<CarsVO> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, request, CarsVO.class);
		}
		catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearEmpty = "car year must not be empty / null";
			String carQtyEmpty = "car qty must not be null or minimun 1 qty";
			String carPriceEmpty = "car price must not be null or minimum 10000";
			String carNameEmpty = "car name must not be empty / null";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearEmpty));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyEmpty));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carPriceEmpty));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carNameEmpty));
		}
		try {
			ResponseEntity<CarsVO> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestValidation1, CarsVO.class);
		}
		catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year must be greater than 1900";
			String carQtyValidation = "car qty must be greater than 0";
			String carPriceValidation = "car price must be greater than 10000";
			String carNameEmpty = "car name must not be empty / null";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carPriceValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carNameEmpty));
		}

		try {
			ResponseEntity<CarsVO> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestValidation2, CarsVO.class);
		}
		catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year must be greater than 1900";
			String carQtyValidation = "car qty must be greater than 0";
			String carPriceValidation = "car price must be greater than 10000";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carPriceValidation));
		}
		try {
			ResponseEntity<CarsVO> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestValidation3, CarsVO.class);
		}
		catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year must be greater than 1900";
			String carQtyValidation = "car qty must be greater than 0";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
		}
		try {
			ResponseEntity<CarsVO> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestValidation4, CarsVO.class);
		}
		catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carQtyValidation = "car year must be greater than 1900";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carQtyValidation));
		}
		try {
			ResponseEntity<CarsVO> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestValidation5, CarsVO.class);
		}
		catch (final HttpClientErrorException e) {
			Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
			String carYearValidation = "car year cannot be greater than 2020";

			Assert.assertThat(e.getResponseBodyAsString(),containsString(carYearValidation));
		}
	}

}
