// package com.raven.calculator.controller;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.raven.calculator.dto.OperationResponseDTO;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
// @ExtendWith(MockitoExtension.class)
// public class CalculatorControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private CalculatorController calculatorController;
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(calculatorController).build();
//    }
//
//    @Test
//    @DisplayName("Test should post the calculation")
//    void calculateRequest() throws Exception {
//        mockMvc.perform(post("/api/calculate")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(
//                        OperationResponseDTO.builder().build())));
//    }
// }
