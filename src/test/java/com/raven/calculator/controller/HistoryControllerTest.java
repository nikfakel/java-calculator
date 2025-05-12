//package com.raven.calculator.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class HistoryControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private HistoryController historyController;
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(historyController).build();
//    }
//
//    @Test
//    @DisplayName("Test should get the list of the operations")
//    public void getOperationById() throws Exception {
//        mockMvc.perform(get("/api/history/{id}", "1").param("id", "1")).andExpect((status().isOk()));
//    }
//}
