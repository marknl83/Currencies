package com.api.currencies;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.api.currencies.start.Currency;
import com.api.currencies.start.CurrencyApplication;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyApplication.class)
@WebAppConfiguration
public class CurrenciesApplicationTests {
    
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;   
    
    @Autowired
    private WebApplicationContext webApplicationContext; 
    
    /* Zet de converters
     */
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }
    
    /* Setup voor het uitvoeren van de test
     */
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    
    /* Test voor het ophalen van alle currencies
     * 
     */
	@Test
    public void getAllCurrencies() throws Exception {
        mockMvc.perform(get("/api/currencies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(3)));
    }
	
    /* Test voor het ophalen van 1 currency
     * 
     */
	@Test
    public void getCurrency() throws Exception {
        mockMvc.perform(get("/api/currencies/BTC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Bitcoin")));
    }
	
    /* Test voor het posten van 1 currency
     */
	@Test
    public void postCurrencies() throws Exception {
	    Currency currency = new Currency.Builder()
                .name("postTest")
                .ticker("postTest")
                .noCoins(96710000L)
                .marketCap(96710000L)
                .build();
	    String currencyString = json(currency);

	    mockMvc.perform(post("/api/currencies")
                .contentType(contentType)
                .content(currencyString))
                .andExpect(status().isOk());
    }
	
    /* Test voor het put commando van een currency
     */
	@Test
    public void putCurrencies() throws Exception {
	    // wijzig naam
	    Currency currency = new Currency.Builder()
	            .name("putTest")
	            .ticker("putTest")
	            .noCoins(96710000L)
	            .marketCap(96710000L)
	            .build();
        String currencyString = json(currency);
        
        // voer de wijziging door
        mockMvc.perform(put("/api/currencies")
                .contentType(contentType)
                .content(currencyString))
                .andExpect(status().isOk());
        
        //controleer of de naam echt gewijzigd is
        mockMvc.perform(get("/api/currencies/putTest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is("putTest")));
    }
	
    /* Test voor het deleten van een currency
     */
	@Test
    public void deleteCurrencies() throws Exception {
        // post currency 
	    Currency currency = new Currency.Builder()
                .name("deleteTest")
                .ticker("deleteTest")
                .noCoins(96710000L)
                .marketCap(96710000L)
                .build();
	    
        String currencyString = json(currency);

        mockMvc.perform(post("/api/currencies")
                .contentType(contentType)
                .content(currencyString))
                .andExpect(status().isOk());
	    
        //controleer dat deze ook echt is toegevoegd
        mockMvc.perform(get("/api/currencies/deleteTest"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.name", is("deleteTest")));
        
       //verwijder de currency
                mockMvc.perform(delete("/api/currencies/deleteTest"))
                .andExpect(status().isOk());
            }
	
    /* Deze functie zet een object om in een json string
     */
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
