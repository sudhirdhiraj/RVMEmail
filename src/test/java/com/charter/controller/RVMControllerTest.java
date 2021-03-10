package com.charter.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.charter.dto.AccountDetails;
import com.charter.dto.PhoneDetails;
import com.charter.service.RVMService;

@RunWith(SpringRunner.class)
@WebMvcTest(RVMController.class)
public class RVMControllerTest {

	@Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private RVMController rvmController;
    
    @Mock
    private RVMService rvmService;

    private MockHttpServletResponse getResponse(String path) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        return response;
    }

    private MockHttpServletResponse postResponse(String path, String content) throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path).content(content)
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        return response;
    }

    private MockHttpServletResponse putResponse(String path, String content, String user) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(path).content(content).
                accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        return response;
    }

    private AccountDetails getaccountDetailswithPhoneNumber(List<PhoneDetails> phone){

        AccountDetails actDet = new AccountDetails();
        actDet.setAccountNumber("1234758PUB");
        actDet.setPhones(phone);
        actDet.setTxnId("wet123");
        return  actDet;
    }

    @Test
    public void getRvmEmailAddressTest() throws Exception{
    	
    	AccountDetails details = new AccountDetails();
    	details.setTxnId("wet123");
    	details.setAccountNumber("1234758PUB");
    	when(rvmService.getRvmEmailAddress(Mockito.anyString(), Mockito.anyString())).thenReturn(details);
    	mockMvc.perform(get("/getRvmEmailAddress").param("accountNumber", "1234758PUB").param("phoneNumber", "1234567890")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
    	.andDo(print());
        
    }

	/*
	 * @Test public void ShouldRetrieveEmailByPhone(){
	 * 
	 * }
	 * 
	 * @Test public void ShouldRetrieveEmailByAccountNumber(){
	 * 
	 * }
	 */

}
